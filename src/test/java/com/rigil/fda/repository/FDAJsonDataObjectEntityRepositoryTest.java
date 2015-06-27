package com.rigil.fda.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;
import com.rigil.fda.dao.entity.FDADataEntity;

public class FDAJsonDataObjectEntityRepositoryTest extends AbstractBaseTest{
	
	@Autowired
	FDADataRepository repository;
	
	@Test
	public void testFindFDADataByCode(){
		FDADataEntity aFDAData = repository.findFDADataByCode(fdaDataType).get(0);
		assertThat("FDA Data is not null.",aFDAData, is(not(nullValue())));		
	}
	
	@Test
	public void testFindFDADataBName(){
		FDADataEntity aFDAData = repository.findFDADataByName(fdaDeviceName).get(0);
		assertThat("FDA Data is not null.",aFDAData, is(not(nullValue())));		
	}
	
	@Test
	public void testFindFDADataBNameAndCode(){
		FDADataEntity aFDAData = repository.findFDADataByNameAndCode(fdaDeviceName, fdaDataType);
		assertThat("FDA Data is not null.",aFDAData, is(not(nullValue())));		
	}
	

}
