package com.news.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.User;
import com.news.security.JwtGenerator;
import com.news.service.LoginAndSignup.LoginAndSignupService;


@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginAndSignupController extends GlobalErrorHandlerController{

	@Autowired
	private LoginAndSignupService loginAndSignupService;
	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	Logger log=LoggerFactory.getLogger(JwtGenerator.class);
	
	@PostMapping("signup")
	public String signup(@Valid @RequestBody User appuser)
	{
		appuser.setRoles("ROLE_USER");
		appuser.setUserStatus(true);
		appuser.setUserPassword(encoder.encode(appuser.getUserPassword()));
		
		return loginAndSignupService.saveUser(appuser);
	}
	@PostMapping("/login")
	public String login(@RequestBody User appUser){
		if(loginAndSignupService.getUserStatus(appUser.getUserEmail())==true){
		Authentication authentication =  authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(appUser.getUserEmail(),appUser.getUserPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtGenerator.generateToken(authentication);
		return jwt;
		}
		else
		{
			return "blocked";
		}
	}
	
}
