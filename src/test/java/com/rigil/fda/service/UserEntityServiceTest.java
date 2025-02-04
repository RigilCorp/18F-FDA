package com.rigil.fda.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
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
		UserEntity user = service.findByEmail(email);
		assertThat("user should not be null.",user, is(not(nullValue())));
		assertThat("retrived incorrect user", user.getUserEmail(), is(email));

	}
	
	@Test
	public void testFindUserByPhone(){		
		UserEntity user = service.findByPhone(phone);
		assertThat("user should not be null.",user, is(not(nullValue())));
		assertThat("retrived incorrect user", user.getPhone(), is(phone));

	}

}
