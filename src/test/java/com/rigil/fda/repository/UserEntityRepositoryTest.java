package com.rigil.fda.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.UserEntity;


public class UserEntityRepositoryTest extends AbstractBaseTest {

    @Autowired
    UserRepository repository;

    @Test
    public void testFindUserByEmail(){
        UserEntity userEntity = repository.findUserByEmail(email).get(0);
        assertThat("userEntity should not be null.", userEntity, is(not(nullValue())));
        assertThat("retrived incorrect userEntity", userEntity.getUserEmail(), is(email));

    }

    @Test
    public void testFindUserByPhone(){
        UserEntity userEntity = repository.findUserByEmail(phone).get(0);
        assertThat("userEntity should not be null.", userEntity, is(not(nullValue())));
        assertThat("retrived incorrect userEntity", userEntity.getPhone(), is(phone));

    }

}
