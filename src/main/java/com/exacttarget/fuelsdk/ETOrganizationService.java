package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETOrganiation;

public interface ETOrganizationService extends ETCrudService {

	public ETServiceResponse<ETOrganiation> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETOrganiation> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
	
	public ETServiceResponse<ETOrganiation> post(ETClient client, ETOrganiation organization)
	    	throws ETSdkException;
	
	public ETServiceResponse<ETOrganiation> patch(ETClient client, ETOrganiation organization)
			throws ETSdkException;
	
	public ETServiceResponse<ETOrganiation> delete(ETClient client, ETOrganiation organization)
	    	throws ETSdkException;
	
}
