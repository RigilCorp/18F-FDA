package com.rigil.fda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.dao.entity.Preference;
import com.rigil.fda.dao.entity.User;
import com.rigil.fda.json.FDADataResponse;
import com.rigil.fda.json.FDADeviceEnforcementResult;
import com.rigil.fda.json.FDADeviceEventResult;
import com.rigil.fda.json.event.Device;
import com.rigil.fda.json.event.FDADeviceResponse;
import com.rigil.fda.json.event.Result;
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
	
	private int threadDelay = 10;	
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PreferencesRepository preferencesRepo;


	@Override
	public User findByEmail(String email) {
		logger.debug("Query User by [Email: {}]", email);
		List<User> userList = repository.findUserByEmail(email);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public User findByPhone(String phone) {
		logger.debug("Query User by [Phone: {}]", phone);
		List<User> userList = repository.findUserByEmail(phone);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
	
	@Override
	@Transactional(readOnly=false)
	public User save(User user) {
		logger.debug("Saving user object [{}]", user);
		return repository.save(user);
		
	}

	@Override
	public void alertNotifications(final String email) {
		if(userList.contains(email))
		{
			System.out.println("There is a thread already running for the User");
		}else{
			System.out.println("First request for the User so start a new Thread");
			ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2); 
			scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {					
					sendAlertNotifications(email);
				}
			}, threadDelay, threadDelay, TimeUnit.SECONDS);
			userList.add(email);
			System.out.println("Successfully started a new Thread for User - " + email);
		}		
	}
	
	public void sendAlertNotifications(String email)
	{
		System.out.println("Sending Alert Notifications for " + email);
		
		List<Preference> preferencesList = preferencesRepo.findPreferencesByEmail(email);
		List<com.rigil.fda.json.Preference> preferencesJsonList = new ArrayList<com.rigil.fda.json.Preference>();
		for(Preference preference: preferencesList)
		{
			if(preference != null)
			{
				StringBuffer emailMsgSB = new StringBuffer();
				String dataName = preference.getFdaData().getDataName();
				StringBuilder uriSB = new StringBuilder();
				uriSB.append("https://api.fda.gov/device/event.json?search=device.generic_name:\"");
				uriSB.append(dataName);
				uriSB.append("\"&limit=1");
				System.out.println("uriSB - "+ uriSB.toString());
				RestTemplate restTemplate = new RestTemplate();
		        FDADeviceResponse fdaDeviceResponse = restTemplate.getForObject(uriSB.toString(), FDADeviceResponse.class);
		        List<Result> resultsList = fdaDeviceResponse.getResults();
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
		        mailService.sendMail("ravi@rigil.com", "Alert Notification - " + dataName, emailMsgSB.toString());
			}
		}
	}
	
}
	