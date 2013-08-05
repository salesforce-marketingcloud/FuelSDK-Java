package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.ListSubscriber;

@InternalType(type = ListSubscriber.class, fields = {
	"ID",
	"CreatedDate",
	"ListID",
	"ModifiedDate",
	"Status",
	"SubscriberKey"
})
public class ETListSubscriber implements ETObject {

	protected Integer id;
	protected Date createdDate;
	protected Integer listId;
	protected Date modifiedDate;
	protected String status;
	protected String subscriberKey;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubscriberKey() {
		return subscriberKey;
	}

	public void setSubscriberKey(String subscriberKey) {
		this.subscriberKey = subscriberKey;
	}

	@Override
	public String toString() {
		return "ETListSubscriber [id=" + id + ", createdDate=" + createdDate
				+ ", listId=" + listId
				+ ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", subscriberKey=" + subscriberKey + "]";
	}
	
}
