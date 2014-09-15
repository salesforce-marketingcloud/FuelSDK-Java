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
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ETSegment extends ETRestObjectImmutable {
    @SerializedName("audienceSegmentDefinitionID")
    @Expose
    private String id = null;
    @SerializedName("audienceDefinitionID")
    @Expose
    private String audienceId = null;
    @Expose
    private String name = null;
    @Expose
    private Integer capNum = null;
    @Expose
    private Integer capPercent = null;
    @Expose
    private Integer count = null;
    @Expose
    private Date countDate = null;
    @Expose
    private String criteria = null;
    @Expose
    private Integer criteriaCount = null;
    @Expose
    private Date criteriaCountDate = null;
    @Expose
    private Boolean exclude = null;
    @SerializedName("filterDefinitionID")
    @Expose
    private String filterDefinitionId = null;
    @Expose
    private Boolean isActive = null;
    @Expose
    private Boolean isCriteriaCountStale = null;
    @Expose
    private Boolean isIncludedInPublish = null;
    @Expose
    private Integer priority = null;
    @Expose
    private String segmentCode = null;
    @SerializedName("segmentDefinitionID")
    @Expose
    private String segmentDefinitionId = null;
    @Expose
    private String status = null;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapNum() {
        return capNum;
    }

    public void setCapNum(Integer capNum) {
        this.capNum = capNum;
    }

    public Integer getCapPercent() {
        return capPercent;
    }

    public void setCapPercent(Integer capPercent) {
        this.capPercent = capPercent;
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

    public Integer getCriteriaCount() {
        return criteriaCount;
    }

    public void setCriteriaCount(Integer criteriaCount) {
        this.criteriaCount = criteriaCount;
    }

    public Date getCriteriaCountDate() {
        return criteriaCountDate;
    }

    public void setCriteriaCountDate(Date criteriaCountDate) {
        this.criteriaCountDate = criteriaCountDate;
    }

    public Boolean getExclude() {
        return exclude;
    }

    public void setExclude(Boolean exclude) {
        this.exclude = exclude;
    }

    public String getFilterDefinitionId() {
        return filterDefinitionId;
    }

    public void setFilterDefinitionId(String filterDefinitionId) {
        this.filterDefinitionId = filterDefinitionId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsCriteriaCountStale() {
        return isCriteriaCountStale;
    }

    public void setIsCriteriaCountStale(Boolean isCriteriaCountStale) {
        this.isCriteriaCountStale = isCriteriaCountStale;
    }

    public Boolean getIsIncludedInPublish() {
        return isIncludedInPublish;
    }

    public void setIsIncludedInPublish(Boolean isIncludedInPublish) {
        this.isIncludedInPublish = isIncludedInPublish;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentDefinitionId() {
        return segmentDefinitionId;
    }

    public void setSegmentDefinitionId(String segmentDefinitionId) {
        this.segmentDefinitionId = segmentDefinitionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
