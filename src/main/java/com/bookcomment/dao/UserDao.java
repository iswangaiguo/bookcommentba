package com.bookcomment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bookcomment.entity.User;

@Mapper
public interface UserDao {
	
	@Select("select * from book_users where username = #{username}")
	User selectUserByUserName(String username);

}


