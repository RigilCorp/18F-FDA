package com.rigil.fda.support;

import com.rigil.common.exception.ServiceErrorCode;
import com.rigil.common.exception.ServiceException;
import com.rigil.fda.builder.FDADataBuilder;
import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.dao.entity.FDAData;
import com.rigil.fda.dao.entity.User;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.FDADataService;
import com.rigil.fda.service.UserService;
import com.rigil.fda.json.DocumentBody;
import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.fda.json.EnterpriseDocument_;
import com.rigil.fda.json.FDADataRequest;
import com.rigil.fda.json.Preference;
import com.rigil.fda.json.Request;
import com.rigil.fda.json.Response;
import com.rigil.fda.json.ResponseMessage;
import com.rigil.fda.json.UserRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FDADataEnterpriseDocumentSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(UserEnterpriseDocumentSupport.class);

	@Autowired
	FDADataBuilder fdaDataBuilder;

	@Autowired
	FDADataService fdaDataService;
	
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
		default:
			/*
			 * throw new EnterpriseDocumentException(
			 * String.format("RequestMethod: [%s] is not supported. ",
			 * requestMethod), EnterpriseDocumentException.ErrorCodeType.
			 * REQUEST_METHOD_NOT_SUPPORTED);
			 */
		}
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
	
		List<FDAData> fdaDataList = new ArrayList<FDAData>();

		switch (criteria.size()) {
		case 1:

			if (criteria.contains("DEVICE")) {
				fdaDataList = fdaDataService.getDevicesData();
			} else if (criteria.contains("DRUG")) {
				fdaDataList = fdaDataService.getDrugData();
			} else if (criteria.contains("FOOD")) {
				fdaDataList = fdaDataService.getFoodData();
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

		return generateResponseMessage(fdaDataList);
	}

	/**
	 * 
	 * @param request
	 * @return ResponseMessage Process all PUT requestMethod requests
	 */
	private ResponseMessage processPutRequest(Request request) {

		logger.debug("ProcessPutRequest method called.");

		final FDADataRequest fdaDataRequest = request.getRequestMessage().getFDADataRequest();
		
		if (fdaDataService.getFDAData(fdaDataRequest.getdataName(), fdaDataRequest.getdataCode()) != null) {
			throw new ServiceException(
					ServiceErrorCode.QUERY_RECORD_ALREADY_EXIST, String.format(
							"FDA Data[dataName: %s]", fdaDataRequest.getdataName()));
		}
		
		FDAData fdaData = fdaDataBuilder.parseEnterpiseDocumentFDAData(fdaDataRequest);		
		fdaData = fdaDataService.save(fdaData);
		List<FDAData> fdaDataList = new ArrayList<FDAData>();
		fdaDataList.add(fdaData);
		return generateResponseMessage(fdaDataList);
	}

	/**
	 * 
	 * @param List
	 *            <com.rigil.fda.fs.dao.entity.FDAData>
	 * @return com.rigil.fda.fs.json.response.FDAData.ResponseMessage This method
	 *         generate ResponseMessage object of user.
	 */
	private ResponseMessage generateResponseMessage(List<FDAData> fdaDataList) {

		logger.debug("GenerateResponseMesasge method called.");
		List<com.rigil.fda.json.FDAData> jsonFDADataList = new ArrayList<com.rigil.fda.json.FDAData>();
		ResponseMessage responseMessage = new ResponseMessage();
		for(FDAData fdaData : fdaDataList)
		{
			com.rigil.fda.json.FDAData jsonFDAData = fdaDataBuilder.generateEnterpriseFDAData(fdaData);
			jsonFDADataList.add(jsonFDAData);
		}
		responseMessage.setFDADataList(jsonFDADataList);
		return responseMessage;
	}

}
