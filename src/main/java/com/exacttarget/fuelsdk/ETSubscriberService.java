package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public interface ETSubscriberService extends ETCrudService {

	public ETServiceResponse<ETSubscriber> get(ETClient client)
			throws ETSdkException;
	 
	public ETServiceResponse<ETSubscriber> get(ETClient client, ETFilter filter)
			throws ETSdkException;
	 
	public ETServiceResponse<ETSubscriber> post(ETClient client, ETSubscriber subscriber)
	    	throws ETSdkException;
	
	public ETServiceResponse<ETSubscriber> patch(ETClient client, ETSubscriber subscriber)
			throws ETSdkException;
	
	public ETServiceResponse<ETSubscriber> delete(ETClient client, ETSubscriber subscriber)
	    	throws ETSdkException;

}
