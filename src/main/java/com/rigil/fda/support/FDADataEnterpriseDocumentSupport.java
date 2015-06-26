package com.rigil.fda.support;

import com.rigil.common.exception.ServiceErrorCode;
import com.rigil.common.exception.ServiceException;
import com.rigil.fda.builder.FDADataBuilder;
import com.rigil.fda.dao.entity.FDADataEntity;
import com.rigil.fda.json.FDAJsonDataObject;
import com.rigil.fda.service.FDADataService;
import com.rigil.fda.json.DocumentBody;
import com.rigil.fda.json.EnterpriseDocument;
import com.rigil.fda.json.EnterpriseDocument_;
import com.rigil.fda.json.FDADataRequest;
import com.rigil.fda.json.Request;
import com.rigil.fda.json.Response;
import com.rigil.fda.json.ResponseMessage;

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

        List<FDADataEntity> fdaDataEntityList = new ArrayList<FDADataEntity>();

        switch (criteria.size()) {
        case 1:

            if (criteria.contains("DEVICE")) {
                fdaDataEntityList = fdaDataService.getDevicesData();
            } else if (criteria.contains("DRUG")) {
                fdaDataEntityList = fdaDataService.getDrugData();
            } else if (criteria.contains("FOOD")) {
                fdaDataEntityList = fdaDataService.getFoodData();
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

        return generateResponseMessage(fdaDataEntityList);
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

        FDADataEntity fdaDataEntity = fdaDataBuilder.parseEnterpiseDocumentFDAData(fdaDataRequest);
        fdaDataEntity = fdaDataService.save(fdaDataEntity);
        List<FDADataEntity> fdaDataEntityList = new ArrayList<FDADataEntity>();
        fdaDataEntityList.add(fdaDataEntity);
        return generateResponseMessage(fdaDataEntityList);
    }

    /**
     *
     * @param fdaDataEntityList List
     *            <com.rigil.fda.fs.dao.entity.FDADataEntity>
     * @return com.rigil.fda.fs.json.response.FDADataEntity.ResponseMessage This method
     *         generate ResponseMessage object of user.
     */
    private ResponseMessage generateResponseMessage(List<FDADataEntity> fdaDataEntityList) {

        logger.debug("GenerateResponseMesasge method called.");
        List<FDAJsonDataObject> jsonFDAJsonDataObjectList = new ArrayList<FDAJsonDataObject>();
        ResponseMessage responseMessage = new ResponseMessage();
        for(FDADataEntity fdaDataEntity : fdaDataEntityList)
        {
            FDAJsonDataObject jsonFDAJsonDataObject = fdaDataBuilder.generateEnterpriseFDAData(fdaDataEntity);
            jsonFDAJsonDataObjectList.add(jsonFDAJsonDataObject);
        }
        responseMessage.setFDADataList(jsonFDAJsonDataObjectList);
        return responseMessage;
    }

}
