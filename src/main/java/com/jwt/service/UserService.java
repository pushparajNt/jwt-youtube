package com.jwt.service;

import com.jwt.entity.User;

public interface UserService {

	
	User createNewUser(User user);
	void initRolesAndUsers();
}
