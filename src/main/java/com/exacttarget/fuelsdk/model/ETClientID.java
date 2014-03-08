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

import com.exacttarget.fuelsdk.annotations.InternalClientObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;

@InternalClientObject
public class ETClientID {
    @InternalSoapField(name = "id")
    private Integer id = null;
    @InternalSoapField(name = "customerKey")
    private String customerKey = null;
    @InternalSoapField(name = "clientID")
    private Integer clientID = null;
    @InternalSoapField(name = "createdBy")
    private Integer createdBy = null;
    @InternalSoapField(name = "enterpriseID")
    private Long enterpriseID = null;
    @InternalSoapField(name = "modifiedBy")
    private Integer modifiedBy = null;
    @InternalSoapField(name = "partnerClientKey")
    private String partnerClientKey = null;
    @InternalSoapField(name = "partnerUserKey")
    private String partnerUserKey = null;
    @InternalSoapField(name = "userID")
    private Integer userID = null;

    public ETClientID() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(Long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPartnerClientKey() {
        return partnerClientKey;
    }

    public void setPartnerClientKey(String partnerClientKey) {
        this.partnerClientKey = partnerClientKey;
    }

    public String getPartnerUserKey() {
        return partnerUserKey;
    }

    public void setPartnerUserKey(String partnerUserKey) {
        this.partnerUserKey = partnerUserKey;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
