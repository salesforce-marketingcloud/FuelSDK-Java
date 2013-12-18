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

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETDataExtension;

public class ETDataExtensionServiceImpl extends ETCrudServiceImpl<ETDataExtension> implements
		ETDataExtensionService {

	public ETResponse<ETDataExtension> get(ETClient client)
			throws ETSdkException {

		return super.get(client, ETDataExtension.class);
	}

	public ETResponse<ETDataExtension> get(ETClient client,
			ETFilter filter) throws ETSdkException {

		return super.get(client, ETDataExtension.class, filter);
	}

	public ETResponse<ETDataExtension> post(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.post(client, dataExtension);
	}

	public ETResponse<ETDataExtension> patch(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.patch(client, dataExtension);
	}

	public ETResponse<ETDataExtension> delete(ETClient client,
			ETDataExtension dataExtension) throws ETSdkException {

		return super.delete(client, dataExtension);
	}
	
	public ETResponse<ETDataExtension> post(ETClient client,
			List<ETDataExtension> dataExtensions) throws ETSdkException {

		return super.post(client, dataExtensions);
	}

	public ETResponse<ETDataExtension> patch(ETClient client,
			List<ETDataExtension> dataExtensions) throws ETSdkException {

		return super.patch(client, dataExtensions);
	}

	public ETResponse<ETDataExtension> delete(ETClient client,
			List<ETDataExtension> dataExtensions) throws ETSdkException {

		return super.delete(client, dataExtensions);
	}


	
}
