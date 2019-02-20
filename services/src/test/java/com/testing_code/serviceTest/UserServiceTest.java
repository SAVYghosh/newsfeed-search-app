package com.testing_code.serviceTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.news.entity.Search;
import com.news.entity.User;
import com.news.repository.UserRepo;
import com.news.repository.UserSearchRepo;
import com.news.service.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepo mockUserRepo;

	@Mock
	UserSearchRepo mockUserSearchRepo;

	@InjectMocks
	UserService mockUserService;
	Optional<Search> optionalSearch;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		optionalSearch = Optional.empty();
	}

	User user = new User();
	Search search = new Search();

	
	//saveSearch
	
	@Test
	public void saveSearchPass() {
		user.setUserEmail("b");
		search.setSearchString("something");
		search.setUser(user);
		when(mockUserRepo.findByUserEmail(search.getUser().getUserEmail())).thenReturn(user);
		when(mockUserSearchRepo.save(search)).thenReturn(search);
		assertEquals("saved", mockUserService.searchSave(search));
	}
	
	@Test
	public void saveSearchFailEmailNotPresent() {
		search.setSearchString("something");
		search.setUser(user);
		assertEquals("no email is present", mockUserService.searchSave(search));
	}
	
	@Test
	public void saveSearchFailEmailNotExist() {
		search.setSearchString("something");
		user.setUserEmail("b");
		search.setUser(user);
		when(mockUserRepo.findByUserEmail("b")).thenReturn(null);
		assertEquals("no such user present", mockUserService.searchSave(search));
	}
	
	
	@Test
	public void saveSearchFailSearchStringNotExist() {
		user.setUserEmail("b");
		search.setUser(user);
		assertEquals("no search data present", mockUserService.searchSave(search));
	}

	
	//deleteSearch
	

	@Test
	public void deleteSearchPass() {

		Long searchId = new Long(1);
		when(mockUserSearchRepo.findById(searchId)).thenReturn(Optional.of(search));
		assertEquals("deleted", mockUserService.searchDelete(1));
	}

	@Test
	public void deleteSearchFail() {
		Long searchId = new Long(1);
		when(mockUserSearchRepo.findById(searchId)).thenReturn(optionalSearch);
		assertEquals("Id does not exist", mockUserService.searchDelete(1));
	}

	
	//searchHistory
	
	@Test
	public void searchHistoryPass() {
		List<Search> listSearch = new ArrayList<Search>();
		listSearch.add(search);
		User user = new User();
		when(mockUserRepo.findByUserEmail("b")).thenReturn(user);
		when(mockUserSearchRepo.findDetailsByUser(user)).thenReturn(listSearch);
		assertEquals(listSearch, mockUserService.searchHistory("b"));
	}

	@Test
	public void searchHistoryFail() {
		when(mockUserRepo.findByUserEmail("b")).thenReturn(null);
		assertEquals(null, mockUserService.searchHistory("b"));
	}
}
