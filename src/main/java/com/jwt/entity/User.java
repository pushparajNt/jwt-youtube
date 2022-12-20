package com.jwt.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
@Entity
public class User {

	
	@Id
	private String userName;
	private String userFirstName;
	private String lastName;
	private String userPassword;
	@ManyToMany(fetch = FetchType.EAGER,   cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_ROLE",
			joinColumns= {
					@JoinColumn( name="USER_ID")
					},
				inverseJoinColumns= {
						@JoinColumn(name="ROLE_ID")
				}			
			)
	private Set<Role> roles;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
