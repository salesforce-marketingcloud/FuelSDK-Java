//
// This file is part of the Fuel Java client library.
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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestConnection;
import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Hierarchy",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETCube extends ETRestObject {
    private static Logger logger = Logger.getLogger(ETCube.class);

    @Expose @SerializedName("pK")
    @ExternalName("key")
    private String key = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("count")
    private Integer count = null;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
        // no-op
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
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static ETResponse<ETCube> retrieve(ETClient client,
                                              String id,
                                              String... keys)
        throws ETSdkException
    {
        ETRestConnection connection = client.getRestConnection();

        String path = "/internal/v1/AudienceBuilder/Hierarchy/" + id;

        if (keys.length > 0) {
            path += "?Values=";
            boolean first = true;
            for (String key : keys) {
                if (!first) {
                    path += ",";
                }
                first = false;
                path += key;
            }
        }

        Response r = retrieve(client, path, "id", (ETFilter) null, null, null, ETCube.class);

        ETResponse<ETCube> response = new ETResponse<ETCube>();

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
            response.setTotalCount(jsonObject.get("totalCount").getAsInt());
            logger.trace("totalCount = " + response.getTotalCount());

            if (response.getPage() * response.getPageSize() < response.getTotalCount()) {
                response.setMoreResults(true);
            }

            JsonArray elements = jsonObject.get("entities").getAsJsonArray();

            for (JsonElement element : elements) {
                ETCube cube = gson.fromJson(element, ETCube.class);
                cube.setClient(client); // XXX
                ETResult<ETCube> result = new ETResult<ETCube>();
                result.setObject(cube);
                response.addResult(result);
            }
        }

        return response;
    }
}
