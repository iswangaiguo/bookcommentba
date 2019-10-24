package com.bookcomment.security;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bookcomment.service.UserService;

@Component
public class BCUserDetailService implements UserDetailsService{

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userService.getUser(username);
		if (userDetails != null) {
			return userDetails;
		}
		throw new UsernameNotFoundException(username + "not found");
		
	}
	
	public String setJwt(UserDetails user) throws IllegalArgumentException, UnsupportedEncodingException {
		Algorithm algorithm = Algorithm.HMAC256("123456");
		Date date = new Date(System.currentTimeMillis() + 3600*1000*24); 
		return JWT.create().withSubject(user.getUsername())
					.withExpiresAt(date)
					.withIssuedAt(new Date())
					.sign(algorithm);
	}

}
