package com.testing_code.serviceTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import com.news.entity.User;
import com.news.repository.LoginAndSignupRepo;
import com.news.service.LoginAndSignup.LoginAndSignupService;

@RunWith(MockitoJUnitRunner.class)
public class LoginAndSignupServiceTest {
	
	@Mock
	LoginAndSignupRepo mockLoginAndSignupRepo;
	
	@InjectMocks
	LoginAndSignupService mockLoginAndSignService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	User user=new User();

	@Test
	public void saveUserPass(){
		user.setUserEmail("b");
		user.setUserName("b");
		user.setUserPassword("b");
		when(mockLoginAndSignupRepo.findByUserEmail("b")).thenReturn(null);
		when(mockLoginAndSignupRepo.save(any(User.class))).thenReturn(user);
		assertEquals("Registered",mockLoginAndSignService.saveUser(user));		
	}
	
	@Test
	public void saveUserFailEmailAlreadyExist(){
		user.setUserEmail("b");
		user.setUserName("b");
		user.setUserPassword("b");
		when(mockLoginAndSignupRepo.findByUserEmail("b")).thenReturn(user);
		assertEquals("Email Already Exist",mockLoginAndSignService.saveUser(user));		
	}
	@Test
	public void saveUserFailUserEmailNull(){
		user.setUserName("b");
		user.setUserPassword("b");
		assertEquals("null data",mockLoginAndSignService.saveUser(user));		
	}
	@Test
	public void saveUserFailUserNameNull(){
		user.setUserEmail("b");
		user.setUserPassword("b");
		assertEquals("null data",mockLoginAndSignService.saveUser(user));		
	}
	@Test
	public void saveUserFailUserPasswordNull(){
		user.setUserEmail("b");
		user.setUserName("b");
		assertEquals("null data",mockLoginAndSignService.saveUser(user));		
	}
	
}
