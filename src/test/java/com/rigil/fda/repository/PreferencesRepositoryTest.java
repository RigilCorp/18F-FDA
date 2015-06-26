package com.rigil.fda.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import com.rigil.fda.dao.entity.PreferenceEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;


public class PreferencesRepositoryTest extends AbstractBaseTest {

    @Autowired
    PreferencesRepository repository;

    @Test
    public void testFindPreferencesByEmail(){
        PreferenceEntity preferenceEntity = repository.findPreferencesByEmail(email).get(0);
        assertThat("preferenceEntity should not be null.", preferenceEntity, is(not(nullValue())));

    }


}
