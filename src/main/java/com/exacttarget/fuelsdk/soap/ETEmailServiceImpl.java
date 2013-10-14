//
// ETEmailServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public class ETEmailServiceImpl extends ETCrudServiceImpl<ETEmail> implements
		ETEmailService {

	public ETServiceResponse<ETEmail> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETEmail.class);
	}

	public ETServiceResponse<ETEmail> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETEmail.class, filter);
	}

	public ETServiceResponse<ETEmail> post(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.post(client, email);
	}

	public ETServiceResponse<ETEmail> patch(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.patch(client, email);
	}

	public ETServiceResponse<ETEmail> delete(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.delete(client, email);
	}
	
	public ETServiceResponse<ETEmail> post(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.post(client, emails);
	}

	public ETServiceResponse<ETEmail> patch(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.patch(client, emails);
	}

	public ETServiceResponse<ETEmail> delete(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.delete(client, emails);
	}

}
