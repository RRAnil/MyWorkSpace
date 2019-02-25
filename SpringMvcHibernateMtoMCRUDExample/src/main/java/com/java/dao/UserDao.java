package com.java.dao;

import java.util.List;

import com.java.model.User;

public interface UserDao {
	User findById(int id);
	User findBySso(String sso);
	void save(User user);
	void deleteBySso(String sso);
	List<User> findAllUsers();

}
