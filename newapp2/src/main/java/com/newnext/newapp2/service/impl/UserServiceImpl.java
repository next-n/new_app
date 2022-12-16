package com.newnext.newapp2.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.newnext.newapp2.dao.AuthorityDao;
import com.newnext.newapp2.dao.UserDao;
import com.newnext.newapp2.dto.SaveUserDto;
import com.newnext.newapp2.dto.UserDto;
import com.newnext.newapp2.entities.Authority;
import com.newnext.newapp2.entities.User;
import com.newnext.newapp2.security.SecurityUser;
import com.newnext.newapp2.service.interfaces.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final AuthorityDao authorityDao;
	private final BCryptPasswordEncoder passwordEncoder;
	@Override
	public void save(SaveUserDto userdto) {
	
		Optional<Authority> oau = authorityDao.findByName("read");
		
		
		String encodedPassword = passwordEncoder.encode(userdto.getPassword());
		User user = new User(userdto.getUsername(), encodedPassword);
		user.addAuthority(oau.get());
		
		userDao.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}


	@Override
	public void update(Integer id, SaveUserDto dto) throws EntityNotFoundException{
		User u = userDao.getReferenceById(id);
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		u.setUsername(dto.getUsername());
		u.setPassword(encodedPassword);
		userDao.save(u);
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String username) throws AccountNotFoundException {
		Optional<User>u = userDao.findByUsername(username);
		
		User user = u.map(r-> r).orElseThrow(() -> new AccountNotFoundException("Not found"));
		System.out.println("inside delete" + user.getAuthorities().size());
//		user.setAuthorities(new HashSet<>());
		userDao.delete(user);
		
	}

	@Override
	public List<UserDto> getUsersByPage(int pgno, int pagesize, String sortBy) {
		Pageable pg = PageRequest.of(pgno, pagesize, Sort.by(sortBy));
//		pg.withPage(pgno);
		Page<User> pu = userDao.findAll(pg);
//		System.out.println(pu.getContent());
//		return null;
		return pu.stream().map(UserDto::new).collect(Collectors.toList());
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getTotalUsers() {
		// TODO Auto-generated method stub
		return Math.toIntExact(userDao.count());
	}

	@Override
	public void removeAuthority(String username) throws AccountNotFoundException {
		Optional<User>u = userDao.findByUsername(username);
//		System.out.println("Before" + u.get().getAuthorities().toString());
//		User user = u.map(r-> r).orElseThrow(() -> new AccountNotFoundException("Not found"));
//		user.getAuthorities().forEach(a->System.out.println(a.getName()));
//		user.getAuthorities().forEach(e-> {user.remove(e); e.remove(user);authorityDao.saveAndFlush(e);});
//		
//		System.out.println(user.getAuthorities().size()+ user.getUsername() + " " + user.getId());
//		user.setUsername("extra1new");
//		
//		userDao.saveAndFlush(user);
//Optional<User>u2 = userDao.findByUsername(username);
//		
//		User user2 = u2.map(r-> r).orElseThrow(() -> new AccountNotFoundException("Not found"));
//		System.out.println(user2.getAuthorities().size());
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savedirect(User user) {
		userDao.save(user);
		// TODO Auto-generated method stub
		
	}

}
