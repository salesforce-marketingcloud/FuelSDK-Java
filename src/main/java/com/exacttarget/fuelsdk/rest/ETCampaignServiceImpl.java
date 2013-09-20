//
// ETCampaignServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.rest;

import com.exacttarget.fuelsdk.ETCampaignService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaign;

public class ETCampaignServiceImpl extends ETCrudServiceImpl implements ETCampaignService {

	public ETServiceResponse<ETCampaign> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETCampaign.class);
	}
	
	public ETServiceResponse<ETCampaign> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETCampaign.class, filter);
	}
	
	public ETServiceResponse<ETCampaign> post(ETClient client, ETCampaign campaign) 
		throws ETSdkException {
		return super.post(client, campaign);
	}
	
	public ETServiceResponse<ETCampaign> patch(ETClient client, ETCampaign campaign) 
		throws ETSdkException {
		return super.patch(client, campaign);
	}
	
	public ETServiceResponse<ETCampaign> delete(ETClient client, ETCampaign campaign) 
		throws ETSdkException {
		return super.delete(client, campaign);
	}
}
