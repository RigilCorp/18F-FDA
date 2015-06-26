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
    "eventResultsList",
    "enforcementResultsList"
})

public class FDADataResponse {

    @JsonProperty("eventResultsList")
    private List<FDADeviceEventResult> eventResultsList = new ArrayList<FDADeviceEventResult>();
    @JsonProperty("enforcementResultsList")
    private List<FDADeviceEnforcementResult> enforcementResultsList = new ArrayList<FDADeviceEnforcementResult>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The eventResultsList
     */
    @JsonProperty("eventResultsList")
    public List<FDADeviceEventResult> getEventResultsList() {
        return eventResultsList;
    }

    /**
     * 
     * @param eventResultsList
     *     The eventResultsList
     */
    @JsonProperty("eventResultsList")
    public void setEventResultsList(List<FDADeviceEventResult> eventResultsList) {
        this.eventResultsList = eventResultsList;
    }
    
    /**
     * 
     * @return
     *     The enforcementResultsList
     */
    @JsonProperty("enforcementResultsList")
    public List<FDADeviceEnforcementResult> getEnforcementResultsList() {
        return enforcementResultsList;
    }

    /**
     * 
     * @param enforcementResultsList
     *     The enforcementResultsList
     */
    @JsonProperty("enforcementResultsList")
    public void setEnforcementResultsList(List<FDADeviceEnforcementResult> enforcementResultsList) {
        this.enforcementResultsList = enforcementResultsList;
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
        return new HashCodeBuilder().append(eventResultsList).append(enforcementResultsList).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        FDADataResponse rhs = ((FDADataResponse) other);
        return new EqualsBuilder().append(eventResultsList, rhs.eventResultsList).append(enforcementResultsList, rhs.enforcementResultsList).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
