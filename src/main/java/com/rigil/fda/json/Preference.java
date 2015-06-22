package com.rigil.fda.json;

import java.util.HashMap;
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
    "fdaData",
    "fdaResponse"
})

public class Preference {

    @JsonProperty("fdaData")
    private FDAData fdaData;
    @JsonProperty("fdaResponse")
    private String fdaResponse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The fdaData
     */
    @JsonProperty("fdaData")
    public FDAData getFdaData() {
        return fdaData;
    }

    /**
     * 
     * @param fdaData
     *     The fdaData
     */
    @JsonProperty("fdaData")
    public void setFdaData(FDAData fdaData) {
        this.fdaData = fdaData;
    }

    /**
     * 
     * @return
     *     The fdaResponse
     */
    @JsonProperty("fdaResponse")
    public String getFdaResponse() {
        return fdaResponse;
    }

    /**
     * 
     * @param fdaResponse
     *     The fdaResponse
     */
    @JsonProperty("fdaResponse")
    public void setFdaResponse(String fdaResponse) {
        this.fdaResponse = fdaResponse;
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
        return new HashCodeBuilder().append(fdaData).append(fdaResponse).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Preference) == false) {
            return false;
        }
        Preference rhs = ((Preference) other);
        return new EqualsBuilder().append(fdaData, rhs.fdaData).append(fdaResponse, rhs.fdaResponse).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
