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

import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmailSendDefinition;

public interface ETEmailSendDefinitionService extends ETCrudService {

	public ETResponse<ETEmailSendDefinition> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETEmailSendDefinition> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETEmailSendDefinition> post(ETClient client, ETEmailSendDefinition emailSendDefinition)
	    	throws ETSdkException;

    public ETResponse<ETEmailSendDefinition> patch(ETClient client, ETEmailSendDefinition emailSendDefinition)
    		throws ETSdkException;

    public ETResponse<ETEmailSendDefinition> delete(ETClient client, ETEmailSendDefinition emailSendDefinition)
	    	throws ETSdkException;

	public ETResponse<ETEmailSendDefinition> send(ETClient client, ETEmailSendDefinition emailSendDefinition)
			throws ETSdkException;
	
	public ETResponse<ETEmailSendDefinition> post(ETClient client, List<ETEmailSendDefinition> emailSendDefinitions)
	    	throws ETSdkException;
	
	public ETResponse<ETEmailSendDefinition> patch(ETClient client, List<ETEmailSendDefinition> emailSendDefinitions)
    		throws ETSdkException;

    public ETResponse<ETEmailSendDefinition> delete(ETClient client, List<ETEmailSendDefinition> emailSendDefinitions)
	    	throws ETSdkException;
}
