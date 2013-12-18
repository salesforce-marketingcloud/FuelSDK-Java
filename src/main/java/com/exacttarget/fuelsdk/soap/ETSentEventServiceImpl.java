//
// ETSentEventServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETSentEventService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSentEvent;

public class ETSentEventServiceImpl extends ETGetServiceImpl<ETSentEvent> implements
		ETSentEventService {

	public ETResponse<ETSentEvent> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETSentEvent.class);
	}

	public ETResponse<ETSentEvent> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETSentEvent.class, filter);
	}

}
