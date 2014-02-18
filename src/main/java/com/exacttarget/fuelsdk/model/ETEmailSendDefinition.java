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

@InternalSoapType(type = EmailSendDefinition.class, ignoredFields = {"ID"})
public class ETEmailSendDefinition extends ETSoapObject {

	@InternalSoapField(name = "name")
    protected String name;

	@InternalSoapField(name = "description")
    protected String description;

	@InternalSoapField(name = "categoryID")
    protected Integer categoryID;

    @InternalSoapField(name = "sendClassification", serializedName = "SendClassification.CustomerKey")
    protected ETSendClassification sendClassification;

    @InternalSoapField(name = "senderProfile", serializedName = "SenderProfile.CustomerKey")
    protected ETSenderProfile senderProfile;

    @InternalSoapField(name = "deliveryProfile", serializedName = "DeliveryProfile.CustomerKey")
    protected ETDeliveryProfile deliveryProfile;

    @InternalSoapField(name = "sendDefinitionList")
    protected List<ETSendDefinitionList> sendDefinitionList;

    @InternalSoapField(name = "suppressTracking")
    protected Boolean suppressTracking;

    @InternalSoapField(name = "isSendLogging")
    protected Boolean isSendLogging;

    @InternalSoapField(name="email", serializedName = "Email.ID")
    protected ETEmail email;

    @InternalSoapField(name = "bccEmail")
    protected String bccEmail;

    @InternalSoapField(name = "autoBccEmail")
    protected String autoBccEmail;

    @InternalSoapField(name = "testEmailAddr")
    protected String testEmailAddr;

    @InternalSoapField(name = "emailSubject")
    protected String emailSubject;

    @InternalSoapField(name = "dynamicEmailSubject")
    protected String dynamicEmailSubject;

    @InternalSoapField(name = "isMultipart")
    protected Boolean isMultipart;

    @InternalSoapField(name = "isWrapped")
    protected Boolean isWrapped;

    @InternalSoapField(name = "sendLimit")
    protected Integer sendLimit;

    @InternalSoapField(name = "deduplicateByEmail")
    protected Boolean deduplicateByEmail;

    @InternalSoapField(name = "exclusionFilter")
    protected String exclusionFilter;

    @InternalSoapField(name = "additional")
    protected String additional;

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

	public ETSendClassification getSendClassification() {
		return sendClassification;
	}

	public void setSendClassification(ETSendClassification sendClassification) {
		this.sendClassification = sendClassification;
	}

	public ETSenderProfile getSenderProfile() {
		return senderProfile;
	}

	public void setSenderProfile(ETSenderProfile senderProfile) {
		this.senderProfile = senderProfile;
	}

	public ETDeliveryProfile getDeliveryProfile() {
		return deliveryProfile;
	}

	public void setDeliveryProfile(ETDeliveryProfile deliveryProfile) {
		this.deliveryProfile = deliveryProfile;
	}

	public List<ETSendDefinitionList> getSendDefinitionList() {
		return sendDefinitionList;
	}

	public void setSendDefinitionList(List<ETSendDefinitionList> sendDefinitionList) {
		this.sendDefinitionList = sendDefinitionList;
	}

	public Boolean getSuppressTracking() {
		return suppressTracking;
	}

	public void setSuppressTracking(Boolean suppressTracking) {
		this.suppressTracking = suppressTracking;
	}

	public Boolean getIsSendLogging() {
		return isSendLogging;
	}

	public void setIsSendLogging(Boolean isSendLogging) {
		this.isSendLogging = isSendLogging;
	}

	public ETEmail getEmail() {
		return email;
	}

	public void setEmail(ETEmail email) {
		this.email = email;
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

	public Boolean getIsMultipart() {
		return isMultipart;
	}

	public void setIsMultipart(Boolean isMultipart) {
		this.isMultipart = isMultipart;
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

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	@Override
	public String toString() {
		return "ETEmailSendDefinition [name=" + name + ", description="
				+ description + ", categoryID=" + categoryID
				+ ", sendClassification=" + sendClassification
				+ ", senderProfile=" + senderProfile + ", deliveryProfile="
				+ deliveryProfile + ", sendDefinitionList="
				+ sendDefinitionList + ", suppressTracking=" + suppressTracking
				+ ", isSendLogging=" + isSendLogging + ", email=" + email
				+ ", bccEmail=" + bccEmail + ", autoBccEmail=" + autoBccEmail
				+ ", testEmailAddr=" + testEmailAddr + ", emailSubject="
				+ emailSubject + ", dynamicEmailSubject=" + dynamicEmailSubject
				+ ", isMultipart=" + isMultipart + ", isWrapped=" + isWrapped
				+ ", sendLimit=" + sendLimit + ", deduplicateByEmail="
				+ deduplicateByEmail + ", exclusionFilter=" + exclusionFilter
				+ ", additional=" + additional + ", id=" + id
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", customerKey=" + customerKey + "]";
	}



}