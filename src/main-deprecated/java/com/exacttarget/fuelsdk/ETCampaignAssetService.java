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
public interface ETCampaignAssetService extends ETCrudService {
    public static enum TYPE {
        AUTOMATION_DEFINITION("automation_definition"),
        CALENDAR_EVENT("calendar_event"),
        CT_FACEBOOK_POST("ct_facebook_post"),
        CT_TWITTER_POST("ct_twitter_post"),
        DATA_EXTENSION("data_extension"),
        EMAIL("email"),
        FACEBOOK_TAB("facebook_tab"),
        GROUP("group"),
        LANDING_PAGE("landing_page"),
        LIST("list"),
        PUSH_MESSAGE("push_message"),
        SENDABLE_CUSTOM_OBJECT("sendable_custom_object"),
        SMS_MESSAGE("sms_message"),
        TRIGGERED("triggered");
        private final String value;

        TYPE(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static TYPE fromValue(String value) {
            for (TYPE v : TYPE.values()) {
                if (v.value.equals(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }

    public ETResponse<ETCampaignAsset> get(ETClient client)
        throws ETSdkException;
    public ETResponse<ETCampaignAsset> get(ETClient client, ETFilter filter)
        throws ETSdkException;
    public ETResponse<ETCampaignAsset> post(ETClient client, ETCampaignAsset asset)
        throws ETSdkException;
    public ETResponse<ETCampaignAsset> patch(ETClient client, ETCampaignAsset asset)
        throws ETSdkException;
    public ETResponse<ETCampaignAsset> delete(ETClient client, ETCampaignAsset asset)
        throws ETSdkException;
}
