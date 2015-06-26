package com.rigil.fda.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.UserEntity;
import com.rigil.fda.repository.UserRepository;


public class UserEntityServiceTest extends AbstractBaseTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;

    @Test
    public void testFindUserByEmail(){
        UserEntity userEntity = service.findByEmail(email);
        assertThat("userEntity should not be null.", userEntity, is(not(nullValue())));
        assertThat("retrived incorrect userEntity", userEntity.getUserEmail(), is(email));

    }

    @Test
    public void testFindUserByPhone(){
        UserEntity userEntity = service.findByPhone(phone);
        assertThat("userEntity should not be null.", userEntity, is(not(nullValue())));
        assertThat("retrived incorrect userEntity", userEntity.getPhone(), is(phone));

    }

}
