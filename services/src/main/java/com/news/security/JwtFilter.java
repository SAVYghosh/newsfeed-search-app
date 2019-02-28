package com.news.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import com.news.service.LoginAndSignup.LoginAndSignupService;

/**
 * @author 729715
 * Name: Sourav Ghosh
 * Date: Feb 28, 2019 5:46:29 PM
 */
public class JwtFilter extends OncePerRequestFilter {

	Logger log = LoggerFactory.getLogger(JwtFilter.class);
	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	LoginAndSignupService loginAndSignupService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("In filter");
		try {
			String jwt = getJwt(request);
			if (jwt != null && jwtGenerator.validateToken(jwt)) {
				log.info("In jwt Generator");
				String userName = jwtGenerator.getUsernameFromToken(jwt);
				UserDetails userDetails = loginAndSignupService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		} catch (Exception ex) {
			logger.error("Cannot authenticate user->{}", ex);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		log.info("In Getting Jwt");
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer "))
			return authHeader.replace("Bearer ", "");
		return null;
	}
}
