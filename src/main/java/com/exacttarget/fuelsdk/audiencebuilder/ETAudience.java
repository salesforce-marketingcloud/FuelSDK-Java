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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

@RestObject(path = "/internal/v1/AudienceBuilder/Audience",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETAudience extends ETRestObject {
    @Expose @SerializedName("audienceDefinitionID")
    @ExternalName("id")
    private String id = null;
    @Expose
    @ExternalName("key")
    private String key = null;
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

    private String persistenceId = UUID.randomUUID().toString();

    private int segmentPriority = 1;

    public ETAudience() {
        //
        // By default we assume just one AudienceBuild:
        //

        audienceBuild = new AudienceBuild();
        audienceBuilds.add(audienceBuild);
        ETSegment segment = new ETSegment();
        segment.setPersistenceId(persistenceId);
        segment.setPriority(Integer.MAX_VALUE);
        addSegment(segment);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;

        this.name = name;

        //
        // Default audience code to audience name:
        //

        if (audienceCode == null || audienceCode.equals(oldName)) {
            audienceCode = name;
        }

        audienceBuilds.get(0).setTrackingCode(name + "_EMAIL");
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

    public void setFilter(ETFilter filter)
        throws ETSdkException
    {
        parsedFilter = filter;
        FilterDefinition filterDefinition = toFilterDefinition(parsedFilter);
        filterDefinition.setPersistenceId(persistenceId);
        this.filter = new Filter();
        this.filter.setFilterDefinition(filterDefinition);
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
        segment.setPersistenceId(persistenceId);
        if (segment.getPriority() == null) {
            segment.setPriority(segmentPriority++);
        }
        segments.add(segment);
        Collections.sort(segments);
    }

    public void addSegment(String filter)
        throws ETSdkException
    {
        ETSegment segment = new ETSegment();
        segment.setFilter(filter);
        addSegment(segment);
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

    public Integer[] getAppendedAttributeSetIds() {
        return audienceBuild.getAppendedAttributeSetIds();

    }

    public void setAppendedAttributeSetIds(Integer[] appendedAttributeSetIds) {
        audienceBuild.setAppendedAttributeSetIds(appendedAttributeSetIds);
    }

    public static Integer retrieveAudienceCount(ETClient client, String filter)
        throws ETSdkException
    {
        AudienceCountsRequest request = new AudienceCountsRequest();
        request.addFilterDefinition(toFilterDefinition(ETFilter.parse(filter)));
        ETRestConnection connection = client.getRestConnection();
        Gson gson = new Gson();
        String requestPayload = gson.toJson(request);
        ETRestConnection.Response r = connection.post("/internal/v1/AudienceBuilder/AudienceCounts",
                                                      requestPayload);
        String responsePayload = r.getResponsePayload();
        AudienceCountsResponse response = gson.fromJson(responsePayload, AudienceCountsResponse.class);
        return response.getCount();
    }

    public void publish()
        throws ETSdkException
    {
        PublishRequest request = new PublishRequest();
        request.setId(audienceBuilds.get(0).getId());
        ETRestConnection connection = getClient().getRestConnection();
        Gson gson = new Gson();
        String requestPayload = gson.toJson(request);
        ETRestConnection.Response r = connection.post("/internal/v1/AudienceBuilder/Publish",
                                                      requestPayload);
        String responsePayload = r.getResponsePayload();
        publishResponse = gson.fromJson(responsePayload, PublishResponse.class);
    }

    public void updatePublishStatus()
        throws ETSdkException
    {
        ETRestConnection connection = getClient().getRestConnection();
        Gson gson = new Gson();
        ETRestConnection.Response r = connection.get("/internal/v1/AudienceBuilder/Publish/" + publishResponse.getId());
        String responsePayload = r.getResponsePayload();
        publishResponse = gson.fromJson(responsePayload, PublishResponse.class);
    }

    public static FilterDefinition toFilterDefinition(ETFilter filter)
        throws ETSdkException
    {
        FilterDefinition filterDefinition = new FilterDefinition();
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case EQUALS:
          case NOT_EQUALS:
          case LESS_THAN:
          case LESS_THAN_OR_EQUALS:
          case GREATER_THAN:
          case GREATER_THAN_OR_EQUALS:
            filterDefinition.addCondition(toCondition(filter));
            break;
          case IN:
          case AND:
          case OR:
            filterDefinition.addConditionSet(toConditionSet(filter));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }
        return filterDefinition;
    }

    public static FilterDefinition.Condition toCondition(ETFilter filter)
        throws ETSdkException
    {
        FilterDefinition.Condition condition =
                new FilterDefinition.Condition();
        condition.setId(filter.getProperty());
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case EQUALS:
            condition.setOperator("Equals");
            break;
          case NOT_EQUALS:
            condition.setOperator("NotEquals");
            break;
          case LESS_THAN:
            condition.setOperator("LessThan");
            break;
          case LESS_THAN_OR_EQUALS:
            condition.setOperator("LessThanOrEquals");
            break;
          case GREATER_THAN:
            condition.setOperator("GreaterThan");
            break;
          case GREATER_THAN_OR_EQUALS:
            condition.setOperator("GreaterThanOrEquals");
            break;
          default:
            throw new ETSdkException("invalid operator: " + operator);
        }
        condition.setConditionValue(filter.getValue());
        return condition;
    }

    public static FilterDefinition.ConditionSet toConditionSet(ETFilter filter)
        throws ETSdkException
    {
        FilterDefinition.ConditionSet conditionSet =
                new FilterDefinition.ConditionSet();
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case IN:
            conditionSet.setOperator("InList");
            for (String value : filter.getValues()) {
                FilterDefinition.Condition condition =
                        new FilterDefinition.Condition();
                condition.setId(filter.getProperty());
                condition.setOperator("Equals");
                condition.setConditionValue(value);
                conditionSet.addCondition(condition);
            }
            break;
          case AND:
          case OR:
            if (operator == ETFilter.Operator.AND) {
                conditionSet.setOperator("AND");
            } else if (operator == ETFilter.Operator.OR) {
                conditionSet.setOperator("OR");
            }
            ETFilter filter1 = filter.getFilters().get(0);
            ETFilter filter2 = filter.getFilters().get(1);
            if (filter1.getOperator() == null) {
                conditionSet.addConditionSet(toConditionSet(filter1.getFilters().get(0)));
            } else if (filter1.getOperator() == ETFilter.Operator.IN ||
                       filter1.getOperator() == ETFilter.Operator.AND ||
                       filter1.getOperator() == ETFilter.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(filter1));
            } else {
                conditionSet.addCondition(toCondition(filter1));
            }
            if (filter2.getOperator() == null) {
                conditionSet.addConditionSet(toConditionSet(filter2.getFilters().get(0)));
            } else if (filter2.getOperator() == ETFilter.Operator.IN ||
                       filter2.getOperator() == ETFilter.Operator.AND ||
                       filter2.getOperator() == ETFilter.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(filter2));
            } else {
                conditionSet.addCondition(toCondition(filter2));
            }
            break;
          default:
            throw new ETSdkException("invalid operator: " + operator);
        }
        return conditionSet;
    }

    public static String toQueryParams(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String internalProperty = null;

        if (filter == null) {
            return "";
        }

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
            stringBuilder.append(toQueryParams(filter.getFilters().get(0)));
            stringBuilder.append("&");
            stringBuilder.append(toQueryParams(filter.getFilters().get(1)));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }

    //
    // These are just here so we can construct the JSON requests:
    //

    protected static class AudienceBuild {
        @Expose
        @SerializedName("buildAudienceDefinitionID")
        private String id = null;
        @Expose
        private String name = "default";
        @Expose
        @SerializedName("publishedDataExtensionName")
        private String dataExtensionName = null;
        @Expose
        @SerializedName("publishedFolderCategoryID")
        private Integer dataExtensionFolderId = null;
        @Expose
        @SerializedName("appendedAttributeSetIDs")
        private Integer[] appendedAttributeSetIds = null;
        @Expose
        private Integer audiencePublishTypeID = 1;
        @Expose
        private Boolean available = true;
        @Expose
        private String publishChannel = "EMAIL";
        @Expose
        private String status = "";
        @Expose
        private String trackingCode = null;

        public String getId() {
            return id;
        }

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

        public Integer[] getAppendedAttributeSetIds() {
            return appendedAttributeSetIds;
        }

        public void setAppendedAttributeSetIds(Integer[] appendedAttributeSetIds) {
            this.appendedAttributeSetIds = appendedAttributeSetIds;
        }

        public String getTrackingCode() {
            return trackingCode;
        }

        public void setTrackingCode(String trackingCode) {
            this.trackingCode = trackingCode;
        }
    }

    protected static class AudienceCountsRequest {
        @Expose
        @SerializedName("FilterDefinitions")
        private List<FilterDefinition> filterDefinitions = new ArrayList<FilterDefinition>();

        public void addFilterDefinition(FilterDefinition filterDefinition) {
            filterDefinitions.add(filterDefinition);
        }
    }

    protected static class AudienceCountsResponse {
        @Expose
        private Integer count = null;

        public Integer getCount() {
            return count;
        }
    }

    protected static class Filter {
        @Expose
        @SerializedName("filterDefinitionJSON")
        private FilterDefinition filterDefinition = null;

        public void setFilterDefinition(FilterDefinition filterDefinition) {
            this.filterDefinition = filterDefinition;
        }
    }

    public static class FilterDefinition {
        @Expose
        @SerializedName("PersistenceID")
        private String persistenceId = null;
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

        public FilterDefinition() {
            conditionSet.setOperator("OR");
            conditionSet.setConditionSetName("");
        }

        public String getPersistenceId() {
            return persistenceId;
        }

        public void setPersistenceId(String persistenceId) {
            this.persistenceId = persistenceId;
        }

        public ConditionSet getConditionSet() {
            return conditionSet;
        }

        public void addConditionSet(ConditionSet conditionSet) {
            this.conditionSet.addConditionSet(conditionSet);
        }

        public void addCondition(Condition condition) {
            this.conditionSet.addCondition(condition);
        }

        public static class Condition {
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

        public static class ConditionSet {
            @Expose
            @SerializedName("Operator")
            private String operator = null;
            @Expose
            @SerializedName("ConditionSetName")
            private String conditionSetName = null;
            @Expose
            @SerializedName("ConditionSet")
            private List<ConditionSet> conditionSets = null;
            @Expose
            @SerializedName("Condition")
            private List<Condition> conditions = null;

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

            public List<ConditionSet> getConditionSets() {
                return conditionSets;
            }

            public void addConditionSet(ConditionSet conditionSet) {
                if (conditionSets == null) {
                    conditionSets = new ArrayList<ConditionSet>();
                }
                conditionSets.add(conditionSet);
            }

            public List<Condition> getConditions() {
                return conditions;
            }

            public void addCondition(Condition condition) {
                if (conditions == null) {
                    conditions = new ArrayList<Condition>();
                }
                conditions.add(condition);
            }
        }
    }

    protected static class PublishRequest {
        @Expose
        @SerializedName("buildAudienceDefinitionID")
        private String id = null;

        public void setId(String id) {
            this.id = id;
        }
    }

    protected static class PublishResponse {
        @Expose
        @SerializedName("audienceBuilderPublishId")
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
