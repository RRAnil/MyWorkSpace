package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.UserDao;
import com.java.model.User;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao dao;
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public User findBySso(String sso) {
		// TODO Auto-generated method stub
		return dao.findBySso(sso);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		dao.save(user);
		
	}

	@Override
	public void updateUser(User user) {
		User entity=dao.findById(user.getId());
		if(entity!=null) {
			entity.setSsoId(user.getSsoId());
			entity.setPassword(user.getPassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
		
	}

	@Override
	public void deleteUserBySso(String sso) {
		dao.deleteBySso(sso);
		
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String sso) {
		// TODO Auto-generated method stub
		User user=findBySso(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}
