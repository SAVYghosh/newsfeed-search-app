package com.news.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.entity.Search;
import com.news.entity.User;
import com.news.repository.UserRepo;
import com.news.repository.UserSearchRepo;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 5:13:03 PM
 */

@Service
public class UserService {
	Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserSearchRepo userSearchRepo;

	public String searchSave(Search search) {
		log.info("search data saved service is called");
		if (search.getUser().getUserEmail() != null) {
			log.debug("In search data saved service- email->{} not null", search.getUser().getUserEmail());
			if (search.getSearchString() != null) {
				log.debug("In search data saved service- search data->{} is not null", search.getSearchString());
				if (userRepo.findByUserEmail(search.getUser().getUserEmail()) != null) {
					log.debug("In search data saved service- email->{} exist", search.getUser().getUserEmail());
					search.setUser(userRepo.findByUserEmail(search.getUser().getUserEmail()));
					userSearchRepo.save(search);
					log.info("Search data saved");
					return "saved";
				} else {
					log.debug("In search data saved service- email->{} does not exist",
							search.getUser().getUserEmail());
					return "no such user present";
				}
			} else {
				log.debug("In search data saved service- search data->{} null", search.getSearchString());
				return "no search data present";
			}
		} else {
			log.debug("In search data saved service- email->{} not null", search.getUser().getUserEmail());
			return "no email is present";
		}
	}

	public List<Search> searchHistory(String userEmail) {
		log.info("search histpry fetched service is called");
		if (userRepo.findByUserEmail(userEmail) != null) {
			log.info("In search histpry fetched service- userEmail->{} is not invalid", userEmail);
			User user = userRepo.findByUserEmail(userEmail);
			log.debug("In search histpry fetched service- list fetched");
			return userSearchRepo.findDetailsByUser(user);
		} else {
			log.info("In search histpry fetched service- userEmail->{} is invalid", userEmail);
			return null;
		}
	}

	public String searchDelete(long searchId) {
		log.info("search histpry delete service is called");
		if (userSearchRepo.findById(searchId).isPresent()) {
			log.info("In search histpry fetched service- searchID->{} is not invalid", searchId);
			userSearchRepo.deleteById(searchId);
			log.info("search deleted");
			return "deleted";
		} else {
			log.info("In search histpry fetched service- searchID->{} is invalid", searchId);
			return "Id does not exist";
		}
	}

}
