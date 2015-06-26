package com.rigil.fda.support;

import com.rigil.fda.repository.PreferencesRepository;
import com.rigil.fda.service.AuthenticationService;
import com.rigil.fda.json.AuthenticationRequest;
import com.rigil.fda.json.AuthenticationResponse;
import com.rigil.fda.json.DocumentBody;
import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.fda.json.EnterpriseDocument_;
import com.rigil.fda.json.Request;
import com.rigil.fda.json.Response;
import com.rigil.fda.json.ResponseMessage;

import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationEnterpriseDocumentSupport {

    private static final Logger logger = LoggerFactory
            .getLogger(AuthenticationEnterpriseDocumentSupport.class);

    @Autowired
    AuthenticationService authenticationService;

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

        ResponseMessage responseMessage;

        switch (requestMethod) {
        case "GET": // handles GET request.

            logger.debug("Processing user GET request.");
            responseMessage = processGetRequest(enterpriseDocumentRequest
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

        logger.debug("ProcessGetRequest method invoked to authenticate user.");

        String result = "";
        final AuthenticationRequest authRequest = request.getRequestMessage().getAuthenticationRequest();
        result = authenticationService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());

        return generateResponseMessage(result);
    }


    /**
     *
     * @param result String
     * @return ResponseMessage This method
     *         generate ResponseMessage object of authentication result.
     */
    private ResponseMessage generateResponseMessage(String result) {

        logger.debug("GenerateResponseMesasge method called.");
        ResponseMessage responseMessage = new ResponseMessage();
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setResult(result);
        responseMessage.setAuthenticationResponse(authResponse);
        return responseMessage;
    }

}
