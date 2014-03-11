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

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.SendClassification;

// XXX why can't you retrieve DeliveryProfile, SendPriority, and SenderProfile?
@InternalSoapType(type = SendClassification.class, ignoredFields = {
    "ID", "DeliveryProfile", "SendPriority", "SenderProfile"
})
public class ETSendClassification extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "deliveryProfile")
    private ETDeliveryProfile deliveryProfile = null;
    @InternalSoapField(name = "sendClassificationType")
    private ETSendClassificationType sendClassificationType = null;
    @InternalSoapField(name = "sendPriority")
    private ETSendPriority sendPriority = null;
    @InternalSoapField(name = "senderProfile")
    private ETSenderProfile senderProfile = null;

    public ETSendClassification() {}

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

    public ETDeliveryProfile getDeliveryProfile() {
        return deliveryProfile;
    }

    public void setDeliveryProfile(ETDeliveryProfile deliveryProfile) {
        this.deliveryProfile = deliveryProfile;
    }

    public ETSendClassificationType getSendClassificationType() {
        return sendClassificationType;
    }

    public void setSendClassificationType(ETSendClassificationType sendClassificationType) {
        this.sendClassificationType = sendClassificationType;
    }

    public ETSendPriority getSendPriority() {
        return sendPriority;
    }

    public void setSendPriority(ETSendPriority sendPriority) {
        this.sendPriority = sendPriority;
    }

    public ETSenderProfile getSenderProfile() {
        return senderProfile;
    }

    public void setSenderProfile(ETSenderProfile senderProfile) {
        this.senderProfile = senderProfile;
    }
}
