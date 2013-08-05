package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.model.ETEmail;

public interface ETEmailService extends ETCrudService {

	public ETServiceResponse<ETEmail> get(ETClient client)
	        throws ETSdkException;
}
