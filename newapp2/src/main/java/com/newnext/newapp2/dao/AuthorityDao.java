package com.newnext.newapp2.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newnext.newapp2.entities.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer>{
	Optional<Authority> findByName(String name);

}
