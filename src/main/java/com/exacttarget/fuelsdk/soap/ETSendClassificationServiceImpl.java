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

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSendClassificationService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETSendClassification;

public class ETSendClassificationServiceImpl extends ETCrudServiceImpl<ETSendClassification>
		implements ETSendClassificationService {

	public ETResponse<ETSendClassification> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETSendClassification.class);
	}

	public ETResponse<ETSendClassification> get(ETClient client,
			ETFilter filter) throws ETSdkException {
		return super.get(client, ETSendClassification.class, filter);
	}

	public ETResponse<ETSendClassification> post(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.post(client, sendClassificiation);
	}

	public ETResponse<ETSendClassification> patch(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.patch(client, sendClassificiation);
	}

	public ETResponse<ETSendClassification> delete(ETClient client,
			ETSendClassification sendClassificiation) throws ETSdkException {
		return super.delete(client, sendClassificiation);
	}
	
	public ETResponse<ETSendClassification> post(ETClient client,
			List<ETSendClassification> sendClassificiations) throws ETSdkException {
		return super.post(client, sendClassificiations);
	}

	public ETResponse<ETSendClassification> patch(ETClient client,
			List<ETSendClassification> sendClassificiations) throws ETSdkException {
		return super.patch(client, sendClassificiations);
	}

	public ETResponse<ETSendClassification> delete(ETClient client,
			List<ETSendClassification> sendClassificiations) throws ETSdkException {
		return super.delete(client, sendClassificiations);
	}

}
