package com.rigil.fda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.count.event.FDAEventCountResponse;
import com.rigil.fda.service.FDASearchService;

@RestController
@RequestMapping("/search")
public class FDASearchRestController {

	@Autowired
	FDASearchService fdaSearchService;
	
	@RequestMapping("/device/{deviceName}")
	public FDADataResponse getFDADeviceSearchResponse(
			@PathVariable String deviceName) {
		return fdaSearchService.getFDASearchResponse(deviceName);
	}

	@RequestMapping("/count")
	public FDAEventCountResponse getFDADeviceCount() {
		return fdaSearchService.getFDADeviceEventCount();
	}	

}
