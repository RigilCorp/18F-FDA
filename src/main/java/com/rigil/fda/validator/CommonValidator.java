package com.rigil.fda.validator;

import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.common.exception.ServiceErrorCode;
import com.rigil.common.exception.ServiceException;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class CommonValidator {
	
	private static final int CODE_LENGTH = 15;
	private static final int NAME_LENGTH = 150;
	private static final int OPER_INITIALS_LENGTH = 5;
	private static final int LAST_NAME_LENGTH = 75;
	private static final int FIRST_NAME_LENGTH = 50;
	private static final int MIDDLE_NAME_LENGTH = 50;
	private static final int EMAIL_ADDRESS_LENGTH = 175;
	private static final int COMMON_ID_LENGTH = 30;
	
	private static final String INTEGER_PATTERN = "^\\d+$";
	private static final String Y_OR_N_PATTERN = "(y)|(n)|(Y)|(N)";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	
	private static final String REQUEST_METHOD_STRING = "enterpriseDocument.documentBody.request.requestMethod";


	
	@Qualifier("dateFormatter")
	@Autowired
	DateFormat dateFormatter;
	
	
	public void validateRequestMethod(Errors errors, String value){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, REQUEST_METHOD_STRING, "required", new Object[]{"RequestMethod"}, "RequestMethod is required.");
		
		if(!errors.hasFieldErrors(REQUEST_METHOD_STRING)){
			if(!("GET".equals(value) || "UPDATE".equals(value) 
					|| "PUT".equals(value) || "DELETE".equals(value))){
				errors.rejectValue(REQUEST_METHOD_STRING, "not.supported", new Object[]{"RequestMethod", value}, "RequestMethod not supported.");
			}
		}
	}
	
	
	public void validateCode(Errors errors, String value, String codePath, String field) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is required");
		
		if(!errors.hasFieldErrors(codePath)){
			
			if(value.trim().length() > CODE_LENGTH){
				errors.rejectValue(codePath
					, "length", new Object[]{"field", CODE_LENGTH }, "The length of " + field + " excede its limit.");
			}
		}
	}
	
	
	public void validateName(Errors errors, String value, String codePath, String field) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is required");
		
		if(!errors.hasFieldErrors(codePath)){
			
			if(value.trim().length() > NAME_LENGTH){
				errors.rejectValue(codePath
					, "length", new Object[]{field, NAME_LENGTH }, "The length of " + field + " excede its limit.");
			}
		}
	}
	

	public void validateActivationDate(Errors errors, String value, String codePath, String field) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, "Activation date is required");

		if(!errors.hasFieldErrors(codePath)){
			if(!isValidDate(value)){
				errors.rejectValue(codePath, "invalid", new Object[]{field, value}, "activation date is invalid");
			}
		}
	}
	
	
	public void validateExpirationDate(Errors errors, String value, String codePath, String field) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, "Expiration date is required");
	}


	public void validateBooleanYN(Errors errors, String value, String codePath, String field) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is Required");
		
		if(!errors.hasFieldErrors(codePath)){
			if(!value.matches(Y_OR_N_PATTERN)){
				errors.rejectValue(codePath, "invalid", new Object[]{field, value}, field + " is invalid.");
			}
		}
	}

	public void validateEmailAddress(Errors errors, String value, String codePath, String field) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is Required");
		
		if(!errors.hasFieldErrors(codePath)){
			if(!value.matches(EMAIL_PATTERN)){
				errors.rejectValue(codePath, "invalid", new Object[]{field, value}, field + " is invalid.");
			}
		}
		
		if(!errors.hasFieldErrors(codePath)){
			if(value.trim().length() > EMAIL_ADDRESS_LENGTH){
				errors.rejectValue(codePath, "length", new Object[]{field, EMAIL_ADDRESS_LENGTH }, "The length of email excede its limit.");
			}
		}
	}
	
	public void validateFirstName(Errors errors, String value, String codePath, String field) {
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is required");
			
			if(!errors.hasFieldErrors(codePath)){
				
				if(value.trim().length() > FIRST_NAME_LENGTH){
					errors.rejectValue(codePath
						, "length", new Object[]{field, FIRST_NAME_LENGTH }, "The length of " + field + " excede its limit.");
				}
			}
		}
	
	public void validateLastName(Errors errors, String value, String codePath, String field) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is required");
		
		if(!errors.hasFieldErrors(codePath)){
			
			if(value.trim().length() > LAST_NAME_LENGTH){
				errors.rejectValue(codePath
					, "length", new Object[]{field, LAST_NAME_LENGTH }, "The length of " + field + " excede its limit.");
			}
		}
	}
	
	public void validateMiddleName(Errors errors, String value, String codePath, String field) {
			
		if(value.trim().length() > MIDDLE_NAME_LENGTH){
			errors.rejectValue(codePath, "length", new Object[]{field, MIDDLE_NAME_LENGTH }, "The length of " + field + " excede its limit.");
				
		}
	}
	
	public void validateOperationInit(Errors errors, String value, String codePath, String field) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, field + " is required");
		
		if(!errors.hasFieldErrors(codePath)){
			if(value.trim().length() > OPER_INITIALS_LENGTH){
				errors.rejectValue(codePath, "length", new Object[]{field, OPER_INITIALS_LENGTH }, "The length of " + field + " excede its limit.");
			}
		}
	}
	
	public void validateNumber(Errors errors, String value, String codePath, String field) {
		if(!value.matches(INTEGER_PATTERN)){
			errors.rejectValue(codePath, "invalid", new Object[]{field, value}, field + " is invalid.");
		}
	}
	
	public void validateCommonId(Errors errors, String value, String codePath, String field){
		if(value.trim().length() > COMMON_ID_LENGTH){
			errors.rejectValue(codePath, "length", new Object[]{field, COMMON_ID_LENGTH }, "The length of " + field + " excede its limit.");
		}
	}
	
	public void validateId(Errors errors, String value, String codePath, String field, boolean ignoreNullValue) {
		if(!ignoreNullValue){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, codePath, "required", new Object[]{field}, "Id is required");
		}

		if(!errors.hasFieldErrors(codePath)){
			if(!value.matches(INTEGER_PATTERN)){
				errors.rejectValue(codePath, "invalid", new Object[]{field, value}, "Id is invalid.");
			}
		}
	}
	

	public boolean isValidDate(String value){
		Date date = null;
		try{
			date = this.dateFormatter.parse(value);
		}
		catch(ParseException ex){
			
		}
		return date != null;
	}


	public void schemaValidation(EnterpriseDocument enterpriseDocument) {
		if(enterpriseDocument == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "enterpriseDocument");
		}
		
		if(enterpriseDocument.getEnterpriseDocument() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "enterpriseDocument");
		}
		
		/*if(enterpriseDocument.getEnterpriseDocument().getDocumentTimeStamp() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "documentTimeStamp");
		}*/
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentHeader() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "documentHeader");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentHeader().getDocumentServiceId() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "documentServiceId");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentHeader().getDocumentBusinessHeader() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "documentBusinessHeader");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentBody() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "documentBody");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentBody().getRequest() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "request");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentBody().getRequest().getRequestMethod() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "requestMethod");
		}
		
		if(enterpriseDocument.getEnterpriseDocument().getDocumentBody().getRequest().getRequestCriteria() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "requestCriteria");
		}
		

		if(enterpriseDocument.getEnterpriseDocument().getDocumentBody().getRequest().getRequestMessage() == null){
			throw new ServiceException(ServiceErrorCode.JSON_ELEMENT_NOT_FOUND, "requestMessage");
		}
	}
}
