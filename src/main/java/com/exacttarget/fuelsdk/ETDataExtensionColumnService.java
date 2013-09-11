package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;

public interface ETDataExtensionColumnService extends ETGetService {

	public ETServiceResponse<ETDataExtensionColumn> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETDataExtensionColumn> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
	
}
