//
// ETClickEventServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETClickEventService;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETClickEvent;

public class ETClickEventServiceImpl extends ETGetServiceImpl<ETClickEvent> implements
		ETClickEventService {

	public ETServiceResponse<ETClickEvent> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETClickEvent.class);
	}

	public ETServiceResponse<ETClickEvent> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETClickEvent.class, filter);
	}

}
