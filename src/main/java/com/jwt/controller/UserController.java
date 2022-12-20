package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.User;
import com.jwt.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostConstruct
	public void initUserAndRoles()
	{
		userService.initRolesAndUsers();
	}
	
	@PostMapping("/registerNewUser")
	public User createNewUser(@RequestBody User user)
	{
	   return userService.createNewUser(user); 
	}
	
	@GetMapping("/forAdmin")
	public String forAdmin()
	{
		return "This url is only accessible for admin";
	}
	
	@GetMapping("/forUser")
	public String forUser()
	{
		return "This url is only accessible for user";
	}
}
