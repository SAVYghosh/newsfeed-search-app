package com.news.service.LoginAndSignup;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.news.entity.User;
import com.news.repository.LoginAndSignupRepo;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 5:36:36 PM
 */
@Service
public class LoginAndSignupService implements ILoginAndSignupService {
	Logger log = LoggerFactory.getLogger(LoginAndSignupService.class);
	@Autowired
	private LoginAndSignupRepo loginAndSignupRepo;

	public String saveUser(User user) {
		log.info("register service called");
		if (user.getUserEmail() != null && user.getUserName() != null && user.getUserPassword() != null) {
			log.debug("In register service email,name,password is not null");
			if (loginAndSignupRepo.findByUserEmail(user.getUserEmail()) == null) {
				log.debug("In register service- new user check done");
				loginAndSignupRepo.save(user);
				log.debug("In register service- registration successful");
				return "Registered";
			} else {
				log.debug("In register service- user already exist");
				return "Email Already Exist";
			}
		} else {
			log.debug("In register service- null data");
			return "null data";
		}
	}

	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		log.info("User->{} log in in process", userEmail);
		User user = loginAndSignupRepo.findByUserEmail(userEmail);
		log.debug("Find the user by email");
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		log.debug("Authority provided");
		roles.add(new SimpleGrantedAuthority(user.getRoles()));
		org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(
				user.getUserEmail(), user.getUserPassword(), roles);
		log.info("Log in done");
		return user1;
	}

	public boolean getUserStatus(String userEmail) {
		log.info("In user status check service");
		User user2 = loginAndSignupRepo.findByUserEmail(userEmail);
		log.info("In user status check service- statuse fetched");
		return user2.getUserStatus();
	}
}
