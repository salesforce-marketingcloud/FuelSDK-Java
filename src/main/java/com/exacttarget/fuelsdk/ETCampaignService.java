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

	ETResponse<ETCampaign> get(ETClient client) throws ETSdkException ;

	ETResponse<ETCampaign> get(ETClient client, ETFilter filter) throws ETSdkException ;

	ETResponse<ETCampaign> post(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETResponse<ETCampaign> patch(ETClient client, ETCampaign campaign) throws ETSdkException ;

	ETResponse<ETCampaign> delete(ETClient client, ETCampaign campaign) throws ETSdkException ;

}
