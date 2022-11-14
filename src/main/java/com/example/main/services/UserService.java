package com.example.main.services;

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
	
	public user getUserById(int id) throws UsernameNotFoundException {
		user userData=userRepo.getUserById(id);
		if(userData==null) {
			throw new UsernameNotFoundException(id+"") ;
		}
		return userData;
	}
}
