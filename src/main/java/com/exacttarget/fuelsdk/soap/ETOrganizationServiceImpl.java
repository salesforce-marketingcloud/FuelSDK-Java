package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETOrganizationService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganiation;

public class ETOrganizationServiceImpl extends ETCrudServiceImpl implements
		ETOrganizationService {

	public ETServiceResponse<ETOrganiation> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETOrganiation.class);
	}

	public ETServiceResponse<ETOrganiation> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETOrganiation.class, filter);
	}

	public ETServiceResponse<ETOrganiation> post(ETClient client,
			ETOrganiation organization) throws ETSdkException {
		return super.post(client, organization);
	}

	public ETServiceResponse<ETOrganiation> patch(ETClient client,
			ETOrganiation organization) throws ETSdkException {
		return super.patch(client, organization);
	}

	public ETServiceResponse<ETOrganiation> delete(ETClient client,
			ETOrganiation organization) throws ETSdkException {
		return super.delete(client, organization);
	}

}
