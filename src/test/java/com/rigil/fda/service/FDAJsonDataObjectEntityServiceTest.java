package com.rigil.fda.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.FDADataEntity;


public class FDAJsonDataObjectEntityServiceTest extends AbstractBaseTest {

    @Autowired
    FDADataService service;

    @Test
    public void testGetFDAData(){
        FDADataEntity aFDADataEntity = service.getFDAData("Tylenol", "Drug");
        assertThat("FDADataEntity should not be null.", aFDADataEntity, is(not(nullValue())));
    }



}
