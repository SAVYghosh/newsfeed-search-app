package com.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.Search;
import com.news.entity.User;

@Repository
public interface UserSearchRepo  extends JpaRepository<Search,Long>{
	List<Search> findDetailsByUser(User user);

}
