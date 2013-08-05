package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.Email;

@InternalType(type = Email.class, fields = {
	"ID",
	"CharacterSet",
	"ContentCheckStatus",
	"CreatedDate",
	"CustomerKey",
	"EmailType",
	"HasDynamicSubjectline",
	"HTMLBody",
	"IsActive",
	"IsHTMLPaste",
	"ModifiedDate",
	"Name",
	"Status",
	"Subject",
	"TextBody"
	})
public class ETEmail implements ETObject {

	protected Integer id;
	protected String characterSet;
	protected String contentCheckStatus;
	protected Date createdDate;
	protected String customerKey;
	protected String emailType;
	protected Boolean hasDynamicSubjectline;
	protected String htmlBody;
	protected Boolean active;
	protected Boolean htmlPaste;
	protected Date modifiedDate;
	protected String name;
	protected String status;
	protected String subject;
	protected String textBody;
	

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
