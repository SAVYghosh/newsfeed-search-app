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

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	User user=new User(){
		@Override
		public void setUserEmail(String Email) {
			super.setUserEmail("b");
		}
		@Override
		public void setUserName(String Name) {
			super.setUserName("b");
		}
		@Override
		public void setUserPassword(String Password) {
			super.setUserPassword("b");
		}
		@Override
		public void setUserStatus(Boolean status) {
			super.setUserStatus(true);
		}
		@Override
		public void setRoles(String Role) {
			super.setRoles("ROLE_USER");
		}
	};
	
	List<User> list=new ArrayList<User>();
	
	
	@Test
	public void getUserPass(){	
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(user);
		assertEquals(user,mockAdminService.getUser("b"));
	}

	@Test
	public void getAllUserPass(){	
		list.add(user);
		when(mockAdminRepo.findAllByRoles()).thenReturn(list);
		assertEquals(list,mockAdminService.getAllUser());
	}
	@Test
	public void blockUserPass(){
		when(mockAdminRepo.findByUserEmail("b")).thenReturn(user);
		when(mockAdminRepo.save(user)).thenReturn(user);
		assertEquals("blocked",mockAdminService.blockUser("b"));
	}
}
