package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETFolder;

public class ETFolderServiceImpl extends ETCrudServiceImpl implements
		ETFolderService {

	public ETServiceResponse<ETFolder> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETFolder.class);
	}

}
