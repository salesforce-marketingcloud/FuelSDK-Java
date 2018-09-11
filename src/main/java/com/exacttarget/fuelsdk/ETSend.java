package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.ETEmail;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalProperty;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.Send;

import java.util.Date;

@SoapObject(internalType = Send.class)
public class ETSend extends ETSoapObject {

    @ExternalName("id")
    private String id = null;

    @ExternalName("sendDate")
    private Date sendDate = null;

    @ExternalName("email")
    @InternalProperty("Email.ID")
    private ETEmail email = null;

    @ExternalName("subject")
    private String subject = null;

    @ExternalName("numberSent")
    private Integer numberSent = null;

    @ExternalName("hardBounces")
    private Integer hardBounces = null;

    @ExternalName("softBounces")
    private Integer softBounces = null;

    @ExternalName("otherBounces")
    private Integer otherBounces = null;

    @ExternalName("uniqueClicks")
    private Integer uniqueClicks = null;

    @ExternalName("uniqueOpens")
    private Integer uniqueOpens = null;

    public ETSend() { }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public ETEmail getEmail() {
        return email;
    }

    public void setEmail(ETEmail email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getNumberSent() { return numberSent; }

    public void setNumberSent(Integer numberSent) { this.numberSent = numberSent; }

    public Integer getHardBounces() { return hardBounces; }

    public void setHardBounces(Integer hardBounces) { this.hardBounces = hardBounces; }

    public Integer getSoftBounces() { return softBounces; }

    public void setSoftBounces(Integer softBounces) { this.softBounces = softBounces; }

    public Integer getOtherBounces() { return otherBounces; }

    public void setOtherBounces(Integer otherBounces) { this.otherBounces = otherBounces; }

    public Integer getUniqueClicks() { return uniqueClicks; }

    public void setUniqueClicks(Integer uniqueClicks) { this.uniqueClicks = uniqueClicks; }

    public Integer getUniqueOpens() { return uniqueOpens; }

    public void setUniqueOpens(Integer uniqueOpens) { this.uniqueOpens = uniqueOpens; }
}
