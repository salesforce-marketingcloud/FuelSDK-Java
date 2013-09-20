//
// ETCampaignService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaign;

public interface ETCampaignService extends ETCrudService {

	ETServiceResponse<ETCampaign> get(ETClient client) throws ETSdkException ;

	ETServiceResponse<ETCampaign> get(ETClient client, ETFilter filter) throws ETSdkException ;

	ETServiceResponse<ETCampaign> post(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> patch(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> delete(ETClient client, ETCampaign campaign) throws ETSdkException ;

}
