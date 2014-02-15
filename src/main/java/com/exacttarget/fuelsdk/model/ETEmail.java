//
// ETEmail.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Email;

@InternalSoapType(type = Email.class)
public class ETEmail extends ETSoapObject {
    @InternalSoapField(name="name")
    protected String name = null;
    @InternalSoapField(name="categoryID")
    protected Integer categoryID = null;
    @InternalSoapField(name="htmlBody")
    protected String htmlBody = null;
    @InternalSoapField(name="textBody")
    protected String textBody = null;
    @InternalSoapField(name="subject")
    protected String subject = null;
    @InternalSoapField(name="isHTMLPaste")
    protected Boolean htmlPaste = null;
    @InternalSoapField(name="emailType")
    protected String emailType = null;
    @InternalSoapField(name="characterSet")
    protected String characterSet = null;

    @InternalSoapField(name="contentCheckStatus")
    protected String contentCheckStatus = null;
    @InternalSoapField(name="hasDynamicSubjectLine")
    protected Boolean hasDynamicSubjectline = null;
    @InternalSoapField(name="isActive")
    protected Boolean active = null;
    @InternalSoapField(name="status")
    protected String status = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getHtmlPaste() {
        return htmlPaste;
    }

    public void setHtmlPaste(Boolean htmlPaste) {
        this.htmlPaste = htmlPaste;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    public String getContentCheckStatus() {
        return contentCheckStatus;
    }

    public void setContentCheckStatus(String contentCheckStatus) {
        this.contentCheckStatus = contentCheckStatus;
    }

    public Boolean getHasDynamicSubjectline() {
        return hasDynamicSubjectline;
    }

    public void setHasDynamicSubjectline(Boolean hasDynamicSubjectline) {
        this.hasDynamicSubjectline = hasDynamicSubjectline;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
