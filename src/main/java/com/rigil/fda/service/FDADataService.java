package com.rigil.fda.service;

import java.util.List;

import com.rigil.fda.dao.entity.FDADataEntity;

public interface FDADataService {

    FDADataEntity getFDAData(String dataName, String dataCode);
    List<FDADataEntity> getDevicesData();
    List<FDADataEntity> getDrugData();
    List<FDADataEntity> getFoodData();
    boolean addFDAData(String dataCode, String dataName, String dataDesc);
    FDADataEntity save(FDADataEntity fdaDataEntity);

}
