package com.java.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.java.model.User;
import com.java.model.UserProfile;
import com.java.service.UserProfileService;
import com.java.service.UserService;

@RestController
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value= {"/","/list"},method=RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<User> users=userService.findAllUsers();
		model.addAttribute("users",users);
		return "userslist";
		
	}
	@RequestMapping(value= {"/","/list"},method=RequestMethod.GET)
	public @ResponseBody List<User> listUsersService(ModelMap model) {
		List<User> users=userService.findAllUsers();
		model.addAttribute("users",users);
		return users;
		
	}
	@RequestMapping(value= {"/newuser"},method=RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user=new User();
		model.addAttribute("user",user);
		model.addAttribute("edit",false);
		return "registration";
	}
	@RequestMapping(value= {"/newuser"},method=RequestMethod.POST)
	public String saveUser(@Valid User user,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "registration";
		}
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError  error=new FieldError("user", "ssoId",messageSource.getMessage("non.unitque.ssoId" ,new String[] {user.getSsoId()},Locale.getDefault()));
			result.addError(error);
			return "registration";
		}
		userService.save(user);
		model.addAttribute("success","user"+user.getFirstName()+""+user.getLastName()+"updated Successfully");
		return "registrationsuccess";
		
	}
	@RequestMapping(value= {"/edit-user-{ssoId}"},method=RequestMethod.GET)
	public String editUser(@PathVariable String ssoId,ModelMap model) {
		User user=userService.findBySso(ssoId);
		model.addAttribute("user",user);
		model.addAttribute("edit",true);
		return "registration";
		
	}
	@RequestMapping(value= {"/edit-user-{ssoId}"},method=RequestMethod.POST)
	public String updateUser(@Valid User user,BindingResult result,ModelMap model,@PathVariable String ssoId) {
		
		if(result.hasErrors()) {
			return "registration";
		}
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError  error=new FieldError("user", "ssoId",messageSource.getMessage("non.unitque.ssoId" ,new String[] {user.getSsoId()},Locale.getDefault()));
			result.addError(error);
			return "registration";
		}
		userService.save(user);
		model.addAttribute("success","user"+user.getFirstName()+""+user.getLastName()+"updated Successfully");
		return "registrationsuccess";
		
		
	}
	@RequestMapping(value="/delete-user-{ssoid}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySso(ssoId);
		return "redirect:/list";
	}
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles(){
		return userProfileService.findAll();
	}

}
