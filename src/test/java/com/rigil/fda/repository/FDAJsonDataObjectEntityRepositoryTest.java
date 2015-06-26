package com.rigil.fda.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.dao.entity.FDADataEntity;


public class FDAJsonDataObjectEntityRepositoryTest {

    @Autowired
    FDADataRepository repository;

    @Test
    public void testFindFDADataByCode(){
        FDADataEntity aFDADataEntity = repository.findFDADataByCode("Food").get(0);
        Assert.assertTrue(aFDADataEntity == null);

    }


}
