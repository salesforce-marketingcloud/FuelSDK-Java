/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.TriggeredSendSummary;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;

/**
 * An <code>ETTriggeredSendSummary</code> represents a Triggered Send Summary
 * in the Salesforce Marketing Cloud. 
 */

@SoapObject(internalType = TriggeredSendSummary.class)
public class ETTriggeredSendSummary extends ETSoapObject{
    
    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;
    @ExternalName("sent")
    private Long sent;
    @ExternalName("notSentDueToOptOut")
    private Long notSentDueToOptOut;
    @ExternalName("notSentDueToUndeliverable")
    private Long notSentDueToUndeliverable;
    @ExternalName("bounces")
    private Long bounces;
    @ExternalName("opens")
    private Long opens;
    @ExternalName("clicks")
    private Long clicks;
    @ExternalName("uniqueOpens")
    private Long uniqueOpens;
    @ExternalName("uniqueClicks")
    private Long uniqueClicks;
    @ExternalName("optOuts")
    private Long optOuts;
    @ExternalName("surveyResponses")
    private Long surveyResponses;
    @ExternalName("conversions")
    private Long conversions;
    @ExternalName("uniqueConversions")
    private Long uniqueConversions;
    @ExternalName("inProcess")
    private Long inProcess;
    @ExternalName("notSentDueToError")
    private Long notSentDueToError;
    @ExternalName("queued")
    private Long queued;    

    /** 
    * @return The Identifier of the ETTriggeredSendSummary object.
    */     
    @Override
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETTriggeredSendSummary object.
    */    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sent
     */
    public Long getSent() {
        return sent;
    }

    /**
     * @param sent the sent to set
     */
    public void setSent(Long sent) {
        this.sent = sent;
    }

    /**
     * @return the notSentDueToOptOut
     */
    public Long getNotSentDueToOptOut() {
        return notSentDueToOptOut;
    }

    /**
     * @param notSentDueToOptOut the notSentDueToOptOut to set
     */
    public void setNotSentDueToOptOut(Long notSentDueToOptOut) {
        this.notSentDueToOptOut = notSentDueToOptOut;
    }

    /**
     * @return the notSentDueToUndeliverable
     */
    public Long getNotSentDueToUndeliverable() {
        return notSentDueToUndeliverable;
    }

    /**
     * @param notSentDueToUndeliverable the notSentDueToUndeliverable to set
     */
    public void setNotSentDueToUndeliverable(Long notSentDueToUndeliverable) {
        this.notSentDueToUndeliverable = notSentDueToUndeliverable;
    }

    /**
     * @return the bounces
     */
    public Long getBounces() {
        return bounces;
    }

    /**
     * @param bounces the bounces to set
     */
    public void setBounces(Long bounces) {
        this.bounces = bounces;
    }

    /**
     * @return the opens
     */
    public Long getOpens() {
        return opens;
    }

    /**
     * @param opens the opens to set
     */
    public void setOpens(Long opens) {
        this.opens = opens;
    }

    /**
     * @return the clicks
     */
    public Long getClicks() {
        return clicks;
    }

    /**
     * @param clicks the clicks to set
     */
    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    /**
     * @return the uniqueOpens
     */
    public Long getUniqueOpens() {
        return uniqueOpens;
    }

    /**
     * @param uniqueOpens the uniqueOpens to set
     */
    public void setUniqueOpens(Long uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
    }

    /**
     * @return the uniqueClicks
     */
    public Long getUniqueClicks() {
        return uniqueClicks;
    }

    /**
     * @param uniqueClicks the uniqueClicks to set
     */
    public void setUniqueClicks(Long uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }

    /**
     * @return the optOuts
     */
    public Long getOptOuts() {
        return optOuts;
    }

    /**
     * @param optOuts the optOuts to set
     */
    public void setOptOuts(Long optOuts) {
        this.optOuts = optOuts;
    }

    /**
     * @return the surveyResponses
     */
    public Long getSurveyResponses() {
        return surveyResponses;
    }

    /**
     * @param surveyResponses the surveyResponses to set
     */
    public void setSurveyResponses(Long surveyResponses) {
        this.surveyResponses = surveyResponses;
    }


    /**
     * @return the conversions
     */
    public Long getConversions() {
        return conversions;
    }

    /**
     * @param conversions the conversions to set
     */
    public void setConversions(Long conversions) {
        this.conversions = conversions;
    }

    /**
     * @return the uniqueConversions
     */
    public Long getUniqueConversions() {
        return uniqueConversions;
    }

    /**
     * @param uniqueConversions the uniqueConversions to set
     */
    public void setUniqueConversions(Long uniqueConversions) {
        this.uniqueConversions = uniqueConversions;
    }

    /**
     * @return the inProcess
     */
    public Long getInProcess() {
        return inProcess;
    }

    /**
     * @param inProcess the inProcess to set
     */
    public void setInProcess(Long inProcess) {
        this.inProcess = inProcess;
    }

    /**
     * @return the notSentDueToError
     */
    public Long getNotSentDueToError() {
        return notSentDueToError;
    }

    /**
     * @param notSentDueToError the notSentDueToError to set
     */
    public void setNotSentDueToError(Long notSentDueToError) {
        this.notSentDueToError = notSentDueToError;
    }

    /**
     * @return the queued
     */
    public Long getQueued() {
        return queued;
    }

    /**
     * @param queued the queued to set
     */
    public void setQueued(Long queued) {
        this.queued = queued;
    }
    
}
