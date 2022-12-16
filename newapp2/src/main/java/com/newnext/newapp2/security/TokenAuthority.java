package com.newnext.newapp2.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class TokenAuthority implements GrantedAuthority{
	private String s;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.s;
	}

}
