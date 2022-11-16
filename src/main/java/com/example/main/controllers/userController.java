package com.example.main.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.main.config.constant;
import com.example.main.exceptions.BadCredentialException;
import com.example.main.exceptions.successTemplate;
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.user;
import com.example.main.repositories.userRepository;
import com.example.main.services.UserService;



import com.example.main.models.changePassword;

@RestController
@Validated
public class userController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private userRepository userRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private successTemplate scf;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseObject login(@NotBlank @ModelAttribute("email") String email,@NotBlank @Size(min=6, max=12 ) @ModelAttribute("password") String password) {

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                		email,
//                        password
//                )
//        );
//        user user=(user) authentication.getPrincipal();
//        String jwt = tokenProvider.generateToken(user);
    	
        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("token", jwt);
//        map.put("expiredDate", 604800000);
        List<user> result=userService.login(email, password);
        if(result.isEmpty()) {
        	throw new BadCredentialException();
        }
        map.put("avatar",result.get(0).getUser_avatar());
        map.put("id", result.get(0).getUser_id());
        return new ResponseObject(200, "Đăng nhập thành công!",map);
    }
    

    
    @RequestMapping(value="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public Map<String, Object> register(@NotBlank @ModelAttribute("email") String email,@NotBlank @Size(min=6, max=12 ) @ModelAttribute("password") String password) {
    	
        userRepo.insertCustomer(email, password );
        Map<String, Object> response=scf.success(200, "Đăng ký thành công!", null);
        
        return response;
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Map<String, Object> registerJSON(@RequestBody user user) {
    	System.out.print(user.getUser_email());
    	
        userService.register(user.getUser_email(), user.getPassword());
        Map<String, Object> response=scf.success(200, "Đăng ký thành công!",null);
        
        return response;
    }
    
    @RequestMapping(value="/update-avatar", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseObject update(@ModelAttribute("id") int id,@RequestPart MultipartFile avatar, @ModelAttribute("old_avatar") String old_avatar) throws IOException {
    byte[] bytes = avatar.getBytes();
//    String jwt=request.getHeader("Authorization").substring(7);
//	String ogEmail=tokenProvider.getUserIdFromJWT(jwt);
    	String genName=UUID.randomUUID().toString();
    	String finalName=genName+"."+ avatar.getOriginalFilename().split("\\.")[1];
		Path path = Paths.get(constant.imageDir+"avatars/" + finalName);
		Files.write(path, bytes);
        userRepo.updateAvatar(finalName, id);
        File fileToDelete = new File(constant.imageDir+"avatars/"+ old_avatar);
        fileToDelete.delete();
        
        return new ResponseObject(200, "Cập nhật ảnh đại diện thành công!", finalName);
    }
    
    @RequestMapping(value="/updateCustomer", method = RequestMethod.PUT)
    public ResponseObject update(@RequestBody user user) throws IOException {

//    String jwt=request.getHeader("Authorization").substring(7);
//	String ogEmail=tokenProvider.getUserIdFromJWT(jwt);
	userService.updateCustomer(user.getUser_fullname(),user.getUser_phone_number(), user.getUser_date_of_birth(), user.getUser_address(), user.getUser_id());
         
        
        return new ResponseObject(200, "Cập nhật thông tin thành công!", null);
    }
    
    
    @RequestMapping(value="/changePassword", method = RequestMethod.PUT )
    public ResponseEntity<Object> changePassword(@RequestBody changePassword user) {
       String result= userService.changePassword( user.getOld_password(),user.getNew_password(), user.getId());
       ResponseEntity<Object> response;
        if(result!="Success!") {
        	response= ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, result,null));
        }else {
        	response=ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Đổi mật khẩu thành công!",null));
        }
    	
//    	String result=userService.changePassword( user.getOld_password(),user.getNew_password(), user.getId());
        return response;
    }
//    @RequestMapping(value="/changePassword", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
//    public ResponseObject changePassword1(@NotBlank @ModelAttribute("email") String email,@NotBlank @Size(min=6, max=12 ) @ModelAttribute("password") String password,@NotBlank @Size(min=6, max=12 ) @ModelAttribute("oldPassword") String oldPassword) {
//
//        String result=userRepo.changePassword(email,oldPassword, password);
//        ResponseObject response;
//        if(result!="Success!") {
//        	response=new ResponseObject(401, result, null);
//        }else {
//        	response=new ResponseObject(200, "Change Password Success!", null);
//        }
//        
//        return response;
//    }
    @RequestMapping(value="/get-profile" )
    public ResponseObject getProfile(@NotNull @RequestParam("id") Integer id) {
//    	String jwt=request.getHeader("Authorization").substring(7);
//    	int id=Integer.parseInt(tokenProvider.getUserIdFromJWT(jwt));
    	user profile=userService.getProfile(id);
        return new ResponseObject(200, "Thành công!", profile);
    }
     
//    @RequestMapping(value="/testingFile", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Map<String, Object> testFile(@ModelAttribute user user, @RequestPart MultipartFile image, HttpServletRequest http) throws IOException {
//    	
//    	byte[] bytes = image.getBytes();
//    	String genName=UUID.randomUUID().toString();
//		Path path = Paths.get("src/main/resources/static/avatars/" + genName+"."+ image.getOriginalFilename().split("\\.")[1]);
//		Files.write(path, bytes);
//        Map<String, Object> response=scf.success(200, "Chỉnh sửa thành công!", "success", "/register");
//        return response;
//    }
}
