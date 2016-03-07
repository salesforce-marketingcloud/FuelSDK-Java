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

package com.exacttarget.fuelsdk;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETRestConnection.Method;

import static com.exacttarget.fuelsdk.ETRestConnection.Method.*;

import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.AudienceBuilderRestCall;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateResult;
import com.exacttarget.fuelsdk.internal.Soap;

/**
 * An <code>ETRestObject</code> represents an object
 * available via the REST API.
 */

public abstract class ETRestObject extends ETApiObject {
    private static Logger logger = Logger.getLogger(ETRestObject.class);

    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotations = type.getAnnotation(RestObject.class);

        assert annotations != null;

        String path = annotations.path();
        String primaryKey = annotations.primaryKey();
        String collection = annotations.collection();
        String totalCount = annotations.totalCount();

        logger.trace("path: " + path);
        logger.trace("primaryKey: " + primaryKey);
        logger.trace("collection: " + collection);
        logger.trace("totalCount: " + totalCount);

        //
        // Build the query parameters:
        //

        StringBuilder stringBuilder = new StringBuilder(path);

        boolean firstQueryParameter = true;

        if (page != null && pageSize != null) {
            firstQueryParameter = false;
            stringBuilder.append("?");
            stringBuilder.append("$page=");
            stringBuilder.append(page);
            stringBuilder.append("&");
            stringBuilder.append("$pagesize=");
            stringBuilder.append(pageSize);
        }

        logger.trace("filter: " + filter);

        ETExpression expression = filter.getExpression();
        if (expression.getOperator() != null) {
            logger.trace("expression: " + filter.getExpression());

            if (expression.getOperator() == ETExpression.Operator.EQUALS
                    && expression.getProperty().equals(primaryKey)) {
                //
                // Append the primary key to the the path:
                //

                String s = "/" + expression.getValue();
                stringBuilder.append(s);
                if (logger.isTraceEnabled()) {
                    logger.trace("appended primary key: " + s);
                }
            } else {
                //
                // Add the filter to the query parameters:
                //

                if (firstQueryParameter) {
                    firstQueryParameter = false;
                    stringBuilder.append("?");
                } else {
                    stringBuilder.append("&");
                }

                java.lang.reflect.Method method = null;
                try {
                    method = type.getMethod("toFilterString", ETExpression.class);
                } catch (NoSuchMethodException ex) {
                    // there's no toFilterString method on TYPE
                } catch (SecurityException ex) {
                    throw new ETSdkException(ex);
                }

                if (method != null) {
                    try {
                        String s = (String) method.invoke(null, expression);
                        stringBuilder.append(s);
                        if (logger.isTraceEnabled()) {
                            logger.trace("appended $filter: " + s);
                        }
                    } catch (Exception ex) {
                        throw new ETSdkException(ex);
                    }
                }
            }
        }

        if (filter.getOrderBy().size() != 0) {
            String s = null;
            boolean firstOrderBy = true;
            for (String orderBy : filter.getOrderBy()) {
                if (firstOrderBy) {
                    firstOrderBy = false;
                    if (firstQueryParameter) {
                        firstQueryParameter = false;
                        stringBuilder.append("?");
                    } else {
                        stringBuilder.append("&");
                    }
                    s = "$orderby=";
                } else {
                    s += ",";
                }
                s += orderBy;
                if (filter.getOrderByAsc()) {
                    s += "%20asc";
                } else {
                    s += "%20desc";
                }
            }
            stringBuilder.append(s);
            if (logger.isTraceEnabled()) {
                logger.trace("appended $orderby: " + s);
            }
        }

        if (filter.getProperties().size() != 0) {
            String s = null;
            boolean firstProperty = true;
            for (String property : filter.getProperties()) {
                if (firstProperty) {
                    firstProperty = false;
                    if (firstQueryParameter) {
                        firstQueryParameter = false;
                        stringBuilder.append("?");
                    } else {
                        stringBuilder.append("&");
                    }
                    s = "$fields=";
                } else {
                    s += ",";
                }
                s += property;
            }
            stringBuilder.append(s);
            if (logger.isTraceEnabled()) {
                logger.trace("appended $fields: " + s);
            }
        }

        path = stringBuilder.toString();

        logger.trace("GET " + path);

        Response r = connection.get(path);

        response.setRequestId(r.getRequestId());
        if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
            response.setStatus(ETResult.Status.OK);
        } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(r.getResponseCode().toString());
        response.setResponseMessage(r.getResponseMessage());

        Gson gson = client.getGson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(r.getResponsePayload()).getAsJsonObject();

        if (jsonObject.get("page") != null) {
            response.setPage(jsonObject.get("page").getAsInt());
            logger.trace("page = " + response.getPage());
            response.setPageSize(jsonObject.get("pageSize").getAsInt());
            logger.trace("pageSize = " + response.getPageSize());
            response.setTotalCount(jsonObject.get(totalCount).getAsInt());
            logger.trace("totalCount = " + response.getTotalCount());

            if (response.getPage() * response.getPageSize() < response.getTotalCount()) {
                response.setMoreResults(true);
            }

            JsonArray elements = jsonObject.get(collection).getAsJsonArray();

            for (JsonElement element : elements) {
                // XXX duplicate code A
                T object = gson.fromJson(element, type);
                object.setClient(client); // XXX
                ETResult<T> result = new ETResult<T>();
                result.setObject(object);
                response.addResult(result);
            }
        } else {
            // XXX duplicate code A
            T object = gson.fromJson(jsonObject, type);
            object.setClient(client); // XXX
            ETResult<T> result = new ETResult<T>();
            result.setObject(object);
            response.addResult(result);
        }

        return response;
    }

    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete(client, POST, objects);
    }

    public static <T extends ETRestObject> ETResponse<T> update(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete(client, PATCH, objects);
    }

    public static <T extends ETRestObject> ETResponse<T> delete(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete(client, DELETE, objects);
    }

    private static <T extends ETRestObject> ETResponse<T> createUpdateDelete(ETClient client,
                                                                             Method method,
                                                                             List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotations = objects.get(0).getClass().getAnnotation(RestObject.class);

        assert annotations != null;

        String path = annotations.path();
        String primaryKey = annotations.primaryKey();
        String collection = annotations.collection();
        String totalCount = annotations.totalCount();

        logger.trace("path: " + path);
        logger.trace("primaryKey: " + primaryKey);
        logger.trace("collection: " + collection);
        logger.trace("totalCount: " + totalCount);

        //
        // There's currently no way to do this in bulk, so
        // we walk through the list of objects and create,
        // update, or delete them one at a time:
        //

        Gson gson = client.getGson();

        for (T object : objects) {
            switch (method) {
              case POST:
                logger.trace("POST " + path);
                break;
              case PATCH:
                logger.trace("PATCH " + path + "/" + object.getId());
                break;
              case DELETE:
                logger.trace("DELETE " + path + "/" + object.getId());
                break;
              default:
                throw new ETSdkException("invalid method: " + method);
            }

            String requestPayload = null;
            if (method != DELETE) {
                // no request payload for deletes
                requestPayload = gson.toJson(object);
                if (logger.isTraceEnabled()) {
                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonObject = jsonParser.parse(requestPayload).getAsJsonObject();
                    String jsonPrettyPrinted = gson.toJson(jsonObject);
                    for (String line : jsonPrettyPrinted.split("\\n")) {
                        logger.trace(line);
                    }
                }
            }

            Response r = null;
            switch (method) {
              case POST:
                r = connection.post(path, requestPayload);
                break;
              case PATCH:
                r = connection.patch(path + "/" + object.getId(), requestPayload);
                break;
              case DELETE:
                r = connection.delete(path + "/" + object.getId());
                break;
              default:
                throw new ETSdkException("invalid method: " + method);
            }

            ETResult<T> result = new ETResult<T>();
            result.setRequestId(r.getRequestId());
            if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
                result.setStatus(ETResult.Status.OK);
            } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(r.getResponseCode().toString());
            result.setResponseMessage(r.getResponseMessage());
            if (method != DELETE) {
                // no response payload for deletes
                String responsePayload = r.getResponsePayload();
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(responsePayload).getAsJsonObject();
                if (logger.isTraceEnabled()) {
                    String jsonPrettyPrinted = gson.toJson(jsonObject);
                    for (String line : jsonPrettyPrinted.split("\\n")) {
                        logger.trace(line);
                    }
                }
                @SuppressWarnings("unchecked")
                T responseObject = (T) gson.fromJson(responsePayload, object.getClass());
                responseObject.setClient(client); // XXX
                result.setObject(responseObject);
            }

            response.addResult(result);

            object.setClient(client); // XXX
        }

        // XXX set overall status

        return response;
    }

    public String serialize() {
        Gson gson = getClient().getGson();
        return gson.toJson(this);
    }

    public static <T extends ETRestObject> T deserialize(ETClient client,
                                                         String payload,
                                                         Class<T> type)
    {
        Gson gson = client.getGson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(payload).getAsJsonObject();
        T object = gson.fromJson(jsonObject, type);
        object.setClient(client);
        return object;
    }

    public static <T extends ETRestObject> ETResponse<T> deserialize(ETClient client,
                                                                     String payload,
                                                                     Class<T> type,
                                                                     String totalCount,
                                                                     String collection)
    {
        ETResponse<T> response = new ETResponse<T>();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(payload).getAsJsonObject();

        if (jsonObject.get("page") != null) {
            response.setPage(jsonObject.get("page").getAsInt());
            logger.trace("page = " + response.getPage());
            response.setPageSize(jsonObject.get("pageSize").getAsInt());
            logger.trace("pageSize = " + response.getPageSize());
            response.setTotalCount(jsonObject.get(totalCount).getAsInt());
            logger.trace("totalCount = " + response.getTotalCount());

            if (response.getPage() * response.getPageSize() < response.getTotalCount()) {
                response.setMoreResults(true);
            }

            JsonArray elements = jsonObject.get(collection).getAsJsonArray();

            for (JsonElement element : elements) {
                ETResult<T> result = new ETResult<T>();
                result.setObject(deserialize(client, element.toString(), type));
                response.addResult(result);
            }
        } else {
            ETResult<T> result = new ETResult<T>();
            result.setObject(deserialize(client, jsonObject.toString(), type));
            response.addResult(result);
        }

        return response;
    }

    protected static String getInternalProperty(Class<? extends ETRestObject> type,
                                                String name)
        throws ETSdkException
    {
        String internalProperty = null;

        Class<? extends ETRestObject> externalType = type; // for code readability

        Field externalField = getField(externalType, name);

        SerializedName serializedNameAnnotation =
                externalField.getAnnotation(SerializedName.class);

        if (serializedNameAnnotation != null) {
            internalProperty = serializedNameAnnotation.value();
        } else {
            // internal name is the same as external name
            internalProperty = externalField.getName();
        }

        return internalProperty;
    }

    private static String toFilterString(String value)
        throws ETSdkException
    {
        return toFilterString(value, false);
    }

    private static String toFilterString(String value, boolean forceQuotes)
        throws ETSdkException
    {
        if (value.equals("")) {
            forceQuotes = true;
        }
        // XXX investigate
        if (value.contains("*")) {
            forceQuotes = true;
        }
        // XXX workaround for FUEL-3348--remove after 02
        if (value.contains("-")) {
            forceQuotes = true;
        }
        boolean quotes = forceQuotes;
        if (!forceQuotes) {
            // needs quotes in the URL if there's whitespace
            for (int i = 0; i < value.length(); i++) {
                if (Character.isWhitespace(value.charAt(i))) {
                    quotes = true;
                    break;
                }
            }
        }
        String v = null;
        if (quotes) {
            v = "'" + value + "'";
        } else {
            v = value.toString();
        }
        try {
            return URLEncoder.encode(v, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new ETSdkException("error URL encoding " + v, ex);
        }
    }

    public static String toFilterString(ETExpression expression)
        throws ETSdkException
    {
        return toFilterString(expression, true);
    }

    private static String toFilterString(ETExpression expression, boolean first)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (first) {
            stringBuilder.append("$filter=");
        }
        ETExpression.Operator operator = expression.getOperator();
        if (operator == null) {
            return null;
        }
        switch (operator) {
          case AND:
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(0), false));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(1), false));
            break;
          case OR:
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(0), false));
            stringBuilder.append("%20");
            stringBuilder.append("or");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(1), false));
            break;
          case NOT:
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getSubexpressions().get(0), false));
            break;
          case EQUALS:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("eq");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case NOT_EQUALS:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("neq");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case LESS_THAN:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lt");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lte");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case GREATER_THAN:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gt");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gte");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), false));
            break;
          case IS_NULL:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("is");
            stringBuilder.append("%20");
            stringBuilder.append("null");
            break;
          case IS_NOT_NULL:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("is");
            stringBuilder.append("%20");
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append("null");
            break;
          case IN:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("in");
            stringBuilder.append("%20");
            stringBuilder.append("(");
            boolean firstValue = true;
            for (String value : expression.getValues()) {
                if (firstValue) {
                    firstValue = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(toFilterString(value));
            }
            stringBuilder.append(")");
            break;
          case BETWEEN:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("between");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValues().get(0), false));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValues().get(1), false));
            break;
          case LIKE:
            stringBuilder.append(expression.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("like");
            stringBuilder.append("%20");
            stringBuilder.append(toFilterString(expression.getValue(), true));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }

    //
    // The remaining methods use the SOAP API to tunnel
    // requests to the REST API. These methods are a workaround
    // for customer issues that will be resolved in August
    // 2015. NOTE: Because these are a workaround you
    // should not rely on them to work beyond August 2015!
    //

    protected static String soapCall(ETClient client,
                                     String method,
                                     String path,
                                     String payload,
                                     String... parameters)
        throws ETSdkException
    {
        List<APIProperty> properties = new ArrayList<APIProperty>();
        for (String parameter : parameters) {
            String tokens[] = parameter.split("=");
            assert tokens.length == 2;
            APIProperty property = new APIProperty();
            property.setName(tokens[0]);
            property.setValue(tokens[1]);
            properties.add(property);
        }
        CreateResponse createResponse = soapCall(client,
                                                 method,
                                                 path,
                                                 payload,
                                                 properties);
        assert createResponse != null;
        assert createResponse.getResults() != null;
        assert createResponse.getResults().size() == 1;
        CreateResult createResult = createResponse.getResults().get(0);
        AudienceBuilderRestCall restResponse =
                (AudienceBuilderRestCall) createResult.getObject();
        return restResponse.getPayload();
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     ETFilter filter)
        throws ETSdkException
    {
        List<APIProperty> properties = toApiProperties(client,
                                                       type,
                                                       filter);
        return soapCall(client, type, method, path, null, properties);
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     List<APIProperty> properties)
        throws ETSdkException
    {
        return soapCall(client, type, method, path, null, properties);
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     Integer page,
                                                                     Integer pageSize,
                                                                     ETFilter filter)
        throws ETSdkException
    {
        List<APIProperty> properties = toApiProperties(client,
                                                       type,
                                                       filter);
        return soapCall(client, type, method, path, page, pageSize, properties);
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     Integer page,
                                                                     Integer pageSize,
                                                                     List<APIProperty> properties)
        throws ETSdkException
    {
        if (page != null) {
            APIProperty property = new APIProperty();
            property.setName("$page");
            property.setValue(page.toString());
            properties.add(property);
        }
        if (pageSize != null) {
            APIProperty property = new APIProperty();
            property.setName("$pageSize");
            property.setValue(pageSize.toString());
            properties.add(property);
        }
        return soapCall(client, type, method, path, null, properties);
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     String payload)
        throws ETSdkException
    {
        return soapCall(client, type, method, path, payload, null);
    }

    protected static <T extends ETRestObject> ETResponse<T> soapCall(ETClient client,
                                                                     Class<T> type,
                                                                     String method,
                                                                     String path,
                                                                     String payload,
                                                                     List<APIProperty> properties)
        throws ETSdkException
    {
        CreateResponse createResponse = soapCall(client,
                                                 method,
                                                 path,
                                                 payload,
                                                 properties);

        ETResponse<T> response = new ETResponse<T>();
        /*response.setRequestId(createResponse.getRequestID());
        if (createResponse.getOverallStatus().equals("OK")) {
            response.setStatus(ETResult.Status.OK);
        } else if (createResponse.getOverallStatus().equals("Error")) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(createResponse.getOverallStatus());
        response.setResponseMessage(createResponse.getOverallStatus());*/
        assert createResponse.getResults() != null;
        assert createResponse.getResults().size() == 1;
        AudienceBuilderRestCall restResponse = (AudienceBuilderRestCall)
                createResponse.getResults().get(0).getObject();
        RestObject annotations = type.getAnnotation(RestObject.class);
        if (restResponse != null) {
            ETResponse<T> r = null;
            if (annotations != null) {
                r = deserialize(client,
                                restResponse.getPayload(),
                                type,
                                annotations.totalCount(),
                                annotations.collection());
            } else {
                r = deserialize(client,
                                restResponse.getPayload(),
                                type,
                                null,
                                null);
            }
            response.setPage(r.getPage());
            response.setPageSize(r.getPageSize());
            response.setTotalCount(r.getTotalCount());
            response.setMoreResults(r.hasMoreResults());
            for (ETResult<T> result : r.getResults()) {
                response.addResult(result);
            }
        }

        return response;
    }

    protected static CreateResponse soapCall(ETClient client,
                                             String method,
                                             String path,
                                             String payload,
                                             List<APIProperty> properties)
        throws ETSdkException
    {
        ETSoapConnection connection = client.getSoapConnection();
        Soap soap = connection.getSoap();
        AudienceBuilderRestCall restRequest = new AudienceBuilderRestCall();
        restRequest.setMethod(method);
        restRequest.setURL(path);
        if (payload != null) {
            restRequest.setPayload(payload);
        }
        if (properties != null) {
            for (APIProperty property : properties) {
                restRequest.getParameters().add(property);
            }
        }
        CreateRequest createRequest = new CreateRequest();
        createRequest.setOptions(new CreateOptions());
        createRequest.getObjects().add(restRequest);
        return soap.create(createRequest);
    }

    protected static <T extends ETRestObject> List<APIProperty> toApiProperties(ETClient client,
                                                                                Class<T> type,
                                                                                ETFilter filter)
        throws ETSdkException
    {
        List<APIProperty> properties = new ArrayList<APIProperty>();

        ETExpression expression = filter.getExpression();
        if (expression.getOperator() != null) {
            java.lang.reflect.Method toFilterString = null;
            if (type != null) {
                try {
                    toFilterString = type.getMethod("toFilterString", ETExpression.class);
                } catch (NoSuchMethodException ex) {
                    // there's no toFilterString method on TYPE
                } catch (SecurityException ex) {
                    throw new ETSdkException(ex);
                }
            }
            if (toFilterString != null) {
                String s = null;
                try {
                    s = (String) toFilterString.invoke(null, expression);
                } catch (Exception ex) {
                    throw new ETSdkException(ex);
                }
                try {
                    s = URLDecoder.decode(s, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    throw new ETSdkException("error URL decoding " + s, ex);
                }
                String tokens[] = s.split("=");
                assert tokens.length == 2;
                APIProperty property = new APIProperty();
                property.setName(tokens[0]);
                property.setValue(tokens[1]);
                properties.add(property);
            }
        }

        String orderby = null;
        for (String s : filter.getOrderBy()) {
            if (orderby == null) {
                orderby = s;
            } else {
                orderby += "," + s;
            }
            if (!filter.getOrderByAsc()) {
                orderby += " desc";
            }
        }
        if (orderby != null) {
            APIProperty property = new APIProperty();
            property.setName("$orderby");
            property.setValue(orderby);
            properties.add(property);
        }

        String fields = null;
        for (String s : filter.getProperties()) {
            if (fields == null) {
                fields = s;
            } else {
                fields += "," + s;
            }
        }
        if (fields != null) {
            APIProperty property = new APIProperty();
            property.setName("$fields");
            property.setValue(fields);
            properties.add(property);
        }

        return properties;
    }
}
