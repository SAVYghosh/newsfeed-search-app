package com.news.service.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.entity.User;
import com.news.repository.AdminRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminRepo;

	public User getUser(String userEmail) {
		if (adminRepo.findByUserEmail(userEmail) != null) {
			User user = adminRepo.findByUserEmail(userEmail);
			return user;
		} else
			return null;
	}

	public List<User> getAllUser() {
		return (List<User>) adminRepo.findAllByRoles();
	}

	public String blockUser(String userEmail) {
		if (userEmail != null) {
			if (adminRepo.findByUserEmail(userEmail) != null) {
				User user = adminRepo.findByUserEmail(userEmail);
				if (user.getUserStatus() == true) {
					user.setUserStatus(false);
					adminRepo.save(user);
					return "blocked";
				} 
				else
					return "already blocked";
			} 
			else
				return "user invalid";
		} 
		else
			return "no email present";
	}

	public String unblockUser(String userEmail) {
		User user = adminRepo.findByUserEmail(userEmail);
		user.setUserStatus(true);
		adminRepo.save(user);
		return "unblocked";
	}
}
