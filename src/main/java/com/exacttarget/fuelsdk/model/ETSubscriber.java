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
import com.exacttarget.fuelsdk.internal.Subscriber;

@InternalSoapType(type = Subscriber.class, ignoredFields = {
    "CustomerKey", "Lists", "ModifiedDate"
})
public class ETSubscriber extends ETSoapObject {
    @InternalSoapField(name = "emailAddress")
    private String emailAddress = null;
    @InternalSoapField(name = "subscriberKey")
    private String subscriberKey = null;
    @InternalSoapField(name = "lists")
    private List<ETSubscriberList> lists = null;
    @InternalSoapField(name = "status")
    private ETSubscriberStatus status = null;
    @InternalSoapField(name = "emailTypePreference")
    private ETEmailType emailTypePreference = null;
    @InternalSoapField(name = "unsubscribedDate")
    private Date unsubscribedDate = null;

    public ETSubscriber() {}

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubscriberKey() {
        return subscriberKey;
    }

    public void setSubscriberKey(String subscriberKey) {
        this.subscriberKey = subscriberKey;
    }

    public List<ETSubscriberList> getLists() {
        return lists;
    }

    public void setLists(List<ETSubscriberList> lists) {
        this.lists = lists;
    }

    public ETSubscriberStatus getStatus() {
        return status;
    }

    public void setStatus(ETSubscriberStatus status) {
        this.status = status;
    }

    public ETEmailType getEmailTypePreference() {
        return emailTypePreference;
    }

    public void setEmailTypePreference(ETEmailType emailTypePreference) {
        this.emailTypePreference = emailTypePreference;
    }

    public Date getUnsubscribedDate() {
        return unsubscribedDate;
    }

    public void setUnsubscribedDate(Date unsubscribedDate) {
        this.unsubscribedDate = unsubscribedDate;
    }
}
