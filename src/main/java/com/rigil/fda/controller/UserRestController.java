package com.rigil.fda.controller;

import com.rigil.fda.service.UserService;
import com.rigil.fda.support.UserEnterpriseDocumentSupport;
import com.rigil.fda.json.EnterpriseDocument;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	UserEnterpriseDocumentSupport enterpriseDocumentSupport;
	
	@RequestMapping(method={
			RequestMethod.POST,RequestMethod.PUT}, 
			consumes="application/json", produces = "application/json"
	)
	@ResponseBody
	public final EnterpriseDocument handleRequest(@Valid @RequestBody EnterpriseDocument enterpriseDocumentRequest ){
		
		logger.debug("Request recevied [{}]", enterpriseDocumentRequest);

		EnterpriseDocument enterpriseDocumentResponse = new EnterpriseDocument();

		enterpriseDocumentSupport.processRequest(enterpriseDocumentRequest, enterpriseDocumentResponse);

		logger.debug("Response sent [{}]", enterpriseDocumentResponse);
		return enterpriseDocumentResponse;
	}
	
	
}
