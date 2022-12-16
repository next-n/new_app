package com.newnext.newapp2.config.security.filter;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.newnext.newapp2.config.security.authentications.TokenAuthentication;
import com.newnext.newapp2.config.security.managers.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component(value="tokenFilter")
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter{
	@Qualifier("customManager")
	private final CustomAuthenticationManager manager;
	private String cookieName = "auth";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Token");
		Cookie c = WebUtils.getCookie(request, cookieName);
		String token = (c == null)? "":c.getValue() ;
		TokenAuthentication ta = new TokenAuthentication(token);
		try {
			Authentication a = manager.authenticate(ta);
			if(a.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(a);
			}
		}catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			// TODO Auto-generated catch block
			
		}
		filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}
	

}
