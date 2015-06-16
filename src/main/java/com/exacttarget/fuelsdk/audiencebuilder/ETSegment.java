//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk.audiencebuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.audiencebuilder.ETAudience.FilterDefinition;

public class ETSegment extends ETRestObject implements Comparable<ETSegment> {
    @Expose @SerializedName("audienceSegmentDefinitionID")
    @ExternalName("id")
    private String id = null;
    @Expose @SerializedName("audienceDefinitionID")
    @ExternalName("audienceId")
    private String audienceId = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("segmentCode")
    private String segmentCode = null;
    @Expose
    @ExternalName("priority")
    private Integer priority = null;
    @Expose @SerializedName("capNum")
    @ExternalName("cap")
    private Integer cap = null;
    @Expose
    @ExternalName("capPercent")
    private Integer capPercent = null;
    @Expose @SerializedName ("isIncludedInPublish")
    @ExternalName("includeInPublish")
    private Boolean includeInPublish = true;
    @Expose
    private Filter filter = new Filter();
    @ExternalName("filter")
    private ETFilter parsedFilter = null; // internal

    private String persistenceId = null;

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getCapPercent() {
        return capPercent;
    }

    public void setCapPercent(Integer capPercent) {
        this.capPercent = capPercent;
    }

    public Boolean getIncludeInPublish() {
        return includeInPublish;
    }

    public void setIncludeInPublish(Boolean includeInPublish) {
        this.includeInPublish = includeInPublish;
    }

    public String getPersistenceId() {
        return persistenceId;
    }

    public void setPersistenceId(String persistenceId) {
        this.persistenceId = persistenceId;
        filter.getFilterDefinition().setPersistenceId(persistenceId);
    }

    public ETFilter getFilter() {
        return parsedFilter;
    }

    public void setFilter(ETFilter filter)
        throws ETSdkException
    {
        parsedFilter = filter;
        FilterDefinition filterDefinition = ETAudience.toFilterDefinition(filter.getExpression());
        filterDefinition.setPersistenceId(persistenceId);
        this.filter = new Filter();
        this.filter.setFilterDefinition(filterDefinition);
    }

    public void setFilter(String filter)
        throws ETSdkException
    {
        setFilter(ETFilter.parse(filter));
    }

    public int compareTo(ETSegment segment) {
        return new Integer(priority) - new Integer(segment.getPriority());
    }

    //
    // These are just here so we can construct the JSON requests:
    //

    protected static class Filter {
        @Expose
        @SerializedName("filterDefinitionJSON")
        private FilterDefinition filterDefinition = new FilterDefinition();

        public FilterDefinition getFilterDefinition() {
            return filterDefinition;
        }

        public void setFilterDefinition(FilterDefinition filterDefinition) {
            this.filterDefinition = filterDefinition;
        }
    }
}
