package com.newnext.newapp2.dto;

import com.newnext.newapp2.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
public class UserDto {
	
	private final User user;
	public Integer getId() {
		return user.getId();
	}
	public String getUsername() {
		return user.getUsername();
	}
	
	
	

}
