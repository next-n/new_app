package com.newnext.newapp2.config.security.managers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.newnext.newapp2.config.security.providers.HttpBasicProvider;
import com.newnext.newapp2.config.security.providers.TokenAuthenticationProvider;

import lombok.AllArgsConstructor;
@Component(value="customManager")
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{
	@Qualifier("httpBasicProvider")
	private final HttpBasicProvider basicProvider;
	@Qualifier("tokenProvider")
	private final TokenAuthenticationProvider tokenProvider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		System.out.println("Test123");
		if(basicProvider.supports(authentication.getClass())) {
			var a = basicProvider.authenticate(authentication);
			System.out.println(a.getName() + " " + a.isAuthenticated());
			return a;
		}
		else if(tokenProvider.supports(authentication.getClass())) {
			return tokenProvider.authenticate(authentication);
		}
		// TODO Auto-generated method stub
		throw new BadCredentialsException("Wrong");
	}

}
