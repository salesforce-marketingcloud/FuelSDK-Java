/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ClickEvent;
import com.exacttarget.fuelsdk.internal.EventType;
import java.util.Date;

/**
 * An <code>ETClickEvent</code> object represents information pertaining to the click events of an email message  in the Salesforce Marketing Cloud.
 */
@SoapObject(internalType = ClickEvent.class)
public class ETClickEvent extends ETSoapObject{

    @InternalName("objectID")    
    private String id;

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
    
    @ExternalName("urlid")
    private Integer urlid;
    
    @ExternalName("url")
    private String url;
    
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

    /**
     * @return the urlid
     */
    public Integer getUrlid() {
        return urlid;
    }

    /**
     * @param urlid the urlid to set
     */
    public void setUrlid(Integer urlid) {
        this.urlid = urlid;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
