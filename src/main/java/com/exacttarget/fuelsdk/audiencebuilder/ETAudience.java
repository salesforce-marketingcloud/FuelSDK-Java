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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Audience/{id}",
            primaryKey = "id",
            collectionKey = "entities")
public class ETAudience extends ETRestObject {
    @Expose
    @SerializedName("audienceDefinitionID")
    private String id = null;
    @Expose
    private String name = null;
    @Expose
    private String description = null;
    @Expose
    private String audienceCode = null;
    @Expose
    @SerializedName("filterDefinitionID")
    private String filterDefinitionId = null;
    @Expose
    @SerializedName("ownerID")
    private String ownerId = null;
    @Expose
    private Integer publishCount = null;
    @Expose
    private Date publishCountDate = null;
    @Expose
    private String status = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAudienceCode() {
        return audienceCode;
    }

    public void setAudienceCode(String audienceCode) {
        this.audienceCode = audienceCode;
    }

    public String getFilterDefinitionId() {
        return filterDefinitionId;
    }

    public void setFilterDefinitionId(String filterDefinitionId) {
        this.filterDefinitionId = filterDefinitionId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public Date getPublishCountDate() {
        return publishCountDate;
    }

    public void setPublishCountDate(Date publishCountDate) {
        this.publishCountDate = publishCountDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
