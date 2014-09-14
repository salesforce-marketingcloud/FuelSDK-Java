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

import java.util.Date;

import com.exacttarget.fuelsdk.ETRestObjectImmutable;
import com.google.gson.annotations.SerializedName;

public class ETAudienceBuild extends ETRestObjectImmutable {
    @SerializedName("buildAudienceDefinitionID")
    private String id = null;
    @SerializedName("audienceDefinitionID")
    private String audienceId = null;
    @SerializedName("customerKey")
    private String key = null;
    private String name = null;
    private String description = null;
    @SerializedName("publishedFolderCategoryID")
    private Integer folderId = null;
    @SerializedName("audiencePublishTypeID")
    private String audiencePublishTypeId = null;
    private Boolean available = null;
    @SerializedName("publishedDataExtensionName")
    private String dataExtensionName = null;
    private String notifyEmail = null;
    private Integer publishBy = null;
    private String publishChannel = null;
    private Date publishDate = null;
    private Integer rowsComplete = null;
    private Boolean splitOutputBySegment = null;
    private String status = null;
    private String trackingCode = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getAudiencePublishTypeId() {
        return audiencePublishTypeId;
    }

    public void setAudiencePublishTypeId(String audiencePublishTypeId) {
        this.audiencePublishTypeId = audiencePublishTypeId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDataExtensionName() {
        return dataExtensionName;
    }

    public void setDataExtensionName(String dataExtensionName) {
        this.dataExtensionName = dataExtensionName;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public Integer getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(Integer publishBy) {
        this.publishBy = publishBy;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getRowsComplete() {
        return rowsComplete;
    }

    public void setRowsComplete(Integer rowsComplete) {
        this.rowsComplete = rowsComplete;
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

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
}
