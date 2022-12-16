package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.Repository.RoleRepository;
import com.jwt.entity.Role;

@Service
public class RoleServiceImpl  implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role createNewRole(Role role) {
		
		return roleRepository.save(role);
	}
	

}
