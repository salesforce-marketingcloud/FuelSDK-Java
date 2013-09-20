//
// ETEmailSendDefinitionService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmailSendDefinition;

public interface ETEmailSendDefinitionService extends ETCrudService {

	public ETServiceResponse<ETEmailSendDefinition> get(ETClient client)
	        throws ETSdkException;

	public ETServiceResponse<ETEmailSendDefinition> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETEmailSendDefinition> post(ETClient client, ETEmailSendDefinition emailSendDefinition)
	    	throws ETSdkException;

    public ETServiceResponse<ETEmailSendDefinition> patch(ETClient client, ETEmailSendDefinition emailSendDefinition)
    		throws ETSdkException;

    public ETServiceResponse<ETEmailSendDefinition> delete(ETClient client, ETEmailSendDefinition emailSendDefinition)
	    	throws ETSdkException;

	public ETServiceResponse<ETEmailSendDefinition> send(ETClient client, ETEmailSendDefinition emailSendDefinition)
			throws ETSdkException;
}
