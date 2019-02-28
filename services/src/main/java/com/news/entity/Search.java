package com.news.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Search {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long searchId;

	@NotBlank(message = "Search string required")
	private String searchString;

	@CreationTimestamp
	private LocalDateTime searchTime;

	@ManyToOne
	@JoinColumn(name = "userId")
	User user;

	public long getSearchId() {
		return searchId;
	}

	public void setSearchId(long searchId) {
		this.searchId = searchId;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public LocalDateTime getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(LocalDateTime searchTime) {
		this.searchTime = searchTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
