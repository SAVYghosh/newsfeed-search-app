package com.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@NotBlank(message = "email can not be blank")
	@NotNull(message = "email can not be null")
	@Size(min = 2, max = 20, message = "Username should contains minimun 4 characters and maximum 20 characters")
	@Pattern(regexp = "^[a-zA-Z ]{4,30}+$", message = "username contains only alphabets")
	private String userName;

	@NotNull(message = "email can not be null")
	@NotBlank(message = "email can not be null")
	@Size(min = 6, max = 40, message = "Invalid Email Id")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "email must contain one '@' and one '.'")
	private String userEmail;

	@NotBlank(message = "email can not be null")
	@NotNull(message = "email can not be null")
	@Size(min = 6, message = "Minimum Password size must be 6")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%?&*_\\.\\-]).{6,}$", message = "password must contain atleast one Uppercase and one lowercase letter one digit and one special character(i.e- !@#-_$%&*.)")
	private String userPassword;

	private String roles;

	private Boolean userStatus;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

}
