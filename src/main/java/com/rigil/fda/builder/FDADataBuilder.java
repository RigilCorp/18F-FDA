package com.rigil.fda.builder;

import com.rigil.fda.dao.entity.FDAData;
import com.rigil.fda.json.FDADataRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FDADataBuilder {
	
	private final static Logger logger = LoggerFactory.getLogger(FDADataBuilder.class);
	
	/**
	 * @param fdaDataRequest.
	 * @return  FDAData
	 * This method converts Enterprise Document User object into User Entity object. 
	 */

	public FDAData parseEnterpiseDocumentFDAData(FDADataRequest FDADataRequest) {
		
		logger.debug("PareseEnterpriseDocumentFDAData method invoked.");
		FDAData fdaData = new FDAData();
		fdaData.setDataCode(FDADataRequest.getdataCode());
		fdaData.setDataName(FDADataRequest.getdataName());
		fdaData.setDataDescription(FDADataRequest.getDataDesc());		
		return fdaData;
	}
	

	/**
	 * 
	 * This method converts FDAData Entity object into Enterprise Document FDAData object. 
	 */
	public com.rigil.fda.json.FDAData generateEnterpriseFDAData(FDAData fdaData) {
		
		logger.debug("GenerateEnterpriseUser method invoked.");
		
		com.rigil.fda.json.FDAData fdaDataJson = new com.rigil.fda.json.FDAData();
		fdaDataJson.setDataCode(fdaData.getDataCode());
		fdaDataJson.setDataName(fdaData.getDataName());
		fdaDataJson.setDataDesc(fdaData.getDataDescription());	
		return fdaDataJson;
	}


}
