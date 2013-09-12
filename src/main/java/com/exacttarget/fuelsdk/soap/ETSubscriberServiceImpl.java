package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public class ETSubscriberServiceImpl extends ETCrudServiceImpl implements ETSubscriberService {

	public ETServiceResponse<ETSubscriber> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETSubscriber.class);
	}
	
	public ETServiceResponse<ETSubscriber> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETSubscriber.class, filter);
	}
	
	public ETServiceResponse<ETSubscriber> post(ETClient client, ETSubscriber subscriber) 
			throws ETSdkException {
		return super.post(client, subscriber);
	}

	public ETServiceResponse<ETSubscriber> patch(ETClient client,
			ETSubscriber subscriber) throws ETSdkException {
		return super.patch(client,  subscriber);
	}

	public ETServiceResponse<ETSubscriber> delete(ETClient client,
			ETSubscriber subscriber) throws ETSdkException {
		return super.delete(client,  subscriber);
	}

}
