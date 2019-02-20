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
		if (search.getUser().getUserEmail() != null) {
			if (search.getSearchString() != null) {
				if (userRepo.findByUserEmail(search.getUser().getUserEmail()) != null) {
					search.setUser(userRepo.findByUserEmail(search.getUser().getUserEmail()));
					userSearchRepo.save(search);
					return "saved";
				} 
				else
					return "no such user present";
			} 
			else
				return "no search data present";
		} 
		else
			return "no email is present";
	}

	public List<Search> searchHistory(String userEmail) {
		if (userRepo.findByUserEmail(userEmail) != null){
		User user = userRepo.findByUserEmail(userEmail);
			return userSearchRepo.findDetailsByUser(user);
		}
		else
			return null;
	}

	public String searchDelete(long searchId) {
		if (userSearchRepo.findById(searchId).isPresent()) {
			userSearchRepo.deleteById(searchId);
			return "deleted";
		} else
			return "Id does not exist";
	}

}
