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
import com.exacttarget.fuelsdk.internal.Email;

@InternalSoapType(type = Email.class)
public class ETEmail extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "categoryID")
    private Integer categoryID = null;
    @InternalSoapField(name = "htmlBody")
    private String htmlBody = null;
    @InternalSoapField(name = "textBody")
    private String textBody = null;
    @InternalSoapField(name = "subject")
    private String subject = null;
    @InternalSoapField(name = "isHTMLPaste")
    private Boolean htmlPaste = null;
    @InternalSoapField(name = "emailType")
    private String emailType = null;
    @InternalSoapField(name = "characterSet")
    private String characterSet = null;

    @InternalSoapField(name = "contentCheckStatus")
    private String contentCheckStatus = null;
    @InternalSoapField(name = "hasDynamicSubjectLine")
    private Boolean hasDynamicSubjectline = null;
    @InternalSoapField(name = "isActive")
    private Boolean active = null;
    @InternalSoapField(name = "status")
    private String status = null;

    public ETEmail() {}

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
