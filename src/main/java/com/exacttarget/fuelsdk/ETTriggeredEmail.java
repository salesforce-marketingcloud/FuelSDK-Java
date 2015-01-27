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

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.InternalProperty;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.TriggeredSendDefinition;

@SoapObject(internalType = TriggeredSendDefinition.class, unretrievable = {
    "SendSourceDataExtension"
})
public class ETTriggeredEmail extends ETSoapObject {
    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("description")
    private String description = null;
    @ExternalName("folderId")
    @InternalName("categoryID")
    private Integer folderId = null;
    @ExternalName("email")
    @InternalProperty("Email.ID")
    private ETEmail email = null;
    @ExternalName("list")
    @InternalProperty("List.ID")
    private ETList list = null;
    // XXX see http://salesforce.stackexchange.com/questions/28441/retrieve-dataextension-for-triggeredsenddefinition
    @ExternalName("dataExtension")
    @InternalName("sendSourceDataExtension")
    private ETDataExtension dataExtension = null;
    @ExternalName("subject")
    @InternalName("emailSubject")
    private String subject = null;
    @ExternalName("priority")
    private String priority = null; // XXX enum?
    @ExternalName("status")
    @InternalName("triggeredSendStatus")
    private Status status = null;
    @ExternalName("autoAddSubscribers")
    private Boolean autoAddSubscribers = null;
    @ExternalName("autoUpdateSubscribers")
    private Boolean autoUpdateSubscribers = null;
    @ExternalName("isMultipart")
    private Boolean isMultipart = null;
    @ExternalName("isWrapped")
    private Boolean isWrapped = null;
//    @ExternalName("refreshContent")
//    private Boolean refreshContent = null;
    @ExternalName("suppressTracking")
    private Boolean suppressTracking = null;

    public ETTriggeredEmail() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

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

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public ETEmail getEmail() {
        return email;
    }

    public void setEmail(ETEmail email) {
        this.email = email;
    }

    public ETList getList() {
        return list;
    }

    public void setList(ETList list) {
        this.list = list;
    }

    public ETDataExtension getDataExtension() {
        return dataExtension;
    }

    public void setDataExtension(ETDataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getAutoAddSubscribers() {
        return autoAddSubscribers;
    }

    public void setAutoAddSubscribers(Boolean autoAddSubscribers) {
        this.autoAddSubscribers = autoAddSubscribers;
    }

    public Boolean getAutoUpdateSubscribers() {
        return autoUpdateSubscribers;
    }

    public void setAutoUpdateSubscribers(Boolean autoUpdateSubscribers) {
        this.autoUpdateSubscribers = autoUpdateSubscribers;
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

//    public Boolean getRefreshContent() {
//        return refreshContent;
//    }
//
//    public void setRefreshContent(Boolean refreshContent) {
//        this.refreshContent = refreshContent;
//    }

    public Boolean getSuppressTracking() {
        return suppressTracking;
    }

    public void setSuppressTracking(Boolean suppressTracking) {
        this.suppressTracking = suppressTracking;
    }

    /**
     * @deprecated
     * Use <code>getKey()</code>.
     */
    @Deprecated
    public String getCustomerKey() {
        return getKey();
    }

    /**
     * @deprecated
     * Use <code>setKey()</code>.
     */
    @Deprecated
    public void setCustomerKey(String customerKey) {
        setKey(customerKey);
    }

    /**
     * @deprecated
     * Use <code>getFolderId()</code>.
     */
    @Deprecated
    public Integer getCategoryId() {
        return getFolderId();
    }

    /**
     * @deprecated
     * Use <code>setFolderId()</code>.
     */
    @Deprecated
    public void setCategoryId(Integer categoryId) {
        setFolderId(categoryId);
    }

    public enum Status {
        ACTIVE("Active"),
        CANCELED("Canceled"),
        DELETED("Deleted"),
        INACTIVE("Inactive"),
        MOVED("Moved"),
        NEW("NEW");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
