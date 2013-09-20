//
// ETOpenEventServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETOpenEventService;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOpenEvent;

public class ETOpenEventServiceImpl extends ETGetServiceImpl implements
		ETOpenEventService {

	public ETServiceResponse<ETOpenEvent> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETOpenEvent.class);
	}

	public ETServiceResponse<ETOpenEvent> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETOpenEvent.class, filter);
	}

}
