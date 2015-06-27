package com.rigil.fda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.rigil.fda.dao.entity.PreferenceEntity;
import com.rigil.fda.dao.entity.UserEntity;
import com.rigil.fda.json.PreferenceJsonDataObject;
import com.rigil.fda.json.event.Device;
import com.rigil.fda.json.event.FDADeviceResponse;
import com.rigil.fda.json.event.Result;
import com.rigil.fda.json.report.FDADeviceEnforcementResponse;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Transactional(readOnly=true)
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private List<String> userList = new ArrayList<String>();
    private ScheduledFuture<?> scheduledFuture;

    private int threadDelay = 86400;

    @Autowired
    UserRepository repository;

    @Autowired
    MailService mailService;

    @Autowired
    PreferencesRepository preferencesRepo;


    @Override
    public UserEntity findByEmail(String email) {
        logger.debug("Query UserEntity by [Email: {}]", email);
        List<UserEntity> userEntityList = repository.findUserByEmail(email);
        if(userEntityList != null && userEntityList.size() > 0)
            return userEntityList.get(0);
        else
            return null;
    }

    @Override
    public UserEntity findByPhone(String phone) {
        logger.debug("Query UserEntity by [Phone: {}]", phone);
        List<UserEntity> userEntityList = repository.findUserByPhone(phone);
        if(userEntityList != null && userEntityList.size() > 0)
            return userEntityList.get(0);
        else
            return null;
    }

    @Override
    @Transactional(readOnly=false)
    public UserEntity save(UserEntity userEntity) {
        logger.debug("Saving userEntity object [{}]", userEntity);
        return repository.save(userEntity);

    }

    @Override
    public void alertNotifications(final String email) {
        if(userList.contains(email))
        {
            logger.debug("There is a thread already running for the UserEntity");
        }else{
            logger.debug("First request for the UserEntity so start a new Thread");
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                    sendAlertNotifications(email);
                }
            }, 0, threadDelay, TimeUnit.SECONDS);
            userList.add(email);
            logger.debug("Successfully started a new Thread for UserEntity - " + email);
        }
    }

    public void sendAlertNotifications(String email)
    {
        logger.debug("Sending Alert Notifications for " + email);

        List<PreferenceEntity> preferencesList = preferencesRepo.findPreferencesByEmail(email);
        List<PreferenceJsonDataObject> preferencesJsonList = new ArrayList<PreferenceJsonDataObject>();
        for(PreferenceEntity preferenceEntity : preferencesList)
        {
            if(preferenceEntity != null)
            {
                sendAdverseEventAlertNotifications(email, preferenceEntity.getFdaDataEntity().getDataName());
                sendEnforcementReportAlertNotifications(email, preferenceEntity.getFdaDataEntity().getDataName());
            }
        }
    }

    public void sendAdverseEventAlertNotifications(String email, String deviceName)
    {
        StringBuffer emailMsgSB = new StringBuffer();
        StringBuilder uriSB = new StringBuilder();
        uriSB.append("https://api.fda.gov/device/event.json?search=device.generic_name:\"");
        uriSB.append(deviceName);
        uriSB.append("\"&limit=1`");
        logger.debug("uriSB - "+ uriSB.toString());
        RestTemplate restTemplate = new RestTemplate();
        try{
            List<Result> resultsList = restTemplate.getForObject(uriSB.toString(), FDADeviceResponse.class).getResults();

            //List<Result> resultsList = fdaDeviceResponse.getResults();
            for(Result result : resultsList)
            {
                List<Device> deviceList = result.getDevice();
                Device device = deviceList.get(0);
                logger.debug("manufacturer_name - " + device.getManufacturerDName());
                emailMsgSB.append("\n" + "Manufacturer Name: " + device.getManufacturerDName());
                logger.debug("generic_name: " + device.getGenericName());
                emailMsgSB.append("\n" + "Generic Name: " + device.getGenericName());
                logger.debug("model_number: " + device.getModelNumber());
                emailMsgSB.append("\n" + "Model Number: " + device.getModelNumber());
                logger.debug("event_type - " + result.getEventType());
                emailMsgSB.append("\n" + "Event Type: " + result.getEventType());
                logger.debug("date_of_event - " + result.getDateOfEvent());
                emailMsgSB.append("\n" + "Date of Event: " + result.getDateOfEvent());
            }
            emailMsgSB.append("\n\n\n" + "Click on the following link to view more details - http://localhost:8080/rigil-18f/#/preferenceEntity/Device/");
            emailMsgSB.append(deviceName);
            mailService.sendMail(email, "Adverse Event Alert Notification - " + deviceName, emailMsgSB.toString());
        }catch(Exception e)
        {
            logger.error("Error while querying the FDA Adverse Event Web Service for Device - " + deviceName, e);
            //e.printStackTrace();
        }
    }

    public void sendEnforcementReportAlertNotifications(String email, String deviceName)
    {
        StringBuffer emailMsgSB = new StringBuffer();
        StringBuilder uriSB = new StringBuilder();
        uriSB.append("https://api.fda.gov/device/enforcement.json?search=product_description:\"");
        uriSB.append(deviceName);
        uriSB.append("\"&limit=1`");
        logger.debug("uriSB - "+ uriSB.toString());
        RestTemplate restTemplate = new RestTemplate();
        try{
            List<com.rigil.fda.json.report.Result> resultsList = restTemplate.getForObject(uriSB.toString(), FDADeviceEnforcementResponse.class).getResults();
            for(com.rigil.fda.json.report.Result result : resultsList)
            {
                logger.debug("recalling_firm - " + result.getRecallingFirm());
                emailMsgSB.append("\n" + "Recalling Firm: " + result.getRecallingFirm());
                logger.debug("product_description: " + result.getProductDescription());
                emailMsgSB.append("\n" + "Product Description: " + result.getProductDescription());
                logger.debug("reason_for_recall: " + result.getReasonForRecall());
                emailMsgSB.append("\n" + "Reason for Recall: " + result.getReasonForRecall());
                logger.debug("recall_initiation_date - " + result.getRecallInitiationDate());
                emailMsgSB.append("\n" + "Recall Initiation Date: " + result.getRecallInitiationDate());
            }
            emailMsgSB.append("\n\n\n" + "Click on the following link to view more details - http://localhost:8080/rigil-18f/#/preferenceEntity/Device/");
            emailMsgSB.append(deviceName);
            mailService.sendMail(email, "Enforcement Report Alert Notification for - " + deviceName, emailMsgSB.toString());
        }catch(Exception e)
        {
            logger.error("Error while querying the FDA Enforcment Report Web Service for Device - " + deviceName, e);
            //e.printStackTrace();
        }
    }

}
