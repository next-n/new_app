package com.newnext.newapp2.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class bcrypt extends BCryptPasswordEncoder implements PasswordEncoder{

}
