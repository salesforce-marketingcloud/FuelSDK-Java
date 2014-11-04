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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETRestConnection;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Audience/{id}",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETAudience extends ETRestObject {
    @Expose @SerializedName("audienceDefinitionID")
    @ExternalName("id")
    private String id = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("description")
    private String description = null;
    @Expose
    @ExternalName("audienceCode")
    private String audienceCode = null;
    @Expose
    @ExternalName("publishCount")
    private Integer publishCount = null;
    @Expose
    @ExternalName("publishCountDate")
    private Date publishCountDate = null;
    @Expose
    private Filter filter = null;
    @ExternalName("filter")
    private ETFilter parsedFilter = null;           // internal
    @Expose
    @ExternalName("segments")
    private List<ETSegment> segments = new ArrayList<ETSegment>();

    private AudienceBuild audienceBuild = null;     // internal
    @Expose
    private List<AudienceBuild> audienceBuilds = new ArrayList<AudienceBuild>();

    private PublishResponse publishResponse = null; // internal

    public ETAudience() {
        //
        // By default we assume just one AudienceBuild:
        //

        audienceBuild = new AudienceBuild();
        audienceBuilds.add(audienceBuild);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudienceCode() {
        return audienceCode;
    }

    public void setAudienceCode(String audienceCode) {
        this.audienceCode = audienceCode;
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public Date getPublishCountDate() {
        return publishCountDate;
    }

    public String getStatus() {
        return publishResponse.getStatus();
    }

    public Integer getSubscribersCopied() {
        return publishResponse.getSubscribersCopied();
    }

    public ETFilter getFilter() {
        return parsedFilter;
    }

    public void setFilter(ETFilter filter) {
        parsedFilter = filter;
        this.filter = new Filter();
        this.filter.setFilterDefinition(toAudienceBuilderFilter(parsedFilter));
    }

    public void setFilter(String filter)
        throws ETSdkException
    {
        setFilter(ETFilter.parse(filter));
    }

    public List<ETSegment> getSegments() {
        return segments;
    }

    public void addSegment(ETSegment segment) {
        segments.add(segment);
    }

    public void addSegment(String filter)
        throws ETSdkException
    {
        ETSegment segment = new ETSegment();
        segment.setFilter(filter);
        segments.add(segment);
    }

    public String getPublishedDataExtensionName() {
        return audienceBuild.getDataExtensionName();
    }

    public void setPublishedDataExtensionName(String name) {
        audienceBuild.setDataExtensionName(name);
    }

    public Integer getPublishedDataExtensionFolderId() {
        return audienceBuild.getDataExtensionFolderId();
    }

    public void setPublishedDataExtensionFolderId(Integer folderId) {
        audienceBuild.setDataExtensionFolderId(folderId);
    }

    public static Integer retrieveAudienceCount(ETClient client, String filter)
        throws ETSdkException
    {
        ETAudience audience = new ETAudience();
        AudienceCountsRequest request = audience.new AudienceCountsRequest();
        ETFilter parsedFilter = ETFilter.parse(filter);
        request.addFilterDefinition(toAudienceBuilderFilter(parsedFilter));
        ETRestConnection connection = client.getRestConnection();
        Gson gson = new Gson();
        String json = connection.post("/internal/v1/AudienceBuilder/AudienceCounts",
                                      gson.toJson(request));
        AudienceCountsResponse response = gson.fromJson(json, AudienceCountsResponse.class);
        return response.getCount();
    }

    public void publish()
        throws ETSdkException
    {
        PublishRequest request = new PublishRequest();
        request.setAudienceDefinitionId(id);
        ETRestConnection connection = getClient().getRestConnection();
        Gson gson = new Gson();
        String json = connection.post("/internal/v1/AudienceBuilder/Publish",
                                      gson.toJson(request));
        publishResponse = gson.fromJson(json, PublishResponse.class);
    }

    public void updatePublishStatus()
        throws ETSdkException
    {
        ETRestConnection connection = getClient().getRestConnection();
        Gson gson = new Gson();
        String json = connection.get("/internal/v1/AudienceBuilder/Publish/" + id);
        publishResponse = gson.fromJson(json, PublishResponse.class);
    }

    public static AudienceBuilderFilter toAudienceBuilderFilter(ETFilter filter) {
        ETAudience audience = new ETAudience();
        AudienceBuilderFilter f = audience.new AudienceBuilderFilter();
        AudienceBuilderFilter.Condition condition = f.new Condition();
        condition.setId(filter.getProperty());
        // XXX others?
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case EQUALS:
            condition.setOperator("Equals");
            break;
          default:
            // XXX throw exception unsupported
        }
        condition.setConditionValue(filter.getValue());
        f.addCondition(condition);

        return f;
    }

    // XXX
    @Override
    protected String getFilterQueryParams(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String internalProperty = null;

        if (filter.getProperty() != null) {
            internalProperty = getInternalProperty(ETDimension.class,
                                                   filter.getProperty());

            // convert " " to "%20" in property
            internalProperty = internalProperty.replaceAll(" ", "%20");

            stringBuilder.append(internalProperty);

            stringBuilder.append("=");
        }

        // convert " " to "%20" in all values
        List<String> values = new ArrayList<String>();
        for (String value : filter.getValues()) {
            values.add(value.replaceAll(" ", "%20"));
        }

        ETFilter.Operator operator = filter.getOperator();
        switch(operator) {
          case EQUALS:
            stringBuilder.append(values.get(0));
            break;
          case NOT_EQUALS:
            stringBuilder.append("not(" + values.get(0) + ")");
            break;
          case LESS_THAN:
            stringBuilder.append("lt(" + values.get(0) + ")");
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append("lte(" + values.get(0) + ")");
            break;
          case GREATER_THAN:
            stringBuilder.append("gt(" + values.get(0) + ")");
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append("gte(" + values.get(0) + ")");
            break;
          case IN:
            stringBuilder.append("in(");
            boolean first = true;
            for (String value : values) {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(value);
            }
            stringBuilder.append(")");
            break;
          case AND:
            stringBuilder.append(getFilterQueryParams(filter.getFilters().get(0)));
            stringBuilder.append("&");
            stringBuilder.append(getFilterQueryParams(filter.getFilters().get(1)));
            break;
          case OR:
            throw new ETSdkException("or expressions are not supported on Audience Builder retrieves");
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }

    public class AudienceBuilderFilter {
        @Expose
        @SerializedName("UseEnterprise")
        private Boolean useEnterprise = false;
        @Expose
        @SerializedName("UseAlsoEngine")
        private Boolean useAlsoEngine = true;
        @Expose
        @SerializedName("Source")
        private String source = "AudienceBuilder";
        @Expose
        @SerializedName("ConditionSet")
        private ConditionSet conditionSet = new ConditionSet();

        public AudienceBuilderFilter() {
            conditionSet.setOperator("INC"); // XXX?
            conditionSet.setConditionSetName("");
        }

        public ConditionSet getConditionSet() {
            return conditionSet;
        }

        public void addCondition(Condition condition) {
            conditionSet.addCondition(condition);
        }

        public class Condition {
            @Expose
            @SerializedName("ID")
            private String id = null;
            @Expose
            @SerializedName("Operator")
            private String operator = null;
            @Expose
            @SerializedName("ConditionValue")
            private String conditionValue = null;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getConditionValue() {
                return conditionValue;
            }

            public void setConditionValue(String conditionValue) {
                this.conditionValue = conditionValue;
            }
        }

        public class ConditionSet {
            @Expose
            @SerializedName("Operator")
            private String operator = null;
            @Expose
            @SerializedName("ConditionSetName")
            private String conditionSetName = null;
            @Expose
            @SerializedName("Condition")
            private List<Condition> conditions = new ArrayList<Condition>();

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getConditionSetName() {
                return conditionSetName;
            }

            public void setConditionSetName(String conditionSetName) {
                this.conditionSetName = conditionSetName;
            }

            public List<Condition> getConditions() {
                return conditions;
            }

            public void addCondition(Condition condition) {
                conditions.add(condition);
            }
        }
    }

    //
    // These are just here so we can construct the JSON requests:
    //

    protected class AudienceBuild {
        @Expose
        private String name = "default";
        @Expose
        @SerializedName("publishedDataExtensionName")
        private String dataExtensionName = null;
        @Expose
        @SerializedName("publishedFolderCategoryID")
        private Integer dataExtensionFolderId = null;
        @Expose
        private Boolean available = true;
        @Expose
        private String publishChannel = "EMAIL";
        @Expose
        private String status = "";

        public String getDataExtensionName() {
            return dataExtensionName;
        }

        public void setDataExtensionName(String dataExtensionName) {
            this.dataExtensionName = dataExtensionName;
        }

        public Integer getDataExtensionFolderId() {
            return dataExtensionFolderId;
        }

        public void setDataExtensionFolderId(Integer dataExtensionFolderId) {
            this.dataExtensionFolderId = dataExtensionFolderId;
        }
    }

    protected class AudienceCountsRequest {
        @Expose
        @SerializedName("FilterDefinitions")
        private List<AudienceBuilderFilter> filterDefinitions = new ArrayList<AudienceBuilderFilter>();

        public void addFilterDefinition(AudienceBuilderFilter audienceBuilderFilter) {
            filterDefinitions.add(audienceBuilderFilter);
        }
    }

    protected class AudienceCountsResponse {
        @Expose
        private Integer count = null;

        public Integer getCount() {
            return count;
        }
    }

    protected class Filter {
        @Expose
        @SerializedName("filterDefinitionJSON")
        private AudienceBuilderFilter filterDefinition = null;

        public void setFilterDefinition(AudienceBuilderFilter filterDefinition) {
            this.filterDefinition = filterDefinition;
        }
    }

    protected class PublishRequest {
        @Expose
        @SerializedName("audienceBuilderPublishID")
        private String id = null;
        @Expose
        @SerializedName("audienceDefinitionID")
        private String audienceDefinitionId = null;

        public void setId(String id) {
            this.id = id;
        }

        public void setAudienceDefinitionId(String audienceDefinitionId) {
            this.audienceDefinitionId = audienceDefinitionId;
        }
    }

    protected class PublishResponse {
        @Expose
        @SerializedName("audienceBuilderPublishID")
        private String id = null;
        @Expose
        private String status = null;
        @Expose
        private Integer subscribersCopied = null;

        public String getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public Integer getSubscribersCopied() {
            return subscribersCopied;
        }
    }
}
