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

@Deprecated
public interface ETCampaignService extends ETCrudService {
    public static enum URL_PARAM {
        page,
        pageSize,
        orderBy;
    }

    public static enum ORDER_BY {
        Id_ASC("Id%20ASC"),
        Id_DESC("Id%20DESC"),
        Name_ASC("Name%20ASC"),
        Name_DESC("Name%20DESC"),
        CreatedDate_ASC("CreatedDate%20ASC"),
        CreatedDate_DESC("CreatedDate%20DESC"),
        ModifiedDate_ASC("ModifiedDate%20ASC"),
        ModifiedDate_DESC("ModifiedDate%20DESC"),
        CampaignCode_ASC("CampaignCode%20ASC"),
        CampaignCode_DESC("CampaignCode%20DESC");
        private final String value;

        ORDER_BY(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static ORDER_BY fromValue(String value) {
            for (ORDER_BY v : ORDER_BY.values()) {
                if (v.value.equals(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }

    public ETResponse<ETCampaign> get(ETClient client)
        throws ETSdkException;
    public ETResponse<ETCampaign> get(ETClient client, ETFilter filter)
        throws ETSdkException;
    public ETResponse<ETCampaign> post(ETClient client, ETCampaign campaign)
        throws ETSdkException;
    public ETResponse<ETCampaign> patch(ETClient client, ETCampaign campaign)
        throws ETSdkException;
    public ETResponse<ETCampaign> delete(ETClient client, ETCampaign campaign)
        throws ETSdkException;
}
