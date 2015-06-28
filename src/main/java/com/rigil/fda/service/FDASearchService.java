package com.rigil.fda.service;

import com.rigil.fda.json.FDADataResponse;

public interface FDASearchService {
	
	FDADataResponse getFDASearchResponse(String dataName);

}
