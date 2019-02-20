package com.testing_code.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
public class AdminControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	ObjectMapper mapper;
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		mapper=new ObjectMapper();
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	 public void testForGetUser() throws Exception {
		mockMvc.perform(get("/admin/getUser/user@user.com"))
		.andExpect(status().isOk())
		.andDo(print());
	 }
	
	@Test
	@WithMockUser(roles="ADMIN")
	 public void testForGetUserFail() throws Exception {
		mockMvc.perform(get("/admin/getUser/''"))
		.andExpect(status().isBadGateway());
	 }

	
	@Test
	@WithMockUser(roles="ADMIN")
	 public void testForGetAllUser() throws Exception {
		mockMvc.perform(get("/admin/getAllUser"))
		.andExpect(status().isOk());	
	 }
	
	@Test
	@WithMockUser(roles="ADMIN")
	 public void testForBlockUser() throws Exception {
		mockMvc.perform(get("/admin/blockUser/useg@user.com"))
		.andExpect(status().isOk());
		
	 }
	@Test
	@WithMockUser(roles="ADMIN")
	 public void testForUnBlockUser() throws Exception {
		mockMvc.perform(get("/admin/unblockUser/useg@user.com"))
		.andExpect(status().isOk());
		
	 }
}
