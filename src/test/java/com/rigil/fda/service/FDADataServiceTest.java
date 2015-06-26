package com.rigil.fda.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.FDAData;


public class FDADataServiceTest extends AbstractBaseTest {
	
	@Autowired
	FDADataService service;
	
	@Test
	public void testGetFDAData(){		
		FDAData aFDAData = service.getFDAData("Tylenol", "Drug");
		assertThat("FDAData should not be null.",aFDAData, is(not(nullValue())));		
	}
	


}
