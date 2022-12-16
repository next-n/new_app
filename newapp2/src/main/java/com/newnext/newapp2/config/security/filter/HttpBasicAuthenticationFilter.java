package com.newnext.newapp2.config.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component(value = "httpBasicFilter")
public class HttpBasicAuthenticationFilter extends BasicAuthenticationFilter{

	public HttpBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		System.out.println("httpbasic");
		// TODO Auto-generated method stub
Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		
		String token = JWT.create()
				.withSubject(authentication.getName())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 *1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
//		ResponseCookie c = new ResponseCookie("auth", token, , token, token, ignoreFailure, ignoreFailure, token);
		ResponseCookie r = ResponseCookie.from("auth", token).sameSite("Strict").httpOnly(false).build();
		
		response.addHeader(HttpHeaders.SET_COOKIE, r.toString());
		
//		response.setHeader("key", token);
	}

}
