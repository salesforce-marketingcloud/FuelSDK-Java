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

import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganization;

public interface ETOrganizationService extends ETCrudService {

	public ETResponse<ETOrganization> get(ETClient client)
	        throws ETSdkException;

	public ETResponse<ETOrganization> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETResponse<ETOrganization> post(ETClient client, ETOrganization organization)
	    	throws ETSdkException;

	public ETResponse<ETOrganization> patch(ETClient client, ETOrganization organization)
			throws ETSdkException;

	public ETResponse<ETOrganization> delete(ETClient client, ETOrganization organization)
	    	throws ETSdkException;
	
	public ETResponse<ETOrganization> post(ETClient client, List<ETOrganization> organizations)
	    	throws ETSdkException;

	public ETResponse<ETOrganization> patch(ETClient client, List<ETOrganization> organizations)
			throws ETSdkException;

	public ETResponse<ETOrganization> delete(ETClient client, List<ETOrganization> organizations)
	    	throws ETSdkException;

}
