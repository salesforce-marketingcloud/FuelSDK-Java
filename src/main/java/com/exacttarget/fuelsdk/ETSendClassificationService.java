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

import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSendClassification;

public interface ETSendClassificationService extends ETCrudService {

	public ETResponse<ETSendClassification> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETSendClassification> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETSendClassification> post(ETClient client, ETSendClassification sendClassificiation)
	    	throws ETSdkException;

	public ETResponse<ETSendClassification> patch(ETClient client, ETSendClassification sendClassificiation)
			throws ETSdkException;

	public ETResponse<ETSendClassification> delete(ETClient client, ETSendClassification sendClassificiation)
	    	throws ETSdkException;
	
	public ETResponse<ETSendClassification> post(ETClient client, List<ETSendClassification> sendClassificiations)
	    	throws ETSdkException;

	public ETResponse<ETSendClassification> patch(ETClient client, List<ETSendClassification> sendClassificiations)
			throws ETSdkException;

	public ETResponse<ETSendClassification> delete(ETClient client, List<ETSendClassification> sendClassificiations)
	    	throws ETSdkException;
}
