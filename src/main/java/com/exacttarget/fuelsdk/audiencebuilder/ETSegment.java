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

import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.audiencebuilder.ETAudience.FilterDefinition;

public class ETSegment implements Comparable<ETSegment> {
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
    @Expose
    private String isIncludedInPublish = "true";
    @Expose
    private Filter filter = new Filter();
    @ExternalName("filter")
    private ETFilter parsedFilter = null; // internal

    private String persistenceId = null;

    public String getId() {
        return id;
    }

    public String getAudienceId() {
        return audienceId;
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
        FilterDefinition filterDefinition = ETAudience.toFilterDefinition(filter);
        filterDefinition.setPersistenceId(persistenceId);
        this.filter = new Filter();
        this.filter.setFilterDefinition(filterDefinition);
    }

    public void setFilter(String filter)
        throws ETSdkException
    {
        setFilter(ETFilter.parse(filter));
    }

    //
    // These are just here so we can construct the JSON requests:
    //

    public int compareTo(ETSegment segment) {
        return new Integer(priority) - new Integer(segment.getPriority());
    }

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
