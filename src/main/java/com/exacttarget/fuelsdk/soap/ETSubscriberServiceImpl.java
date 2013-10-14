//
// ETSubscriberServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public class ETSubscriberServiceImpl extends ETCrudServiceImpl<ETSubscriber> implements ETSubscriberService {

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
	
	public ETServiceResponse<ETSubscriber> post(ETClient client, List<ETSubscriber> subscribers) 
			throws ETSdkException {
		return super.post(client, subscribers);
	}

	public ETServiceResponse<ETSubscriber> patch(ETClient client,
			ETSubscriber subscriber) throws ETSdkException {
		return super.patch(client,  subscriber);
	}
	
	public ETServiceResponse<ETSubscriber> patch(ETClient client,
			List<ETSubscriber> subscribers) throws ETSdkException {
		return super.patch(client,  subscribers);
	}
	
	public ETServiceResponse<ETSubscriber> delete(ETClient client,
			ETSubscriber subscriber) throws ETSdkException {
		return super.delete(client,  subscriber);
	}
	
	public ETServiceResponse<ETSubscriber> delete(ETClient client,
			List<ETSubscriber> subscribers) throws ETSdkException {
		return super.delete(client,  subscribers);
	}

}
