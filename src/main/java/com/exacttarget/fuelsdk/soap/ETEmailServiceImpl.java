package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public class ETEmailServiceImpl extends ETCrudServiceImpl implements
		ETEmailService {

	public ETServiceResponse<ETEmail> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETEmail.class);
	}

	public ETServiceResponse<ETEmail> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETEmail.class, filter);
	}

}
