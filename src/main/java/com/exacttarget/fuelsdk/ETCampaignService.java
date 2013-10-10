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
		ModifiedDate_ASC( "ModifiedDate%20ASC" ),
		Name_ASC("Name%20ASC" ),
		CreatedDate_ASC( "CreatedDate%20ASC" ),
		CampaignCode_ASC( "CampaignCode%20ASC" ),
		Id_ASC( "Id%20ASC" ),
		ModifiedDate_DESC( "ModifiedDate%20DESC" ),
		Name_DESC( "Name%20DESC" ),
		CreatedDate_DESC( "CreatedDate%20DESC" ),
		CampaignCode_DESC( "CampaignCode%20DESC" ),
		Id_DESC( "Id%20DESC" );

	    private final String value;
		
		ORDER_BY( String v )
		{
			value = v;
		}
		
		@Override
		public String toString()
		{
			return value;
		}
	}
	
	ETServiceResponse<ETCampaign> get(ETClient client) throws ETSdkException ;

	ETServiceResponse<ETCampaign> get(ETClient client, ETFilter filter) throws ETSdkException ;

	ETServiceResponse<ETCampaign> post(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> patch(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETServiceResponse<ETCampaign> delete(ETClient client, ETCampaign campaign) throws ETSdkException ;

}
