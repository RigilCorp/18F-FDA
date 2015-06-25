package com.rigil.fda.support;

import com.rigil.common.exception.ServiceErrorCode;
import com.rigil.common.exception.ServiceException;
import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.dao.entity.User;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.UserService;
import com.rigil.fda.json.DocumentBody;
import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.fda.json.EnterpriseDocument_;
import com.rigil.fda.json.Preference;
import com.rigil.fda.json.Request;
import com.rigil.fda.json.Response;
import com.rigil.fda.json.ResponseMessage;
import com.rigil.fda.json.UserRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEnterpriseDocumentSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(UserEnterpriseDocumentSupport.class);

	public static final String USER_EMAIL = "email".toUpperCase();
	public static final String USER_FIRST_NAME = "firstName".toUpperCase();
	public static final String USER_MIDDLE_NAME = "middleName".toUpperCase();
	public static final String USER_LAST_NAME = "lastName".toUpperCase();
	public static final String USER_PHONE = "phone".toUpperCase();
	public static final String USER_ZIPCODE = "zipcode".toUpperCase();
	public static final String USER_ALL = "ALL";

	@Autowired
	UserBuilder userBuilder;

	@Autowired
	UserService userService;
	
	@Autowired
	PreferencesRepository preferencesRepo;

	public void processRequest(EnterpriseDocument enterpriseDocumentRequest,
			EnterpriseDocument enterpriseDocumentResponse) {

		enterpriseDocumentResponse
				.setEnterpriseDocument(new EnterpriseDocument_());
		enterpriseDocumentResponse.getEnterpriseDocument().setDocumentBody(
				new DocumentBody());
		enterpriseDocumentResponse.getEnterpriseDocument().getDocumentBody()
				.setResponse(new Response());
		enterpriseDocumentResponse.getEnterpriseDocument()
				.setDocumentTimeStamp(new Date());

		final String requestMethod = enterpriseDocumentRequest
				.getEnterpriseDocument().getDocumentBody().getRequest()
				.getRequestMethod().toUpperCase().trim();

		ResponseMessage responseMessage = null;

		switch (requestMethod) {
		case "GET": // handles GET request.

			logger.debug("Processing user GET request.");
			responseMessage = processGetRequest(enterpriseDocumentRequest
					.getEnterpriseDocument().getDocumentBody().getRequest());
			enterpriseDocumentResponse.getEnterpriseDocument()
					.getDocumentBody().getResponse()
					.setResponseMessage(responseMessage);
			break;
		case "PUT": // handles create[PUT] request.
			logger.debug("Processing user PUT request.");
			responseMessage = processPutRequest(enterpriseDocumentRequest
					.getEnterpriseDocument().getDocumentBody().getRequest());
			enterpriseDocumentResponse.getEnterpriseDocument()
					.getDocumentBody().getResponse()
					.setResponseMessage(responseMessage);
			break;
		case "UPDATE": // handles UPDATE request.
			logger.debug("Processing user UPDATE request.");
			responseMessage = processUpdateRequest(enterpriseDocumentRequest
					.getEnterpriseDocument().getDocumentBody().getRequest());
			enterpriseDocumentResponse.getEnterpriseDocument()
					.getDocumentBody().getResponse()
					.setResponseMessage(responseMessage);
			break;
		default:
			/*
			 * throw new EnterpriseDocumentException(
			 * String.format("RequestMethod: [%s] is not supported. ",
			 * requestMethod), EnterpriseDocumentException.ErrorCodeType.
			 * REQUEST_METHOD_NOT_SUPPORTED);
			 */
		}
				
		//userService.alertNotifications(enterpriseDocumentRequest
		//		.getEnterpriseDocument().getDocumentBody().getRequest().getRequestMessage().getUserRequest().getEmail());
	}

	/**
	 * 
	 * @param request
	 * @return ResponseMessage Process all GET requestMethod requests
	 */
	private ResponseMessage processGetRequest(Request request) {

		logger.debug("ProcessGetRequest method invoked to query user.");

		final Set<String> criteria = new HashSet<>(Arrays.asList(request
				.getRequestCriteria().replaceAll("\\s", "").toUpperCase()
				.split(",")));
		final UserRequest userRequest = request.getRequestMessage()
				.getUserRequest();

		User user = null;

		switch (criteria.size()) {
		case 1:

			if (criteria.contains("EMAIL")) {
				user = userService.findByEmail(userRequest.getEmail());
			} else if (criteria.contains("PHONE")) {
				user = userService.findByEmail(userRequest.getPhone());
			} else {
				throw new ServiceException(
						ServiceErrorCode.JSON_REQUEST_CRITERIA_NOT_SUPPORTED,
						request.getRequestCriteria());
			}
			break;

		default:
			throw new ServiceException(
					ServiceErrorCode.JSON_REQUEST_CRITERIA_NOT_SUPPORTED,
					request.getRequestCriteria());
		}

		return generateResponseMessage(user);
	}

	/**
	 * 
	 * @param request
	 * @return ResponseMessage Process all PUT requestMethod requests
	 */
	private ResponseMessage processPutRequest(Request request) {

		logger.debug("ProcessPutRequest method called.");

		final UserRequest userRequest = request.getRequestMessage()
				.getUserRequest();

		if (userService.findByEmail(userRequest.getEmail()) != null) {
			throw new ServiceException(
					ServiceErrorCode.QUERY_RECORD_ALREADY_EXIST, String.format(
							"User[faaId: %s]", userRequest.getEmail()));
		}

		User user = userBuilder.parseEnterpiseDocumentUser(userRequest);
		user = userService.save(user);
		// Process the Preferences
		List<Preference> preferences = userRequest.getPreferencesList();
		for (Preference preference : preferences) {
			if (preference != null) {
				com.rigil.fda.dao.entity.Preference preferenceEntity = userBuilder
						.parseEnterpiseDocumentUserPreference(user, preference);
				preferencesRepo.save(preferenceEntity);
			}
		}

		return generateResponseMessage(user);
	}

	/**
	 * 
	 * @param request
	 * @return ResponseMessage Process all UPDATE requestMethod requests
	 */
	private ResponseMessage processUpdateRequest(Request request) {

		logger.debug("ProcessUpdateRequest method called.");
		final UserRequest userRequest = request.getRequestMessage()
				.getUserRequest();
		final Set<String> criteria = new HashSet<>(Arrays.asList(request
				.getRequestCriteria().replaceAll("\\s", "").toUpperCase()
				.split(",")));

		User user = userService.findByEmail(userRequest.getEmail());

		if (user == null) {
			throw new ServiceException(ServiceErrorCode.QUERY_RECORD_NOT_FOUND,
					String.format("User[email: %s]", userRequest.getEmail()));
		}

		user = userBuilder.updateUserFromRequestUser(user, userRequest,
				criteria);

		return generateResponseMessage(userService.save(user));
	}

	/**
	 * 
	 * @param List
	 *            <com.rigil.fda.fs.dao.entity.User>
	 * @return com.rigil.fda.fs.json.response.user.ResponseMessage This method
	 *         generate ResponseMessage object of user.
	 */
	private ResponseMessage generateResponseMessage(User user) {

		logger.debug("GenerateResponseMesasge method called.");
		com.rigil.fda.json.User jsonUser = null;
		ResponseMessage responseMessage = new ResponseMessage();

		if (user != null) {
			jsonUser = userBuilder.generateEnterpiseUser(user);

		}
		responseMessage.setUser(jsonUser);
		return responseMessage;
	}

}
