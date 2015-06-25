package com.rigil.fda.json.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "report_number",
    "manufacturer_contact_state",
    "device_date_of_manufacturer",
    "event_type",
    "source_type",
    "manufacturer_g1_address_1",
    "manufacturer_contact_pcountry",
    "report_to_manufacturer",
    "previous_use_code",
    "manufacturer_postal_code",
    "manufacturer_city",
    "reporter_occupation_code",
    "manufacturer_contact_extension",
    "manufacturer_state",
    "event_location",
    "date_of_event",
    "manufacturer_g1_country",
    "removal_correction_number",
    "manufacturer_g1_city",
    "mdr_text",
    "distributor_zip_code_ext",
    "date_received",
    "manufacturer_zip_code",
    "manufacturer_contact_address_2",
    "date_report",
    "manufacturer_contact_address_1",
    "manufacturer_contact_plocal",
    "manufacturer_name",
    "type_of_report",
    "reprocessed_and_reused_flag",
    "manufacturer_link_flag",
    "manufacturer_contact_zip_code",
    "manufacturer_g1_zip_code_ext",
    "manufacturer_contact_country",
    "distributor_zip_code",
    "report_source_code",
    "distributor_name",
    "date_manufacturer_received",
    "remedial_action",
    "manufacturer_contact_postal_code",
    "manufacturer_contact_city",
    "product_problem_flag",
    "event_key",
    "manufacturer_g1_zip_code",
    "manufacturer_g1_state",
    "patient",
    "manufacturer_contact_phone_number",
    "manufacturer_zip_code_ext",
    "manufacturer_contact_pcity",
    "manufacturer_g1_postal_code",
    "manufacturer_contact_area_code",
    "adverse_event_flag",
    "single_use_flag",
    "distributor_city",
    "manufacturer_country",
    "manufacturer_contact_f_name",
    "distributor_state",
    "number_devices_in_event",
    "manufacturer_contact_exchange",
    "distributor_address_2",
    "manufacturer_contact_t_name",
    "distributor_address_1",
    "manufacturer_g1_address_2",
    "health_professional",
    "initial_report_to_fda",
    "number_patients_in_event",
    "mdr_report_key",
    "report_to_fda",
    "manufacturer_address_2",
    "manufacturer_address_1",
    "manufacturer_g1_name",
    "manufacturer_contact_zip_ext",
    "manufacturer_contact_l_name",
    "device"
})
public class Result {

    @JsonProperty("report_number")
    private String reportNumber;
    @JsonProperty("manufacturer_contact_state")
    private String manufacturerContactState;
    @JsonProperty("device_date_of_manufacturer")
    private String deviceDateOfManufacturer;
    @JsonProperty("event_type")
    private String eventType;
    @JsonProperty("source_type")
    private List<Object> sourceType = new ArrayList<Object>();
    @JsonProperty("manufacturer_g1_address_1")
    private String manufacturerG1Address1;
    @JsonProperty("manufacturer_contact_pcountry")
    private String manufacturerContactPcountry;
    @JsonProperty("report_to_manufacturer")
    private String reportToManufacturer;
    @JsonProperty("previous_use_code")
    private String previousUseCode;
    @JsonProperty("manufacturer_postal_code")
    private String manufacturerPostalCode;
    @JsonProperty("manufacturer_city")
    private String manufacturerCity;
    @JsonProperty("reporter_occupation_code")
    private String reporterOccupationCode;
    @JsonProperty("manufacturer_contact_extension")
    private String manufacturerContactExtension;
    @JsonProperty("manufacturer_state")
    private String manufacturerState;
    @JsonProperty("event_location")
    private String eventLocation;
    @JsonProperty("date_of_event")
    private String dateOfEvent;
    @JsonProperty("manufacturer_g1_country")
    private String manufacturerG1Country;
    @JsonProperty("removal_correction_number")
    private String removalCorrectionNumber;
    @JsonProperty("manufacturer_g1_city")
    private String manufacturerG1City;
    @JsonProperty("mdr_text")
    private List<Object> mdrText = new ArrayList<Object>();
    @JsonProperty("distributor_zip_code_ext")
    private String distributorZipCodeExt;
    @JsonProperty("date_received")
    private String dateReceived;
    @JsonProperty("manufacturer_zip_code")
    private String manufacturerZipCode;
    @JsonProperty("manufacturer_contact_address_2")
    private String manufacturerContactAddress2;
    @JsonProperty("date_report")
    private String dateReport;
    @JsonProperty("manufacturer_contact_address_1")
    private String manufacturerContactAddress1;
    @JsonProperty("manufacturer_contact_plocal")
    private String manufacturerContactPlocal;
    @JsonProperty("manufacturer_name")
    private String manufacturerName;
    @JsonProperty("type_of_report")
    private List<String> typeOfReport = new ArrayList<String>();
    @JsonProperty("reprocessed_and_reused_flag")
    private String reprocessedAndReusedFlag;
    @JsonProperty("manufacturer_link_flag")
    private String manufacturerLinkFlag;
    @JsonProperty("manufacturer_contact_zip_code")
    private String manufacturerContactZipCode;
    @JsonProperty("manufacturer_g1_zip_code_ext")
    private String manufacturerG1ZipCodeExt;
    @JsonProperty("manufacturer_contact_country")
    private String manufacturerContactCountry;
    @JsonProperty("distributor_zip_code")
    private String distributorZipCode;
    @JsonProperty("report_source_code")
    private String reportSourceCode;
    @JsonProperty("distributor_name")
    private String distributorName;
    @JsonProperty("date_manufacturer_received")
    private String dateManufacturerReceived;
    @JsonProperty("remedial_action")
    private String remedialAction;
    @JsonProperty("manufacturer_contact_postal_code")
    private String manufacturerContactPostalCode;
    @JsonProperty("manufacturer_contact_city")
    private String manufacturerContactCity;
    @JsonProperty("product_problem_flag")
    private String productProblemFlag;
    @JsonProperty("event_key")
    private String eventKey;
    @JsonProperty("manufacturer_g1_zip_code")
    private String manufacturerG1ZipCode;
    @JsonProperty("manufacturer_g1_state")
    private String manufacturerG1State;
    @JsonProperty("patient")
    private List<Patient> patient = new ArrayList<Patient>();
    @JsonProperty("manufacturer_contact_phone_number")
    private String manufacturerContactPhoneNumber;
    @JsonProperty("manufacturer_zip_code_ext")
    private String manufacturerZipCodeExt;
    @JsonProperty("manufacturer_contact_pcity")
    private String manufacturerContactPcity;
    @JsonProperty("manufacturer_g1_postal_code")
    private String manufacturerG1PostalCode;
    @JsonProperty("manufacturer_contact_area_code")
    private String manufacturerContactAreaCode;
    @JsonProperty("adverse_event_flag")
    private String adverseEventFlag;
    @JsonProperty("single_use_flag")
    private String singleUseFlag;
    @JsonProperty("distributor_city")
    private String distributorCity;
    @JsonProperty("manufacturer_country")
    private String manufacturerCountry;
    @JsonProperty("manufacturer_contact_f_name")
    private String manufacturerContactFName;
    @JsonProperty("distributor_state")
    private String distributorState;
    @JsonProperty("number_devices_in_event")
    private String numberDevicesInEvent;
    @JsonProperty("manufacturer_contact_exchange")
    private String manufacturerContactExchange;
    @JsonProperty("distributor_address_2")
    private String distributorAddress2;
    @JsonProperty("manufacturer_contact_t_name")
    private String manufacturerContactTName;
    @JsonProperty("distributor_address_1")
    private String distributorAddress1;
    @JsonProperty("manufacturer_g1_address_2")
    private String manufacturerG1Address2;
    @JsonProperty("health_professional")
    private String healthProfessional;
    @JsonProperty("initial_report_to_fda")
    private String initialReportToFda;
    @JsonProperty("number_patients_in_event")
    private String numberPatientsInEvent;
    @JsonProperty("mdr_report_key")
    private String mdrReportKey;
    @JsonProperty("report_to_fda")
    private String reportToFda;
    @JsonProperty("manufacturer_address_2")
    private String manufacturerAddress2;
    @JsonProperty("manufacturer_address_1")
    private String manufacturerAddress1;
    @JsonProperty("manufacturer_g1_name")
    private String manufacturerG1Name;
    @JsonProperty("manufacturer_contact_zip_ext")
    private String manufacturerContactZipExt;
    @JsonProperty("manufacturer_contact_l_name")
    private String manufacturerContactLName;
    @JsonProperty("device")
    private List<Device> device = new ArrayList<Device>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The reportNumber
     */
    @JsonProperty("report_number")
    public String getReportNumber() {
        return reportNumber;
    }

    /**
     * 
     * @param reportNumber
     *     The report_number
     */
    @JsonProperty("report_number")
    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerContactState
     */
    @JsonProperty("manufacturer_contact_state")
    public String getManufacturerContactState() {
        return manufacturerContactState;
    }

    /**
     * 
     * @param manufacturerContactState
     *     The manufacturer_contact_state
     */
    @JsonProperty("manufacturer_contact_state")
    public void setManufacturerContactState(String manufacturerContactState) {
        this.manufacturerContactState = manufacturerContactState;
    }

    /**
     * 
     * @return
     *     The deviceDateOfManufacturer
     */
    @JsonProperty("device_date_of_manufacturer")
    public String getDeviceDateOfManufacturer() {
        return deviceDateOfManufacturer;
    }

    /**
     * 
     * @param deviceDateOfManufacturer
     *     The device_date_of_manufacturer
     */
    @JsonProperty("device_date_of_manufacturer")
    public void setDeviceDateOfManufacturer(String deviceDateOfManufacturer) {
        this.deviceDateOfManufacturer = deviceDateOfManufacturer;
    }

    /**
     * 
     * @return
     *     The eventType
     */
    @JsonProperty("event_type")
    public String getEventType() {
        return eventType;
    }

    /**
     * 
     * @param eventType
     *     The event_type
     */
    @JsonProperty("event_type")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * 
     * @return
     *     The sourceType
     */
    @JsonProperty("source_type")
    public List<Object> getSourceType() {
        return sourceType;
    }

    /**
     * 
     * @param sourceType
     *     The source_type
     */
    @JsonProperty("source_type")
    public void setSourceType(List<Object> sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 
     * @return
     *     The manufacturerG1Address1
     */
    @JsonProperty("manufacturer_g1_address_1")
    public String getManufacturerG1Address1() {
        return manufacturerG1Address1;
    }

    /**
     * 
     * @param manufacturerG1Address1
     *     The manufacturer_g1_address_1
     */
    @JsonProperty("manufacturer_g1_address_1")
    public void setManufacturerG1Address1(String manufacturerG1Address1) {
        this.manufacturerG1Address1 = manufacturerG1Address1;
    }

    /**
     * 
     * @return
     *     The manufacturerContactPcountry
     */
    @JsonProperty("manufacturer_contact_pcountry")
    public String getManufacturerContactPcountry() {
        return manufacturerContactPcountry;
    }

    /**
     * 
     * @param manufacturerContactPcountry
     *     The manufacturer_contact_pcountry
     */
    @JsonProperty("manufacturer_contact_pcountry")
    public void setManufacturerContactPcountry(String manufacturerContactPcountry) {
        this.manufacturerContactPcountry = manufacturerContactPcountry;
    }

    /**
     * 
     * @return
     *     The reportToManufacturer
     */
    @JsonProperty("report_to_manufacturer")
    public String getReportToManufacturer() {
        return reportToManufacturer;
    }

    /**
     * 
     * @param reportToManufacturer
     *     The report_to_manufacturer
     */
    @JsonProperty("report_to_manufacturer")
    public void setReportToManufacturer(String reportToManufacturer) {
        this.reportToManufacturer = reportToManufacturer;
    }

    /**
     * 
     * @return
     *     The previousUseCode
     */
    @JsonProperty("previous_use_code")
    public String getPreviousUseCode() {
        return previousUseCode;
    }

    /**
     * 
     * @param previousUseCode
     *     The previous_use_code
     */
    @JsonProperty("previous_use_code")
    public void setPreviousUseCode(String previousUseCode) {
        this.previousUseCode = previousUseCode;
    }

    /**
     * 
     * @return
     *     The manufacturerPostalCode
     */
    @JsonProperty("manufacturer_postal_code")
    public String getManufacturerPostalCode() {
        return manufacturerPostalCode;
    }

    /**
     * 
     * @param manufacturerPostalCode
     *     The manufacturer_postal_code
     */
    @JsonProperty("manufacturer_postal_code")
    public void setManufacturerPostalCode(String manufacturerPostalCode) {
        this.manufacturerPostalCode = manufacturerPostalCode;
    }

    /**
     * 
     * @return
     *     The manufacturerCity
     */
    @JsonProperty("manufacturer_city")
    public String getManufacturerCity() {
        return manufacturerCity;
    }

    /**
     * 
     * @param manufacturerCity
     *     The manufacturer_city
     */
    @JsonProperty("manufacturer_city")
    public void setManufacturerCity(String manufacturerCity) {
        this.manufacturerCity = manufacturerCity;
    }

    /**
     * 
     * @return
     *     The reporterOccupationCode
     */
    @JsonProperty("reporter_occupation_code")
    public String getReporterOccupationCode() {
        return reporterOccupationCode;
    }

    /**
     * 
     * @param reporterOccupationCode
     *     The reporter_occupation_code
     */
    @JsonProperty("reporter_occupation_code")
    public void setReporterOccupationCode(String reporterOccupationCode) {
        this.reporterOccupationCode = reporterOccupationCode;
    }

    /**
     * 
     * @return
     *     The manufacturerContactExtension
     */
    @JsonProperty("manufacturer_contact_extension")
    public String getManufacturerContactExtension() {
        return manufacturerContactExtension;
    }

    /**
     * 
     * @param manufacturerContactExtension
     *     The manufacturer_contact_extension
     */
    @JsonProperty("manufacturer_contact_extension")
    public void setManufacturerContactExtension(String manufacturerContactExtension) {
        this.manufacturerContactExtension = manufacturerContactExtension;
    }

    /**
     * 
     * @return
     *     The manufacturerState
     */
    @JsonProperty("manufacturer_state")
    public String getManufacturerState() {
        return manufacturerState;
    }

    /**
     * 
     * @param manufacturerState
     *     The manufacturer_state
     */
    @JsonProperty("manufacturer_state")
    public void setManufacturerState(String manufacturerState) {
        this.manufacturerState = manufacturerState;
    }

    /**
     * 
     * @return
     *     The eventLocation
     */
    @JsonProperty("event_location")
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * 
     * @param eventLocation
     *     The event_location
     */
    @JsonProperty("event_location")
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * 
     * @return
     *     The dateOfEvent
     */
    @JsonProperty("date_of_event")
    public String getDateOfEvent() {
        return dateOfEvent;
    }

    /**
     * 
     * @param dateOfEvent
     *     The date_of_event
     */
    @JsonProperty("date_of_event")
    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * 
     * @return
     *     The manufacturerG1Country
     */
    @JsonProperty("manufacturer_g1_country")
    public String getManufacturerG1Country() {
        return manufacturerG1Country;
    }

    /**
     * 
     * @param manufacturerG1Country
     *     The manufacturer_g1_country
     */
    @JsonProperty("manufacturer_g1_country")
    public void setManufacturerG1Country(String manufacturerG1Country) {
        this.manufacturerG1Country = manufacturerG1Country;
    }

    /**
     * 
     * @return
     *     The removalCorrectionNumber
     */
    @JsonProperty("removal_correction_number")
    public String getRemovalCorrectionNumber() {
        return removalCorrectionNumber;
    }

    /**
     * 
     * @param removalCorrectionNumber
     *     The removal_correction_number
     */
    @JsonProperty("removal_correction_number")
    public void setRemovalCorrectionNumber(String removalCorrectionNumber) {
        this.removalCorrectionNumber = removalCorrectionNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerG1City
     */
    @JsonProperty("manufacturer_g1_city")
    public String getManufacturerG1City() {
        return manufacturerG1City;
    }

    /**
     * 
     * @param manufacturerG1City
     *     The manufacturer_g1_city
     */
    @JsonProperty("manufacturer_g1_city")
    public void setManufacturerG1City(String manufacturerG1City) {
        this.manufacturerG1City = manufacturerG1City;
    }

    /**
     * 
     * @return
     *     The mdrText
     */
    @JsonProperty("mdr_text")
    public List<Object> getMdrText() {
        return mdrText;
    }

    /**
     * 
     * @param mdrText
     *     The mdr_text
     */
    @JsonProperty("mdr_text")
    public void setMdrText(List<Object> mdrText) {
        this.mdrText = mdrText;
    }

    /**
     * 
     * @return
     *     The distributorZipCodeExt
     */
    @JsonProperty("distributor_zip_code_ext")
    public String getDistributorZipCodeExt() {
        return distributorZipCodeExt;
    }

    /**
     * 
     * @param distributorZipCodeExt
     *     The distributor_zip_code_ext
     */
    @JsonProperty("distributor_zip_code_ext")
    public void setDistributorZipCodeExt(String distributorZipCodeExt) {
        this.distributorZipCodeExt = distributorZipCodeExt;
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
     *     The manufacturerZipCode
     */
    @JsonProperty("manufacturer_zip_code")
    public String getManufacturerZipCode() {
        return manufacturerZipCode;
    }

    /**
     * 
     * @param manufacturerZipCode
     *     The manufacturer_zip_code
     */
    @JsonProperty("manufacturer_zip_code")
    public void setManufacturerZipCode(String manufacturerZipCode) {
        this.manufacturerZipCode = manufacturerZipCode;
    }

    /**
     * 
     * @return
     *     The manufacturerContactAddress2
     */
    @JsonProperty("manufacturer_contact_address_2")
    public String getManufacturerContactAddress2() {
        return manufacturerContactAddress2;
    }

    /**
     * 
     * @param manufacturerContactAddress2
     *     The manufacturer_contact_address_2
     */
    @JsonProperty("manufacturer_contact_address_2")
    public void setManufacturerContactAddress2(String manufacturerContactAddress2) {
        this.manufacturerContactAddress2 = manufacturerContactAddress2;
    }

    /**
     * 
     * @return
     *     The dateReport
     */
    @JsonProperty("date_report")
    public String getDateReport() {
        return dateReport;
    }

    /**
     * 
     * @param dateReport
     *     The date_report
     */
    @JsonProperty("date_report")
    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }

    /**
     * 
     * @return
     *     The manufacturerContactAddress1
     */
    @JsonProperty("manufacturer_contact_address_1")
    public String getManufacturerContactAddress1() {
        return manufacturerContactAddress1;
    }

    /**
     * 
     * @param manufacturerContactAddress1
     *     The manufacturer_contact_address_1
     */
    @JsonProperty("manufacturer_contact_address_1")
    public void setManufacturerContactAddress1(String manufacturerContactAddress1) {
        this.manufacturerContactAddress1 = manufacturerContactAddress1;
    }

    /**
     * 
     * @return
     *     The manufacturerContactPlocal
     */
    @JsonProperty("manufacturer_contact_plocal")
    public String getManufacturerContactPlocal() {
        return manufacturerContactPlocal;
    }

    /**
     * 
     * @param manufacturerContactPlocal
     *     The manufacturer_contact_plocal
     */
    @JsonProperty("manufacturer_contact_plocal")
    public void setManufacturerContactPlocal(String manufacturerContactPlocal) {
        this.manufacturerContactPlocal = manufacturerContactPlocal;
    }

    /**
     * 
     * @return
     *     The manufacturerName
     */
    @JsonProperty("manufacturer_name")
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * 
     * @param manufacturerName
     *     The manufacturer_name
     */
    @JsonProperty("manufacturer_name")
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * 
     * @return
     *     The typeOfReport
     */
    @JsonProperty("type_of_report")
    public List<String> getTypeOfReport() {
        return typeOfReport;
    }

    /**
     * 
     * @param typeOfReport
     *     The type_of_report
     */
    @JsonProperty("type_of_report")
    public void setTypeOfReport(List<String> typeOfReport) {
        this.typeOfReport = typeOfReport;
    }

    /**
     * 
     * @return
     *     The reprocessedAndReusedFlag
     */
    @JsonProperty("reprocessed_and_reused_flag")
    public String getReprocessedAndReusedFlag() {
        return reprocessedAndReusedFlag;
    }

    /**
     * 
     * @param reprocessedAndReusedFlag
     *     The reprocessed_and_reused_flag
     */
    @JsonProperty("reprocessed_and_reused_flag")
    public void setReprocessedAndReusedFlag(String reprocessedAndReusedFlag) {
        this.reprocessedAndReusedFlag = reprocessedAndReusedFlag;
    }

    /**
     * 
     * @return
     *     The manufacturerLinkFlag
     */
    @JsonProperty("manufacturer_link_flag")
    public String getManufacturerLinkFlag() {
        return manufacturerLinkFlag;
    }

    /**
     * 
     * @param manufacturerLinkFlag
     *     The manufacturer_link_flag
     */
    @JsonProperty("manufacturer_link_flag")
    public void setManufacturerLinkFlag(String manufacturerLinkFlag) {
        this.manufacturerLinkFlag = manufacturerLinkFlag;
    }

    /**
     * 
     * @return
     *     The manufacturerContactZipCode
     */
    @JsonProperty("manufacturer_contact_zip_code")
    public String getManufacturerContactZipCode() {
        return manufacturerContactZipCode;
    }

    /**
     * 
     * @param manufacturerContactZipCode
     *     The manufacturer_contact_zip_code
     */
    @JsonProperty("manufacturer_contact_zip_code")
    public void setManufacturerContactZipCode(String manufacturerContactZipCode) {
        this.manufacturerContactZipCode = manufacturerContactZipCode;
    }

    /**
     * 
     * @return
     *     The manufacturerG1ZipCodeExt
     */
    @JsonProperty("manufacturer_g1_zip_code_ext")
    public String getManufacturerG1ZipCodeExt() {
        return manufacturerG1ZipCodeExt;
    }

    /**
     * 
     * @param manufacturerG1ZipCodeExt
     *     The manufacturer_g1_zip_code_ext
     */
    @JsonProperty("manufacturer_g1_zip_code_ext")
    public void setManufacturerG1ZipCodeExt(String manufacturerG1ZipCodeExt) {
        this.manufacturerG1ZipCodeExt = manufacturerG1ZipCodeExt;
    }

    /**
     * 
     * @return
     *     The manufacturerContactCountry
     */
    @JsonProperty("manufacturer_contact_country")
    public String getManufacturerContactCountry() {
        return manufacturerContactCountry;
    }

    /**
     * 
     * @param manufacturerContactCountry
     *     The manufacturer_contact_country
     */
    @JsonProperty("manufacturer_contact_country")
    public void setManufacturerContactCountry(String manufacturerContactCountry) {
        this.manufacturerContactCountry = manufacturerContactCountry;
    }

    /**
     * 
     * @return
     *     The distributorZipCode
     */
    @JsonProperty("distributor_zip_code")
    public String getDistributorZipCode() {
        return distributorZipCode;
    }

    /**
     * 
     * @param distributorZipCode
     *     The distributor_zip_code
     */
    @JsonProperty("distributor_zip_code")
    public void setDistributorZipCode(String distributorZipCode) {
        this.distributorZipCode = distributorZipCode;
    }

    /**
     * 
     * @return
     *     The reportSourceCode
     */
    @JsonProperty("report_source_code")
    public String getReportSourceCode() {
        return reportSourceCode;
    }

    /**
     * 
     * @param reportSourceCode
     *     The report_source_code
     */
    @JsonProperty("report_source_code")
    public void setReportSourceCode(String reportSourceCode) {
        this.reportSourceCode = reportSourceCode;
    }

    /**
     * 
     * @return
     *     The distributorName
     */
    @JsonProperty("distributor_name")
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * 
     * @param distributorName
     *     The distributor_name
     */
    @JsonProperty("distributor_name")
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * 
     * @return
     *     The dateManufacturerReceived
     */
    @JsonProperty("date_manufacturer_received")
    public String getDateManufacturerReceived() {
        return dateManufacturerReceived;
    }

    /**
     * 
     * @param dateManufacturerReceived
     *     The date_manufacturer_received
     */
    @JsonProperty("date_manufacturer_received")
    public void setDateManufacturerReceived(String dateManufacturerReceived) {
        this.dateManufacturerReceived = dateManufacturerReceived;
    }

    /**
     * 
     * @return
     *     The remedialAction
     */
    @JsonProperty("remedial_action")
    public String getRemedialAction() {
        return remedialAction;
    }

    /**
     * 
     * @param remedialAction
     *     The remedial_action
     */
    @JsonProperty("remedial_action")
    public void setRemedialAction(String remedialAction) {
        this.remedialAction = remedialAction;
    }

    /**
     * 
     * @return
     *     The manufacturerContactPostalCode
     */
    @JsonProperty("manufacturer_contact_postal_code")
    public String getManufacturerContactPostalCode() {
        return manufacturerContactPostalCode;
    }

    /**
     * 
     * @param manufacturerContactPostalCode
     *     The manufacturer_contact_postal_code
     */
    @JsonProperty("manufacturer_contact_postal_code")
    public void setManufacturerContactPostalCode(String manufacturerContactPostalCode) {
        this.manufacturerContactPostalCode = manufacturerContactPostalCode;
    }

    /**
     * 
     * @return
     *     The manufacturerContactCity
     */
    @JsonProperty("manufacturer_contact_city")
    public String getManufacturerContactCity() {
        return manufacturerContactCity;
    }

    /**
     * 
     * @param manufacturerContactCity
     *     The manufacturer_contact_city
     */
    @JsonProperty("manufacturer_contact_city")
    public void setManufacturerContactCity(String manufacturerContactCity) {
        this.manufacturerContactCity = manufacturerContactCity;
    }

    /**
     * 
     * @return
     *     The productProblemFlag
     */
    @JsonProperty("product_problem_flag")
    public String getProductProblemFlag() {
        return productProblemFlag;
    }

    /**
     * 
     * @param productProblemFlag
     *     The product_problem_flag
     */
    @JsonProperty("product_problem_flag")
    public void setProductProblemFlag(String productProblemFlag) {
        this.productProblemFlag = productProblemFlag;
    }

    /**
     * 
     * @return
     *     The eventKey
     */
    @JsonProperty("event_key")
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 
     * @param eventKey
     *     The event_key
     */
    @JsonProperty("event_key")
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 
     * @return
     *     The manufacturerG1ZipCode
     */
    @JsonProperty("manufacturer_g1_zip_code")
    public String getManufacturerG1ZipCode() {
        return manufacturerG1ZipCode;
    }

    /**
     * 
     * @param manufacturerG1ZipCode
     *     The manufacturer_g1_zip_code
     */
    @JsonProperty("manufacturer_g1_zip_code")
    public void setManufacturerG1ZipCode(String manufacturerG1ZipCode) {
        this.manufacturerG1ZipCode = manufacturerG1ZipCode;
    }

    /**
     * 
     * @return
     *     The manufacturerG1State
     */
    @JsonProperty("manufacturer_g1_state")
    public String getManufacturerG1State() {
        return manufacturerG1State;
    }

    /**
     * 
     * @param manufacturerG1State
     *     The manufacturer_g1_state
     */
    @JsonProperty("manufacturer_g1_state")
    public void setManufacturerG1State(String manufacturerG1State) {
        this.manufacturerG1State = manufacturerG1State;
    }

    /**
     * 
     * @return
     *     The patient
     */
    @JsonProperty("patient")
    public List<Patient> getPatient() {
        return patient;
    }

    /**
     * 
     * @param patient
     *     The patient
     */
    @JsonProperty("patient")
    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    /**
     * 
     * @return
     *     The manufacturerContactPhoneNumber
     */
    @JsonProperty("manufacturer_contact_phone_number")
    public String getManufacturerContactPhoneNumber() {
        return manufacturerContactPhoneNumber;
    }

    /**
     * 
     * @param manufacturerContactPhoneNumber
     *     The manufacturer_contact_phone_number
     */
    @JsonProperty("manufacturer_contact_phone_number")
    public void setManufacturerContactPhoneNumber(String manufacturerContactPhoneNumber) {
        this.manufacturerContactPhoneNumber = manufacturerContactPhoneNumber;
    }

    /**
     * 
     * @return
     *     The manufacturerZipCodeExt
     */
    @JsonProperty("manufacturer_zip_code_ext")
    public String getManufacturerZipCodeExt() {
        return manufacturerZipCodeExt;
    }

    /**
     * 
     * @param manufacturerZipCodeExt
     *     The manufacturer_zip_code_ext
     */
    @JsonProperty("manufacturer_zip_code_ext")
    public void setManufacturerZipCodeExt(String manufacturerZipCodeExt) {
        this.manufacturerZipCodeExt = manufacturerZipCodeExt;
    }

    /**
     * 
     * @return
     *     The manufacturerContactPcity
     */
    @JsonProperty("manufacturer_contact_pcity")
    public String getManufacturerContactPcity() {
        return manufacturerContactPcity;
    }

    /**
     * 
     * @param manufacturerContactPcity
     *     The manufacturer_contact_pcity
     */
    @JsonProperty("manufacturer_contact_pcity")
    public void setManufacturerContactPcity(String manufacturerContactPcity) {
        this.manufacturerContactPcity = manufacturerContactPcity;
    }

    /**
     * 
     * @return
     *     The manufacturerG1PostalCode
     */
    @JsonProperty("manufacturer_g1_postal_code")
    public String getManufacturerG1PostalCode() {
        return manufacturerG1PostalCode;
    }

    /**
     * 
     * @param manufacturerG1PostalCode
     *     The manufacturer_g1_postal_code
     */
    @JsonProperty("manufacturer_g1_postal_code")
    public void setManufacturerG1PostalCode(String manufacturerG1PostalCode) {
        this.manufacturerG1PostalCode = manufacturerG1PostalCode;
    }

    /**
     * 
     * @return
     *     The manufacturerContactAreaCode
     */
    @JsonProperty("manufacturer_contact_area_code")
    public String getManufacturerContactAreaCode() {
        return manufacturerContactAreaCode;
    }

    /**
     * 
     * @param manufacturerContactAreaCode
     *     The manufacturer_contact_area_code
     */
    @JsonProperty("manufacturer_contact_area_code")
    public void setManufacturerContactAreaCode(String manufacturerContactAreaCode) {
        this.manufacturerContactAreaCode = manufacturerContactAreaCode;
    }

    /**
     * 
     * @return
     *     The adverseEventFlag
     */
    @JsonProperty("adverse_event_flag")
    public String getAdverseEventFlag() {
        return adverseEventFlag;
    }

    /**
     * 
     * @param adverseEventFlag
     *     The adverse_event_flag
     */
    @JsonProperty("adverse_event_flag")
    public void setAdverseEventFlag(String adverseEventFlag) {
        this.adverseEventFlag = adverseEventFlag;
    }

    /**
     * 
     * @return
     *     The singleUseFlag
     */
    @JsonProperty("single_use_flag")
    public String getSingleUseFlag() {
        return singleUseFlag;
    }

    /**
     * 
     * @param singleUseFlag
     *     The single_use_flag
     */
    @JsonProperty("single_use_flag")
    public void setSingleUseFlag(String singleUseFlag) {
        this.singleUseFlag = singleUseFlag;
    }

    /**
     * 
     * @return
     *     The distributorCity
     */
    @JsonProperty("distributor_city")
    public String getDistributorCity() {
        return distributorCity;
    }

    /**
     * 
     * @param distributorCity
     *     The distributor_city
     */
    @JsonProperty("distributor_city")
    public void setDistributorCity(String distributorCity) {
        this.distributorCity = distributorCity;
    }

    /**
     * 
     * @return
     *     The manufacturerCountry
     */
    @JsonProperty("manufacturer_country")
    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    /**
     * 
     * @param manufacturerCountry
     *     The manufacturer_country
     */
    @JsonProperty("manufacturer_country")
    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    /**
     * 
     * @return
     *     The manufacturerContactFName
     */
    @JsonProperty("manufacturer_contact_f_name")
    public String getManufacturerContactFName() {
        return manufacturerContactFName;
    }

    /**
     * 
     * @param manufacturerContactFName
     *     The manufacturer_contact_f_name
     */
    @JsonProperty("manufacturer_contact_f_name")
    public void setManufacturerContactFName(String manufacturerContactFName) {
        this.manufacturerContactFName = manufacturerContactFName;
    }

    /**
     * 
     * @return
     *     The distributorState
     */
    @JsonProperty("distributor_state")
    public String getDistributorState() {
        return distributorState;
    }

    /**
     * 
     * @param distributorState
     *     The distributor_state
     */
    @JsonProperty("distributor_state")
    public void setDistributorState(String distributorState) {
        this.distributorState = distributorState;
    }

    /**
     * 
     * @return
     *     The numberDevicesInEvent
     */
    @JsonProperty("number_devices_in_event")
    public String getNumberDevicesInEvent() {
        return numberDevicesInEvent;
    }

    /**
     * 
     * @param numberDevicesInEvent
     *     The number_devices_in_event
     */
    @JsonProperty("number_devices_in_event")
    public void setNumberDevicesInEvent(String numberDevicesInEvent) {
        this.numberDevicesInEvent = numberDevicesInEvent;
    }

    /**
     * 
     * @return
     *     The manufacturerContactExchange
     */
    @JsonProperty("manufacturer_contact_exchange")
    public String getManufacturerContactExchange() {
        return manufacturerContactExchange;
    }

    /**
     * 
     * @param manufacturerContactExchange
     *     The manufacturer_contact_exchange
     */
    @JsonProperty("manufacturer_contact_exchange")
    public void setManufacturerContactExchange(String manufacturerContactExchange) {
        this.manufacturerContactExchange = manufacturerContactExchange;
    }

    /**
     * 
     * @return
     *     The distributorAddress2
     */
    @JsonProperty("distributor_address_2")
    public String getDistributorAddress2() {
        return distributorAddress2;
    }

    /**
     * 
     * @param distributorAddress2
     *     The distributor_address_2
     */
    @JsonProperty("distributor_address_2")
    public void setDistributorAddress2(String distributorAddress2) {
        this.distributorAddress2 = distributorAddress2;
    }

    /**
     * 
     * @return
     *     The manufacturerContactTName
     */
    @JsonProperty("manufacturer_contact_t_name")
    public String getManufacturerContactTName() {
        return manufacturerContactTName;
    }

    /**
     * 
     * @param manufacturerContactTName
     *     The manufacturer_contact_t_name
     */
    @JsonProperty("manufacturer_contact_t_name")
    public void setManufacturerContactTName(String manufacturerContactTName) {
        this.manufacturerContactTName = manufacturerContactTName;
    }

    /**
     * 
     * @return
     *     The distributorAddress1
     */
    @JsonProperty("distributor_address_1")
    public String getDistributorAddress1() {
        return distributorAddress1;
    }

    /**
     * 
     * @param distributorAddress1
     *     The distributor_address_1
     */
    @JsonProperty("distributor_address_1")
    public void setDistributorAddress1(String distributorAddress1) {
        this.distributorAddress1 = distributorAddress1;
    }

    /**
     * 
     * @return
     *     The manufacturerG1Address2
     */
    @JsonProperty("manufacturer_g1_address_2")
    public String getManufacturerG1Address2() {
        return manufacturerG1Address2;
    }

    /**
     * 
     * @param manufacturerG1Address2
     *     The manufacturer_g1_address_2
     */
    @JsonProperty("manufacturer_g1_address_2")
    public void setManufacturerG1Address2(String manufacturerG1Address2) {
        this.manufacturerG1Address2 = manufacturerG1Address2;
    }

    /**
     * 
     * @return
     *     The healthProfessional
     */
    @JsonProperty("health_professional")
    public String getHealthProfessional() {
        return healthProfessional;
    }

    /**
     * 
     * @param healthProfessional
     *     The health_professional
     */
    @JsonProperty("health_professional")
    public void setHealthProfessional(String healthProfessional) {
        this.healthProfessional = healthProfessional;
    }

    /**
     * 
     * @return
     *     The initialReportToFda
     */
    @JsonProperty("initial_report_to_fda")
    public String getInitialReportToFda() {
        return initialReportToFda;
    }

    /**
     * 
     * @param initialReportToFda
     *     The initial_report_to_fda
     */
    @JsonProperty("initial_report_to_fda")
    public void setInitialReportToFda(String initialReportToFda) {
        this.initialReportToFda = initialReportToFda;
    }

    /**
     * 
     * @return
     *     The numberPatientsInEvent
     */
    @JsonProperty("number_patients_in_event")
    public String getNumberPatientsInEvent() {
        return numberPatientsInEvent;
    }

    /**
     * 
     * @param numberPatientsInEvent
     *     The number_patients_in_event
     */
    @JsonProperty("number_patients_in_event")
    public void setNumberPatientsInEvent(String numberPatientsInEvent) {
        this.numberPatientsInEvent = numberPatientsInEvent;
    }

    /**
     * 
     * @return
     *     The mdrReportKey
     */
    @JsonProperty("mdr_report_key")
    public String getMdrReportKey() {
        return mdrReportKey;
    }

    /**
     * 
     * @param mdrReportKey
     *     The mdr_report_key
     */
    @JsonProperty("mdr_report_key")
    public void setMdrReportKey(String mdrReportKey) {
        this.mdrReportKey = mdrReportKey;
    }

    /**
     * 
     * @return
     *     The reportToFda
     */
    @JsonProperty("report_to_fda")
    public String getReportToFda() {
        return reportToFda;
    }

    /**
     * 
     * @param reportToFda
     *     The report_to_fda
     */
    @JsonProperty("report_to_fda")
    public void setReportToFda(String reportToFda) {
        this.reportToFda = reportToFda;
    }

    /**
     * 
     * @return
     *     The manufacturerAddress2
     */
    @JsonProperty("manufacturer_address_2")
    public String getManufacturerAddress2() {
        return manufacturerAddress2;
    }

    /**
     * 
     * @param manufacturerAddress2
     *     The manufacturer_address_2
     */
    @JsonProperty("manufacturer_address_2")
    public void setManufacturerAddress2(String manufacturerAddress2) {
        this.manufacturerAddress2 = manufacturerAddress2;
    }

    /**
     * 
     * @return
     *     The manufacturerAddress1
     */
    @JsonProperty("manufacturer_address_1")
    public String getManufacturerAddress1() {
        return manufacturerAddress1;
    }

    /**
     * 
     * @param manufacturerAddress1
     *     The manufacturer_address_1
     */
    @JsonProperty("manufacturer_address_1")
    public void setManufacturerAddress1(String manufacturerAddress1) {
        this.manufacturerAddress1 = manufacturerAddress1;
    }

    /**
     * 
     * @return
     *     The manufacturerG1Name
     */
    @JsonProperty("manufacturer_g1_name")
    public String getManufacturerG1Name() {
        return manufacturerG1Name;
    }

    /**
     * 
     * @param manufacturerG1Name
     *     The manufacturer_g1_name
     */
    @JsonProperty("manufacturer_g1_name")
    public void setManufacturerG1Name(String manufacturerG1Name) {
        this.manufacturerG1Name = manufacturerG1Name;
    }

    /**
     * 
     * @return
     *     The manufacturerContactZipExt
     */
    @JsonProperty("manufacturer_contact_zip_ext")
    public String getManufacturerContactZipExt() {
        return manufacturerContactZipExt;
    }

    /**
     * 
     * @param manufacturerContactZipExt
     *     The manufacturer_contact_zip_ext
     */
    @JsonProperty("manufacturer_contact_zip_ext")
    public void setManufacturerContactZipExt(String manufacturerContactZipExt) {
        this.manufacturerContactZipExt = manufacturerContactZipExt;
    }

    /**
     * 
     * @return
     *     The manufacturerContactLName
     */
    @JsonProperty("manufacturer_contact_l_name")
    public String getManufacturerContactLName() {
        return manufacturerContactLName;
    }

    /**
     * 
     * @param manufacturerContactLName
     *     The manufacturer_contact_l_name
     */
    @JsonProperty("manufacturer_contact_l_name")
    public void setManufacturerContactLName(String manufacturerContactLName) {
        this.manufacturerContactLName = manufacturerContactLName;
    }

    /**
     * 
     * @return
     *     The device
     */
    @JsonProperty("device")
    public List<Device> getDevice() {
        return device;
    }

    /**
     * 
     * @param device
     *     The device
     */
    @JsonProperty("device")
    public void setDevice(List<Device> device) {
        this.device = device;
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
