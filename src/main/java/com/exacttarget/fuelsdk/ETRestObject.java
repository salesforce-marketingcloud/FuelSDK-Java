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

package com.exacttarget.fuelsdk;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETRestConnection.Method;

import static com.exacttarget.fuelsdk.ETRestConnection.Method.*;

import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

public abstract class ETRestObject extends ETApiObject {
    private static Logger logger = Logger.getLogger(ETRestObject.class);

    @Expose
    @ExternalName("createdDate")
    private Date createdDate = null;
    @Expose
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  ETFilter filter,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  Class<T> type,
                                                                  String... properties)
        throws ETSdkException
    {
        ETRestConnection connection = client.getRestConnection();

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

        Response r = retrieve(client, path, primaryKey, filter, page, pageSize, properties);

        ETResponse<T> response = new ETResponse<T>();

        response.setRequestId(r.getRequestId());
        if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
            response.setStatus(ETResult.Status.OK);
        } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(r.getResponseCode().toString());
        response.setResponseMessage(r.getResponseMessage());

        Gson gson = connection.getGson();
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

    //
    // ETRestObject has an additional retrieve method that takes
    // path and primaryKey strings. In most cases, these
    // strings are read from the RestObject annotation, but
    // in a few cases, custom code needs to directly access the
    // REST layer (e.g., DataExtension selects and exports).
    //
    // In addition, this method returns the raw REST layer Response
    // object rather than the generic ETResponse object, as in a few
    // cases, the JSON response needs special processing
    // (e.g., data extension retrieves return a collection of
    // keys and values that are not in a flat collection).
    //

    protected static <T extends ETRestObject> Response retrieve(ETClient client,
                                                                String path,
                                                                String primaryKey,
                                                                ETFilter filter,
                                                                Integer page,
                                                                Integer pageSize,
                                                                String... properties)
        throws ETSdkException
    {
        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Build the query parameters:
        //

        StringBuilder stringBuilder = new StringBuilder(path);

        boolean firstQueryParameter = true;

        // XXX can pageSize be unspecified to get default page size?
        if (page != null && pageSize != null) {
            firstQueryParameter = false;
            stringBuilder.append("?");
            stringBuilder.append("$page=");
            stringBuilder.append(page);
            stringBuilder.append("&");
            stringBuilder.append("$pagesize=");
            stringBuilder.append(pageSize);
        }

        if (filter != null) {
            logger.trace("filter: " + filter);

            if ((filter.getOperator() == ETFilter.Operator.EQUALS) &&
                (filter.getProperty().equals(primaryKey)))
            {
                //
                // Append the primary key to the the path:
                //

                stringBuilder.append("/" + filter.getValue());
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
                stringBuilder.append("$filter=");
                stringBuilder.append(toQueryParameter(filter));
            }
        }

        String orderBy = null;

        List<String> fields = new ArrayList<String>();

        if (properties.length > 0) {
            for (String property : properties) {
                if (property.length() >= 8 &&
                    property.substring(0, 8).toLowerCase().equals("order by")) {
                    orderBy = property;
                } else {
                    fields.add(property);
                }
            }
        }

        if (orderBy != null) {
            // XXX support multiple values
            String tokens[] = orderBy.substring(9).split(" ");
            String property = tokens[0];
            Boolean ascending = null;
            if (tokens.length == 2) {
                if (tokens[1].toLowerCase().equals("asc")) {
                    ascending = true;
                } else if (tokens[1].toLowerCase().equals("desc")) {
                    ascending = false;
                }
            }
            if (firstQueryParameter) {
                firstQueryParameter = false;
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append("$orderby=");
            stringBuilder.append(property);
            if (ascending != null) {
                stringBuilder.append("%20");
                if (ascending) {
                    stringBuilder.append("asc");
                } else {
                    stringBuilder.append("desc");
                }
            }
        }

        //
        // Only request the fields specified:
        //

        boolean firstField = true;
        for (String field : fields) {
            if (firstField) {
                firstField = false;
                if (firstQueryParameter) {
                    firstQueryParameter = false;
                    stringBuilder.append("?");
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append("$fields=");
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(field);
        }

        path = stringBuilder.toString();

        logger.trace("GET " + path);

        return connection.get(path);
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

        Gson gson = connection.getGson();

        for (T object : objects) {
            switch (method) {
              case POST:
                logger.trace("POST " + path);
                break;
              case PATCH:
                logger.trace("PATCH " + path);
                break;
              case DELETE:
                logger.trace("DELETE " + path);
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
                r = connection.patch(path, requestPayload);
                break;
              case DELETE:
                r = connection.delete(path);
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

    private static String toQueryParameter(String value)
        throws ETSdkException
    {
        return toQueryParameter(value, false);
    }

    private static String toQueryParameter(String value, boolean forceQuotes)
        throws ETSdkException
    {
        if (value.equals("")) {
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

    protected static String toQueryParameter(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case AND:
            stringBuilder.append(toQueryParameter(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getFilters().get(1)));
            break;
          case OR:
            stringBuilder.append(toQueryParameter(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("or");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getFilters().get(1)));
            break;
          case NOT:
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getFilters().get(0)));
            break;
          case EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("eq");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case NOT_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("neq");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case LESS_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lt");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lte");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case GREATER_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gt");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gte");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue()));
            break;
          case IS_NULL:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("is");
            stringBuilder.append("%20");
            stringBuilder.append("null");
            break;
          case IS_NOT_NULL:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("is");
            stringBuilder.append("%20");
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append("null");
            break;
          case IN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("in");
            stringBuilder.append("%20");
            stringBuilder.append("(");
            boolean first = true;
            for (String value : filter.getValues()) {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(toQueryParameter(value));
            }
            stringBuilder.append(")");
            break;
          case BETWEEN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("between");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValues().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValues().get(1)));
            break;
          case LIKE:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("like");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameter(filter.getValue(), true));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }
}
