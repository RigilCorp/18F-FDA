package com.rigil.fda.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;


public class AuthenticationServiceTest extends AbstractBaseTest {
	
	@Autowired
	AuthenticationService service;
	
	@Test
	public void testAuthenticateUserSuccess(){		
		String result = service.authenticateUser(email, email);
		assertThat("result should not be null.", result, is(not(nullValue())));
		assertEquals(result, "SUCCESS");
	}
		
	@Test
	public void testAuthenticateUserFailed(){		
		String result = service.authenticateUser(email, "password");
		assertThat("result should not be null.", result, is(not(nullValue())));
		assertEquals(result, "FAILED");
	}
	
	@Test
	public void testAuthenticateUserInvalid(){		
		String result = service.authenticateUser("email", "password");
		assertThat("result should not be null.", result, is(not(nullValue())));
		assertEquals(result, "INVALID");
	}
}
