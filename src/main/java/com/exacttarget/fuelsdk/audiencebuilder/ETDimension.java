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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Dimension",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETDimension extends ETRestObject {
    @Expose @SerializedName("dimensionID")
    @ExternalName("id")
    private String id = null;
    @Expose
    @ExternalName("key")
    private String key = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("type")
    private Integer type = null;
    @Expose @SerializedName("customObjectFieldID")
    @ExternalName("fieldId")
    private String fieldId = null;
    @Expose @SerializedName("customObjectFieldName")
    @ExternalName("fieldName")
    private String fieldName = null;
    @Expose @SerializedName("dataType")
    @ExternalName("fieldType")
    private Integer fieldType = null;
    @Expose @SerializedName("recordCount")
    @ExternalName("count")
    private Integer count = null;
    @Expose
    @ExternalName("values")
    private List<ETDimensionValue> values = null;

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

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public Integer getCount() {
        return count;
    }

    public List<ETDimensionValue> getValues() {
        return values;
    }

    @SuppressWarnings("unchecked")
    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        ETResponse<T> response = null;

        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            ETExpression expression = filter.getExpression();
            String path = "AudienceBuilder/Dimension";
            if (expression.getOperator() == ETExpression.Operator.EQUALS &&
                expression.getProperty().equals("id"))
            {
                path = "AudienceBuilder/Dimension/{dimensionID}";
            }
            response = (ETResponse<T>) ETRestObject.soapCall(client,
                                                             ETDimension.class,
                                                             "GET",
                                                             path,
                                                             page,
                                                             pageSize,
                                                             filter);
        } else {
            response = ETRestObject.retrieve(client,
                                             type,
                                             page,
                                             pageSize,
                                             filter);
        }

        //
        // Retrieve all dimension values to preserve backward compatibility:
        //

        ((ETDimension) response.getObject()).hydrate();

        return response;
    }

    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        throw new ETSdkException("unsupported operation: create");
    }

    public static <T extends ETRestObject> ETResponse<T> update(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        throw new ETSdkException("unsupported operation: update");
    }

    public static <T extends ETRestObject> ETResponse<T> delete(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        throw new ETSdkException("unsupported operation: delete");
    }

    public void hydrate()
        throws ETSdkException
    {
        values = new ArrayList<ETDimensionValue>();

        ETClient client = getClient();

        ETResponse<ETDimensionValue> response = null;
        int page = 0;
        do {
            page++;
            response = client.retrieve(ETDimensionValue.class,
                                       page,
                                       2500,
                                       "id=" + id);
            if (response.getStatus() == ETResult.Status.ERROR) {
                throw new ETSdkException("error retrieving dimension values: "
                        + response.getResponseMessage());
            }
            for (ETDimensionValue value : response.getObjects()) {
                values.add(value);
            }
        } while (response.hasMoreResults());
    }

    public static String toFilterString(ETExpression expression)
        throws ETSdkException
    {
        return ETAudience.toFilterString(expression);
    }

    public static ETResponse<ETDimension> search(ETClient client,
                                                 String... filters)
        throws ETSdkException
    {
        ETResponse<ETDimension> response = null;

        SearchRequest searchRequest = new SearchRequest();

        for (String filter : filters) {
            searchRequest.add(filter);
        }

        Gson gson = client.getGson();

        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            ETResponse<SearchResponse> r =
                    ETRestObject.soapCall(client,
                                          SearchResponse.class,
                                          "POST",
                                          "AudienceBuilder/Dimension/Search",
                                          gson.toJson(searchRequest));

            //
            // We have to copy here because unfortunately this
            // route returns results differently than straight
            // retrieve :-(
            //

            response = new ETResponse<ETDimension>();
            response.setStatus(r.getStatus());
            response.setRequestId(r.getRequestId());
            response.setResponseCode(r.getResponseCode());
            response.setResponseMessage(r.getResponseMessage());
            response.setMoreResults(r.hasMoreResults());
            response.setPage(r.getPage());
            response.setPageSize(r.getPageSize());
            response.setTotalCount(r.getTotalCount());
            for (ETDimension dimension : r.getResult().getObject().getResponses()) {
                ETResult<ETDimension> result = new ETResult<ETDimension>();
                result.setObject(dimension);
                response.addResult(result);
            }
        } else {
            throw new ETSdkException("unsupported operation: search"); // XXX
        }

        return response;
    }

    protected static class SearchRequest {
        @Expose
        @SerializedName("Search")
        private List<Request> requests = new ArrayList<Request>();

        protected static class Request {
            @Expose
            @SerializedName("customObjectName")
            private String name = null;
            @Expose
            @SerializedName("customObjectFieldName")
            private String fieldName = "name";
            @Expose
            @SerializedName("dimensionValues")
            private List<String> values = new ArrayList<String>();

            public void setName(String name) {
                this.name = name;
            }

            public void addValue(String value) {
                this.values.add(value);
            }
        }

        public void add(String filter)
            throws ETSdkException
        {
            ETExpression expression = ETFilter.parse(filter).getExpression();
            if (expression.getOperator() != ETExpression.Operator.EQUALS &&
                expression.getOperator() != ETExpression.Operator.IN)
            {
                throw new ETSdkException("unsupported operator: " + expression.getOperator());
            }
            Request request = new Request();
            request.setName(expression.getProperty());
            for (String value : expression.getValues()) {
                request.addValue(value);
            }
            requests.add(request);
        }
    }

    protected static class SearchResponse extends ETRestObject {
        @Expose
        @SerializedName("results")
        private List<ETDimension> responses = null;

        @Override
        public String getId() {
            return null;
        }

        @Override
        public void setId(String id) {
        }

        public List<ETDimension> getResponses() {
            return responses;
        }
    }

    /**
     * @deprecated
     * Use <code>getFieldId</code>.
     */
    @Deprecated
    public String getCustomObjectFieldId() {
        return getFieldId();
    }

    /**
     * @deprecated
     * Use <code>getFieldName</code>.
     */
    @Deprecated
    public String getCustomObjectFieldName() {
        return getFieldName();
    }

    /**
     * @deprecated
     * Use <code>getFieldType</code>.
     */
    @Deprecated
    public Integer getCustomObjectFieldType() {
        return getFieldType();
    }
}
