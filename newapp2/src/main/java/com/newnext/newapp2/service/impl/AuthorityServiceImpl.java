package com.newnext.newapp2.service.impl;

import org.springframework.stereotype.Service;

import com.newnext.newapp2.dao.AuthorityDao;
import com.newnext.newapp2.entities.Authority;
import com.newnext.newapp2.service.interfaces.AuthorityService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService{
	private final AuthorityDao authorityDao;
	@Override
	public void save(Authority au) {
		authorityDao.save(au);
		
	}

}
