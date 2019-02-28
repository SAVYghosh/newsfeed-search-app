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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.news.entity.User;
import com.news.service.Admin.AdminService;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 4:04:23 PM
 */

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController extends GlobalErrorHandlerController {
	Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@GetMapping("getUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUser(@PathVariable("userEmail") String userEmail) {
		log.info("Get details of UserEmail->{}", userEmail);
		User userDetails = adminService.getUser(userEmail);
		log.debug("Details of user->{}", userDetails);
		return new ResponseEntity<User>(userDetails, HttpStatus.OK);
	}

	@GetMapping("getAllUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList;
		userList = adminService.getAllUser();
		log.info("List of user fetched");
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@GetMapping("blockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> blockUser(@Valid @PathVariable("userEmail") String userEmail) {
		log.info("Block UserEmail->{}", userEmail);
		String response = adminService.blockUser(userEmail);
		log.debug("user"+ response);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("unblockUser/{userEmail}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> unblockUser(@Valid @PathVariable("userEmail") String userEmail) {
		log.info("Unblock UserEmail->{}", userEmail);
		String response = adminService.unblockUser(userEmail);
		log.debug("user"+ response);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
