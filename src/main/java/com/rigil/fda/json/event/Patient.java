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
    "date_received",
    "patient_sequence_number",
    "sequence_number_treatment",
    "sequence_number_outcome"
})
public class Patient {

    @JsonProperty("date_received")
    private String dateReceived;
    @JsonProperty("patient_sequence_number")
    private String patientSequenceNumber;
    @JsonProperty("sequence_number_treatment")
    private String sequenceNumberTreatment;
    @JsonProperty("sequence_number_outcome")
    private List<Object> sequenceNumberOutcome = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The patientSequenceNumber
     */
    @JsonProperty("patient_sequence_number")
    public String getPatientSequenceNumber() {
        return patientSequenceNumber;
    }

    /**
     * 
     * @param patientSequenceNumber
     *     The patient_sequence_number
     */
    @JsonProperty("patient_sequence_number")
    public void setPatientSequenceNumber(String patientSequenceNumber) {
        this.patientSequenceNumber = patientSequenceNumber;
    }

    /**
     * 
     * @return
     *     The sequenceNumberTreatment
     */
    @JsonProperty("sequence_number_treatment")
    public String getSequenceNumberTreatment() {
        return sequenceNumberTreatment;
    }

    /**
     * 
     * @param sequenceNumberTreatment
     *     The sequence_number_treatment
     */
    @JsonProperty("sequence_number_treatment")
    public void setSequenceNumberTreatment(String sequenceNumberTreatment) {
        this.sequenceNumberTreatment = sequenceNumberTreatment;
    }

    /**
     * 
     * @return
     *     The sequenceNumberOutcome
     */
    @JsonProperty("sequence_number_outcome")
    public List<Object> getSequenceNumberOutcome() {
        return sequenceNumberOutcome;
    }

    /**
     * 
     * @param sequenceNumberOutcome
     *     The sequence_number_outcome
     */
    @JsonProperty("sequence_number_outcome")
    public void setSequenceNumberOutcome(List<Object> sequenceNumberOutcome) {
        this.sequenceNumberOutcome = sequenceNumberOutcome;
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
