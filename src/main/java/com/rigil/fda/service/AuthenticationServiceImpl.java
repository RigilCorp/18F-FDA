package com.rigil.fda.service;

import com.rigil.fda.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserService userService;

    public static final String SUCCESS = "success".toUpperCase();
    public static final String FAILED = "failed".toUpperCase();
    public static final String INVALID = "invalid".toUpperCase();

    public String authenticateUser(String username, String password)
    {
        String result;
        UserEntity userEntity;

        userEntity = userService.findByEmail(username);
        if(userEntity != null)
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
