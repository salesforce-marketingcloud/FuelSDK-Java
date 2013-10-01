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

	/** Represents all the URL Parameters  */
	public static enum URL_PARAM
	{
		page,
		pageSize,
		orderBy;
	}

	/** Represents all available orderBy options. */
	public static enum ORDER_BY
	{
		ModifiedDate_ASC,
		Name_ASC,
		CreatedDate_ASC,
		CampaignCode_ASC,
		Id_ASC,
		ModifiedDate_DESC,
		Name_DESC,
		CreatedDate_DESC,
		CampaignCode_DESC,
		Id_DESC;
		
		@Override
		public String toString()
		{
			return name().replace("_", "%20");
		}
	}
	
	ETServiceResponse<ETCampaign> get(ETClient client) throws ETSdkException ;

	ETServiceResponse<ETCampaign> get(ETClient client, ETFilter filter) throws ETSdkException ;

	ETServiceResponse<ETCampaign> post(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> patch(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> delete(ETClient client, ETCampaign campaign) throws ETSdkException ;

}
