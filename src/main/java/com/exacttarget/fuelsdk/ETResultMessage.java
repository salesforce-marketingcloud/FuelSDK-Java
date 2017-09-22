/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.ResultMessage;
import com.exacttarget.fuelsdk.internal.SMSTriggeredSend;
import com.exacttarget.fuelsdk.internal.SMSTriggeredSendDefinition;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.Subscriber;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * An <code>ETResultMessage</code> object represents a message containing results of async call in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = ResultMessage.class)
public class ETResultMessage extends ETSoapObject{

    @InternalName("objectID")    
    private String id;
    
    @InternalName("customerKey")    
    private String customerKey;
    @ExternalName("requestID")
    private String requestID;
    
    @InternalName("correlationID")
    private String correlationID;
    
    @ExternalName("conversationID")
    private String conversationID;
    @ExternalName("overallStatusCode")
    private String overallStatusCode;
    @ExternalName("statusCode")
    private String statusCode;
    @ExternalName("statusMessage")
    private String statusMessage;
    @ExternalName("errorCode")
    private Integer errorCode;
    @ExternalName("resultType")
    private String resultType;
    @ExternalName("resultDetailXML")
    private String resultDetailXML;
    @ExternalName("sequenceCode")
    private Integer sequenceCode;
    @ExternalName("callsInConversation")
    private Integer callsInConversation;
    
    @ExternalName("createdDate")
    private Date createdDate = null;
    @InternalName("modifiedDate")
    private Date modifiedDate = null;    

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
     * @return the customerKey
     */
    public String getCustomerKey() {
        return customerKey;
    }

    /**
     * @param customerKey the customerKey to set
     */
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    /**
     * @return the requestID
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    /**
     * @return the correlationID
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * @param correlationID the correlationID to set
     */
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    /**
     * @return the conversationID
     */
    public String getConversationID() {
        return conversationID;
    }

    /**
     * @param conversationID the conversationID to set
     */
    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    /**
     * @return the overallStatusCode
     */
    public String getOverallStatusCode() {
        return overallStatusCode;
    }

    /**
     * @param overallStatusCode the overallStatusCode to set
     */
    public void setOverallStatusCode(String overallStatusCode) {
        this.overallStatusCode = overallStatusCode;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the resultDetailXML
     */
    public String getResultDetailXML() {
        return resultDetailXML;
    }

    /**
     * @param resultDetailXML the resultDetailXML to set
     */
    public void setResultDetailXML(String resultDetailXML) {
        this.resultDetailXML = resultDetailXML;
    }

    /**
     * @return the sequenceCode
     */
    public Integer getSequenceCode() {
        return sequenceCode;
    }

    /**
     * @param sequenceCode the sequenceCode to set
     */
    public void setSequenceCode(Integer sequenceCode) {
        this.sequenceCode = sequenceCode;
    }

    /**
     * @return the callsInConversation
     */
    public Integer getCallsInConversation() {
        return callsInConversation;
    }

    /**
     * @param callsInConversation the callsInConversation to set
     */
    public void setCallsInConversation(Integer callsInConversation) {
        this.callsInConversation = callsInConversation;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
}
