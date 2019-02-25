package com.java.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.java.model.UserProfile;
import com.java.service.UserProfileService;
@Component
public class RoleToUserProfileConverter implements Converter<Object , UserProfile>{
	@Autowired
	UserProfileService userprofileServce;

	@Override
	public UserProfile convert(Object element) {
		Integer id=Integer.parseInt((String) element);
		UserProfile userProfile=userprofileServce.findById(id);
		return userProfile;
	}

}
