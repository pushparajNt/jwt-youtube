package com.jwt.Repository;

import org.springframework.data.repository.CrudRepository;

import com.jwt.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
