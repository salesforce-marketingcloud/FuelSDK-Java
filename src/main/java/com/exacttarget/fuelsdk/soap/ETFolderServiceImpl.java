package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceImpl extends ETCrudServiceImpl implements
		ETFolderService {

	public ETServiceResponse<ETFolder> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETFolder.class);
	}
	
	public ETServiceResponse<ETFolder> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETFolder.class, filter);
	}

	public ETServiceResponse<ETFolder> post(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.post(client, folder);
	}

	public ETServiceResponse<ETFolder> patch(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.patch(client, folder);
	}

	public ETServiceResponse<ETFolder> delete(ETClient client, ETFolder folder)
			throws ETSdkException {
		return super.delete(client, folder);
	}

}
