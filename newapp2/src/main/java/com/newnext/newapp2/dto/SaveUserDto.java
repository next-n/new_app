package com.newnext.newapp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SaveUserDto {
	private String username;
	private String password;
}
