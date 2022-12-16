package com.newnext.newapp2.config.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.newnext.newapp2.config.security.authentications.HttpBasicAuthentication;
import com.newnext.newapp2.config.security.authentications.TokenAuthentication;
import com.newnext.newapp2.security.TokenUser;
@Component(value = "tokenProvider")
public class TokenAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		TokenAuthentication ta = (TokenAuthentication) authentication;
		String token = ta.getCredentials().toString();
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		try {
			DecodedJWT jwt = JWT.require(algorithm)
			        .build()
			        .verify(token);
			TokenUser tu = new TokenUser(jwt.getClaims());
			HttpBasicAuthentication ha = new HttpBasicAuthentication(tu);
			ha.setAuthenticated(true);
			return ha;
//			byte[] b = Base64.getDecoder().decode(jwt.getPayload());
//			System.out.println("JWT IS  " + new String(Base64.getDecoder().decode(jwt.getPayload()), "UTF-8"));
//			b[]
		}catch(JWTVerificationException e) {
			throw new BadCredentialsException("bad key");
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return TokenAuthentication.class.equals(authentication);
	}

}
