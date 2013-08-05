package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.model.ETFolder;

public interface ETFolderService extends ETCrudService {

	public ETServiceResponse<ETFolder> get(ETClient client)
	        throws ETSdkException;
}
