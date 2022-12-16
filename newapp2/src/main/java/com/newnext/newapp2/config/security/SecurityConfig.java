package com.newnext.newapp2.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.newnext.newapp2.config.security.authentications.HttpBasicAuthentication;
import com.newnext.newapp2.config.security.extra.OnSuccess;
import com.newnext.newapp2.config.security.filter.HttpBasicAuthenticationFilter;
import com.newnext.newapp2.config.security.filter.TokenAuthenticationFilter;
//import com.newnext.newapp2.config.security.extra.OnSuccess;
import com.newnext.newapp2.config.security.managers.CustomAuthenticationManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	@Qualifier("onSuccess")
	private final OnSuccess onSuccess;
	@Qualifier("customManager")
	private final CustomAuthenticationManager manager;
//	@Autowired
//	@Qualifier("fig")
//	private UsernamePasswordAuthenticationFilter filter;
	@Qualifier("httpBasicFilter")
	private final HttpBasicAuthenticationFilter filter;
	@Qualifier("tokenFilter")
	private final TokenAuthenticationFilter tokenFilter;
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception{
		return http
//				.formLogin().successHandler(onSuccess).permitAll().and()
				.httpBasic().and().authenticationManager(manager)
//				.authorizeHttpRequests().requestMatchers(HttpMethod.OPTIONS, "/saveuser").permitAll().and()
				.cors().configurationSource(configurationSource()).and()
//				.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
//				.authorizeHttpRequests().requestMatchers("/csrftoken").permitAll().and()
				.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class)
				.addFilterAt(filter, BasicAuthenticationFilter.class)
				.csrf().disable()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				
				.authorizeHttpRequests().anyRequest().authenticated().and()
				.build();
		
		
		
		
	}
	
//	X-XSRF-TOKEN
	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration =  new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
		configuration.addExposedHeader("*");
		configuration.setAllowedHeaders(Arrays.asList("X-XSRF-TOKEN", "authorization", "content-type"));
		
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
	

}
