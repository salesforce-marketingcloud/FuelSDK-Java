package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETUnsubEventService;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETUnsubEvent;

public class ETUnsubEventServiceImpl extends ETGetServiceImpl implements
		ETUnsubEventService {

	public ETServiceResponse<ETUnsubEvent> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETUnsubEvent.class);
	}

	public ETServiceResponse<ETUnsubEvent> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETUnsubEvent.class, filter);
	}

}
