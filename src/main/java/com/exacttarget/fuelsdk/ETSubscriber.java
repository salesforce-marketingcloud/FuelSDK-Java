//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.Attribute;
import com.exacttarget.fuelsdk.internal.Subscriber;

/**
 * An <code>ETSubscriber</code> object represents an email subscriber
 * in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = Subscriber.class, unretrievable = {
    "Attributes", "ModifiedDate"
})
public class ETSubscriber extends ETSoapObject {
    @ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("subscriberKey")
    private String key = null;
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @ExternalName("emailAddress")
    private String emailAddress = null;
    @ExternalName("preferredEmailType")
    @InternalName("emailTypePreference")
    private ETEmail.Type preferredEmailType = null;
    @ExternalName("status")
    private Status status = null;
    @ExternalName("unsubscribedDate")
    private Date unsubscribedDate = null;
    @ExternalName("lists")
    protected List<ETSubscriberList> lists = null;
    @ExternalName("attributes")
    @InternalName("attributes")
    protected List<ETProfileAttribute> attributes = null;

    /** 
    * Class constructor, Initializes a new instance of the class.
    */
    public ETSubscriber() {}

    /** 
    * @return The Identifier of the ETSubscriber object.
    */    
    @Override
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETSubscriber object.
    */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return       The Customer Key of the ETSubscriber object.
    */     
    public String getKey() {
        return key;
    }

    /** 
    * @param key    The Customer Key of the ETSubscriber object.
    */      
    public void setKey(String key) {
        this.key = key;
    }

    /** 
    * @return     The created date of the ETSubscriber object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETSubscriber object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
    * @return     The modified date of the ETSubscriber object.
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /** 
    * @param modifiedDate       The modified date of the ETSubscriber object.
    */    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /** 
    * @return The email address of the ETSubscriber object.
    */    
    public String getEmailAddress() {
        return emailAddress;
    }

    /** 
    * @param emailAddress     The email address of the ETSubscriber object.
    */    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /** 
    * @return The preferred email type of the ETSubscriber object.
    * {@link ETEmail.Type}
    */    
    public ETEmail.Type getPreferredEmailType() {
        return preferredEmailType;
    }

    /** 
    * @param preferredEmailType     The preferred email type of the ETSubscriber object.
    * {@link ETEmail.Type}
    */    
    public void setPreferredEmailType(ETEmail.Type preferredEmailType) {
        this.preferredEmailType = preferredEmailType;
    }

    /** 
    * @return The Status of the ETSubscriber object.
    * {@link ETSubscriber.Status}
    */    
    public Status getStatus() {
        return status;
    }

    /** 
    * @param status     The status of the ETSubscriber object.
    * {@link ETSubscriber.Status}
    */    
    public void setStatus(Status status) {
        this.status = status;
    }

    /** 
    * @return The unsubscribed date of the ETSubscriber object.
    */    
    public Date getUnsubscribedDate() {
        return unsubscribedDate;
    }

    /** 
    * @param unsubscribedDate     The unsubscribed date of the ETSubscriber object.
    */    
    public void setUnsubscribedDate(Date unsubscribedDate) {
        this.unsubscribedDate = unsubscribedDate;
    }

    /** 
    * @return The List of {@link ETSubscriberList} of this ETSubscriber object.
    */    
    public List<ETSubscriberList> getLists() {
	   if (lists == null) {
		   lists = new ArrayList<ETSubscriberList>();
	   }
	   return lists;
    }

    /**
     * @return  Gets the List of the attributes property.
     */
    public List<ETProfileAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<ETProfileAttribute>();
        }
        return this.attributes;
    }		
    
    public void setAttributes(List<ETProfileAttribute> attribs) {
//        if (attributes == null)
//            attributes = new ArrayList<Attribute>();
        this.attributes = attribs;
    }    

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

    /**
     *  Subscriber Status that can be used
     */
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
