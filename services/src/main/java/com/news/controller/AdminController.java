package com.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@CrossOrigin(origins = "http://localhost:4200")
public class AdminController extends GlobalErrorHandlerController {
	Logger log = LoggerFactory.getLogger(JwtGenerator.class);

	@Autowired
	private AdminService adminService;

	@GetMapping("getUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUser( @PathVariable("userEmail") String userEmail) {
		log.info(userEmail);
		if (!userEmail.isEmpty()) {
			User userDetails = adminService.getUser(userEmail);
			return new ResponseEntity<User>(userDetails, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("no email", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("getAllUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList;
		userList = adminService.getAllUser();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@GetMapping("blockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> blockUser(@Valid @PathVariable("userEmail") String userEmail) {
		String response = adminService.blockUser(userEmail);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("unblockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> unblockUser(@Valid @PathVariable("userEmail") String userEmail) {
		String response = adminService.unblockUser(userEmail);
		 return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
