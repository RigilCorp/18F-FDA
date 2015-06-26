
package com.rigil.fda.builder;

import com.rigil.fda.dao.entity.FDADataEntity;
import com.rigil.fda.dao.entity.PreferenceEntity;
import com.rigil.fda.dao.entity.UserEntity;
import com.rigil.fda.json.FDAJsonDataObject;
import com.rigil.fda.json.PreferenceJsonDataObject;
import com.rigil.fda.json.UserRequestJsonDataObject;
import com.rigil.fda.repository.FDADataRepository;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.MailService;
import com.rigil.fda.support.UserEnterpriseDocumentSupport;
import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.FDADeviceEnforcementResult;
import com.rigil.fda.json.FDADeviceEventResult;
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
     * @param userRequestJsonDataObject
     * @return  UserEntity
     * This method converts Enterprise Document UserEntity object into UserEntity Entity object.
     */

    public UserEntity parseEnterpiseDocumentUser(UserRequestJsonDataObject userRequestJsonDataObject) {

        logger.debug("PareseEnterpriseDocumentUser method invoked.");
        UserEntity userEntity = new UserEntity();

        userEntity.setUserEmail(userRequestJsonDataObject.getEmail());
        userEntity.setFirstName(userRequestJsonDataObject.getFirstName());
        userEntity.setMiddleName(userRequestJsonDataObject.getMiddleName());
        userEntity.setLastName(userRequestJsonDataObject.getLastName());
        userEntity.setPhone(userRequestJsonDataObject.getPhone());
        userEntity.setZipcode(userRequestJsonDataObject.getZipcode());

        return userEntity;
    }


    /**
     * @param userEntity
     * @param preferenceJsonDataObject
     * @return UserEntity
     * This method converts Enterprise Document PreferenceEntity object into PreferenceEntity Entity object.
     */

    public PreferenceEntity parseEnterpriseDocumentUserPreference(UserEntity userEntity, PreferenceJsonDataObject preferenceJsonDataObject) {
        logger.debug("parseEnterpriseDocumentUserPreference method invoked.");
        PreferenceEntity preferenceEntityEntity = new PreferenceEntity();
        String dataName = preferenceJsonDataObject.getFdaJsonDataObject().getDataName();
        List<FDADataEntity> fdaDataEntityList = fdaDataRepo.findFDADataByName(dataName);
        if(fdaDataEntityList.size() > 0)
        {
            FDADataEntity fdaDataEntity = fdaDataEntityList.get(0);
            preferenceEntityEntity.setPreferenceUserEntity(userEntity);
            preferenceEntityEntity.setFdaDataEntity(fdaDataEntity);
        }
        return preferenceEntityEntity;
    }

    /**
     *
     * This method converts UserEntity Entity object into Enterprise Document UserEntity object.
     */
    public com.rigil.fda.json.User generateEnterpriseUser(UserEntity userEntity) {

        logger.debug("GenerateEnterpriseUser method invoked.");

        com.rigil.fda.json.User jsonUser = new com.rigil.fda.json.User();

        jsonUser.setFirstName(userEntity.getFirstName());
        jsonUser.setMiddleName(userEntity.getMiddleName());
        jsonUser.setLastName(userEntity.getLastName());
        jsonUser.setUserEmail(userEntity.getUserEmail());
        jsonUser.setPhone(userEntity.getPhone());
        jsonUser.setZipcode(userEntity.getZipcode());

        List<PreferenceEntity> preferencesList;
        preferencesList = preferencesRepo.findPreferencesByEmail(userEntity.getUserEmail());
        List<PreferenceJsonDataObject> preferencesJsonList = new ArrayList<PreferenceJsonDataObject>();
        for(PreferenceEntity preferenceEntity : preferencesList)
        {
            if(preferenceEntity != null)
            {
                PreferenceJsonDataObject preferenceJsonDataObjectJson = new PreferenceJsonDataObject();
                FDAJsonDataObject fdaJsonDataObject = new FDAJsonDataObject();
                fdaJsonDataObject.setDataName(preferenceEntity.getFdaDataEntity().getDataName());
                fdaJsonDataObject.setDataCode(preferenceEntity.getFdaDataEntity().getDataCode());
                fdaJsonDataObject.setDataDesc(preferenceEntity.getFdaDataEntity().getDataDescription());
                preferenceJsonDataObjectJson.setFdaJsonDataObject(fdaJsonDataObject);
                preferenceJsonDataObjectJson.setFdaResponse(getFDADeviceResponse(preferenceEntity.getFdaDataEntity().getDataName()));
                preferencesJsonList.add(preferenceJsonDataObjectJson);
            }
        }
        jsonUser.setPreferencesList(preferencesJsonList);

        return jsonUser;
    }

    private FDADataResponse getFDADeviceResponse(String dataName)
    {
        FDADataResponse fdaDataResponse = new FDADataResponse();
        List<FDADeviceEventResult> eventResultsList = getAdverseEventsList(dataName);
        List<FDADeviceEnforcementResult> enforcementResultsList = getEnforcementReportsList(dataName);
        fdaDataResponse.setEventResultsList(eventResultsList);
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
     * @param userEntity
     * @param userRequestJsonDataObject
     * @param criteria
     * @return entity UserEntity
     * this method updates entity userEntity base on criteria.
     */
    public UserEntity updateUserFromRequestUser(UserEntity userEntity, UserRequestJsonDataObject userRequestJsonDataObject, Set<String> criteria) {

        logger.debug("updateUserFromRequestUser method invoked.");

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_EMAIL)){
            userEntity.setUserEmail(userRequestJsonDataObject.getEmail());
        }

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_PHONE)){
            userEntity.setPhone(userRequestJsonDataObject.getPhone());
        }

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_FIRST_NAME)){
            userEntity.setFirstName(userRequestJsonDataObject.getFirstName());
        }

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_LAST_NAME)){
            userEntity.setLastName(userRequestJsonDataObject.getLastName());
        }

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_MIDDLE_NAME)){
            userEntity.setMiddleName(userRequestJsonDataObject.getMiddleName());
        }

        if(criteria.contains(UserEnterpriseDocumentSupport.USER_ALL)
                || criteria.contains(UserEnterpriseDocumentSupport.USER_ZIPCODE)){
            userEntity.setZipcode(userRequestJsonDataObject.getZipcode());
        }

        return userEntity;
    }

}
