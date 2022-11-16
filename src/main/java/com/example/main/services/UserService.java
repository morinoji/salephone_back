package com.example.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.main.models.user;
import com.example.main.repositories.userRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private userRepository userRepo;

	@Override
	public user loadUserByUsername(String email) throws UsernameNotFoundException {
		user userData=userRepo.findByUserName(email);
		if(userData==null) {
			throw new UsernameNotFoundException(email);
		}
		return userData;
	}
	
	public user getProfile(int id) {
		user profile=userRepo.getProfile(id).get(0);
		return profile;
	}
	
	public void updateCustomer(String fullname, String phone_number, String dob, String address, int id) {
		userRepo.updateCustomer(fullname, phone_number, dob, address, id);
	}
	
	public String changePassword(String oldPassword, String password, int id) {
		return userRepo.changePassword(id, oldPassword, password);
	}
	
	public user getUserById(int id){
		user userData=userRepo.getUserById(id);
		if(userData==null) {
			throw new UsernameNotFoundException(id+"") ;
		}
		return userData;
	}
	
	public List<user> login(String email, String password){
		return userRepo.login(email, password);
	}
	
	public void register(String email, String password) {
		userRepo.insertCustomer(email, password);
	}
}
