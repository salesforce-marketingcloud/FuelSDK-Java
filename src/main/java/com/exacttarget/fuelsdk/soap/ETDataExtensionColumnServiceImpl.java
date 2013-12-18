//
// ETDataExtensionColumnServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionColumnService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;

public class ETDataExtensionColumnServiceImpl extends ETGetServiceImpl<ETDataExtensionColumn> 
		implements ETDataExtensionColumnService {

	public ETResponse<ETDataExtensionColumn> get(ETClient client)
			throws ETSdkException {

		return super.get(client, ETDataExtensionColumn.class);
	}

	public ETResponse<ETDataExtensionColumn> get(ETClient client,
			ETFilter filter) throws ETSdkException {
		
		return super.get(client, ETDataExtensionColumn.class, filter);
	}

}
