package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETClickEvent;

public interface ETClickEventService extends ETGetService {

	public ETServiceResponse<ETClickEvent> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETClickEvent> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
}
