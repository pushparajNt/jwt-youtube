package com.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.Repository.RoleRepository;
import com.jwt.Repository.UserRepository;
import com.jwt.entity.Role;
import com.jwt.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public User createNewUser(User user) {
	
		return userRepository.save(user);
	}
	
	public void initRolesAndUsers()
	{
		Role adminRole=new Role();
		adminRole.setRoleName("admin");
		adminRole.setRoleDescription("Admin role");
		roleRepository.save(adminRole);
		
		Role userRole=new Role();
		userRole.setRoleName("user");
		userRole.setRoleDescription("Default Role for newly created record");
		roleRepository.save(userRole);
		
		User adminUser=new User();
		adminUser.setUserName("admin@123");
		adminUser.setUserFirstName("admin");
		adminUser.setLastName("admin");
		adminUser.setUserPassword("admin@passs");		
		Set<Role> rolSet=new HashSet<>();
		rolSet.add(adminRole);
		adminUser.setRoles(rolSet);		
		userRepository.save(adminUser);
		
		User normalUser =new User();
		normalUser.setUserName("pushparaj123");
		normalUser.setUserFirstName("Pushparaj");
		normalUser.setLastName("Narasimman");
		normalUser.setUserPassword("user@passs");		
		Set<Role> normalRoleSet=new HashSet<>();
		normalRoleSet.add(userRole);
		normalUser.setRoles(normalRoleSet);		
		userRepository.save(normalUser);
	}

}
