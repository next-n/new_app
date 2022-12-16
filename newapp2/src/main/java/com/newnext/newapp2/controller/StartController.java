package com.newnext.newapp2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.newnext.newapp2.dto.SaveUserDto;
import com.newnext.newapp2.dto.UserDto;
import com.newnext.newapp2.entities.JustTest;
import com.newnext.newapp2.entities.User;
import com.newnext.newapp2.service.interfaces.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StartController {
	public static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
	private final UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<JustTest> start() {
Authentication u = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("size is " + u.getAuthorities().size());
		return ResponseEntity.ok(new JustTest("Hello", "palo"));
	}
	@RequestMapping(value="/csrftoken", method = RequestMethod.GET)
    public void getLoginApiJson(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		
	    response.addHeader(CSRF_HEADER_NAME, csrf.getToken());
		
    }
	@GetMapping("/index")
	public String in() {
		return "Index";
	}
	

	@GetMapping("/users")
	public ResponseEntity<List<JustTest>> u() {
		List<JustTest> js = new ArrayList<>();
		js.add(new JustTest("A", "pass"));
		js.add(new JustTest("B", "pass"));
		
		return ResponseEntity.ok(js);
	}
	@GetMapping("/userpage")
	public List<UserDto> getPage(@RequestParam(name="pgno") int pgno, @RequestParam(name="pgsize") int pgsize){
		return userService.getUsersByPage(pgno, pgsize, "id");
		
	}
	@GetMapping("/totalusers")
	public Integer getTotalUsers() {
		return userService.getTotalUsers();
	}
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<String> su(@RequestBody SaveUserDto u){
//		System.out.println("entered user u" + u);
		userService.save(u);
		return ResponseEntity.ok("fine");
	}
//	@RequestMapping(value = "/saveuser", method = RequestMethod.OPTIONS)
//	@CrossOrigin("http://localhost:4200")
//	public void optiontest() {
//		System.out.println("Option ya tl");
//	}
	@RequestMapping(value="/deleteuser", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteuser(@RequestParam String username){
		System.out.println("delete" + username);
		try {
			userService.delete(username);
			return ResponseEntity.ok("fine");
		}catch(AccountNotFoundException ane) {
			System.out.println(ane.getMessage());
			return ResponseEntity.badRequest().body(ane.getMessage());
			
		}
		
	}
	@RequestMapping(value="/deleteuserauth", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteuserauth(@RequestParam String username){
		System.out.println("delete" + username);
		try {
			userService.removeAuthority(username);
			return ResponseEntity.ok("fine");
		}catch(AccountNotFoundException ane) {
			System.out.println(ane.getMessage());
			return ResponseEntity.badRequest().body(ane.getMessage());
			
		}
		
	}
	@RequestMapping(value="/updateuser", method = RequestMethod.PUT)
	public ResponseEntity<String> updateuser(@RequestParam Integer id, @RequestBody SaveUserDto dto){
		System.out.println("update" + id);
		try {
			userService.update(id, dto);
			return ResponseEntity.ok("fine");
		}catch(EntityNotFoundException ane) {
			System.out.println(ane.getMessage());
			return ResponseEntity.badRequest().body(ane.getMessage());
			
		}
		
	}
	
}
