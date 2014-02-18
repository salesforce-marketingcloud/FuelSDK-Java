//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
