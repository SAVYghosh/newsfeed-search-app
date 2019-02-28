package com.testing_code.controllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.App;
import com.news.entity.Search;
import com.news.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	ObjectMapper mapper;
	Search search;
	User user;
	int i = 2;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		mapper = new ObjectMapper();
		search = new Search();
		user = new User();
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testForsaveSearchPass() throws Exception {

		search.setSearchString("all");
		user.setUserEmail("user@user.com");
		search.setUser(user);
		mockMvc.perform(post("/user/searchSave").content(mapper.writeValueAsString(search))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testForsaveSearchFail() throws Exception {

		mockMvc.perform(post("/user/searchSave").content(mapper.writeValueAsString(search))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testForGetAllSearchHistory() throws Exception {
		mockMvc.perform(get("/user/searchHistory/user@user.com")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testForDeleteSerchHistoryPass() throws Exception {

		mockMvc.perform(get("/user/searchDelete/" + i)).andExpect(status().isOk())
				.andExpect(content().string(containsString("deleted")));
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testForDeleteSerchHistoryFail() throws Exception {
		mockMvc.perform(get("/user/searchDelete/1098")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Id does not exist")));
	}
}
