package com.news.service.LoginAndSignup;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.news.entity.User;

public interface ILoginAndSignupService extends UserDetailsService{
	public String saveUser(User user);	
	public UserDetails loadUserByUsername(String userEmail);

}
