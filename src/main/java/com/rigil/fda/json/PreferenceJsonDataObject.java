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
    "fdaJsonDataObject",
    "fdaResponse"
})

public class PreferenceJsonDataObject {

    @JsonProperty("fdaJsonDataObject")
    private FDAJsonDataObject fdaJsonDataObject;
    @JsonProperty("fdaResponse")
    private FDADataResponse fdaResponse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The fdaJsonDataObject
     */
    @JsonProperty("fdaJsonDataObject")
    public FDAJsonDataObject getFdaJsonDataObject() {
        return fdaJsonDataObject;
    }

    /**
     *
     * @param fdaJsonDataObject
     *     The fdaJsonDataObject
     */
    @JsonProperty("fdaJsonDataObject")
    public void setFdaJsonDataObject(FDAJsonDataObject fdaJsonDataObject) {
        this.fdaJsonDataObject = fdaJsonDataObject;
    }

    /**
     *
     * @return
     *     The fdaResponse
     */
    @JsonProperty("fdaResponse")
    public FDADataResponse getFdaResponse() {
        return fdaResponse;
    }

    /**
     *
     * @param fdaResponse
     *     The fdaResponse
     */
    @JsonProperty("fdaResponse")
    public void setFdaResponse(FDADataResponse fdaResponse) {
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
        return new HashCodeBuilder().append(fdaJsonDataObject).append(fdaResponse).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PreferenceJsonDataObject) == false) {
            return false;
        }
        PreferenceJsonDataObject rhs = ((PreferenceJsonDataObject) other);
        return new EqualsBuilder().append(fdaJsonDataObject, rhs.fdaJsonDataObject).append(fdaResponse, rhs.fdaResponse).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
