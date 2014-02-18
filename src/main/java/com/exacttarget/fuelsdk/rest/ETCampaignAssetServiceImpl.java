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

package com.exacttarget.fuelsdk.rest;

import com.exacttarget.fuelsdk.ETCampaignAssetService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaignAsset;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ETCampaignAssetServiceImpl extends ETCrudServiceImpl implements ETCampaignAssetService {

	public ETResponse<ETCampaignAsset> get(ETClient client)
			throws ETSdkException {
		return super.get(client, ETCampaignAsset.class);
	}

	public ETResponse<ETCampaignAsset> get(ETClient client, ETFilter filter)
			throws ETSdkException {
		return super.get(client, ETCampaignAsset.class, filter);
	}

	public ETResponse<ETCampaignAsset> post(ETClient client, ETCampaignAsset asset)
		throws ETSdkException {
		return super.post(client, asset);
	}

	public ETResponse<ETCampaignAsset> patch(ETClient client, ETCampaignAsset asset)
		throws ETSdkException {
		return super.patch(client, asset);
	}

	public ETResponse<ETCampaignAsset> delete(ETClient client, ETCampaignAsset asset)
		throws ETSdkException
	{
		return super.delete(client, asset);
	}

	@Override
	protected <T extends ETObject> JsonObject createRequest(T object, Class<T> type) throws ETSdkException {

		ETCampaignAsset asset = (ETCampaignAsset)object;

		JsonObject root = new JsonObject();

		JsonArray ids = new JsonArray();

		ids.add(new JsonPrimitive(asset.getItemID()));

		root.add("ids", ids);

		root.addProperty("type", asset.getType());

		return root;
	}
}
