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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETExpression;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.internal.APIProperty;

@RestObject(path = "/internal/v1/AudienceBuilder/Dimension/{id}/Values",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETDimensionValue extends ETRestObject {
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

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    @SuppressWarnings("unchecked")
    public static <T extends ETRestObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            List<APIProperty> properties = new ArrayList<APIProperty>();
            ETExpression expression = filter.getExpression();
            if (expression.getOperator() == ETExpression.Operator.EQUALS &&
                expression.getProperty().equals("id"))
            {
                APIProperty property = new APIProperty();
                property.setName("DimensionID");
                property.setValue(expression.getValue());
                properties.add(property);
            } else {
                throw new ETSdkException("dimension ID not specified");
            }
            return (ETResponse<T>) ETRestObject.soapCall(client,
                                                         ETDimensionValue.class,
                                                         "GET",
                                                         "AudienceBuilder/Dimension/{dimensionID}/Values",
                                                         page,
                                                         pageSize,
                                                         properties);
        }
        return ETRestObject.retrieve(client, type, page, pageSize, filter);
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
}
