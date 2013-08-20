package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOpenEvent;

public interface ETOpenEventService extends ETGetService {

	public ETServiceResponse<ETOpenEvent> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETOpenEvent> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
}
