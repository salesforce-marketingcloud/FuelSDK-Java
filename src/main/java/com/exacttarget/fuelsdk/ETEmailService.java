package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public interface ETEmailService extends ETCrudService {

	public ETServiceResponse<ETEmail> get(ETClient client)
	        throws ETSdkException;
	
	public ETServiceResponse<ETEmail> get(ETClient client, ETFilter filter)
	        throws ETSdkException;
	
	public ETServiceResponse<ETEmail> post(ETClient client, ETEmail email)
	    	throws ETSdkException;

	public ETServiceResponse<ETEmail> patch(ETClient client, ETEmail email)
			throws ETSdkException;

	public ETServiceResponse<ETEmail> delete(ETClient client, ETEmail email)
	    	throws ETSdkException;
}
