
package com.rigil.fda.builder;

import com.rigil.fda.dao.entity.FDAData;
import com.rigil.fda.dao.entity.Preference;
import com.rigil.fda.dao.entity.User;
import com.rigil.fda.repository.FDADataRepository;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.MailService;
import com.rigil.fda.support.UserEnterpriseDocumentSupport;
import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.FDADeviceEnforcementResult;
import com.rigil.fda.json.FDADeviceEventResult;
import com.rigil.fda.json.UserRequest;
import com.rigil.fda.json.event.Device;
import com.rigil.fda.json.event.FDADeviceResponse;
import com.rigil.fda.json.event.Result;
import com.rigil.fda.json.report.FDADeviceEnforcementResponse;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public class UserBuilder {
	
	private final static Logger logger = LoggerFactory.getLogger(UserBuilder.class);
	

	@Qualifier("dateFormatter")
	@Autowired
	DateFormat dateFormatter;
	
	@Autowired
	PreferencesRepository preferencesRepo;
	
	@Autowired
	FDADataRepository fdaDataRepo;
	
	@Autowired
	MailService mailService;
	
	/**
	 * @param userRequest.
	 * @return  User
	 * This method converts Enterprise Document User object into User Entity object. 
	 */

	public User parseEnterpiseDocumentUser(UserRequest userRequest) {
		
		logger.debug("PareseEnterpriseDocumentUser method invoked.");
		User user = new User();
		
		user.setUserEmail(userRequest.getEmail());
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setPhone(userRequest.getPhone());
		user.setZipcode(userRequest.getZipcode());
		
		return user;
	}
	
	
	/**
	 * @param userRequest.
	 * @return  User
	 * This method converts Enterprise Document Preference object into Preference Entity object. 
	 */

	public Preference parseEnterpiseDocumentUserPreference(User user, com.rigil.fda.json.Preference preference) {		
		logger.debug("parseEnterpiseDocumentUserPreference method invoked.");
		Preference preferenceEntity = new Preference();
		String dataName = preference.getFdaData().getDataName();
		List<FDAData> fdaDataList = fdaDataRepo.findFDADataByName(dataName);
		if(fdaDataList.size() > 0)
		{
			FDAData fdaData = fdaDataList.get(0);
			preferenceEntity.setPreferenceUser(user);
			preferenceEntity.setFdaData(fdaData);
		}		
		return preferenceEntity;
	}
	
	/**
	 * 
	 * This method converts User Entity object into Enterprise Document User object. 
	 */
	public com.rigil.fda.json.User generateEnterpiseUser(User user) {
		
		logger.debug("GenerateEnterpriseUser method invoked.");
		
		com.rigil.fda.json.User jsonUser = new com.rigil.fda.json.User();
		
		jsonUser.setFirstName(user.getFirstName());
		jsonUser.setMiddleName(user.getMiddleName());
		jsonUser.setLastName(user.getLastName());
		jsonUser.setUserEmail(user.getUserEmail());
		jsonUser.setPhone(user.getPhone());
		jsonUser.setZipcode(user.getZipcode());		
		
		List<Preference> preferencesList = new ArrayList<Preference>();
		preferencesList = preferencesRepo.findPreferencesByEmail(user.getUserEmail());
		List<com.rigil.fda.json.Preference> preferencesJsonList = new ArrayList<com.rigil.fda.json.Preference>();
		for(Preference preference: preferencesList)
		{
			if(preference != null)
			{
				com.rigil.fda.json.Preference preferenceJson = new com.rigil.fda.json.Preference();
				com.rigil.fda.json.FDAData fdaData = new com.rigil.fda.json.FDAData();
				fdaData.setDataName(preference.getFdaData().getDataName());
				fdaData.setDataCode(preference.getFdaData().getDataCode());
				fdaData.setDataDesc(preference.getFdaData().getDataDescription());
				preferenceJson.setFdaData(fdaData);
				preferenceJson.setFdaResponse(getFDADeviceResponse(preference.getFdaData().getDataName()));
				preferencesJsonList.add(preferenceJson);
			}
		}
		jsonUser.setPreferencesList(preferencesJsonList);
		
	
		return jsonUser;
	}
	
	private FDADataResponse getFDADeviceResponse(String dataName)
	{
		FDADataResponse fdaDataResponse = new FDADataResponse();
		List<FDADeviceEventResult> eventResulstList = getAdverseEventsList(dataName);
		List<FDADeviceEnforcementResult> enforcementResultsList = getEnforcementReportsList(dataName);
        fdaDataResponse.setEventResultsList(eventResulstList);
        fdaDataResponse.setEnforcementResultsList(enforcementResultsList);
        return fdaDataResponse;
	}
	
	public List<FDADeviceEventResult> getAdverseEventsList(String deviceName)
	{
		List<FDADeviceEventResult> eventResultsList = new ArrayList<FDADeviceEventResult>();
		StringBuilder uriSB = new StringBuilder();
		uriSB.append("https://api.fda.gov/device/event.json?search=device.generic_name:\"");
		uriSB.append(deviceName);
		uriSB.append("\"&limit=1");
		logger.debug("uriSB - "+ uriSB.toString());
		//uriSB.append("https://api.fda.gov/device/event.json?search=device.generic_name:x-ray&limit=1");
        RestTemplate restTemplate = new RestTemplate();
        try{
	        FDADeviceResponse fdaDeviceResponse = restTemplate.getForObject(uriSB.toString(), FDADeviceResponse.class);
	        List<Result> resultsList = fdaDeviceResponse.getResults();
	        for(Result result : resultsList)
	        {
	        	FDADeviceEventResult deviceEventResult = new FDADeviceEventResult();
	        	List<Device> deviceList = result.getDevice();
	            Device device = deviceList.get(0);
	            logger.debug("manufacturer_name - " + device.getManufacturerDName());
	            deviceEventResult.setManufacturerName(device.getManufacturerDName());
	            logger.debug("generic_name - " + device.getGenericName());
	            deviceEventResult.setGenericName(device.getGenericName());
	            logger.debug("model_number - " + device.getModelNumber());
	            deviceEventResult.setModelNumber(device.getModelNumber());
	            logger.debug("event_type - " + result.getEventType());
	            deviceEventResult.setEventType(result.getEventType());
	            logger.debug("date_of_event - " + result.getDateOfEvent());
	            deviceEventResult.setDateOfEvent(result.getDateOfEvent());
	            eventResultsList.add(deviceEventResult);	            
	        }

        }catch(Exception e)
        {
        	logger.error("Error while querying the FDA Adverse Report Web Service for Device - " + deviceName, e);
        	//e.printStackTrace();
        }		
		return eventResultsList;
	}
	
	public List<FDADeviceEnforcementResult> getEnforcementReportsList(String deviceName)
	{
		List<FDADeviceEnforcementResult> enforcementsResultsList = new ArrayList<FDADeviceEnforcementResult>();
		StringBuilder uriSB = new StringBuilder();
		uriSB.append("https://api.fda.gov/device/enforcement.json?search=product_description:\"");
		uriSB.append(deviceName);
		uriSB.append("\"&limit=1");
		logger.debug("uriSB - "+ uriSB.toString());
	    RestTemplate restTemplate = new RestTemplate();
        try{
			List<com.rigil.fda.json.report.Result> resultsList = restTemplate.getForObject(uriSB.toString(), FDADeviceEnforcementResponse.class).getResults();
	        for(com.rigil.fda.json.report.Result result : resultsList)
	        {
	        	FDADeviceEnforcementResult enforcementResult = new FDADeviceEnforcementResult();
	        	logger.debug("recalling_firm - " + result.getRecallingFirm());
	        	enforcementResult.setRecallingFirm(result.getRecallingFirm());
	        	logger.debug("product_description: " + result.getProductDescription());
	            enforcementResult.setProductDescription(result.getProductDescription());
	            logger.debug("reason_for_recall: " + result.getReasonForRecall());
	            enforcementResult.setReasonForRecall(result.getReasonForRecall());
	            logger.debug("recall_initiation_date - " + result.getRecallInitiationDate());
	            enforcementResult.setRecallInitiationDate(result.getRecallInitiationDate());	  
	            enforcementsResultsList.add(enforcementResult);
		     }
        }catch(Exception e)
        {
        	logger.error("Error while querying the FDA Enforcement Report Web Service for Device - " + deviceName, e);
        	//e.printStackTrace();
        }
		return enforcementsResultsList;
	}

	/**
	 * 
	 * @param user
	 * @param enterpriseRequestUser
	 * @param criteria
	 * @return entity User
	 * this method updates entity user base on criteria.
	 */
	public User updateUserFromRequestUser(User user, com.rigil.fda.json.UserRequest userRequest, Set<String> criteria) {
		
		logger.debug("updateUserFromRequestUser method invoked.");
	
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_EMAIL)){
			user.setUserEmail(userRequest.getEmail());
		}
	
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_PHONE)){
			user.setPhone(userRequest.getPhone());
		}
		
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_FIRST_NAME)){
			user.setFirstName(userRequest.getFirstName());
		}
	
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_LAST_NAME)){
			user.setLastName(userRequest.getLastName());
		}
		
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_MIDDLE_NAME)){
			user.setMiddleName(userRequest.getMiddleName());
		}
	
		if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL) 
				|| criteria.contains(UserEnterpriseDocumentSupport.USER_ZIPCODE)){
			user.setZipcode(userRequest.getZipcode());
		}
		
		return user;
	}

}
