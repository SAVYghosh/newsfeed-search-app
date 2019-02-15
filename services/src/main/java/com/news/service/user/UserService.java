package com.news.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.entity.Search;
import com.news.entity.User;
import com.news.repository.UserRepo;
import com.news.repository.UserSearchRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserSearchRepo userSearchRepo;
	
	public String searchSave(Search search) {
		search.setUser(userRepo.findByUserEmail(search.getUser().getUserEmail()));
		userSearchRepo.save(search);
		return "saved";
	}
	
	public List<Search> searchHistory(String userEmail) {
		User user = userRepo.findByUserEmail(userEmail);
		return userSearchRepo.findDetailsByUser(user);

	}
	public String searchDelete(long searchId) {
		userSearchRepo.deleteById(searchId);
		return "deleted";
	}
	
	
}
