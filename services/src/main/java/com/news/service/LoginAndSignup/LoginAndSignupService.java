package com.news.service.LoginAndSignup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.news.entity.User;
import com.news.repository.LoginAndSignupRepo;

@Service
public class LoginAndSignupService implements ILoginAndSignupService {

	@Autowired
	private LoginAndSignupRepo loginAndSignupRepo;
	
	public String saveUser(User user){	
//		if(getUser(user.getUserEmail())==true)
	//	{ 
			loginAndSignupRepo.save(user);
	//	}
		 return "Registered";
//		}
//		else
//		{
//			return "Email Already Exist";
//		}
	}
	
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = loginAndSignupRepo.findByUserEmail(userEmail);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(user.getRoles()));
		org.springframework.security.core.userdetails.User user1=new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), roles);
		return user1;
	}
	
	public boolean getUser(String userEmail){
		User user2 = loginAndSignupRepo.findByUserEmail(userEmail);
		return user2.getUserStatus();
	}
}
