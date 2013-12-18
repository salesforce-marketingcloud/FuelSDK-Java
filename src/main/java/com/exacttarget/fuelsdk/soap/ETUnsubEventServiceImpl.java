//
// ETUnsubEventServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETUnsubEventService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETUnsubEvent;

public class ETUnsubEventServiceImpl extends ETGetServiceImpl<ETUnsubEvent> implements
		ETUnsubEventService {

	public ETResponse<ETUnsubEvent> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETUnsubEvent.class);
	}

	public ETResponse<ETUnsubEvent> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETUnsubEvent.class, filter);
	}

}
