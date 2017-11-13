/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.BounceEvent;
import com.exacttarget.fuelsdk.internal.EventType;
import java.util.Date;

/**
 * An <code>ETBounceEvent</code> object represents information pertaining to the specific event of an email message bounce.
 */

@SoapObject(internalType = BounceEvent.class)
public class ETBounceEvent  extends ETSoapObject{

    @InternalName("objectID")    
    private String id;

    @InternalName("smtpCode")
    private String smtpCode;
    
    @ExternalName("bounceCategory")
    private String bounceCategory;
    
    @InternalName("smtpReason")
    private String smtpReason;
    
    @ExternalName("bounceType")
    private String bounceType;
    
    @ExternalName("sendID")
    private Integer sendID;
    
    @ExternalName("subscriberKey")
    private String subscriberKey;
    
    @ExternalName("eventDate")
    private Date eventDate;
    
    @ExternalName("eventType")
    private EventType eventType;
    
    @ExternalName("triggeredSendDefinitionObjectID")
    private String triggeredSendDefinitionObjectID;
    
    @ExternalName("batchID")
    private Integer batchID;
    
    /**
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the smtpCode
     */
    public String getSmtpCode() {
        return smtpCode;
    }

    /**
     * @param smtpCode the smtpCode to set
     */
    public void setSmtpCode(String smtpCode) {
        this.smtpCode = smtpCode;
    }

    /**
     * @return the bounceCategory
     */
    public String getBounceCategory() {
        return bounceCategory;
    }

    /**
     * @param bounceCategory the bounceCategory to set
     */
    public void setBounceCategory(String bounceCategory) {
        this.bounceCategory = bounceCategory;
    }

    /**
     * @return the smtpReason
     */
    public String getSmtpReason() {
        return smtpReason;
    }

    /**
     * @param smtpReason the smtpReason to set
     */
    public void setSmtpReason(String smtpReason) {
        this.smtpReason = smtpReason;
    }

    /**
     * @return the bounceType
     */
    public String getBounceType() {
        return bounceType;
    }

    /**
     * @param bounceType the bounceType to set
     */
    public void setBounceType(String bounceType) {
        this.bounceType = bounceType;
    }

    /**
     * @return the sendID
     */
    public Integer getSendID() {
        return sendID;
    }

    /**
     * @param sendID the sendID to set
     */
    public void setSendID(Integer sendID) {
        this.sendID = sendID;
    }

    /**
     * @return the subscriberKey
     */
    public String getSubscriberKey() {
        return subscriberKey;
    }

    /**
     * @param subscriberKey the subscriberKey to set
     */
    public void setSubscriberKey(String subscriberKey) {
        this.subscriberKey = subscriberKey;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the triggeredSendDefinitionObjectID
     */
    public String getTriggeredSendDefinitionObjectID() {
        return triggeredSendDefinitionObjectID;
    }

    /**
     * @param triggeredSendDefinitionObjectID the triggeredSendDefinitionObjectID to set
     */
    public void setTriggeredSendDefinitionObjectID(String triggeredSendDefinitionObjectID) {
        this.triggeredSendDefinitionObjectID = triggeredSendDefinitionObjectID;
    }

    /**
     * @return the batchID
     */
    public Integer getBatchID() {
        return batchID;
    }

    /**
     * @param batchID the batchID to set
     */
    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }
    
}
