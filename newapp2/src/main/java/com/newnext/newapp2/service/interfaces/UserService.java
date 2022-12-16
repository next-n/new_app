package com.newnext.newapp2.service.interfaces;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import com.newnext.newapp2.dto.SaveUserDto;
import com.newnext.newapp2.dto.UserDto;
import com.newnext.newapp2.entities.Authority;
import com.newnext.newapp2.entities.User;

public interface UserService {
	void savedirect(User user);
	void save(SaveUserDto user);
	Optional<User> findByUsername(String username);
	void update(Integer id, SaveUserDto dto);
	void delete(String username) throws AccountNotFoundException;
	List<UserDto> getUsersByPage(int pgno, int pagesize, String id);
	Integer getTotalUsers();
	void removeAuthority(String username)throws AccountNotFoundException;
	
	
}
