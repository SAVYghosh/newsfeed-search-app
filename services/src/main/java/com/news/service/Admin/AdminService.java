package com.news.service.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.entity.User;
import com.news.repository.AdminRepo;

@Service
public class AdminService implements IAdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	public User getUser(String userEmail){
		User user=  adminRepo.findByUserEmail(userEmail);
		return user;
	}

	public List<User> getAllUser(){
		return (List<User>) adminRepo.findAllByRoles();
	}
	
	public String blockUser(String userEmail){
		User user=  adminRepo.findByUserEmail(userEmail);
		System.out.println(user.getUserEmail());
		System.out.println(user.getUserStatus());
		user.setUserStatus(false);
		adminRepo.save(user);
		return "blocked";
	}
	
	public String unblockUser(String userEmail){
		User user=  adminRepo.findByUserEmail(userEmail);
		user.setUserStatus(true);
		adminRepo.save(user);
		return "unblocked";
	}
}
