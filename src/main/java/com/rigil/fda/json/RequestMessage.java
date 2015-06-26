
package com.rigil.fda.json;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

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
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "authenticationRequest",
    "userRequestJsonDataObject",
    "fdaDataRequest"

})
public class RequestMessage {

    /**
     * Authentication Request Details
     *
     */
    @JsonProperty("authenticationRequest")
    private AuthenticationRequest authenticationRequest;

    /**
     * UserEntity Request Details
     *
     */
    @JsonProperty("userRequestJsonDataObject")
    private UserRequestJsonDataObject userRequestJsonDataObject;

    /**
     * UserEntity Request Details
     *
     */
    @JsonProperty("fdaDataRequest")
    private FDADataRequest fdaDataRequest;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * UserEntity Request Details
     *
     * @return
     *     The authenticationRequest
     */
    @JsonProperty("authenticationRequest")
    public AuthenticationRequest getAuthenticationRequest() {
        return authenticationRequest;
    }

    /**
     * authenticationRequest Details
     *
     * @param authenticationRequest
     *     The authenticationRequest
     */
    @JsonProperty("authenticationRequest")
    public void setAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        this.authenticationRequest = authenticationRequest;
    }

    /**
     * UserEntity Request Details
     *
     * @return
     *     The userRequestJsonDataObject
     */
    @JsonProperty("userRequestJsonDataObject")
    public UserRequestJsonDataObject getUserRequestJsonDataObject() {
        return userRequestJsonDataObject;
    }

    /**
     * Facility Request Details
     *
     * @param userRequestJsonDataObject
     *     The userRequestJsonDataObject
     */
    @JsonProperty("userRequestJsonDataObject")
    public void setUserRequestJsonDataObject(UserRequestJsonDataObject userRequestJsonDataObject) {
        this.userRequestJsonDataObject = userRequestJsonDataObject;
    }

    /**
     * fdaDataRequest Details
     *
     * @return
     *     The fdaDataRequest
     */
    @JsonProperty("fdaDataRequest")
    public FDADataRequest getFDADataRequest() {
        return fdaDataRequest;
    }

    /**
     * fdaDataRequestDetails
     *
     * @param fdaDataRequest
     *     The fdaDataRequest
     */
    @JsonProperty("fdaDataRequest")
    public void setFDADataRequest(FDADataRequest fdaDataRequest) {
        this.fdaDataRequest = fdaDataRequest;
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
        return new HashCodeBuilder().append(userRequestJsonDataObject).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RequestMessage) == false) {
            return false;
        }
        RequestMessage rhs = ((RequestMessage) other);
        return new EqualsBuilder().append(userRequestJsonDataObject, rhs.userRequestJsonDataObject).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
