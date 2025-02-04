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
		UserEntity user = repository.findUserByEmail(email).get(0);
		assertThat("user should not be null.",user, is(not(nullValue())));
		assertThat("retrived incorrect user", user.getUserEmail(), is(email));

	}
	
	@Test
	public void testFindUserByPhone(){
		UserEntity user = repository.findUserByPhone(phone).get(0);
		assertThat("user should not be null.",user, is(not(nullValue())));
		assertThat("retrived incorrect user", user.getPhone(), is(phone));

	}

}
