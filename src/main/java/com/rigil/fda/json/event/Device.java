package com.rigil.fda.json.event;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "date_removed_flag",
    "other_id_number",
    "manufacturer_d_zip_code_ext",
    "baseline_510_k__number",
    "implant_flag",
    "device_operator",
    "brand_name",
    "lot_number",
    "manufacturer_d_state",
    "generic_name",
    "manufacturer_d_name",
    "device_availability",
    "manufacturer_d_city",
    "device_sequence_number",
    "device_age_text",
    "catalog_number",
    "baseline_510_k__exempt_flag",
    "date_received",
    "manufacturer_d_address_2",
    "manufacturer_d_address_1",
    "manufacturer_d_postal_code",
    "baseline_510_k__flag",
    "model_number",
    "manufacturer_d_zip_code",
    "device_report_product_code",
    "manufacturer_d_country",
    "device_event_key",
    "device_evaluated_by_manufacturer"
})
public class Device {

    @JsonProperty("date_removed_flag")
    private String dateRemovedFlag;
    @JsonProperty("other_id_number")
    private String otherIdNumber;
    @JsonProperty("manufacturer_d_zip_code_ext")
    private String manufacturerDZipCodeExt;
    @JsonProperty("baseline_510_k__number")
    private String baseline510KNumber;
    @JsonProperty("implant_flag")
    private String implantFlag;
    @JsonProperty("device_operator")
    private String deviceOperator;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("lot_number")
    private String lotNumber;
    @JsonProperty("manufacturer_d_state")
    private String manufacturerDState;
    @JsonProperty("generic_name")
    private String genericName;
    @JsonProperty("manufacturer_d_name")
    private String manufacturerDName;
    @JsonProperty("device_availability")
    private String deviceAvailability;
    @JsonProperty("manufacturer_d_city")
    private String manufacturerDCity;
    @JsonProperty("device_sequence_number")
    private String deviceSequenceNumber;
    @JsonProperty("device_age_text")
    private String deviceAgeText;
    @JsonProperty("catalog_number")
    private String catalogNumber;
    @JsonProperty("baseline_510_k__exempt_flag")
    private String baseline510KExemptFlag;
    @JsonProperty("date_received")
    private String dateReceived;
    @JsonProperty("manufacturer_d_address_2")
    private String manufacturerDAddress2;
    @JsonProperty("manufacturer_d_address_1")
    private String manufacturerDAddress1;
    @JsonProperty("manufacturer_d_postal_code")
    private String manufacturerDPostalCode;
    @JsonProperty("baseline_510_k__flag")
    private String baseline510KFlag;
    @JsonProperty("model_number")
    private String modelNumber;
    @JsonProperty("manufacturer_d_zip_code")
    private String manufacturerDZipCode;
    @JsonProperty("device_report_product_code")
    private String deviceReportProductCode;
    @JsonProperty("manufacturer_d_country")
    private String manufacturerDCountry;
    @JsonProperty("device_event_key")
    private String deviceEventKey;
    @JsonProperty("device_evaluated_by_manufacturer")
    private String deviceEvaluatedByManufacturer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dateRemovedFlag
     */
    @JsonProperty("date_removed_flag")
    public String getDateRemovedFlag() {
        return dateRemovedFlag;
    }

    /**
     * 
     * @param dateRemovedFlag
     *     The date_removed_flag
     */
    @JsonProperty("date_removed_flag")
    public void setDateRemovedFlag(String dateRemovedFlag) {
        this.dateRemovedFlag = dateRemovedFlag;
    }

    /**
     * 
     * @return
     *     The otherIdNumber
     */
    @JsonProperty("other_id_number")
    public String getOtherIdNumber() {
        return otherIdNumber;
    }

    /**
     * 
     * @param otherIdNumber
     *     The other_id_number
     */
    @JsonProperty("other_id_number")
    public void setOtherIdNumber(String otherIdNumber) {
        this.otherIdNumber = otherIdNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerDZipCodeExt
     */
    @JsonProperty("manufacturer_d_zip_code_ext")
    public String getManufacturerDZipCodeExt() {
        return manufacturerDZipCodeExt;
    }

    /**
     * 
     * @param manufacturerDZipCodeExt
     *     The manufacturer_d_zip_code_ext
     */
    @JsonProperty("manufacturer_d_zip_code_ext")
    public void setManufacturerDZipCodeExt(String manufacturerDZipCodeExt) {
        this.manufacturerDZipCodeExt = manufacturerDZipCodeExt;
    }

    /**
     * 
     * @return
     *     The baseline510KNumber
     */
    @JsonProperty("baseline_510_k__number")
    public String getBaseline510KNumber() {
        return baseline510KNumber;
    }

    /**
     * 
     * @param baseline510KNumber
     *     The baseline_510_k__number
     */
    @JsonProperty("baseline_510_k__number")
    public void setBaseline510KNumber(String baseline510KNumber) {
        this.baseline510KNumber = baseline510KNumber;
    }

    /**
     * 
     * @return
     *     The implantFlag
     */
    @JsonProperty("implant_flag")
    public String getImplantFlag() {
        return implantFlag;
    }

    /**
     * 
     * @param implantFlag
     *     The implant_flag
     */
    @JsonProperty("implant_flag")
    public void setImplantFlag(String implantFlag) {
        this.implantFlag = implantFlag;
    }

    /**
     * 
     * @return
     *     The deviceOperator
     */
    @JsonProperty("device_operator")
    public String getDeviceOperator() {
        return deviceOperator;
    }

    /**
     * 
     * @param deviceOperator
     *     The device_operator
     */
    @JsonProperty("device_operator")
    public void setDeviceOperator(String deviceOperator) {
        this.deviceOperator = deviceOperator;
    }

    /**
     * 
     * @return
     *     The brandName
     */
    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    /**
     * 
     * @param brandName
     *     The brand_name
     */
    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 
     * @return
     *     The lotNumber
     */
    @JsonProperty("lot_number")
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 
     * @param lotNumber
     *     The lot_number
     */
    @JsonProperty("lot_number")
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerDState
     */
    @JsonProperty("manufacturer_d_state")
    public String getManufacturerDState() {
        return manufacturerDState;
    }

    /**
     * 
     * @param manufacturerDState
     *     The manufacturer_d_state
     */
    @JsonProperty("manufacturer_d_state")
    public void setManufacturerDState(String manufacturerDState) {
        this.manufacturerDState = manufacturerDState;
    }

    /**
     * 
     * @return
     *     The genericName
     */
    @JsonProperty("generic_name")
    public String getGenericName() {
        return genericName;
    }

    /**
     * 
     * @param genericName
     *     The generic_name
     */
    @JsonProperty("generic_name")
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    /**
     * 
     * @return
     *     The manufacturerDName
     */
    @JsonProperty("manufacturer_d_name")
    public String getManufacturerDName() {
        return manufacturerDName;
    }

    /**
     * 
     * @param manufacturerDName
     *     The manufacturer_d_name
     */
    @JsonProperty("manufacturer_d_name")
    public void setManufacturerDName(String manufacturerDName) {
        this.manufacturerDName = manufacturerDName;
    }

    /**
     * 
     * @return
     *     The deviceAvailability
     */
    @JsonProperty("device_availability")
    public String getDeviceAvailability() {
        return deviceAvailability;
    }

    /**
     * 
     * @param deviceAvailability
     *     The device_availability
     */
    @JsonProperty("device_availability")
    public void setDeviceAvailability(String deviceAvailability) {
        this.deviceAvailability = deviceAvailability;
    }

    /**
     * 
     * @return
     *     The manufacturerDCity
     */
    @JsonProperty("manufacturer_d_city")
    public String getManufacturerDCity() {
        return manufacturerDCity;
    }

    /**
     * 
     * @param manufacturerDCity
     *     The manufacturer_d_city
     */
    @JsonProperty("manufacturer_d_city")
    public void setManufacturerDCity(String manufacturerDCity) {
        this.manufacturerDCity = manufacturerDCity;
    }

    /**
     * 
     * @return
     *     The deviceSequenceNumber
     */
    @JsonProperty("device_sequence_number")
    public String getDeviceSequenceNumber() {
        return deviceSequenceNumber;
    }

    /**
     * 
     * @param deviceSequenceNumber
     *     The device_sequence_number
     */
    @JsonProperty("device_sequence_number")
    public void setDeviceSequenceNumber(String deviceSequenceNumber) {
        this.deviceSequenceNumber = deviceSequenceNumber;
    }

    /**
     * 
     * @return
     *     The deviceAgeText
     */
    @JsonProperty("device_age_text")
    public String getDeviceAgeText() {
        return deviceAgeText;
    }

    /**
     * 
     * @param deviceAgeText
     *     The device_age_text
     */
    @JsonProperty("device_age_text")
    public void setDeviceAgeText(String deviceAgeText) {
        this.deviceAgeText = deviceAgeText;
    }

    /**
     * 
     * @return
     *     The catalogNumber
     */
    @JsonProperty("catalog_number")
    public String getCatalogNumber() {
        return catalogNumber;
    }

    /**
     * 
     * @param catalogNumber
     *     The catalog_number
     */
    @JsonProperty("catalog_number")
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    /**
     * 
     * @return
     *     The baseline510KExemptFlag
     */
    @JsonProperty("baseline_510_k__exempt_flag")
    public String getBaseline510KExemptFlag() {
        return baseline510KExemptFlag;
    }

    /**
     * 
     * @param baseline510KExemptFlag
     *     The baseline_510_k__exempt_flag
     */
    @JsonProperty("baseline_510_k__exempt_flag")
    public void setBaseline510KExemptFlag(String baseline510KExemptFlag) {
        this.baseline510KExemptFlag = baseline510KExemptFlag;
    }

    /**
     * 
     * @return
     *     The dateReceived
     */
    @JsonProperty("date_received")
    public String getDateReceived() {
        return dateReceived;
    }

    /**
     * 
     * @param dateReceived
     *     The date_received
     */
    @JsonProperty("date_received")
    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * 
     * @return
     *     The manufacturerDAddress2
     */
    @JsonProperty("manufacturer_d_address_2")
    public String getManufacturerDAddress2() {
        return manufacturerDAddress2;
    }

    /**
     * 
     * @param manufacturerDAddress2
     *     The manufacturer_d_address_2
     */
    @JsonProperty("manufacturer_d_address_2")
    public void setManufacturerDAddress2(String manufacturerDAddress2) {
        this.manufacturerDAddress2 = manufacturerDAddress2;
    }

    /**
     * 
     * @return
     *     The manufacturerDAddress1
     */
    @JsonProperty("manufacturer_d_address_1")
    public String getManufacturerDAddress1() {
        return manufacturerDAddress1;
    }

    /**
     * 
     * @param manufacturerDAddress1
     *     The manufacturer_d_address_1
     */
    @JsonProperty("manufacturer_d_address_1")
    public void setManufacturerDAddress1(String manufacturerDAddress1) {
        this.manufacturerDAddress1 = manufacturerDAddress1;
    }

    /**
     * 
     * @return
     *     The manufacturerDPostalCode
     */
    @JsonProperty("manufacturer_d_postal_code")
    public String getManufacturerDPostalCode() {
        return manufacturerDPostalCode;
    }

    /**
     * 
     * @param manufacturerDPostalCode
     *     The manufacturer_d_postal_code
     */
    @JsonProperty("manufacturer_d_postal_code")
    public void setManufacturerDPostalCode(String manufacturerDPostalCode) {
        this.manufacturerDPostalCode = manufacturerDPostalCode;
    }

    /**
     * 
     * @return
     *     The baseline510KFlag
     */
    @JsonProperty("baseline_510_k__flag")
    public String getBaseline510KFlag() {
        return baseline510KFlag;
    }

    /**
     * 
     * @param baseline510KFlag
     *     The baseline_510_k__flag
     */
    @JsonProperty("baseline_510_k__flag")
    public void setBaseline510KFlag(String baseline510KFlag) {
        this.baseline510KFlag = baseline510KFlag;
    }

    /**
     * 
     * @return
     *     The modelNumber
     */
    @JsonProperty("model_number")
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * 
     * @param modelNumber
     *     The model_number
     */
    @JsonProperty("model_number")
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerDZipCode
     */
    @JsonProperty("manufacturer_d_zip_code")
    public String getManufacturerDZipCode() {
        return manufacturerDZipCode;
    }

    /**
     * 
     * @param manufacturerDZipCode
     *     The manufacturer_d_zip_code
     */
    @JsonProperty("manufacturer_d_zip_code")
    public void setManufacturerDZipCode(String manufacturerDZipCode) {
        this.manufacturerDZipCode = manufacturerDZipCode;
    }

    /**
     * 
     * @return
     *     The deviceReportProductCode
     */
    @JsonProperty("device_report_product_code")
    public String getDeviceReportProductCode() {
        return deviceReportProductCode;
    }

    /**
     * 
     * @param deviceReportProductCode
     *     The device_report_product_code
     */
    @JsonProperty("device_report_product_code")
    public void setDeviceReportProductCode(String deviceReportProductCode) {
        this.deviceReportProductCode = deviceReportProductCode;
    }

    /**
     * 
     * @return
     *     The manufacturerDCountry
     */
    @JsonProperty("manufacturer_d_country")
    public String getManufacturerDCountry() {
        return manufacturerDCountry;
    }

    /**
     * 
     * @param manufacturerDCountry
     *     The manufacturer_d_country
     */
    @JsonProperty("manufacturer_d_country")
    public void setManufacturerDCountry(String manufacturerDCountry) {
        this.manufacturerDCountry = manufacturerDCountry;
    }

    /**
     * 
     * @return
     *     The deviceEventKey
     */
    @JsonProperty("device_event_key")
    public String getDeviceEventKey() {
        return deviceEventKey;
    }

    /**
     * 
     * @param deviceEventKey
     *     The device_event_key
     */
    @JsonProperty("device_event_key")
    public void setDeviceEventKey(String deviceEventKey) {
        this.deviceEventKey = deviceEventKey;
    }

    /**
     * 
     * @return
     *     The deviceEvaluatedByManufacturer
     */
    @JsonProperty("device_evaluated_by_manufacturer")
    public String getDeviceEvaluatedByManufacturer() {
        return deviceEvaluatedByManufacturer;
    }

    /**
     * 
     * @param deviceEvaluatedByManufacturer
     *     The device_evaluated_by_manufacturer
     */
    @JsonProperty("device_evaluated_by_manufacturer")
    public void setDeviceEvaluatedByManufacturer(String deviceEvaluatedByManufacturer) {
        this.deviceEvaluatedByManufacturer = deviceEvaluatedByManufacturer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
