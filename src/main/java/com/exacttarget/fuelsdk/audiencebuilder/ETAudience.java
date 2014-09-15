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
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.RestAnnotations;

@RestAnnotations(path = "/internal/v1/AudienceBuilder/Audience/{id}",
                 primaryKey = "id",
                 collectionKey = "entities")
public class ETAudience extends ETRestObject {
    @SerializedName("audienceDefinitionID")
    @Expose
    private String id = null;
    @Expose
    private String name = null;
    @Expose
    private String description = null;
    @Expose
    private String audienceCode = null;
    @Expose
    private Integer count = null;
    @Expose
    private Date countDate = null;
    @Expose
    private String criteria = null;
    @SerializedName("filterDefinitionID")
    @Expose
    private String filterDefinitionId = null;
    @Expose
    private Boolean isCountStale = null;
    @Expose
    private Boolean isCriteriaCountStale = null;
    @SerializedName("ownerID")
    @Expose
    private String ownerId = null;
    @Expose
    private Integer publishCount = null;
    @Expose
    private Date publishCountDate = null;
    @Expose
    private String status = null;
    @Expose
    private ETFilter filter = null;
    @Expose
    private List<ETAudienceBuild> audienceBuilds = null;
    @Expose
    private List<ETSegment> segments = null;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getFilterDefinitionId() {
        return filterDefinitionId;
    }

    public void setFilterDefinitionId(String filterDefinitionId) {
        this.filterDefinitionId = filterDefinitionId;
    }

    public Boolean getIsCountStale() {
        return isCountStale;
    }

    public void setIsCountStale(Boolean isCountStale) {
        this.isCountStale = isCountStale;
    }

    public Boolean getIsCriteriaCountStale() {
        return isCriteriaCountStale;
    }

    public void setIsCriteriaCountStale(Boolean isCriteriaCountStale) {
        this.isCriteriaCountStale = isCriteriaCountStale;
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

    public ETFilter getFilter() {
        return filter;
    }

    public void setFilter(ETFilter filter) {
        this.filter = filter;
    }

    public List<ETAudienceBuild> getAudienceBuilds() {
        return audienceBuilds;
    }

    public void setAudienceBuilds(List<ETAudienceBuild> audienceBuilds) {
        this.audienceBuilds = audienceBuilds;
    }

    public List<ETSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<ETSegment> segments) {
        this.segments = segments;
    }

    public static ETResponse<ETAudience> create(ETClient client,
                                                ETAudience audience)
        throws ETSdkException
    {
        return ETRestObject.create(client, audience);
    }

    public static ETResponse<ETAudience> retrieve(ETClient client)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, ETAudience.class);
    }

    public static ETResponse<ETAudience> retrieve(ETClient client,
                                                  Integer page,
                                                  Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, page, pageSize, ETAudience.class);
    }

    public static ETResponse<ETAudience> retrieve(ETClient client,
                                                  String filter)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, ETAudience.class);
    }

    public static ETResponse<ETAudience> retrieve(ETClient client,
                                                  String filter,
                                                  Integer page,
                                                  Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, page, pageSize, ETAudience.class);
    }

    public ETResponse<ETAudience> update(ETClient client)
        throws ETSdkException
    {
        return super.update(client);
    }

    public ETResponse<ETAudience> delete(ETClient client)
        throws ETSdkException
    {
        return super.delete(client);
    }

    public static ETResponse<ETAudience> delete(ETClient client,
                                                String filter)
        throws ETSdkException
    {
        return ETRestObject.delete(client, filter, ETAudience.class);
    }
}
