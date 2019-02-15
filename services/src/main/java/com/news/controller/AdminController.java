package com.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.User;
import com.news.security.JwtGenerator;
import com.news.service.Admin.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController extends GlobalErrorHandlerController {
	Logger log=LoggerFactory.getLogger(JwtGenerator.class);
	
	@Autowired
	private AdminService adminService;

	@GetMapping("getUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable("userEmail") String userEmail){
	log.info(userEmail);
	User userDetails=adminService.getUser(userEmail);	
	return userDetails;	
	}
	
	@GetMapping("getAllUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getAllUser(){
	return adminService.getAllUser();	
	}

	@GetMapping("blockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String blockUser(@PathVariable("userEmail") String userEmail){
		return adminService.blockUser(userEmail);
	}
	@GetMapping("unblockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String unblockUser(@PathVariable("userEmail") String userEmail){
		return adminService.unblockUser(userEmail);
	}
	
}
