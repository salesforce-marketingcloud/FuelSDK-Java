//
// ETListServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETList;

public class ETListServiceImpl extends ETCrudServiceImpl<ETList>
    implements ETListService
{
    public ETServiceResponse<ETList> get(ETClient client)
        throws ETSdkException
    {
        return super.get(client, ETList.class);
    }

	public ETServiceResponse<ETList> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETList.class, filter);
	}

	public ETServiceResponse<ETList> post(ETClient client, ETList list)
			throws ETSdkException {
		return super.post(client, list);
	}

	public ETServiceResponse<ETList> patch(ETClient client, ETList list)
			throws ETSdkException {
		return super.patch(client, list);
	}

	public ETServiceResponse<ETList> delete(ETClient client, ETList list)
			throws ETSdkException {
		return super.delete(client, list);
	}
	
	public ETServiceResponse<ETList> post(ETClient client, List<ETList> lists)
			throws ETSdkException {
		return super.post(client, lists);
	}

	public ETServiceResponse<ETList> patch(ETClient client, List<ETList> lists)
			throws ETSdkException {
		return super.patch(client, lists);
	}

	public ETServiceResponse<ETList> delete(ETClient client, List<ETList> lists)
			throws ETSdkException {
		return super.delete(client, lists);
	}
}
