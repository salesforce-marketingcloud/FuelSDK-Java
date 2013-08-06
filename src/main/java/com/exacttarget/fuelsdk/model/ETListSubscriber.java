package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.ListSubscriber;

@InternalType(type = ListSubscriber.class)
public class ETListSubscriber extends BaseSerializableObject implements ETObject {
    @InternalField(name="listID")
	protected Integer listId;
    @InternalField(name="status")
	protected String status;
    @InternalField(name="subscriberKey")
	protected String subscriberKey;

	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
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
