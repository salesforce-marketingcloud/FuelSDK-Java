//
// ETListSubscriberService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETListSubscriber;

public interface ETListSubscriberService extends ETGetService {

	public ETResponse<ETListSubscriber> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETListSubscriber> get(ETClient client, ETFilter filter)
		 	throws ETSdkException;
}
