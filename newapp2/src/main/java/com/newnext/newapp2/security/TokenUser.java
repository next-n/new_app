package com.newnext.newapp2.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.interfaces.Claim;


import lombok.AllArgsConstructor;
@AllArgsConstructor
public class TokenUser implements UserDetails{
	private final Map<String, Claim> claims;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> s = claims.get("roles").asList(String.class);
		
		return s.stream().map(TokenAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		String token_username_noedit = claims.get("sub").toString();
		return token_username_noedit.substring(1, token_username_noedit.length()-1);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
