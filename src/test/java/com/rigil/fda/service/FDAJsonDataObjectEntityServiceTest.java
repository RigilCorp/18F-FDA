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
		FDADataEntity aFDAData = service.getFDAData(fdaDeviceName, fdaDataType);
		assertThat("FDAData should not be null.",aFDAData, is(not(nullValue())));		
	}
	
	@Test
	public void testGetDevicesData(){		
		FDADataEntity aFDAData = service.getDevicesData().get(0);
		assertThat("FDA Device Data should not be null.",aFDAData, is(not(nullValue())));		
	}
	
	@Test
	public void testGetFoodData(){		
		FDADataEntity aFDAData = service.getFoodData().get(0);
		assertThat("FDA Device Data should not be null.",aFDAData, is(not(nullValue())));		
	}
	
	@Test
	public void testGetDrugData(){		
		FDADataEntity aFDAData = service.getDrugData().get(0);
		assertThat("FDA Device Data should not be null.",aFDAData, is(not(nullValue())));		
	}
	
}
