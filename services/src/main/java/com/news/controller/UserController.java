package com.news.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.news.entity.Search;
import com.news.service.user.UserService;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 4:43:43 PM
 */

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends GlobalErrorHandlerController {

	@Autowired
	private UserService userService;
	Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("searchSave")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> searchSave(@RequestBody Search search) {
		if (search.getSearchString() != null) {
			log.info("User ->{}"+ search.getUser().getUserEmail()+ "seacrh once and string is",
					search.getSearchString());
			String response = userService.searchSave(search);
			log.debug("Search save process result"+ response);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} else
			log.info("Search data null");
		return new ResponseEntity<String>("no search data", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("searchHistory/{userEmail}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> searchHistory(@PathVariable("userEmail") String userEmail) {
		log.info("search history fetched for user ->{}", userEmail);
		List<Search> searchList;
		searchList = userService.searchHistory(userEmail);
		log.debug("List Fetched");
		return new ResponseEntity<List<Search>>(searchList, HttpStatus.OK);
	}

	@GetMapping("searchDelete/{searchId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> searchDelete(@PathVariable("searchId") Long searchId) {
		log.info("User request to delete a search string");
		String response = userService.searchDelete(searchId);
		log.debug("search history deletion process result"+ response);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
