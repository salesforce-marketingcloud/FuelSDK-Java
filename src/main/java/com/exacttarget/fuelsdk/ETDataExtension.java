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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;

/**
 * The <code>ETDataExtension</code> class represents an ExactTarget
 * data extension.
 */
@SoapObject(internalType = DataExtension.class, unretrievable = {
    "ID", "Fields"
})
public class ETDataExtension extends ETSoapObject {
    private static Logger logger = Logger.getLogger(ETDataExtension.class);

    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("description")
    private String description = null;
    @ExternalName("folderId")
    @InternalName("categoryID")
    private Integer folderId = null;
    @ExternalName("columns")
    @InternalName("fields")
    private List<ETDataExtensionColumn> columns = new ArrayList<ETDataExtensionColumn>();
    @ExternalName("isSendable")
    private Boolean isSendable = null;
    @ExternalName("isTestable")
    private Boolean isTestable = null;

    public ETDataExtension() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    /**
     * @deprecated
     * Use <code>getFolderId()</code>.
     */
    @Deprecated
    public Integer getCategoryId() {
        return getFolderId();
    }

    /**
     * @deprecated
     * Use <code>setFolderId()</code>.
     */
    @Deprecated
    public void setCategoryId(Integer categoryId) {
        setFolderId(categoryId);
    }

    public List<ETDataExtensionColumn> getColumns() {
        return columns;
    }

    public void addColumn(String name) {
        ETDataExtensionColumn column = new ETDataExtensionColumn();
        column.setName(name);
        addColumn(column);
    }

    public void addColumn(ETDataExtensionColumn column) {
        columns.add(column);
    }

    public Boolean getIsSendable() {
        return isSendable;
    }

    public void setIsSendable(Boolean isSendable) {
        this.isSendable = isSendable;
    }

    public Boolean getIsTestable() {
        return isTestable;
    }

    public void setIsTestable(Boolean isTestable) {
        this.isTestable = isTestable;
    }

    public ETResponse<ETDataExtensionRow> insert(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETClient client = getClient();

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }
        }

        return client.create(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> select()
        throws ETSdkException
    {
        // new String[0] = empty properties
        return select((ETFilter) null, null, null, new String[0]);
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 String... columns)
        throws ETSdkException
    {
        // XXX copied and pasted from ETClient.retrieve(filter, properties)
        ETFilter f = null;
        String[] c = columns;
        try {
            f = ETFilter.parse(filter);
        } catch (ETSdkException ex) {
            // XXX check against ex.getCause();

            //
            // The filter argument is actually a column. This is a bit
            // of a hack, but this method needs to handle the case of
            // both a filtered and a filterless retrieve with columns,
            // as having one method for each results in ambiguous methods.
            //

            c = new String[columns.length + 1];
            c[0] = filter;
            int i = 1;
            for (String column : columns) {
                c[i++] = column;
            }
        }
        return select(f, null, null, c);
    }

    public ETResponse<ETDataExtensionRow> select(Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        return select((ETFilter) null, page, pageSize, columns);
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        return select(ETFilter.parse(filter), page, pageSize, columns);
    }

    public ETResponse<ETDataExtensionRow> select(ETFilter filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        ETClient client = getClient();

        // XXX copied and pasted from ETRestObject.retrieve

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        //
        // Get handle to the REST connection:
        //

        ETRestConnection connection = client.getRestConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        String path = "/data/v1/customobjectdata/key/" + getKey() + "/rowset";

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

        if (columns.length > 0) {
            //
            // Only request those columns specified:
            //

            boolean firstField = true;
            for (String column : columns) {
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
                stringBuilder.append(column);
            }
        }

        if (filter != null) {
            if (firstQueryParameter) {
                firstQueryParameter = false;
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append("$filter=");
            stringBuilder.append(toQueryParameters(filter));
        }

        path = stringBuilder.toString();

        logger.trace("GET " + path);

        String json = connection.get(path);

        response.setRequestId(connection.getLastCallRequestId());
        response.setResponseCode(connection.getLastCallResponseCode());
        response.setResponseMessage(connection.getLastCallResponseMessage());

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
            response.setTotalCount(jsonObject.get("count").getAsInt());
            logger.trace("totalCount = " + response.getTotalCount());

            if (response.getPage() * response.getPageSize() < response.getTotalCount()) {
                response.setMoreResults(true);
            }

            JsonArray collection = jsonObject.get("items").getAsJsonArray();

            for (JsonElement element : collection) {
                JsonObject object = element.getAsJsonObject();
                ETDataExtensionRow row = new ETDataExtensionRow();
                JsonObject keys = object.get("keys").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : keys.entrySet()) {
                    row.setColumn(entry.getKey(), entry.getValue().getAsString());
                }
                JsonObject values = object.get("values").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : values.entrySet()) {
                    row.setColumn(entry.getKey(), entry.getValue().getAsString());
                }
                row.setClient(client);
                ETResult<ETDataExtensionRow> result = new ETResult<ETDataExtensionRow>();
                result.setObject(row);
                response.addResult(result);
            }
        }

        return response;
    }

    public ETResponse<ETDataExtensionRow> update(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETClient client = getClient();

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }
        }

        return client.update(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> update(String filter, String... values)
        throws ETSdkException
    {
        ETClient client = getClient();
        return client.update(ETDataExtensionRow.class, filter, values);
    }

    public ETResponse<ETDataExtensionRow> delete(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETClient client = getClient();

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }
        }

        return client.delete(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> delete(String filter)
        throws ETSdkException
    {
        ETClient client = getClient();
        return client.delete(ETDataExtensionRow.class, filter);
    }

//    public void addColumn(ETDataExtensionColumn column)
//        throws ETSdkException
//    {
//        ETClient client = getClient();
//
//        //
//        // Get handle to the SOAP connection:
//        //
//
//        ETSoapConnection connection = client.getSoapConnection();
//
//        //
//        // Automatically refresh the token if necessary:
//        //
//
//        client.refreshToken();
//
//        //
//        // Add the column to the data extension:
//        //
//
//        Soap soap = connection.getSoap();
//
//        DataExtension dataExtension = new DataExtension();
//        dataExtension.setCustomerKey(getKey());
//
//        DataExtension.Fields fields = new DataExtension.Fields();
//        fields.getField().add((DataExtensionField) column.toInternal());
//        dataExtension.setFields(fields);
//
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.setOptions(new UpdateOptions());
//        updateRequest.getObjects().add(dataExtension);
//
//        soap.update(updateRequest);
//
//        // XXX check for errors and throw the appropriate exception
//    }

    public List<ETDataExtensionColumn> retrieveColumns()
        throws ETSdkException
    {
        ETClient client = getClient();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Retrieve all columns with CustomerKey = this data extension:
        //

        ETFilter filter = new ETFilter();
        filter.setProperty("DataExtension.CustomerKey");
        filter.setOperator("=");
        filter.addValue(getKey());

        ETResponse<ETDataExtensionColumn> response =
                ETDataExtensionColumn.retrieve(client,
                                               filter,
                                               null, // page
                                               null, // pageSize
                                               ETDataExtensionColumn.class,
                                               new String[0]); // properties

        // XXX check for errors and throw the appropriate exception

        return response.getObjects();
    }

    private String toQueryParameters(ETFilter filter) {
        StringBuilder stringBuilder = new StringBuilder();

        String operator = filter.getOperator();
        if (operator.equals("and") ||
            operator.equals("or"))
        {
            stringBuilder.append(toQueryParameters(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append(operator);
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameters(filter.getFilters().get(1)));
        } else if (operator.equals("not")) {
            stringBuilder.append(operator);
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameters(filter.getFilters().get(0)));
        } else {
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            if (operator.equals("=")) {
                stringBuilder.append("eq");
            } else if (operator.equals("!=")) {
                stringBuilder.append("ne");
            } else if (operator.equals("<")) {
                stringBuilder.append("lt");
            } else if (operator.equals("<=")) {
                stringBuilder.append("lte");
            } else if (operator.equals(">")) {
                stringBuilder.append("gt");
            } else if (operator.equals(">=")) {
                stringBuilder.append("gte");
            } else {
                stringBuilder.append(operator);
            }
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
        }

        return stringBuilder.toString();
    }
}
