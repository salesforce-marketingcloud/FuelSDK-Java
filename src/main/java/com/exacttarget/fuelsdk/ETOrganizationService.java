//
// ETOrganizationService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganization;

public interface ETOrganizationService extends ETCrudService {

	public ETServiceResponse<ETOrganization> get(ETClient client)
	        throws ETSdkException;

	public ETServiceResponse<ETOrganization> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETOrganization> post(ETClient client, ETOrganization organization)
	    	throws ETSdkException;

	public ETServiceResponse<ETOrganization> patch(ETClient client, ETOrganization organization)
			throws ETSdkException;

	public ETServiceResponse<ETOrganization> delete(ETClient client, ETOrganization organization)
	    	throws ETSdkException;

}
