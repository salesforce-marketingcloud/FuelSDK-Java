//
// ETListSubscriberServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETListSubscriberService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETListSubscriber;

public class ETListSubscriberServiceImpl extends ETGetServiceImpl<ETListSubscriber> implements
		ETListSubscriberService {

	public ETServiceResponse<ETListSubscriber> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETListSubscriber.class);
	}

	public ETServiceResponse<ETListSubscriber> get(ETClient client,
			ETFilter filter) throws ETSdkException {
		return super.get(client, ETListSubscriber.class, filter);
	}

}
