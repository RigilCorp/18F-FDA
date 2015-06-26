
package com.rigil.fda.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "authenticationResponse",
     "user",
     "fdaJsonDataObjectList"
    })
public class ResponseMessage {

    @JsonProperty("authenticationResponse")
    private AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    @JsonProperty("user")
    private User user = new User();
    @JsonProperty("fdaJsonDataObjectList")
    private List<FDAJsonDataObject> fdaJsonDataObjectList = new ArrayList<FDAJsonDataObject>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
    *
    * @return
    *     The authenticationResponse
    */
   @JsonProperty("authenticationResponse")
   public AuthenticationResponse getAuthenticationResponse() {
       return authenticationResponse;
   }

   /**
    *
    * @param authenticationResponse
    *     The authenticationResponse
    */
   @JsonProperty("authenticationResponse")
   public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) {
       this.authenticationResponse = authenticationResponse;
   }

    /**
    *
    * @return
    *     The UserEntity
    */
   @JsonProperty("user")
   public User getUser() {
       return user;
   }

   /**
    *
    * @param user
    *     The user
    */
   @JsonProperty("user")
   public void setUser(User user) {
       this.user = user;
   }

   /**
   *
   * @return
   *     The fdaJsonDataObjectList
   */
  @JsonProperty("fdaJsonDataObjectList")
  public List<FDAJsonDataObject> getFDADataList() {
      return fdaJsonDataObjectList;
  }

  /**
   *
   * @param fdaJsonDataObjectList
   *     The fdaJsonDataObjectList
   */
  @JsonProperty("fdaJsonDataObjectList")
  public void setFDADataList(List<FDAJsonDataObject> fdaJsonDataObjectList) {
      this.fdaJsonDataObjectList = fdaJsonDataObjectList;
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
        return new HashCodeBuilder().append(user).append(authenticationResponse).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseMessage) == false) {
            return false;
        }
        ResponseMessage rhs = ((ResponseMessage) other);
        return new EqualsBuilder().append(user, rhs.user).append(authenticationResponse, rhs.authenticationResponse).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
