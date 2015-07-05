package com.rigil.fda.service;

import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.count.event.FDAEventCountResponse;

public interface FDASearchService {
	
	FDADataResponse getFDASearchResponse(String dataName);
	FDAEventCountResponse getFDADeviceEventCount();
	

}
