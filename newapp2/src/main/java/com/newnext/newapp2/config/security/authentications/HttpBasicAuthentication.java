package com.newnext.newapp2.config.security.authentications;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class HttpBasicAuthentication implements Authentication{
	private static final long serialVersionUID = 1L;
	final UserDetails user;
	private boolean authenticationValue;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticationValue;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticationValue = isAuthenticated;
		// TODO Auto-generated method stub
		
	}
	

}
