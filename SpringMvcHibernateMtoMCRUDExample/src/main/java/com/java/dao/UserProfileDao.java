package com.java.dao;

import java.util.List;

import com.java.model.UserProfile;

public interface UserProfileDao {
	List<UserProfile> findAll();
	UserProfile findByType(String type);
	UserProfile findById(int id);

}
