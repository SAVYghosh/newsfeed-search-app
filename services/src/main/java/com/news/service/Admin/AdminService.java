package com.news.service.Admin;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.entity.User;
import com.news.repository.AdminRepo;
import com.news.security.JwtGenerator;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 4:55:46 PM
 */
@Service
public class AdminService {
	Logger log = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private AdminRepo adminRepo;

	public User getUser(String userEmail) {
		log.debug("get a user service called");
		if (adminRepo.findByUserEmail(userEmail) != null) {
			User user = adminRepo.findByUserEmail(userEmail);
			log.info("details fetched");
			return user;
		} else
			log.debug("userEmail ->{} not found", userEmail);
		return null;
	}

	public List<User> getAllUser() {
		log.info("List of user fetched in service ");
		return (List<User>) adminRepo.findAllByRoles();
	}

	public String blockUser(String userEmail) {
		log.debug("service of block user called");
		if (userEmail != null) {
			log.debug("In service of block user- email is not null");
			if (adminRepo.findByUserEmail(userEmail) != null) {
				log.debug("In service of block user- email exist");
				User user = adminRepo.findByUserEmail(userEmail);
				log.debug("In service of block user- user details fetched");
				if (user.getUserStatus() == true) {
					log.debug("In service of block user- user is not already blocked");
					user.setUserStatus(false);
					adminRepo.save(user);
					log.debug("In service of block user- user blocked");
					return "blocked";
				} else {
					log.debug("In service of block user- user is not already blocked");
					return "already blocked";
				}
			} else {
				log.debug("In service of block user- email exist");
				return "user invalid";
			}
		} else {
			log.debug("In service of block user- email null");
			return "no email present";
		}
	}

	public String unblockUser(String userEmail) {
		log.debug("service of unblock user called");
		User user = adminRepo.findByUserEmail(userEmail);
		log.debug("In service of unblock user- user details fetched");
		user.setUserStatus(true);
		adminRepo.save(user);
		log.debug("In service of block user- user unblocked");
		return "unblocked";
	}
}
