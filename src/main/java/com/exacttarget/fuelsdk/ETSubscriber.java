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

import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.Subscriber;

@SoapObject(internalType = Subscriber.class, unretrievable = {
    "Attributes", "ModifiedDate"
})
public class ETSubscriber extends ETSoapObject {
    @ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("subscriberKey")
    private String key = null;
    @ExternalName("emailAddress")
    private String emailAddress = null;
    @ExternalName("preferredEmailType")
    @InternalName("emailTypePreference")
    private ETEmail.Type preferredEmailType = null;
    @ExternalName("status")
    private Status status = null;
    @ExternalName("unsubscribedDate")
    private Date unsubscribedDate = null;
//    @ExternalName("attributes")
//    private List<Attribute> attributes = null;
//    @ExternalName("lists")
//    private List<ETList> lists = null;

    public ETSubscriber() {}

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ETEmail.Type getPreferredEmailType() {
        return preferredEmailType;
    }

    public void setPreferredEmailType(ETEmail.Type preferredEmailType) {
        this.preferredEmailType = preferredEmailType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getUnsubscribedDate() {
        return unsubscribedDate;
    }

    public void setUnsubscribedDate(Date unsubscribedDate) {
        this.unsubscribedDate = unsubscribedDate;
    }

//    public List<Attribute> getAttributes() {
//        return attributes;
//    }
//
//    public void setAttributes(List<Attribute> attributes) {
//        this.attributes = attributes;
//    }
//
//    public List<ETList> getLists() {
//        return lists;
//    }
//
//    public void setLists(List<ETList> lists) {
//        this.lists = lists;
//    }

    /**
     * @deprecated
     * Use <code>getKey()</code>.
     */
    @Deprecated
    public String getSubscriberKey() {
        return getKey();
    }

    /**
     * @deprecated
     * Use <code>setKey()</code>.
     */
    @Deprecated
    public void setSubscriberKey(String subscriberKey) {
        setKey(subscriberKey);
    }

    public class Attribute {
        private String name = null;
        private String value = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum Status {
        ACTIVE("Active"),
        BOUNCED("Bounced"),
        DELETED("Deleted"),
        HELD("Held"),
        UNSUBSCRIBED("Unsubscribed");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
