package com.testing_code.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class LoginAndSignupControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	ObjectMapper mapper;
	Search search;
	User user;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		mapper = new ObjectMapper();
		search = new Search();
		user = new User();
	}

	@Test
	public void testSignupPass() throws Exception {
		Random rand = new Random();

		int n = rand.nextInt(50) * 4;
		System.out.println(n + "@mail.com");

		user.setUserEmail(n + "@mail.com");
		user.setUserName("sourav");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
	}

	@Test
	public void testSignupFailWrongEmail() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("sourav");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSignupFailWrongPasswordPattern() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("sourav");
		user.setUserPassword("User10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSignupFailWrongPasswordLength() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("sourav");
		user.setUserPassword("User10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSignupFailWrongUserNameLength() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("s");
		user.setUserPassword("User10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSignupFailWrongUserNamePattern() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("s1111");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSignupFailWrongUserNameLonglength() throws Exception {

		user.setUserEmail("userSouravuser.com");
		user.setUserName("s1111111111111111111111111111111111111111111111111");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/signup").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testLoginSuccessfull() throws Exception {

		user.setUserEmail("user@user.com");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/login").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
	}

	@Test
	public void testLoginFailUserBlock() throws Exception {

		user.setUserEmail("useg@user.com");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/login").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
	}

	@Test
	public void testLoginFailWrongEmail() throws Exception {

		user.setUserEmail("us@user.com");
		user.setUserPassword("User@10");
		mockMvc.perform(post("/auth/login").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}

	@Test
	public void testLoginFailWrongPassword() throws Exception {

		user.setUserEmail("user@user.com");
		user.setUserPassword("User!!10");
		mockMvc.perform(post("/auth/login").content(mapper.writeValueAsString(user))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isBadRequest());
	}
}
