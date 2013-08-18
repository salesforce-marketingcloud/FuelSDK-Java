package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSubscriber;

public interface ETSubscriberService extends ETCrudService {

	 public ETServiceResponse<ETSubscriber> get(ETClient client)
		        throws ETSdkException;
	 
	 public ETServiceResponse<ETSubscriber> get(ETClient client, ETFilter filter)
		        throws ETSdkException;
}
