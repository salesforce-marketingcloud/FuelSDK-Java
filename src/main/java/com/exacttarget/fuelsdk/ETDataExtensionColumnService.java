//
// ETDataExtensionColumnService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;

public interface ETDataExtensionColumnService extends ETGetService {

	public ETResponse<ETDataExtensionColumn> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETDataExtensionColumn> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

}
