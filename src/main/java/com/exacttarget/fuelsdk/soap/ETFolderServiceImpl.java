//
// ETFolderServiceImpl.java -
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
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceImpl extends ETCrudServiceImpl<ETFolder> implements
		ETFolderService {

	public ETResponse<ETFolder> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETFolder.class);
	}
	
	public ETResponse<ETFolder> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETFolder.class, filter);
	}

	public ETResponse<ETFolder> post(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.post(client, folder);
	}

	public ETResponse<ETFolder> patch(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.patch(client, folder);
	}

	public ETResponse<ETFolder> delete(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.delete(client, folder);
	}
	
	public ETResponse<ETFolder> post(ETClient client, List<ETFolder> folders)
			throws ETSdkException {
		return super.post(client, folders);
	}

	public ETResponse<ETFolder> patch(ETClient client, List<ETFolder> folders)
			throws ETSdkException {
		return super.patch(client, folders);
	}

	public ETResponse<ETFolder> delete(ETClient client, List<ETFolder> folders)
			throws ETSdkException {
		return super.delete(client, folders);
	}

}
