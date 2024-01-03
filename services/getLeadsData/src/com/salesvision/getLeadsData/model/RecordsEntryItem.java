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

package com.salesvision.getLeadsData.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.salesvision.getLeadsData.model.Attributes;
/**
 * RecordsEntryItem
 */

public class RecordsEntryItem {
  @JsonProperty("Company")
  private String Company = null;

  @JsonProperty("Email")
  private String Email = null;

  @JsonProperty("Phone")
  private String Phone = null;

  @JsonProperty("State")
  private String State = null;

  @JsonProperty("attributes")
  private Attributes attributes = null;

  @JsonProperty("City")
  private String City = null;

  @JsonProperty("Name")
  private String Name = null;

  public RecordsEntryItem Company(String Company) {
    this.Company = Company;
    return this;
  }

   /**
   * Get Company
   * @return Company
  **/
  public String getCompany() {
    return Company;
  }

  public void setCompany(String Company) {
    this.Company = Company;
  }

  public RecordsEntryItem Email(String Email) {
    this.Email = Email;
    return this;
  }

   /**
   * Get Email
   * @return Email
  **/
  public String getEmail() {
    return Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public RecordsEntryItem Phone(String Phone) {
    this.Phone = Phone;
    return this;
  }

   /**
   * Get Phone
   * @return Phone
  **/
  public String getPhone() {
    return Phone;
  }

  public void setPhone(String Phone) {
    this.Phone = Phone;
  }

  public RecordsEntryItem State(String State) {
    this.State = State;
    return this;
  }

   /**
   * Get State
   * @return State
  **/
  public String getState() {
    return State;
  }

  public void setState(String State) {
    this.State = State;
  }

  public RecordsEntryItem attributes(Attributes attributes) {
    this.attributes = attributes;
    return this;
  }

   /**
   * Get attributes
   * @return attributes
  **/
  public Attributes getAttributes() {
    return attributes;
  }

  public void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }

  public RecordsEntryItem City(String City) {
    this.City = City;
    return this;
  }

   /**
   * Get City
   * @return City
  **/
  public String getCity() {
    return City;
  }

  public void setCity(String City) {
    this.City = City;
  }

  public RecordsEntryItem Name(String Name) {
    this.Name = Name;
    return this;
  }

   /**
   * Get Name
   * @return Name
  **/
  public String getName() {
    return Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecordsEntryItem recordsEntryItem = (RecordsEntryItem) o;
    return Objects.equals(this.Company, recordsEntryItem.Company) &&
        Objects.equals(this.Email, recordsEntryItem.Email) &&
        Objects.equals(this.Phone, recordsEntryItem.Phone) &&
        Objects.equals(this.State, recordsEntryItem.State) &&
        Objects.equals(this.attributes, recordsEntryItem.attributes) &&
        Objects.equals(this.City, recordsEntryItem.City) &&
        Objects.equals(this.Name, recordsEntryItem.Name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Company, Email, Phone, State, attributes, City, Name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecordsEntryItem {\n");
    
    sb.append("    Company: ").append(toIndentedString(Company)).append("\n");
    sb.append("    Email: ").append(toIndentedString(Email)).append("\n");
    sb.append("    Phone: ").append(toIndentedString(Phone)).append("\n");
    sb.append("    State: ").append(toIndentedString(State)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    City: ").append(toIndentedString(City)).append("\n");
    sb.append("    Name: ").append(toIndentedString(Name)).append("\n");
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
