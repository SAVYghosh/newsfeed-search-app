package com.news.service.LoginAndSignup;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ILoginAndSignupService extends UserDetailsService{
	public UserDetails loadUserByUsername(String userEmail);

}
