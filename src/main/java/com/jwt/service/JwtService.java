package com.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.Repository.UserRepository;
import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.entity.User;
import com.jwt.util.JwtUtil;

@Service 
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;  
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
      User  user=   userRepository.findById(username).get();
      
      if(user!=null)
      {
    	  return new org.springframework.security.core.userdetails.User(user.getUserName(), 
    			  user.getUserPassword(), 
    			  getAuthorities(user));
      }
    	  else
    		{
    			throw new UsernameNotFoundException("User name is not valid");
    		}
	}
	
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception
	{
		String userName=jwtRequest.getUserName();
		String password=jwtRequest.getUserPassword();
		authenticate(userName, password);
		
		final UserDetails userDetails=loadUserByUsername(userName);
		String generateToken=jwtUtil.generateToken(userDetails);
		User user=userRepository.findById(userName).get();
		
		return new JwtResponse(user, generateToken);
	}
	
	public void authenticate(String userName,String password) throws Exception
	{
		
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}
		catch(DisabledException exception)
		{
			throw new Exception("User is diabled");
		}
		catch (BadCredentialsException e) {
			System.out.println("Bad credentials from user");
		}
	}
	

	
	private Set getAuthorities(User user)
	{
		Set authorities=new HashSet<>();
		user.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities;
	}
}
