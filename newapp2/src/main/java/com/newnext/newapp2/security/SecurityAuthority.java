package com.newnext.newapp2.security;

import org.springframework.security.core.GrantedAuthority;

import com.newnext.newapp2.entities.Authority;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority{
	private final Authority authority;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority.getName();
	}

}
