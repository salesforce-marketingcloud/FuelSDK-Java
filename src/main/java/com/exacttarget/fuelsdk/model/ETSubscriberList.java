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
import com.exacttarget.fuelsdk.internal.SubscriberList;

@InternalSoapType(type = SubscriberList.class)
public class ETSubscriberList extends ETSoapObject {

	@InternalSoapField(name = "status")
    private ETSubscriberStatus status;

	@InternalSoapField(name = "list")
	private ETList list;

	@InternalSoapField(name = "action")
	private String action;

	@InternalSoapField(name = "subscriber")
	private ETSubscriber subscriber;

	public ETSubscriberList() {}

	public ETSubscriberStatus getStatus() {
		return status;
	}

	public void setStatus(ETSubscriberStatus status) {
		this.status = status;
	}

	public ETList getList() {
		return list;
	}

	public void setList(ETList list) {
		this.list = list;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ETSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ETSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "ETSubscriberList [status=" + status + ", list=" + list
				+ ", action=" + action + ", subscriber=" + subscriber + ", id="
				+ id + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", customerKey=" + customerKey + "]";
	}



}
