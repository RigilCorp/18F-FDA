package com.rigil.fda.builder;

import com.rigil.fda.dao.entity.FDAData;
import com.rigil.fda.dao.entity.Preference;
import com.rigil.fda.dao.entity.User;
import com.rigil.fda.repository.FDADataRepository;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.support.UserEnterpriseDocumentSupport;
import com.rigil.fda.json.UserRequest;

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
			System.out.println("FDA Data - " + fdaData.getDataName());
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
				preferenceJson.setFdaResponse("FDA Response");
				//preferenceJson.setFdaResponse(getFDAResponse(preference.getFdaData().getDataName()));
				preferencesJsonList.add(preferenceJson);
			}
		}
		jsonUser.setPreferencesList(preferencesJsonList);
		
	
		return jsonUser;
	}
	
	private String getFDAResponse(String dataName)
	{
		StringBuilder uriSB = new StringBuilder();
		uriSB.append("https://api.fda.gov/drug/event.json?search=patient.drug.medicinalproduct:\"");
		uriSB.append(dataName);
		uriSB.append("\"&limit=10");
        RestTemplate restTemplate = new RestTemplate();
        String fdaResponse = restTemplate.getForObject(uriSB.toString(), String.class);
        return fdaResponse;
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
