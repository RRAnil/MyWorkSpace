package com.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.java.model.User;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	@Override
	public User findById(int id) {
		User user=getByKey(id);
		if(user!=null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public User findBySso(String sso) {
		System.out.println("SSO"+sso);
		Criteria criteria=createCriteria();
	
		criteria.add(Restrictions.eq("ssoId", sso));
		User user=(User) criteria.uniqueResult();
		if(user!=null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;	}

	@Override
	public void save(User user) {
		persist(user);
		
	}

	@Override
	public void deleteBySso(String sso) {
		Criteria criteria=createCriteria();
		criteria.add( Restrictions.eq("ssoId", sso));
		User user=(User) criteria.uniqueResult();
		delete(user);
	}

	@Override
	public List<User> findAllUsers() {
		Criteria criteria=createCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<User> users=(List<User>)criteria.list();
		
		return users;
	}
	

}
