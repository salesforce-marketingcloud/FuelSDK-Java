package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Email;

@InternalSoapType(type = Email.class)
public class ETEmail extends BaseSoapSerializableObject implements ETObject {
    @InternalSoapField(name="characterSet")
	protected String characterSet;
    @InternalSoapField(name="contentCheckStatus")
	protected String contentCheckStatus;
    @InternalSoapField(name="emailType")
	protected String emailType;
    @InternalSoapField(name="hasDynamicSubjectLine")
	protected Boolean hasDynamicSubjectline;
    @InternalSoapField(name="htmlBody")
	protected String htmlBody;
    @InternalSoapField(name="isActive")
	protected Boolean active;
    @InternalSoapField(name="isHTMLPaste")
	protected Boolean htmlPaste;
    @InternalSoapField(name="name")
	protected String name;
    @InternalSoapField(name="status")
	protected String status;
    @InternalSoapField(name="subject")
	protected String subject;
    @InternalSoapField(name="textBody")
	protected String textBody;

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
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public Boolean getHasDynamicSubjectline() {
		return hasDynamicSubjectline;
	}
	public void setHasDynamicSubjectline(Boolean hasDynamicSubjectline) {
		this.hasDynamicSubjectline = hasDynamicSubjectline;
	}
	public String getHtmlBody() {
		return htmlBody;
	}
	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getHtmlPaste() {
		return htmlPaste;
	}
	public void setHtmlPaste(Boolean htmlPaste) {
		this.htmlPaste = htmlPaste;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTextBody() {
		return textBody;
	}
	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	@Override
	public String toString() {
		return "ETEmail [id=" + id + ", characterSet=" + characterSet
            + ", contentCheckStatus=" + contentCheckStatus
            + ", createdDate=" + createdDate + ", customerKey="
            + customerKey + ", emailType=" + emailType
            + ", hasDynamicSubjectline=" + hasDynamicSubjectline
            + ", htmlBody=" + htmlBody + ", active=" + active
            + ", htmlPaste=" + htmlPaste + ", modifiedDate=" + modifiedDate
            + ", name=" + name + ", status=" + status + ", subject="
            + subject + ", textBody=" + textBody + "]";
	}
	
}
