//
// ETCampaignAssetService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaignAsset;

public interface ETCampaignAssetService extends ETCrudService {

	/** Represents all possible Asset Types  */
	public static enum TYPE
	{
		EMAIL("email"),
		AUTOMATION_DEF("automation_definition"),
		CALENDAR_EVENT("calendar_event"),
		TRIGGERED("triggered"),
		LIST("list"),
		GROUP("group"),
		DATA_EXTENSION("data_extension"),
		SENDABLE_CUSTOM_OBJ("sendable_custom_object"),
		LANDING_PAGE("landing_page"),
		FACEBOOK_TAB("facebook_tab"),
		CT_FACEBOOK_POST("ct_facebook_post"),
		CT_TWITTER_POST("ct_twitter_post"),
		SMS_MESSAGE("sms_message"),
		PUSH_MESSAGE("push_message");
		
		private final String value;
		
		TYPE( String v )
		{
			value = v;
		}
		
		@Override
		public String toString()
		{
			return value;
		}
	}
	
	ETResponse<ETCampaignAsset> get(ETClient client) throws ETSdkException ;

	ETResponse<ETCampaignAsset> get(ETClient client, ETFilter filter) throws ETSdkException ;

	ETResponse<ETCampaignAsset> post(ETClient client, ETCampaignAsset asset) throws ETSdkException ;

	ETResponse<ETCampaignAsset> patch(ETClient client, ETCampaignAsset asset) throws ETSdkException ;

	ETResponse<ETCampaignAsset> delete(ETClient client, ETCampaignAsset asset) throws ETSdkException ;

}
