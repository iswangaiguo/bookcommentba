package com.bookcomment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/loginpage").setViewName("loginpage");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/basic-form.html").setViewName("basic-form");
        registry.addViewController("/form-layouts.html").setViewName("form-layouts");
        registry.addViewController("/tables.html").setViewName("tables");
        registry.addViewController("/authentication/require").setViewName("login");
    }
	
}
