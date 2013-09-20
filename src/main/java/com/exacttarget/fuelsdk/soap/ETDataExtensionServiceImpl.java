//
// ETDataExtensionServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtension;

public class ETDataExtensionServiceImpl extends ETCrudServiceImpl implements
		ETDataExtensionService {

	public ETServiceResponse<ETDataExtension> get(ETClient client)
			throws ETSdkException {

		return super.get(client, ETDataExtension.class);
	}

	public ETServiceResponse<ETDataExtension> get(ETClient client,
			ETFilter filter) throws ETSdkException {

		return super.get(client, ETDataExtension.class, filter);
	}

	public ETServiceResponse<ETDataExtension> post(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.post(client, dataExtension);
	}

	public ETServiceResponse<ETDataExtension> patch(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.patch(client, dataExtension);
	}

	public ETServiceResponse<ETDataExtension> delete(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.delete(client, dataExtension);
	}


	
}
