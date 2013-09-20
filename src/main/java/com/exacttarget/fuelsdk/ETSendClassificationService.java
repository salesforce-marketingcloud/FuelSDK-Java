//
// ETSendClassificationService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSendClassification;

public interface ETSendClassificationService extends ETCrudService {

	public ETServiceResponse<ETSendClassification> get(ETClient client)
	        throws ETSdkException;

	public ETServiceResponse<ETSendClassification> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETSendClassification> post(ETClient client, ETSendClassification sendClassificiation)
	    	throws ETSdkException;

	public ETServiceResponse<ETSendClassification> patch(ETClient client, ETSendClassification sendClassificiation)
			throws ETSdkException;

	public ETServiceResponse<ETSendClassification> delete(ETClient client, ETSendClassification sendClassificiation)
	    	throws ETSdkException;
}
