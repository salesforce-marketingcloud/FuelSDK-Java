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

import java.util.List;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.EmailSendDefinition;

@InternalSoapType(type = EmailSendDefinition.class, ignoredFields = { "ID" })
public class ETEmailSendDefinition extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "categoryID")
    private Integer categoryID = null;
    @InternalSoapField(name = "email", serializedName = "Email.ID")
    private ETEmail email = null;
    @InternalSoapField(name = "sendDefinitionList")
    private List<ETSendDefinitionList> sendDefinitionList = null;
    @InternalSoapField(name = "sendClassification", serializedName = "SendClassification.CustomerKey")
    private ETSendClassification sendClassification = null;
    @InternalSoapField(name = "deliveryProfile", serializedName = "DeliveryProfile.CustomerKey")
    private ETDeliveryProfile deliveryProfile = null;
    @InternalSoapField(name = "senderProfile", serializedName = "SenderProfile.CustomerKey")
    private ETSenderProfile senderProfile = null;
    @InternalSoapField(name = "emailSubject")
    private String emailSubject = null;
    @InternalSoapField(name = "dynamicEmailSubject")
    private String dynamicEmailSubject = null;
    @InternalSoapField(name = "bccEmail")
    private String bccEmail = null;
    @InternalSoapField(name = "autoBccEmail")
    private String autoBccEmail = null;
    @InternalSoapField(name = "testEmailAddr")
    private String testEmailAddr = null;

    @InternalSoapField(name = "additional")
    private String additional = null;
    @InternalSoapField(name = "deduplicateByEmail")
    private Boolean deduplicateByEmail = null;
    @InternalSoapField(name = "exclusionFilter")
    private String exclusionFilter = null;
    @InternalSoapField(name = "isMultipart")
    private Boolean isMultipart = null;
    @InternalSoapField(name = "isSendLogging")
    private Boolean isSendLogging = null;
    @InternalSoapField(name = "isWrapped")
    private Boolean isWrapped = null;
    @InternalSoapField(name = "sendLimit")
    private Integer sendLimit = null;
    @InternalSoapField(name = "suppressTracking")
    private Boolean suppressTracking = null;

    public ETEmailSendDefinition() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public ETEmail getEmail() {
        return email;
    }

    public void setEmail(ETEmail email) {
        this.email = email;
    }

    public List<ETSendDefinitionList> getSendDefinitionList() {
        return sendDefinitionList;
    }

    public void setSendDefinitionList(List<ETSendDefinitionList> sendDefinitionList) {
        this.sendDefinitionList = sendDefinitionList;
    }

    public ETSendClassification getSendClassification() {
        return sendClassification;
    }

    public void setSendClassification(ETSendClassification sendClassification) {
        this.sendClassification = sendClassification;
    }

    public ETDeliveryProfile getDeliveryProfile() {
        return deliveryProfile;
    }

    public void setDeliveryProfile(ETDeliveryProfile deliveryProfile) {
        this.deliveryProfile = deliveryProfile;
    }

    public ETSenderProfile getSenderProfile() {
        return senderProfile;
    }

    public void setSenderProfile(ETSenderProfile senderProfile) {
        this.senderProfile = senderProfile;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getDynamicEmailSubject() {
        return dynamicEmailSubject;
    }

    public void setDynamicEmailSubject(String dynamicEmailSubject) {
        this.dynamicEmailSubject = dynamicEmailSubject;
    }

    public String getBccEmail() {
        return bccEmail;
    }

    public void setBccEmail(String bccEmail) {
        this.bccEmail = bccEmail;
    }

    public String getAutoBccEmail() {
        return autoBccEmail;
    }

    public void setAutoBccEmail(String autoBccEmail) {
        this.autoBccEmail = autoBccEmail;
    }

    public String getTestEmailAddr() {
        return testEmailAddr;
    }

    public void setTestEmailAddr(String testEmailAddr) {
        this.testEmailAddr = testEmailAddr;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Boolean getDeduplicateByEmail() {
        return deduplicateByEmail;
    }

    public void setDeduplicateByEmail(Boolean deduplicateByEmail) {
        this.deduplicateByEmail = deduplicateByEmail;
    }

    public String getExclusionFilter() {
        return exclusionFilter;
    }

    public void setExclusionFilter(String exclusionFilter) {
        this.exclusionFilter = exclusionFilter;
    }

    public Boolean getIsMultipart() {
        return isMultipart;
    }

    public void setIsMultipart(Boolean isMultipart) {
        this.isMultipart = isMultipart;
    }

    public Boolean getIsSendLogging() {
        return isSendLogging;
    }

    public void setIsSendLogging(Boolean isSendLogging) {
        this.isSendLogging = isSendLogging;
    }

    public Boolean getIsWrapped() {
        return isWrapped;
    }

    public void setIsWrapped(Boolean isWrapped) {
        this.isWrapped = isWrapped;
    }

    public Integer getSendLimit() {
        return sendLimit;
    }

    public void setSendLimit(Integer sendLimit) {
        this.sendLimit = sendLimit;
    }

    public Boolean getSuppressTracking() {
        return suppressTracking;
    }

    public void setSuppressTracking(Boolean suppressTracking) {
        this.suppressTracking = suppressTracking;
    }
}
