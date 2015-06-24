package com.rigil.fda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.dao.entity.FDAData;
import com.rigil.fda.repository.FDADataRepository;

public class FDADataServiceImpl implements FDADataService{
	
	private static final Logger logger = LoggerFactory.getLogger(FDADataServiceImpl.class);

	@Autowired
	FDADataRepository fdaDataRepo;
	
	@Override
	public List<FDAData> getDevicesData() {
		List<FDAData> devicesList = new ArrayList<FDAData>();
		devicesList = fdaDataRepo.findFDADataByCode("DEVICE");
		return devicesList;
	}

	@Override
	public List<FDAData> getDrugData() {
		List<FDAData> drugsList = new ArrayList<FDAData>();
		drugsList = fdaDataRepo.findFDADataByCode("DRUG");
		return drugsList;
	}

	@Override
	public List<FDAData> getFoodData() {
		List<FDAData> foodList = new ArrayList<FDAData>();
		foodList = fdaDataRepo.findFDADataByCode("FOOD");
		return foodList;
	}

	@Override
	public boolean addFDAData(String dataCode, String dataName, String dataDesc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FDAData getFDAData(String dataName, String dataCode) {
		logger.debug("getFDAData object [{}]", dataName);
		FDAData fdaData = null;
		fdaData = fdaDataRepo.findFDADataByNameAndCode(dataName, dataCode);
		return fdaData;
	}

	@Override
	public FDAData save(FDAData fdaData) {
		logger.debug("Saving user object [{}]", fdaData);
		return fdaDataRepo.save(fdaData);
	}

}
