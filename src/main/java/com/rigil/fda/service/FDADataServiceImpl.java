package com.rigil.fda.service;

import java.util.ArrayList;
import java.util.List;

import com.rigil.fda.dao.entity.FDADataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.repository.FDADataRepository;

public class FDADataServiceImpl implements FDADataService{

    private static final Logger logger = LoggerFactory.getLogger(FDADataServiceImpl.class);

    @Autowired
    FDADataRepository fdaDataRepo;

    @Override
    public List<FDADataEntity> getDevicesData() {
        List<FDADataEntity> devicesList;
        devicesList = fdaDataRepo.findFDADataByCode("DEVICE");
        return devicesList;
    }

    @Override
    public List<FDADataEntity> getDrugData() {
        List<FDADataEntity> drugsList;
        drugsList = fdaDataRepo.findFDADataByCode("DRUG");
        return drugsList;
    }

    @Override
    public List<FDADataEntity> getFoodData() {
        List<FDADataEntity> foodList;
        foodList = fdaDataRepo.findFDADataByCode("FOOD");
        return foodList;
    }

    @Override
    public boolean addFDAData(String dataCode, String dataName, String dataDesc) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public FDADataEntity getFDAData(String dataName, String dataCode) {
        logger.debug("getFDAData object [{}]", dataName);
        FDADataEntity fdaDataEntity;
        fdaDataEntity = fdaDataRepo.findFDADataByNameAndCode(dataName, dataCode);
        return fdaDataEntity;
    }

    @Override
    public FDADataEntity save(FDADataEntity fdaDataEntity) {
        logger.debug("Saving user object [{}]", fdaDataEntity);
        return fdaDataRepo.save(fdaDataEntity);
    }

}
