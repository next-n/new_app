package com.newnext.newapp2.config.security.providers;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.newnext.newapp2.config.security.authentications.HttpBasicAuthentication;
import com.newnext.newapp2.entities.User;
import com.newnext.newapp2.security.SecurityUser;
import com.newnext.newapp2.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;
@Component(value="httpBasicProvider")
@RequiredArgsConstructor
public class HttpBasicProvider implements AuthenticationProvider {
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String input_username = authentication.getName();
		String input_password = (String) authentication.getCredentials();
		Optional<User> u = userService.findByUsername(input_username);
		SecurityUser su = u.map(SecurityUser::new).orElseThrow(() -> new BadCredentialsException("Not Found"));
		if(passwordEncoder.matches(input_password, su.getPassword())) {
			HttpBasicAuthentication ha = new HttpBasicAuthentication(su);
			ha.setAuthenticated(true);
			return ha;
		}
		throw new BadCredentialsException("Not Found");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
