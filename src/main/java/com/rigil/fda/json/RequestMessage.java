
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
    "userRequest"

})
public class RequestMessage {

    /**
     * User Request Details
     * 
     */
    @JsonProperty("userRequest")
    private UserRequest userRequest;
 
   
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * User Request Details
     * 
     * @return
     *     The facilityRequest
     */
    @JsonProperty("userRequest")
    public UserRequest getUserRequest() {
        return userRequest;
    }

    /**
     * Facility Request Details
     * 
     * @param userRequest
     *     The userRequest
     */
    @JsonProperty("userRequest")
    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
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
        return new HashCodeBuilder().append(userRequest).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(userRequest, rhs.userRequest).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
