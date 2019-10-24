package com.bookcomment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookcomment.filter.MyUsernamePasswordAuthenticationFilter;
import com.bookcomment.service.UserService;

@Configuration
@EnableWebSecurity
public class BCSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService bCUserDetailService;
	
	@Autowired 
	UserService userService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter(bCUserDetailService, userService);
		http.addFilterBefore(myUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**","/images/**").permitAll()
				.antMatchers("/loginpage","/api/**").permitAll()
				//.antMatchers("/api/**").hasAnyAuthority("user")
				.antMatchers("/**").hasAuthority("admin")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/loginpage")
				.loginProcessingUrl("/loginform");
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(bCUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
