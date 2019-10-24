package com.bookcomment.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bookcomment.security.BCUserDetailService;

public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private BCUserDetailService bCUserDetailService;
	
	public JsonLoginSuccessHandler(BCUserDetailService bCUserDetailService) {
		this.bCUserDetailService = bCUserDetailService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = bCUserDetailService.setJwt((UserDetails)authentication.getPrincipal());
		response.setHeader("Authorization", token);
	}

}
