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

import com.exacttarget.fuelsdk.annotations.RestAnnotations;

@RestAnnotations(path = "/hub/v1/campaigns/{campaignId}/assets/{id}",
                 primaryKey = "id",
                 collectionKey = "entities")
public class ETCampaignAsset extends ETRestObject {
    private String campaignId = null;
    private String type = null;
    private String objectId = null;

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public static ETResponse<ETCampaignAsset> create(ETClient client,
                                                     ETCampaignAsset campaignAsset)
        throws ETSdkException
    {
        return ETRestObject.create(client, campaignAsset);
    }

    public static ETResponse<ETCampaignAsset> retrieve(ETClient client)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, ETCampaignAsset.class);
    }

    public static ETResponse<ETCampaignAsset> retrieve(ETClient client,
                                                       Integer page,
                                                       Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, page, pageSize, ETCampaignAsset.class);
    }

    public static ETResponse<ETCampaignAsset> retrieve(ETClient client,
                                                       String filter)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, ETCampaignAsset.class);
    }

    public static ETResponse<ETCampaignAsset> retrieve(ETClient client,
                                                       String filter,
                                                       Integer page,
                                                       Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, page, pageSize, ETCampaignAsset.class);
    }

    public ETResponse<ETCampaignAsset> update(ETClient client)
        throws ETSdkException
    {
        return super.update(client);
    }

    public ETResponse<ETCampaignAsset> delete(ETClient client)
        throws ETSdkException
    {
        return super.delete(client);
    }

    @Override
    public String toString() {
        toStringOpen();
        toStringAppend("campaignId", getCampaignId());
        toStringAppend("type", getType());
        toStringAppend("objectId", getObjectId());
        toStringClose();
        return getToString();
    }
}
