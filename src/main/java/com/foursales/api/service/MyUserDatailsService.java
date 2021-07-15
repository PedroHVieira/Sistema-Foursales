package com.foursales.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foursales.api.model.MyUserDetails;
import com.foursales.api.model.User;
import com.foursales.api.repository.UserRepository;


@Service
public class MyUserDatailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username ou password não encontrado");
		}
		
		return new MyUserDetails(user);
	}
}
