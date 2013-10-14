//
// ETOrganizationServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETOrganizationService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganization;

public class ETOrganizationServiceImpl extends ETCrudServiceImpl<ETOrganization> implements
		ETOrganizationService {

	public ETServiceResponse<ETOrganization> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETOrganization.class);
	}

	public ETServiceResponse<ETOrganization> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETOrganization.class, filter);
	}

	public ETServiceResponse<ETOrganization> post(ETClient client,
			ETOrganization organization) throws ETSdkException {
		return super.post(client, organization);
	}

	public ETServiceResponse<ETOrganization> patch(ETClient client,
			ETOrganization organization) throws ETSdkException {
		return super.patch(client, organization);
	}

	public ETServiceResponse<ETOrganization> delete(ETClient client,
			ETOrganization organization) throws ETSdkException {
		return super.delete(client, organization);
	}
	
	public ETServiceResponse<ETOrganization> post(ETClient client,
			List<ETOrganization> organizations) throws ETSdkException {
		return super.post(client, organizations);
	}

	public ETServiceResponse<ETOrganization> patch(ETClient client,
			List<ETOrganization> organizations) throws ETSdkException {
		return super.patch(client, organizations);
	}

	public ETServiceResponse<ETOrganization> delete(ETClient client,
			List<ETOrganization> organizations) throws ETSdkException {
		return super.delete(client, organizations);
	}

}
