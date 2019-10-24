package com.bookcomment.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookcomment.entity.User;
import com.bookcomment.security.BCUserDetailService;
import com.bookcomment.service.UserService;
import com.mysql.jdbc.Security;

public class MyUsernamePasswordAuthenticationFilter extends OncePerRequestFilter {

	private BCUserDetailService bcUserDetailService;
	private RequestMatcher rmToken = new AntPathRequestMatcher("/applogin");
	private RequestMatcher rmdata = new AntPathRequestMatcher("/api/**");
	private DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); 
	private UserService userService;
	
	public MyUsernamePasswordAuthenticationFilter(UserDetailsService bcUserDetailService, UserService userService) {
		this.bcUserDetailService = (BCUserDetailService)bcUserDetailService;
		this.userService = userService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (rmToken.matches(request)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			daoAuthenticationProvider.setUserDetailsService(bcUserDetailService);
			daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
			Authentication authenticate;
			try {
				authenticate = daoAuthenticationProvider
						.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				String token = bcUserDetailService.setJwt((UserDetails)authenticate.getPrincipal());
				response.addHeader("token", token);
				return;
			} catch (Exception e) {
				response.sendError(403,"认证失败");
			}
		} 
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (rmdata.matches(request)) {
			String token = request.getHeader("token");
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256("123456")).build();
			DecodedJWT jwt = verifier.verify(token);
			String username = jwt.getSubject();
			System.out.println(username);
			User user = userService.getUser(username);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), 
					user.getPassword(), user.getAuthorities());;
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			filterChain.doFilter(request, response);
			return;
		}
		filterChain.doFilter(request, response);
	}
}
