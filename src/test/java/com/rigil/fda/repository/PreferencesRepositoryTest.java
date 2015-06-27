package com.rigil.fda.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.PreferenceEntity;

public class PreferencesRepositoryTest extends AbstractBaseTest {
	
	@Autowired
	PreferencesRepository repository;
	
	@Test
	public void testFindPreferencesByEmail(){
		PreferenceEntity preference = repository.findPreferencesByEmail(email).get(0);
		assertThat("preference should not be null.", preference, is(not(nullValue())));
	}
	
	@Test
	public void testFindPreferencesByPhone(){
		PreferenceEntity preference = repository.findPreferencesByPhone(phone).get(0);
		assertThat("preference should not be null.", preference, is(not(nullValue())));
	}
	
	@Test
	public void testFindPreferencesByEmailAndDataName(){
		PreferenceEntity preference = repository.findPreferencesByEmailAndDataName(email, fdaDeviceName).get(0);
		assertThat("preference should not be null.", preference, is(not(nullValue())));
	}
	

}
