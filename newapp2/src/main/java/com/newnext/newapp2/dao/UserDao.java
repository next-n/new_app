package com.newnext.newapp2.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newnext.newapp2.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	

}
