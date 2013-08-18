package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.model.ETListSubscriber;

public interface ETListSubscriberService extends ETGetService {

	public ETServiceResponse<ETListSubscriber> get(ETClient client)
	        throws ETSdkException;
}
