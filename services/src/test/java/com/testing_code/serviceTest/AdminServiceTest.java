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

import com.news.entity.User;
import com.news.repository.AdminRepo;
import com.news.service.Admin.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
	
	@Mock
	AdminRepo mockAdminRepo;
	
	@InjectMocks
	AdminService mockAdminService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	User user=new User();

	
	//get one user list
	
	
	@Test
	public void getUserPass(){	
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(user);
		assertEquals(user,mockAdminService.getUser("b"));
	}

	@Test
	public void getUserFail(){	
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(null);
		assertEquals(null,mockAdminService.getUser("b"));
	}
	
	
	
	
	//get all user
	
	@Test
	public void getAllUserPass(){	
		List<User> list=new ArrayList<User>();
		list.add(user);
		when(mockAdminRepo.findAllByRoles()).thenReturn(list);
		assertEquals(list,mockAdminService.getAllUser());
	}
	
	
	//block a user
	
	@Test
	public void blockUserPass(){
		user.setUserStatus(true);
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(user);
		assertEquals("blocked",mockAdminService.blockUser("b"));
	}
	@Test
	public void blockUserFailEmailNull(){
		assertEquals("no email present",mockAdminService.blockUser(null));
	}
	@Test
	public void blockUserFailEmailNotPresent(){
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(null);
		assertEquals("user invalid",mockAdminService.blockUser("b"));
	}
	@Test
	public void blockUserFailAlreadyBlock(){
		user.setUserStatus(false);
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(user);
		assertEquals("already blocked",mockAdminService.blockUser("b"));
	}
}
