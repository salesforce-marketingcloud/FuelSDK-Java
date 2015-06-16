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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtension;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestConnection;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapConnection;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.AudienceBuilderRestCall;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.Soap;

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
        segment.setName("Remainder");
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
        FilterDefinition filterDefinition = toFilterDefinition(parsedFilter.getExpression());
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

    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            throw new ETSdkException("unsupported operation: retrieve");
        }
        return ETRestObject.retrieve(client, type, page, pageSize, filter);
    }

    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        Gson gson = client.getGson();
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            ETResponse<T> response = new ETResponse<T>();
            for (T object : objects) {
                @SuppressWarnings("unchecked")
                ETResponse<T> r = (ETResponse<T>) ETRestObject.soapCall(client,
                                                                        object.getClass(),
                                                                        "POST",
                                                                        "AudienceBuilder/Audience",
                                                                        gson.toJson(object));
                assert r.getResults().size() == 1;
                ETResult<T> result = r.getResults().get(0);
                result.setRequestId(r.getRequestId());
                result.setResponseCode(r.getResponseCode());
                result.setResponseMessage(r.getResponseMessage());
                response.addResult(r.getResult());
            }
            return response;
        }
        return ETRestObject.create(client, objects);
    }

    public static <T extends ETRestObject> ETResponse<T> update(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            throw new ETSdkException("unsupported operation: update");
        }
        return ETRestObject.update(client, objects);
    }

    public static <T extends ETRestObject> ETResponse<T> delete(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            ETResponse<T> response = new ETResponse<T>();
            for (T object : objects) {
                APIProperty apiProperty = new APIProperty();
                apiProperty.setName("audienceDefinitionID");
                apiProperty.setValue(object.getId());
                @SuppressWarnings("unchecked")
                ETResponse<T> r = (ETResponse<T>) ETRestObject.soapCall(client,
                                                                        object.getClass(),
                                                                        "DELETE",
                                                                        "AudienceBuilder/Audience/{audienceDefinitionID}",
                                                                        Arrays.asList(apiProperty));
                response.addResult(r.getResult());
            }
            return response;
        }
        return ETRestObject.delete(client, objects);
    }

    public static Integer retrieveAudienceCount(ETClient client, String filter)
        throws ETSdkException
    {
        AudienceCountsRequest request = new AudienceCountsRequest();
        request.addFilterDefinition(toFilterDefinition(ETFilter.parse(filter).getExpression()));
        Gson gson = client.getGson();
        String requestPayload = gson.toJson(request);
        String responsePayload = null;
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            responsePayload = ETRestObject.soapCall(client,
                                                    "POST",
                                                    "AudienceBuilder/AudienceCounts",
                                                    requestPayload);
        } else {
            ETRestConnection connection = client.getRestConnection();
            ETRestConnection.Response r = connection.post("/internal/v1/AudienceBuilder/AudienceCounts",
                                                          requestPayload);
            responsePayload = r.getResponsePayload();
        }
        AudienceCountsResponse response = gson.fromJson(responsePayload, AudienceCountsResponse.class);
        return response.getCount();
    }

    public void publish()
        throws ETSdkException
    {
        PublishRequest request = new PublishRequest();
        request.setId(audienceBuilds.get(0).getId());
        Gson gson = getClient().getGson();
        String requestPayload = gson.toJson(request);
        String responsePayload = null;
        if (getClient().getConfiguration().equals("audienceBuilderApi", "soap")) {
            responsePayload = ETRestObject.soapCall(getClient(),
                                                    "POST",
                                                    "AudienceBuilder/Publish",
                                                    requestPayload);
        } else {
            ETRestConnection connection = getClient().getRestConnection();
            ETRestConnection.Response r = connection.post("/internal/v1/AudienceBuilder/Publish",
                                                          requestPayload);
            responsePayload = r.getResponsePayload();
        }
        publishResponse = gson.fromJson(responsePayload, PublishResponse.class);
    }

    public void updatePublishStatus()
        throws ETSdkException
    {
        Gson gson = getClient().getGson();
        String responsePayload = null;
        if (getClient().getConfiguration().equals("audienceBuilderApi", "soap")) {
            responsePayload = ETRestObject.soapCall(getClient(),
                                                    "GET",
                                                    "AudienceBuilder/Publish/{audienceBuilderPublishId}",
                                                    null,
                                                    "audienceBuilderPublishId=" + publishResponse.getId());
        } else {
            ETRestConnection connection = getClient().getRestConnection();
            ETRestConnection.Response r = connection.get("/internal/v1/AudienceBuilder/Publish/" + publishResponse.getId());
            responsePayload = r.getResponsePayload();
        }
        publishResponse = gson.fromJson(responsePayload, PublishResponse.class);
    }

    public static ETResponse<ETDataExtensionRow> export(ETClient client,
                                                        ETDataExtension dataExtension,
                                                        ETFilter filter,
                                                        String fileName)
        throws ETSdkException
    {
        return export(client, dataExtension, filter, fileName, true, false, null);
    }

    public static ETResponse<ETDataExtensionRow> export(ETClient client,
                                                        ETDataExtension dataExtension,
                                                        ETFilter filter,
                                                        String fileName,
                                                        Boolean includeHeader,
                                                        Boolean compress,
                                                        Integer encryptionKey)
        throws ETSdkException
    {
        Gson gson = client.getGson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customerKey", dataExtension.getKey());
        jsonObject.addProperty("fileName", fileName);
        if (includeHeader != null) {
            jsonObject.addProperty("includeHeader", includeHeader);
        }
        if (compress != null) {
            jsonObject.addProperty("compress", compress);
        }
        if (encryptionKey != null) {
            jsonObject.addProperty("encryptionKey", encryptionKey);
        }

        String json = gson.toJson(jsonObject);

        String filterString = ETRestObject.toFilterString(filter.getExpression());

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            AudienceBuilderRestCall restRequest = new AudienceBuilderRestCall();
            restRequest.setMethod("POST");
            restRequest.setURL("customobjectdata/export");
            String tokens[] = filterString.split("=");
            assert tokens.length == 2;
            tokens[1] = tokens[1].replaceAll("%20", " ");
            APIProperty apiProperty = new APIProperty();
            apiProperty.setName(tokens[0]);
            apiProperty.setValue(tokens[1]);
            restRequest.getParameters().add(apiProperty);
            restRequest.setPayload(json);
            ETSoapConnection connection = client.getSoapConnection();
            Soap soap = connection.getSoap();
            CreateRequest createRequest = new CreateRequest();
            createRequest.setOptions(new CreateOptions());
            createRequest.getObjects().add(restRequest);
            CreateResponse createResponse = soap.create(createRequest);
            response.setRequestId(createResponse.getRequestID());
            if (createResponse.getOverallStatus().equals("OK")) {
                response.setStatus(ETResult.Status.OK);
            } else if (createResponse.getOverallStatus().equals("Error")) {
                response.setStatus(ETResult.Status.ERROR);
            }
            response.setResponseCode(createResponse.getOverallStatus());
            response.setResponseMessage(createResponse.getOverallStatus());
        } else {
            String path = "/data/v1/customobjectdata/export";
            StringBuilder stringBuilder = new StringBuilder(path);
            if (filter != null) {
                stringBuilder.append("?" + filterString);
            }
            path = stringBuilder.toString();
            ETRestConnection connection = client.getRestConnection();
            ETRestConnection.Response r = connection.post(path, json);
            response.setRequestId(r.getRequestId());
            if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                response.setStatus(ETResult.Status.OK);
            } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                response.setStatus(ETResult.Status.ERROR);
            }
            response.setResponseCode(r.getResponseCode().toString());
            response.setResponseMessage(r.getResponseMessage());
        }

        return response;
    }

    public static ETResponse<ETDataExtensionRow> export(ETClient client,
                                                        ETDataExtension dataExtension,
                                                        String filter,
                                                        String fileName)
        throws ETSdkException
    {
        return export(client, dataExtension, ETFilter.parse(filter), fileName);
    }

    public static ETResponse<ETDataExtensionRow> export(ETClient client,
                                                        ETDataExtension dataExtension,
                                                        String filter,
                                                        String fileName,
                                                        Boolean includeHeader,
                                                        Boolean compress,
                                                        Integer encryptionKey)
        throws ETSdkException
    {
        return export(client, dataExtension, ETFilter.parse(filter), fileName,
                      includeHeader,
                      compress,
                      encryptionKey);
    }

    public static FilterDefinition toFilterDefinition(ETExpression expression)
        throws ETSdkException
    {
        FilterDefinition filterDefinition = new FilterDefinition();
        ETExpression.Operator operator = expression.getOperator();
        switch (operator) {
          case EQUALS:
          case NOT_EQUALS:
          case LESS_THAN:
          case LESS_THAN_OR_EQUALS:
          case GREATER_THAN:
          case GREATER_THAN_OR_EQUALS:
          case LIKE:
            filterDefinition.addCondition(toCondition(expression));
            break;
          case IN:
          case AND:
          case OR:
            filterDefinition.addConditionSet(toConditionSet(expression));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }
        return filterDefinition;
    }

    public static FilterDefinition.Condition toCondition(ETExpression expression)
        throws ETSdkException
    {
        FilterDefinition.Condition condition =
                new FilterDefinition.Condition();
        condition.setId(expression.getProperty());
        ETExpression.Operator operator = expression.getOperator();
        switch (operator) {
          case EQUALS:
            condition.setOperator("Equals");
            condition.setConditionValue(expression.getValue());
            break;
          case NOT_EQUALS:
            condition.setOperator("NotEquals");
            condition.setConditionValue(expression.getValue());
            break;
          case LESS_THAN:
            condition.setOperator("LessThan");
            condition.setConditionValue(expression.getValue());
            break;
          case LESS_THAN_OR_EQUALS:
            condition.setOperator("LessThanOrEquals");
            condition.setConditionValue(expression.getValue());
            break;
          case GREATER_THAN:
            condition.setOperator("GreaterThan");
            condition.setConditionValue(expression.getValue());
            break;
          case GREATER_THAN_OR_EQUALS:
            condition.setOperator("GreaterThanOrEquals");
            condition.setConditionValue(expression.getValue());
            break;
          case LIKE:
            //
            // AudienceBuilder does not support LIKE natively,
            // so we emulate it using "ExistsIn", "Begins" and
            // "Ends":
            //
            String v = expression.getValue();
            if (v.startsWith("%") && v.endsWith("%")) {
                condition.setOperator("ExistsIn");
                condition.setConditionValue(v.substring(1, v.length() - 1));
            } else if (v.endsWith("%") && !v.startsWith("%")) {
                condition.setOperator("Begins");
                condition.setConditionValue(v.substring(0, v.length() - 1));
            } else if (v.startsWith("%") && !v.endsWith("%")) {
                condition.setOperator("Ends");
                condition.setConditionValue(v.substring(1));
            }
            break;
          default:
            throw new ETSdkException("invalid operator: " + operator);
        }
        return condition;
    }

    public static FilterDefinition.ConditionSet toConditionSet(ETExpression expression)
        throws ETSdkException
    {
        FilterDefinition.ConditionSet conditionSet =
                new FilterDefinition.ConditionSet();
        ETExpression.Operator operator = expression.getOperator();
        switch (operator) {
          case IN:
            conditionSet.setOperator("InList");
            for (String value : expression.getValues()) {
                FilterDefinition.Condition condition =
                        new FilterDefinition.Condition();
                condition.setId(expression.getProperty());
                condition.setOperator("Equals");
                condition.setConditionValue(value);
                conditionSet.addCondition(condition);
            }
            break;
          case AND:
            conditionSet.setOperator("AND");
            List<ETExpression> subexpressions = expression.getSubexpressions();
            ETExpression subexpression1 = null;
            ETExpression subexpression2 = null;
            do {
                subexpression1 = subexpressions.get(0);
                subexpression2 = subexpressions.get(1);
                if (subexpression2.getOperator() == null) {
                    conditionSet.addConditionSet(toConditionSet(subexpression2.getSubexpressions().get(0)));
                } else if (subexpression2.getOperator() == ETExpression.Operator.IN ||
                           subexpression2.getOperator() == ETExpression.Operator.AND ||
                           subexpression2.getOperator() == ETExpression.Operator.OR)
                {
                    conditionSet.addConditionSet(toConditionSet(subexpression2));
                } else {
                    conditionSet.addCondition(toCondition(subexpression2));
                }
                subexpressions = subexpression1.getSubexpressions();
            } while (subexpression1.getOperator() == ETExpression.Operator.AND);
            if (subexpression1.getOperator() == null) {
                conditionSet.addConditionSet(toConditionSet(subexpression1.getSubexpressions().get(0)));
            } else if (subexpression1.getOperator() == ETExpression.Operator.IN ||
                       subexpression1.getOperator() == ETExpression.Operator.AND ||
                       subexpression1.getOperator() == ETExpression.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(subexpression1));
            } else {
                conditionSet.addCondition(toCondition(subexpression1));
            }
            if (subexpression2.getOperator() == null) {
                conditionSet.addConditionSet(toConditionSet(subexpression2.getSubexpressions().get(0)));
            } else if (subexpression2.getOperator() == ETExpression.Operator.IN ||
                       subexpression2.getOperator() == ETExpression.Operator.AND ||
                       subexpression2.getOperator() == ETExpression.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(subexpression2));
            } else {
                conditionSet.addCondition(toCondition(subexpression2));
            }
            break;
          case OR:
            conditionSet.setOperator("OR");
            subexpressions = expression.getSubexpressions();
            subexpression1 = null;
            subexpression2 = null;
            do {
                subexpression1 = subexpressions.get(0);
                subexpression2 = subexpressions.get(1);
                if (subexpression2.getOperator() == ETExpression.Operator.IN ||
                    subexpression2.getOperator() == ETExpression.Operator.AND ||
                    subexpression2.getOperator() == ETExpression.Operator.OR)
                {
                    conditionSet.addConditionSet(toConditionSet(subexpression2));
                } else {
                    conditionSet.addCondition(toCondition(subexpression2));
                }
                subexpressions = subexpression1.getSubexpressions();
            } while (subexpression1.getOperator() == ETExpression.Operator.OR);
            if (subexpression1.getOperator() == ETExpression.Operator.IN ||
                subexpression1.getOperator() == ETExpression.Operator.AND ||
                subexpression1.getOperator() == ETExpression.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(subexpression1));
            } else {
                conditionSet.addCondition(toCondition(subexpression1));
            }
            if (subexpression2.getOperator() == ETExpression.Operator.IN ||
                subexpression2.getOperator() == ETExpression.Operator.AND ||
                subexpression2.getOperator() == ETExpression.Operator.OR)
            {
                conditionSet.addConditionSet(toConditionSet(subexpression2));
            } else {
                conditionSet.addCondition(toCondition(subexpression2));
            }
            break;
          default:
            throw new ETSdkException("invalid operator: " + operator);
        }
        return conditionSet;
    }

    public static String toFilterString(ETExpression expression)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String internalProperty = null;

        if (expression.getOperator() == null) {
            return "";
        }

        if (expression.getProperty() != null) {
            internalProperty = getInternalProperty(ETDimension.class,
                                                   expression.getProperty());

            // convert " " to "%20" in property
            internalProperty = internalProperty.replaceAll(" ", "%20");

            stringBuilder.append(internalProperty);

            stringBuilder.append("=");
        }

        // convert " " to "%20" in all values
        List<String> values = new ArrayList<String>();
        for (String value : expression.getValues()) {
            values.add(value.replaceAll(" ", "%20"));
        }

        ETExpression.Operator operator = expression.getOperator();
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
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(0)));
            stringBuilder.append("&");
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(1)));
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
