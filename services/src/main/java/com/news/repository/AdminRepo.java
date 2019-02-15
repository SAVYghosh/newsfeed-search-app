package com.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.entity.User;

@Repository
public interface AdminRepo extends JpaRepository<User,Long>{
	User findByUserEmail(String userEmail);
	
	@Query("Select u from User u where u.roles='ROLE_USER'")
	List<User> findAllByRoles(); 
	
	
	
}
