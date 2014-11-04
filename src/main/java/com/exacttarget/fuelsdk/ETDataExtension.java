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
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.DeleteResult;
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
        addColumn(name, null, null, null, null, null, null, null);
    }

    public void addColumn(String name, ETDataExtensionColumnType type)
    {
        addColumn(name, type, null, null, null, null, null, null);
    }

    public void addColumn(String name, Boolean isPrimaryKey)
    {
        addColumn(name, null, null, null, null, isPrimaryKey, null, null);
    }

    public void addColumn(String name,
                          ETDataExtensionColumnType type,
                          Boolean isPrimaryKey)
    {
        addColumn(name, type, null, null, null, isPrimaryKey, null, null);
    }

    private void addColumn(String name,
                           ETDataExtensionColumnType type,
                           Integer length,
                           Integer precision,
                           Integer scale,
                           Boolean isPrimaryKey,
                           Boolean isRequired,
                           String defaultValue)
    {
        ETDataExtensionColumn column = new ETDataExtensionColumn();
        column.setName(name);
        if (type != null) {
            column.setType(type);
        } else {
            // default is text type
            column.setType(ETDataExtensionColumnType.TEXT);
        }
        // mimics the UI default values for length, precision, and scale
        if (column.getType() == ETDataExtensionColumnType.TEXT) {
            if (length != null) {
                column.setLength(length);
            } else {
                column.setLength(50);
            }
        }
        if (column.getType() == ETDataExtensionColumnType.DECIMAL) {
            if (precision != null) {
                column.setPrecision(precision);
            } else {
                column.setPrecision(18);
            }
            if (scale != null) {
                column.setScale(scale);
            } else {
                column.setScale(0);
            }
        }
        column.setIsPrimaryKey(isPrimaryKey);
        if (isPrimaryKey != null && isPrimaryKey) {
            column.setIsRequired(true);
        } else {
            column.setIsRequired(isRequired);
        }
        column.setDefaultValue(defaultValue);
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
        // XXX much of this is copied and pasted from ETClient.retrieve(filter, properties)
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

        // XXX much of this is copied and pasted from ETRestObject.retrieve

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
        // XXX optimize

        ETResponse<ETDataExtensionRow> response = select(filter);

        List<ETDataExtensionRow> rows = response.getObjects();
        for (ETDataExtensionRow row : rows) {
            for (String value : values) {
                ETFilter parsedFilter = ETFilter.parse(value);
                // XXX throw exception if operation not = ?
                row.setColumn(parsedFilter.getProperty(), parsedFilter.getValue());
            }
        }

        return update(rows.toArray(new ETDataExtensionRow[rows.size()]));
    }

    public ETResponse<ETDataExtensionRow> delete(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETClient client = getClient();

        // XXX much of this is copied and pasted from ETSoapObject.delete

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        if (rows == null || rows.length == 0) {
            return response;
        }

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP delete:
        //

        Soap soap = connection.getSoap();

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setOptions(new DeleteOptions());
        for (ETDataExtensionRow row : rows) {
            row.setClient(client);

            //
            // We hand construct this one, since all we need
            // to pass in is the primary key, and we pass it
            // in to DeleteRequest differently (in the Keys
            // property) than we received it from
            // RetrieveRequest (in the Properties property):
            //

            DataExtensionObject internalRow = new DataExtensionObject();
            DataExtensionObject.Keys keys = new DataExtensionObject.Keys();
            // XXX really only needs to set the primary
            // key but we don't have a reliable way
            // of knowing what that is yet (coming soon)
            for (String name : row.getColumnNames()) {
                APIProperty property = new APIProperty();
                property.setName(name);
                property.setValue(row.getColumn(name));
                keys.getKey().add(property);
            }
            internalRow.setName(name);
            internalRow.setKeys(keys);

            deleteRequest.getObjects().add(internalRow);
        }

        if (logger.isTraceEnabled()) {
            logger.trace("DeleteRequest:");
            logger.trace("  objects = {");
            for (APIObject object : deleteRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.delete...");

        DeleteResponse deleteResponse = soap.delete(deleteRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("DeleteResponse:");
            logger.trace("  requestId = " + deleteResponse.getRequestID());
            logger.trace("  overallStatus = " + deleteResponse.getOverallStatus());
            logger.trace("  results = {");
            for (DeleteResult result : deleteResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(deleteResponse.getRequestID());
        response.setResponseCode(deleteResponse.getOverallStatus());
        response.setResponseMessage(deleteResponse.getOverallStatus());
        for (DeleteResult deleteResult : deleteResponse.getResults()) {
            ETResult<ETDataExtensionRow> result = new ETResult<ETDataExtensionRow>();
            result.setResponseCode(deleteResult.getStatusCode());
            result.setResponseMessage(deleteResult.getStatusMessage());
            result.setErrorCode(deleteResult.getErrorCode());
            response.addResult(result);
        }

        return response;
    }

    public ETResponse<ETDataExtensionRow> delete(String filter)
        throws ETSdkException
    {
        // XXX optimize

        ETResponse<ETDataExtensionRow> response = select(filter);

        List<ETDataExtensionRow> rows = response.getObjects();

        return delete(rows.toArray(new ETDataExtensionRow[rows.size()]));
    }

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
        filter.setOperator(ETFilter.Operator.EQUALS);
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

    private String toQueryParameters(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case AND:
            stringBuilder.append(toQueryParameters(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameters(filter.getFilters().get(1)));
            break;
          case OR:
            stringBuilder.append(toQueryParameters(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("or");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameters(filter.getFilters().get(1)));
            break;
          case NOT:
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParameters(filter.getFilters().get(0)));
            break;
          case EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("eq");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          case NOT_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("ne");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          case LESS_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lt");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lte");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          case GREATER_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gt");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gte");
            stringBuilder.append("%20");
            stringBuilder.append(filter.getValue());
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }
}
