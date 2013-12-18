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
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETEmail;

public class ETEmailServiceImpl extends ETCrudServiceImpl<ETEmail> implements
		ETEmailService {

	public ETResponse<ETEmail> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETEmail.class);
	}

	public ETResponse<ETEmail> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETEmail.class, filter);
	}

	public ETResponse<ETEmail> post(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.post(client, email);
	}

	public ETResponse<ETEmail> patch(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.patch(client, email);
	}

	public ETResponse<ETEmail> delete(ETClient client, ETEmail email)
			throws ETSdkException {
		return super.delete(client, email);
	}
	
	public ETResponse<ETEmail> post(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.post(client, emails);
	}

	public ETResponse<ETEmail> patch(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.patch(client, emails);
	}

	public ETResponse<ETEmail> delete(ETClient client, List<ETEmail> emails)
			throws ETSdkException {
		return super.delete(client, emails);
	}

}
