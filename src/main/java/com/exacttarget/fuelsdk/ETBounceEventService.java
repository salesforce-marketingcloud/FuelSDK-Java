//
// ETBounceEventService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETBounceEvent;

public interface ETBounceEventService extends ETGetService {

	public ETResponse<ETBounceEvent> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETBounceEvent> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
}
