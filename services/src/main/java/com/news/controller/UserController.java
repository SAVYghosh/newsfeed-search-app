package com.news.controller;

import java.util.Date;
import java.util.List;

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
import com.news.entity.User;
import com.news.service.user.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends GlobalErrorHandlerController{
	
	@Autowired
	private UserService userService;
	
	@PostMapping("searchSave")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> searchSave(@RequestBody Search search)
	{
		if(search.getSearchString()!=null)
		{
		String response= userService.searchSave(search);
		return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("no search data", HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("searchHistory/{userEmail}")
	@PreAuthorize("hasRole('ROLE_USER')")
		public ResponseEntity<?> searchHistory(@PathVariable("userEmail") String userEmail){	
		List<Search> searchList;
		searchList=userService.searchHistory(userEmail);
			return new ResponseEntity<List<Search>>(searchList, HttpStatus.OK);	
			}
	
	
	@GetMapping("searchDelete/{searchId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> searchDelete(@PathVariable("searchId") Long searchId){
		String response= userService.searchDelete(searchId);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}

}
