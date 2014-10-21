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

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

public abstract class ETRestObject extends ETObject {
    private static Logger logger = Logger.getLogger(ETRestObject.class);

    private ETClient client = null;

    @ExternalName("id") @Expose
    private String id = null;
    @ExternalName("key") @Expose
    private String key = null;
    @ExternalName("createdDate") @Expose
    private Date createdDate = null;
    @ExternalName("modifiedDate") @Expose
    private Date modifiedDate = null;

    protected ETClient getClient() {
        return client;
    }

    protected void setClient(ETClient client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    protected static <T extends ETRestObject> ETResponse<ETResult> create(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get handle to the REST connection:
        //

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
        logger.trace("path: " + path);
        String primaryKey = annotations.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotations.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        //
        // Remove the primary key from the end of the path:
        //

        path = removePrimaryKeyFromEnd(path, primaryKey);

        Gson gson = connection.getGson();
        JsonParser jsonParser = new JsonParser();

        // XXX is there a way to do this in bulk
        for (T object : objects) {
            object.setClient(client);

            String json = gson.toJson(object);

            logger.trace("POST " + path);

            if (logger.isTraceEnabled()) {
                JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            json = connection.post(path, json);

            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

            if (logger.isTraceEnabled()) {
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            //gson.fromJson(json, object.getClass()));

            response.addResult(connection.getResult());
        }

        // XXX set overall requestId, statusCode, and statusMessage

        return response;
    }

    protected static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                     ETFilter filter,
                                                                     Integer page,
                                                                     Integer pageSize,
                                                                     Class<T> type,
                                                                     String... properties)
        throws ETSdkException
    {
        if (properties.length != 0) {
            throw new ETSdkException("REST objects do not support partial retrieves");
        }

        ETResponse<T> response = new ETResponse<T>();

        //
        // Get handle to the REST connection:
        //

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read call details from the RestObject annotation:
        //

        RestObject annotation = type.getAnnotation(RestObject.class);

        assert annotation != null;

        String path = annotation.path();
        logger.trace("path: " + path);
        String primaryKey = annotation.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotation.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        if (filter != null) {
            //
            // Replace all variables in the path per the filter:
            //

            logger.trace("filter: " + filter);

            path = replaceVariable(path, filter.getProperty(), filter.getValue());

            // XXX should throw an exception if not all are specified
        } else {
            //
            // Remove the primary key from the end of the path:
            //

            path = removePrimaryKeyFromEnd(path, primaryKey);
        }

        StringBuilder stringBuilder = new StringBuilder(path);

        if (page != null && pageSize != null) {
            stringBuilder.append("?");
            stringBuilder.append("$page=");
            stringBuilder.append(page);
            stringBuilder.append("&");
            stringBuilder.append("$pagesize=");
            stringBuilder.append(pageSize);
        }

        path = stringBuilder.toString();

        logger.trace("GET " + path);

        String json = connection.get(path);

        Gson gson = connection.getGson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        if (logger.isTraceEnabled()) {
            String jsonPrettyPrinted = gson.toJson(jsonObject);
            for (String line : jsonPrettyPrinted.split("\\n")) {
                logger.trace(line);
            }
        }

        if (jsonObject.get("page") != null) {
            response.setPage(jsonObject.get("page").getAsInt());
            logger.trace("page = " + response.getPage());
            response.setPageSize(jsonObject.get("pageSize").getAsInt());
            logger.trace("pageSize = " + response.getPageSize());
            JsonElement totalCount = jsonObject.get("totalCount");
            if (totalCount == null) {
                // XXX this should be standardized
                totalCount = jsonObject.get("count");
            }
            response.setTotalCount(totalCount.getAsInt());
            logger.trace("totalCount = " + response.getTotalCount());

            if (response.getPage() * response.getPageSize() < response.getTotalCount()) {
                response.setMoreResults(true);
            }

            JsonArray collection = jsonObject.get(collectionKey).getAsJsonArray();

            for (JsonElement element : collection) {
                response.addResult(gson.fromJson(element, type));
            }
        } else {
            ETRestObject object = gson.fromJson(json, type);
            object.setClient(client);
            response.addResult(object);
        }

        // XXX set overall requestId, statusCode, and statusMessage

        return response;
    }

    protected static <T extends ETRestObject> ETResponse<ETResult> update(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get handle to the REST connection:
        //

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
        logger.trace("path: " + path);
        String primaryKey = annotations.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotations.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        Gson gson = connection.getGson();
        JsonParser jsonParser = new JsonParser();

        // XXX is there a way to do this in bulk
        for (T object : objects) {
            object.setClient(client);

            //
            // Construct the path to the object:
            //

            // XXX should throw an exception for complex expressions

            // XXX substitute primaryKey here w/ reflection to get getter
            String p = replaceVariable(path, "id", object.getId());

            String json = gson.toJson(object);

            logger.trace("PATCH " + p);

            if (logger.isTraceEnabled()) {
                JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            json = connection.patch(p, json);

            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

            if (logger.isTraceEnabled()) {
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            //gson.fromJson(json, object.getClass()));

            response.addResult(connection.getResult());
        }

        // XXX set overall requestId, statusCode, and statusMessage

        return response;
    }

    protected static <T extends ETRestObject> ETResponse<ETResult> delete(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get handle to the REST connection:
        //

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
        logger.trace("path: " + path);
        String primaryKey = annotations.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotations.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        Gson gson = connection.getGson();
        JsonParser jsonParser = new JsonParser();

        // XXX is there a way to do this in bulk
        for (T object : objects) {
            object.setClient(client);

            //
            // Construct the path to the object:
            //

            // XXX should throw an exception for complex expressions

            // XXX substitute primaryKey here w/ reflection to get getter
            String p = replaceVariable(path, "id", object.getId());

            String json = gson.toJson(object);

            logger.trace("DELETE " + p);

            if (logger.isTraceEnabled()) {
                JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
                String jsonPrettyPrinted = gson.toJson(jsonObject);
                for (String line : jsonPrettyPrinted.split("\\n")) {
                    logger.trace(line);
                }
            }

            json = connection.delete(p);

            // doesn't return any data.. are all like this?
//            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
//
//            if (logger.isTraceEnabled()) {
//                String jsonPrettyPrinted = gson.toJson(jsonObject);
//                for (String line : jsonPrettyPrinted.split("\\n")) {
//                    logger.trace(line);
//                }
//            }
//
//            //gson.fromJson(json, object.getClass()));
//
//            response.addResult(connection.getResult());
        }

        // XXX set overall requestId, statusCode, and statusMessage

        return response;
    }

    // XXX private?
    protected static String removePrimaryKeyFromEnd(String path, String primaryKey)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder(path);
        int index = stringBuilder.lastIndexOf("/");
        if (!stringBuilder.substring(index + 1).equals("{" + primaryKey + "}")) {
            throw new ETSdkException("path \""
                                     + path
                                     + "\" does not end with variable \"{"
                                     + primaryKey
                                     + "}\"");
        }
        stringBuilder.delete(index, stringBuilder.length());
        return stringBuilder.toString();
    }

    // XXX private?
    protected static String replaceVariable(String path,
                                            String key,
                                            String value)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder(path);
        String variable = "{" + key + "}";
        int index = stringBuilder.indexOf(variable);
        if (index == -1) {
            throw new ETSdkException("path \""
                                     + path
                                     + "\" does not contain variable \"{"
                                     + key
                                     + "}\"");
        }
        stringBuilder.replace(index, index + variable.length(), value);
        return stringBuilder.toString();
    }
}
