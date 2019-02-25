package com.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.java.model.UserProfile;
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao{
	@Override
	public UserProfile findById(int id) {
		
		return getByKey(id);
	}
	@Override
	public UserProfile findByType(String type) {
		Criteria criteria=createCriteria();
		criteria.add(Restrictions.eq("type", type));
		
		return (UserProfile) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfile> findAll() {
		Criteria criteria=createCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<UserProfile>)criteria.list();
	}

	
		

}
