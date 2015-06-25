package com.rigil.fda.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "manufacturerName",
    "genericName",
    "modelNumber",
    "eventType",
    "dateOfEvent",
    "text",
})

public class FDADeviceEventResult {

    @JsonProperty("manufacturerName")
    private String manufacturerName;
    @JsonProperty("genericName")
    private String genericName;
    @JsonProperty("modelNumber")
    private String modelNumber;
    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("dateOfEvent")
    private String dateOfEvent;
    @JsonProperty("text")
    private String text;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
manufacturerName
     */
    @JsonProperty("manufacturerName")
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * 
     * @param manufacturerName
     *     The manufacturerName
     */
    @JsonProperty("manufacturerName")
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * 
     * @return
     *     The genericName
     */
    @JsonProperty("genericName")
    public String getGenericName() {
        return genericName;
    }

    /**
     * 
     * @param genericName
     *     The genericName
     */
    @JsonProperty("genericName")
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    /**
     * 
     * @return
     *     The modelNumber
     */
    @JsonProperty("modelNumber")
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * 
     * @param modelNumber
     *     The modelNumber
     */
    @JsonProperty("modelNumber")
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * 
     * @return
     *     The eventType
     */
    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;
    }

    /**
     * 
     * @param eventType
     *     The eventType
     */
    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * 
     * @return
     *     The dateOfEvent
     */
    @JsonProperty("dateOfEvent")
    public String getDateOfEvent() {
        return dateOfEvent;
    }

    /**
     * 
     * @param dateOfEvent
     *     The dateOfEvent
     */
    @JsonProperty("dateOfEvent")
    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * 
     * @return
     *     The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(manufacturerName).append(genericName).append(modelNumber).append(eventType).append(dateOfEvent).append(text).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        FDADeviceEventResult rhs = ((FDADeviceEventResult) other);
        return new EqualsBuilder().append(manufacturerName, rhs.manufacturerName).append(genericName, rhs.genericName).append(modelNumber, rhs.modelNumber).append(eventType, rhs.eventType).append(dateOfEvent, rhs.dateOfEvent).append(text, rhs.text).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
