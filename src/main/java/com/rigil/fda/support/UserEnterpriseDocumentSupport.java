package com.rigil.fda.support;

import com.rigil.common.exception.ServiceErrorCode;
import com.rigil.common.exception.ServiceException;
import com.rigil.fda.builder.UserBuilder;
import com.rigil.fda.dao.entity.PreferenceEntity;
import com.rigil.fda.dao.entity.UserEntity;
import com.rigil.fda.json.PreferenceJsonDataObject;
import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.UserService;
import com.rigil.fda.json.DocumentBody;
import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.fda.json.EnterpriseDocument_;
import com.rigil.fda.json.Request;
import com.rigil.fda.json.Response;
import com.rigil.fda.json.ResponseMessage;
import com.rigil.fda.json.UserRequestJsonDataObject;

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

            logger.debug("Processing userEntity GET request.");
            responseMessage = processGetRequest(enterpriseDocumentRequest
                    .getEnterpriseDocument().getDocumentBody().getRequest());
            enterpriseDocumentResponse.getEnterpriseDocument()
                    .getDocumentBody().getResponse()
                    .setResponseMessage(responseMessage);
            break;
        case "PUT": // handles create[PUT] request.
            logger.debug("Processing userEntity PUT request.");
            responseMessage = processPutRequest(enterpriseDocumentRequest
                    .getEnterpriseDocument().getDocumentBody().getRequest());
            enterpriseDocumentResponse.getEnterpriseDocument()
                    .getDocumentBody().getResponse()
                    .setResponseMessage(responseMessage);
            break;
        case "UPDATE": // handles UPDATE request.
            logger.debug("Processing userEntity UPDATE request.");
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
        String email = enterpriseDocumentRequest
                .getEnterpriseDocument().getDocumentBody().getRequest().getRequestMessage().getUserRequestJsonDataObject().getEmail();
        userService.alertNotifications(email);
    }

    /**
     *
     * @param request
     * @return ResponseMessage Process all GET requestMethod requests
     */
    private ResponseMessage processGetRequest(Request request) {

        logger.debug("ProcessGetRequest method invoked to query userEntity.");

        final Set<String> criteria = new HashSet<>(Arrays.asList(request
                .getRequestCriteria().replaceAll("\\s", "").toUpperCase()
                .split(",")));
        final UserRequestJsonDataObject userRequestJsonDataObject = request.getRequestMessage()
                .getUserRequestJsonDataObject();

        UserEntity userEntity = null;

        switch (criteria.size()) {
        case 1:

            if (criteria.contains("EMAIL")) {
                userEntity = userService.findByEmail(userRequestJsonDataObject.getEmail());
            } else if (criteria.contains("PHONE")) {
                userEntity = userService.findByEmail(userRequestJsonDataObject.getPhone());
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

        return generateResponseMessage(userEntity);
    }

    /**
     *
     * @param request
     * @return ResponseMessage Process all PUT requestMethod requests
     */
    private ResponseMessage processPutRequest(Request request) {

        logger.debug("ProcessPutRequest method called.");

        final UserRequestJsonDataObject userRequestJsonDataObject = request.getRequestMessage()
                .getUserRequestJsonDataObject();

        if (userService.findByEmail(userRequestJsonDataObject.getEmail()) != null) {
            throw new ServiceException(
                    ServiceErrorCode.QUERY_RECORD_ALREADY_EXIST, String.format(
                            "UserEntity[id: %s]", userRequestJsonDataObject.getEmail()));
        }

        UserEntity userEntity = userBuilder.parseEnterpiseDocumentUser(userRequestJsonDataObject);
        userEntity = userService.save(userEntity);
        // Process the Preferences
        List<PreferenceJsonDataObject> preferenceJsonDataObjects = userRequestJsonDataObject.getPreferencesList();
        for (PreferenceJsonDataObject preferenceJsonDataObject : preferenceJsonDataObjects) {
            if (preferenceJsonDataObject != null) {
                PreferenceEntity preferenceEntityEntity = userBuilder
                        .parseEnterpriseDocumentUserPreference(userEntity, preferenceJsonDataObject);
                preferencesRepo.save(preferenceEntityEntity);
            }
        }

        return generateResponseMessage(userEntity);
    }

    /**
     *
     * @param request
     * @return ResponseMessage Process all UPDATE requestMethod requests
     */
    private ResponseMessage processUpdateRequest(Request request) {

        logger.debug("ProcessUpdateRequest method called.");
        final UserRequestJsonDataObject userRequestJsonDataObject = request.getRequestMessage()
                .getUserRequestJsonDataObject();
        final Set<String> criteria = new HashSet<>(Arrays.asList(request
                .getRequestCriteria().replaceAll("\\s", "").toUpperCase()
                .split(",")));

        UserEntity userEntity = userService.findByEmail(userRequestJsonDataObject.getEmail());

        if (userEntity == null) {
            throw new ServiceException(ServiceErrorCode.QUERY_RECORD_NOT_FOUND,
                    String.format("UserEntity[email: %s]", userRequestJsonDataObject.getEmail()));
        }

        //Update the preferenceJsonDataObjects
        List<PreferenceJsonDataObject> preferenceJsonDataObjects = userRequestJsonDataObject.getPreferencesList();
        for (PreferenceJsonDataObject preferenceJsonDataObject : preferenceJsonDataObjects) {
            if (preferenceJsonDataObject != null) {
                System.out.println("Processing preferenceJsonDataObject - " + preferenceJsonDataObject.getFdaJsonDataObject().getDataName());
                PreferenceEntity preferenceEntityEntity = userBuilder
                        .parseEnterpriseDocumentUserPreference(userEntity, preferenceJsonDataObject);
                System.out.println("Saving PreferenceEntity Entity - " + preferenceEntityEntity.getId());
                List<PreferenceEntity> preferenceEntityList = preferencesRepo.findPreferencesByEmailAndDataName(userRequestJsonDataObject.getEmail(), preferenceJsonDataObject.getFdaJsonDataObject().getDataName());
                if(preferenceEntityList.size() == 0)
                    preferencesRepo.save(preferenceEntityEntity);
            }
        }

        return generateResponseMessage(userService.save(userEntity));
    }

    /**
     *
     * @param List
     *            <com.rigil.fda.fs.dao.entity.UserEntity>
     * @return com.rigil.fda.fs.json.response.userEntity.ResponseMessage This method
     *         generate ResponseMessage object of userEntity.
     */
    private ResponseMessage generateResponseMessage(UserEntity userEntity) {

        logger.debug("GenerateResponseMesasge method called.");
        com.rigil.fda.json.User jsonUser = null;
        ResponseMessage responseMessage = new ResponseMessage();

        if (userEntity != null) {
            jsonUser = userBuilder.generateEnterpriseUser(userEntity);

        }
        responseMessage.setUser(jsonUser);
        return responseMessage;
    }

}
