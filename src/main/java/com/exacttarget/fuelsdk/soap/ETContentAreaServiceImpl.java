package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETContentAreaService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETContentArea;

public class ETContentAreaServiceImpl extends ETCrudServiceImpl implements
		ETContentAreaService {

	public ETServiceResponse<ETContentArea> get(ETClient client)
			throws ETSdkException {
		return super.get(client,  ETContentArea.class);
	}

}
