package com.rigil.fda.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.json.FDADataResponse;

public class FDASearchServiceImpl implements FDASearchService{

	@Autowired
	UserBuilder userBuilder;
	
	@Override
	public FDADataResponse getFDASearchResponse(String dataName) {		
		return userBuilder.getFDADeviceResponse(dataName);
	}

}
