package com.rigil.fda.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.dao.entity.FDAData;


public class FDADataRepositoryTest {
	
	@Autowired
	FDADataRepository repository;
	
	@Test
	public void testFindFDADataByCode(){
		FDAData aFDAData = repository.findFDADataByCode("Food").get(0);
		Assert.assertTrue(aFDAData == null);

	}
	

}
