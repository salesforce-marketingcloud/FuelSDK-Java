package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETBounceEvent;

public interface ETBouncEventService extends ETGetService {

	public ETServiceResponse<ETBounceEvent> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETBounceEvent> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
}
