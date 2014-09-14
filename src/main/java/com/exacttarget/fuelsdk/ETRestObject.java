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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.annotations.RestAnnotations;

public abstract class ETRestObject extends ETRestObjectImmutable {
    private static Logger logger = Logger.getLogger(ETRestObject.class);

    public static <T extends ETRestObject> ETResponse<T> create(ETClient client,
                                                                T object)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        ETRestConnection connection = client.getRESTConnection();

        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson = null;
        if (logger.isTraceEnabled()) {
            gson = gsonBuilder.setPrettyPrinting().create();
        } else {
            gson = gsonBuilder.create();
        }
        JsonParser jsonParser = new JsonParser();

        RestAnnotations annotations = object.getClass().getAnnotation(RestAnnotations.class);

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

        logger.trace("POST " + path);

        String json = gson.toJson(object);

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

        response.addResult(gson.fromJson(json, object.getClass()));

        // XXX set requestId, statusCode, and statusMessage

        return response;
    }

    public <T extends ETRestObject> ETResponse<T> update(ETClient client)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        ETRestConnection connection = client.getRESTConnection();

        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson = null;
        if (logger.isTraceEnabled()) {
            gson = gsonBuilder.setPrettyPrinting().create();
        } else {
            gson = gsonBuilder.create();
        }
        JsonParser jsonParser = new JsonParser();

        RestAnnotations annotations = getClass().getAnnotation(RestAnnotations.class);

        assert annotations != null;

        String path = annotations.path();
        logger.trace("path: " + path);
        String primaryKey = annotations.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotations.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        //
        // Construct the path to the object:
        //

        // XXX substitute primaryKey here w/ reflection to get getter
        path = replaceVariable(path, "id", getId());

        logger.trace("PATCH " + path);

        String json = gson.toJson(this);

        if (logger.isTraceEnabled()) {
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            String jsonPrettyPrinted = gson.toJson(jsonObject);
            for (String line : jsonPrettyPrinted.split("\\n")) {
                logger.trace(line);
            }
        }

        // XXX patch
        json = connection.post(path, json);

        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        if (logger.isTraceEnabled()) {
            String jsonPrettyPrinted = gson.toJson(jsonObject);
            for (String line : jsonPrettyPrinted.split("\\n")) {
                logger.trace(line);
            }
        }

        response.addResult(gson.fromJson(json, getClass()));

        // XXX set requestId, statusCode, and statusMessage

        return response;
    }

    public <T extends ETRestObject> ETResponse<T> delete(ETClient client)
        throws ETSdkException
    {
        // XXX substitute primaryKey here w/ reflection to get getter
        return (ETResponse<T>) ETRestObject.delete(client, "id = " + getId(), getClass());
    }

    public static <T extends ETRestObject> ETResponse<T> delete(ETClient client,
                                                                String filter,
                                                                Class<T> type)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        ETRestConnection connection = client.getRESTConnection();

//        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        Gson gson = null;
//        if (logger.isTraceEnabled()) {
//            gson = gsonBuilder.setPrettyPrinting().create();
//        } else {
//            gson = gsonBuilder.create();
//        }
//        JsonParser jsonParser = new JsonParser();

        RestAnnotations annotations = type.getAnnotation(RestAnnotations.class);

        assert annotations != null;

        String path = annotations.path();
        logger.trace("path: " + path);
        String primaryKey = annotations.primaryKey();
        logger.trace("primaryKey: " + primaryKey);
        String collectionKey = annotations.collectionKey();
        logger.trace("collectionKey: " + collectionKey);

        //
        // Replace all variables in the path per the filter:
        //

        logger.trace("filter: " + filter);

        // XXX should throw an exception for complex expressions

        ETFilterExpression parsedFilter = new ETFilterExpression(filter);

        // XXX doesn't support multiple variables yet

        path = replaceVariable(path,
                               parsedFilter.getColumn(),
                               parsedFilter.getValues().get(0));

        // XXX should throw an exception if not all are specified

//        //
//        // Construct the path to the object:
//        //
//
//        // XXX substitute primaryKey here w/ reflection to get getter
//        path = replaceVariable(path, "id", getId());

        logger.trace("DELETE " + path);

        connection.delete(path);

        // doesn't return any data.. are all like this?
//        String json = connection.delete(path);
//
//        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
//
//        if (logger.isTraceEnabled()) {
//            String jsonPrettyPrinted = gson.toJson(jsonObject);
//            for (String line : jsonPrettyPrinted.split("\\n")) {
//                logger.trace(line);
//            }
//        }
//
//        response.addResult(gson.fromJson(json, type));

        // XXX set requestId, statusCode, and statusMessage

        return response;
    }
}
