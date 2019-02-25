package com.java.service;

import java.util.List;

import com.java.model.User;

public interface UserService {
	User findById(int id);
	User findBySso(String sso);
	void save(User user);
	void updateUser(User user);
	void deleteUserBySso(String sso);
	List<User> findAllUsers();
	boolean isUserSSOUnique(Integer id,String sso);

}
