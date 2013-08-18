package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.model.ETContentArea;

public interface ETContentAreaService extends ETCrudService {
	public ETServiceResponse<ETContentArea> get(ETClient client)
	        throws ETSdkException;
}
