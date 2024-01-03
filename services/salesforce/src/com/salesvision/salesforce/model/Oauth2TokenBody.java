/*
 * 
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.salesvision.salesforce.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Oauth2TokenBody
 */

public class Oauth2TokenBody {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("client_secret")
  private String client_secret = null;

  @JsonProperty("client_id")
  private String client_id = null;

  @JsonProperty("grant_type")
  private String grant_type = null;

  public Oauth2TokenBody username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Oauth2TokenBody password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Oauth2TokenBody client_secret(String client_secret) {
    this.client_secret = client_secret;
    return this;
  }

   /**
   * Get client_secret
   * @return client_secret
  **/
  public String getClientSecret() {
    return client_secret;
  }

  public void setClientSecret(String client_secret) {
    this.client_secret = client_secret;
  }

  public Oauth2TokenBody client_id(String client_id) {
    this.client_id = client_id;
    return this;
  }

   /**
   * Get client_id
   * @return client_id
  **/
  public String getClientId() {
    return client_id;
  }

  public void setClientId(String client_id) {
    this.client_id = client_id;
  }

  public Oauth2TokenBody grant_type(String grant_type) {
    this.grant_type = grant_type;
    return this;
  }

   /**
   * Get grant_type
   * @return grant_type
  **/
  public String getGrantType() {
    return grant_type;
  }

  public void setGrantType(String grant_type) {
    this.grant_type = grant_type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Oauth2TokenBody oauth2_token_body = (Oauth2TokenBody) o;
    return Objects.equals(this.username, oauth2_token_body.username) &&
        Objects.equals(this.password, oauth2_token_body.password) &&
        Objects.equals(this.client_secret, oauth2_token_body.client_secret) &&
        Objects.equals(this.client_id, oauth2_token_body.client_id) &&
        Objects.equals(this.grant_type, oauth2_token_body.grant_type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, client_secret, client_id, grant_type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Oauth2TokenBody {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    client_secret: ").append(toIndentedString(client_secret)).append("\n");
    sb.append("    client_id: ").append(toIndentedString(client_id)).append("\n");
    sb.append("    grant_type: ").append(toIndentedString(grant_type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}