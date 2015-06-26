package com.rigil.fda.builder;

import com.rigil.fda.dao.entity.FDADataEntity;
import com.rigil.fda.json.FDADataRequest;

import com.rigil.fda.json.FDAJsonDataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FDADataBuilder {

    private final static Logger logger = LoggerFactory.getLogger(FDADataBuilder.class);

    /**
     * @param FDADataRequest
     * @return  FDADataEntity
     * This method converts Enterprise Document UserEntity object into UserEntity Entity object.
     */

    public FDADataEntity parseEnterpiseDocumentFDAData(FDADataRequest FDADataRequest) {

        logger.debug("ParseEnterpriseDocumentFDAData method invoked.");
        FDADataEntity fdaDataEntity = new FDADataEntity();
        fdaDataEntity.setDataCode(FDADataRequest.getdataCode());
        fdaDataEntity.setDataName(FDADataRequest.getdataName());
        fdaDataEntity.setDataDescription(FDADataRequest.getDataDesc());
        return fdaDataEntity;
    }


    /**
     *
     * This method converts FDADataEntity Entity object into Enterprise Document FDADataEntity object.
     */
    public FDAJsonDataObject generateEnterpriseFDAData(FDADataEntity fdaDataEntity) {

        logger.debug("GenerateEnterpriseUser method invoked.");

        FDAJsonDataObject fdaJsonDataObjectJson = new FDAJsonDataObject();
        fdaJsonDataObjectJson.setDataCode(fdaDataEntity.getDataCode());
        fdaJsonDataObjectJson.setDataName(fdaDataEntity.getDataName());
        fdaJsonDataObjectJson.setDataDesc(fdaDataEntity.getDataDescription());
        return fdaJsonDataObjectJson;
    }


}
