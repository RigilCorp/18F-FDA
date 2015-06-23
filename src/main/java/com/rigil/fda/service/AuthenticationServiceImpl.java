package com.rigil.fda.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.dao.entity.User;

public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	UserService userService;
	
	public static final String SUCCESS = "success".toUpperCase();
	public static final String FAILED = "failed".toUpperCase();
	public static final String INVALID = "invalid".toUpperCase();
	
	public String authenticateUser(String username, String password)
	{
		String result = null;
		User user = null;		
		
		user = userService.findByEmail(username);
		if(user != null)
		{
			if(username.equalsIgnoreCase(password))
			{
				result = SUCCESS;
			}else{
				result = FAILED;
			}
		}else{
			result = INVALID;
		}	
		return result;
	}

}
