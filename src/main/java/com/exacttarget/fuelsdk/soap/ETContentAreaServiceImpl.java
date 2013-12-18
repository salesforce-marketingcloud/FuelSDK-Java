//
// ETContentAreaServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETContentAreaService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETContentArea;

public class ETContentAreaServiceImpl extends ETCrudServiceImpl<ETContentArea> implements
		ETContentAreaService {

	public ETResponse<ETContentArea> get(ETClient client)
			throws ETSdkException {
		return super.get(client,  ETContentArea.class);
	}

	public ETResponse<ETContentArea> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETContentArea.class, filter);
	}

	public ETResponse<ETContentArea> post(ETClient client,
			ETContentArea contentArea) throws ETSdkException {
		return super.post(client, contentArea);
	}

	public ETResponse<ETContentArea> patch(ETClient client,
			ETContentArea contentArea) throws ETSdkException {
		return super.patch(client, contentArea);
	}

	public ETResponse<ETContentArea> delete(ETClient client,
			ETContentArea contentArea) throws ETSdkException {
		return super.delete(client, contentArea);
	}
	
	public ETResponse<ETContentArea> post(ETClient client,
			List<ETContentArea> contentAreas) throws ETSdkException {
		return super.post(client, contentAreas);
	}

	public ETResponse<ETContentArea> patch(ETClient client,
			List<ETContentArea> contentAreas) throws ETSdkException {
		return super.patch(client, contentAreas);
	}

	public ETResponse<ETContentArea> delete(ETClient client,
			List<ETContentArea> contentAreas) throws ETSdkException {
		return super.delete(client, contentAreas);
	}

}
