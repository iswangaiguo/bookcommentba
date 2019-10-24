package com.bookcomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookcomment.dao.BookDao;
import com.bookcomment.dao.UserDao;
import com.bookcomment.entity.BookDetails;
import com.bookcomment.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public User getUser(String username) {
		
		return userDao.selectUserByUserName(username);
	}
	
}
