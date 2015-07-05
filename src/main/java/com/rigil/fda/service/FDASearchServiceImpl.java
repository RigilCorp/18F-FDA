package com.rigil.fda.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.count.event.FDAEventCountResponse;

public class FDASearchServiceImpl implements FDASearchService{

	@Autowired
	UserBuilder userBuilder;
	
	@Override
	public FDADataResponse getFDASearchResponse(String dataName) {		
		return userBuilder.getFDADeviceResponse(dataName);
	}

	@Override
	public FDAEventCountResponse getFDADeviceEventCount() {
		return userBuilder.getFDADeviceCountResponse();
	}

}
