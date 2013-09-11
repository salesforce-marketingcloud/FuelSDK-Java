package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionColumnService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;

public class ETDataExtensionColumnServiceImpl extends ETGetServiceImpl
		implements ETDataExtensionColumnService {

	public ETServiceResponse<ETDataExtensionColumn> get(ETClient client)
			throws ETSdkException {

		return super.get(client, ETDataExtensionColumn.class);
	}

	public ETServiceResponse<ETDataExtensionColumn> get(ETClient client,
			ETFilter filter) throws ETSdkException {
		
		return super.get(client, ETDataExtensionColumn.class, filter);
	}

}
