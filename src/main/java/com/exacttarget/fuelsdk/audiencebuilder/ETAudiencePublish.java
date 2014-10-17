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

package com.exacttarget.fuelsdk.audiencebuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ETAudiencePublish {
    @Expose
    private Integer audienceBuilderPublishId = null;
    @Expose
    private Integer asyncId = null;
    @Expose
    private Integer audienceDefinitionId = null;
    @Expose
    private String audienceName = null;
    @Expose
    private String buildAudienceActivityId = null;
    @Expose
    private Integer buildAudienceDefinitionId = null;
    @Expose
    private Integer criteriaCount = null;
    @Expose
    private String notifyEmail = null;
    @Expose
    private String publishChannel = null;
    @Expose
    @SerializedName("publishedDEName")
    private String publishedDeName = null;
    @Expose
    private Integer publishedFolderCategoryId = null;
    @Expose
    private Boolean splitOutputBySegment = null;
    @Expose
    private String status = null;
    @Expose
    private Integer subscribersCopied = null;
    @Expose
    private String trackingCode = null;

    public Integer getAudienceBuilderPublishId() {
        return audienceBuilderPublishId;
    }

    public void setAudienceBuilderPublishId(Integer audienceBuilderPublishId) {
        this.audienceBuilderPublishId = audienceBuilderPublishId;
    }

    public Integer getAsyncId() {
        return asyncId;
    }

    public void setAsyncId(Integer asyncId) {
        this.asyncId = asyncId;
    }

    public Integer getAudienceDefinitionId() {
        return audienceDefinitionId;
    }

    public void setAudienceDefinitionId(Integer audienceDefinitionId) {
        this.audienceDefinitionId = audienceDefinitionId;
    }

    public String getAudienceName() {
        return audienceName;
    }

    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
    }

    public String getBuildAudienceActivityId() {
        return buildAudienceActivityId;
    }

    public void setBuildAudienceActivityId(String buildAudienceActivityId) {
        this.buildAudienceActivityId = buildAudienceActivityId;
    }

    public Integer getBuildAudienceDefinitionId() {
        return buildAudienceDefinitionId;
    }

    public void setBuildAudienceDefinitionId(Integer buildAudienceDefinitionId) {
        this.buildAudienceDefinitionId = buildAudienceDefinitionId;
    }

    public Integer getCriteriaCount() {
        return criteriaCount;
    }

    public void setCriteriaCount(Integer criteriaCount) {
        this.criteriaCount = criteriaCount;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public String getPublishedDeName() {
        return publishedDeName;
    }

    public void setPublishedDeName(String publishedDeName) {
        this.publishedDeName = publishedDeName;
    }

    public Integer getPublishedFolderCategoryId() {
        return publishedFolderCategoryId;
    }

    public void setPublishedFolderCategoryId(Integer publishedFolderCategoryId) {
        this.publishedFolderCategoryId = publishedFolderCategoryId;
    }

    public Boolean getSplitOutputBySegment() {
        return splitOutputBySegment;
    }

    public void setSplitOutputBySegment(Boolean splitOutputBySegment) {
        this.splitOutputBySegment = splitOutputBySegment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubscribersCopied() {
        return subscribersCopied;
    }

    public void setSubscribersCopied(Integer subscribersCopied) {
        this.subscribersCopied = subscribersCopied;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
}