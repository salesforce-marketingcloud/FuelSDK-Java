//
// ETSendClassificationServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSendClassificationService;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSendClassification;

public class ETSendClassificationServiceImpl extends ETCrudServiceImpl
		implements ETSendClassificationService {

	public ETServiceResponse<ETSendClassification> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETSendClassification.class);
	}

	public ETServiceResponse<ETSendClassification> get(ETClient client,
			ETFilter filter) throws ETSdkException {
		return super.get(client, ETSendClassification.class, filter);
	}

	public ETServiceResponse<ETSendClassification> post(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.post(client, sendClassificiation);
	}

	public ETServiceResponse<ETSendClassification> patch(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.patch(client, sendClassificiation);
	}

	public ETServiceResponse<ETSendClassification> delete(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.delete(client, sendClassificiation);
	}

}
