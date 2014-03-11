//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Account;

@InternalSoapType(type = Account.class)
public class ETOrganization extends ETSoapObject {
    @InternalSoapField(name = "accountType")
    private ETAccountType accountType = null;
    @InternalSoapField(name = "address")
    private String address = null;
    @InternalSoapField(name = "brandID")
    private Integer brandId = null;
    @InternalSoapField(name = "businessName")
    private String businessName = null;
    @InternalSoapField(name = "city")
    private String city = null;
    @InternalSoapField(name = "country")
    private String country = null;
    @InternalSoapField(name = "deletedDate")
    private Date deletedDate = null;
    @InternalSoapField(name = "editionID")
    private Integer editionId = null;
    @InternalSoapField(name = "email")
    private String email = null;
    @InternalSoapField(name = "fax")
    private String fax = null;
    @InternalSoapField(name = "fromName")
    private String fromName = null;
    @InternalSoapField(name = "inheritAddress")
    private Boolean inheritAddress = null;
    @InternalSoapField(name = "isActive")
    private Boolean isActive = null;
    @InternalSoapField(name = "isTestAccount")
    private Boolean isTestAccount = null;
    @InternalSoapField(name = "isTrialAccount")
    private Boolean isTrialAccount = null;
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "parentAccount", serializedName = "ParentAccount.id")
    private ETOrganization parentOrganization = null;
    @InternalSoapField(name = "parentID")
    private Integer parentId = null;
    @InternalSoapField(name = "parentName")
    private String parentName = null;
    @InternalSoapField(name = "phone")
    private String phone = null;
    @InternalSoapField(name = "privateLabelID")
    private Integer privateLabelId = null;
    @InternalSoapField(name = "roles")
    private List<ETRole> roles = null;
    @InternalSoapField(name = "state")
    private String state = null;
    @InternalSoapField(name = "zip")
    private String zip = null;

    public ETOrganization() {}

    public ETAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(ETAccountType accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Boolean getInheritAddress() {
        return inheritAddress;
    }

    public void setInheritAddress(Boolean inheritAddress) {
        this.inheritAddress = inheritAddress;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsTestAccount() {
        return isTestAccount;
    }

    public void setIsTestAccount(Boolean isTestAccount) {
        this.isTestAccount = isTestAccount;
    }

    public Boolean getIsTrialAccount() {
        return isTrialAccount;
    }

    public void setIsTrialAccount(Boolean isTrialAccount) {
        this.isTrialAccount = isTrialAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ETOrganization getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(ETOrganization parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrivateLabelId() {
        return privateLabelId;
    }

    public void setPrivateLabelId(Integer privateLabelId) {
        this.privateLabelId = privateLabelId;
    }

    public List<ETRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ETRole> roles) {
        this.roles = roles;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
