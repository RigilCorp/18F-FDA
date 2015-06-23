package com.rigil.fda.service;

import java.util.List;

import com.rigil.fda.dao.entity.FDAData;

public interface FDADataService {	
	
	public FDAData getFDAData(String dataName, String dataCode);
	public List<FDAData> getDevicesData();
	public List<FDAData> getDrugData();
	public List<FDAData> getFoodData();
	public boolean addFDAData(String dataCode, String dataName, String dataDesc);
	public FDAData save(FDAData fdaData);
	
}
