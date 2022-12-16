package com.newnext.newapp2;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.newnext.newapp2.entities.Authority;
import com.newnext.newapp2.entities.User;
import com.newnext.newapp2.service.interfaces.AuthorityService;
import com.newnext.newapp2.service.interfaces.UserService;


@SpringBootApplication
public class Newapp2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Newapp2Application.class, args);

	}
//	@Bean
//	@Order
//	CommandLineRunner run(UserService userService, AuthorityService authorityService) {
//		return args -> {
//			User user1 = User.builder().username("newuser").password("12345").build();
//			Authority au = Authority.builder().name("read").build();
//			authorityService.save(au);
//			Set<Authority> aset = new HashSet<>();
//			aset.add(au);
//			user1.setAuthorities(aset);
//			userService.save(user1);
//			
//			User admin = User.builder().username("newadmin").password("12345").build();
//			Authority au2 = Authority.builder().name("write").build();
//			authorityService.save(au2);
//			admin.getAuthorities().add(au2);
//			userService.save(admin);
//			
//			
//			
//		};
//		
//	
//	}
	
}
