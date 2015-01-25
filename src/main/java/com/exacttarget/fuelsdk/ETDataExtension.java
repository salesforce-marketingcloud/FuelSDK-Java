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
import java.net.URLEncoder;
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
import com.exacttarget.fuelsdk.ETDataExtensionColumn.Type;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;

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

    private boolean isHydrated = false;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public List<ETDataExtensionColumn> getColumns() {
        return columns;
    }

    public ETDataExtensionColumn getColumn(String name) {
        for (ETDataExtensionColumn column : columns) {
            if (column.getName().equals(name.toLowerCase())) {
                return column;
            }
        }
        return null;
    }

    public void addColumn(String name) {
        addColumn(name, null, null, null, null, null, null, null);
    }

    public void addColumn(String name, Type type) {
        addColumn(name, type, null, null, null, null, null, null);
    }

    public void addColumn(String name, Boolean isPrimaryKey) {
        addColumn(name, null, null, null, null, isPrimaryKey, null, null);
    }

    public void addColumn(String name, Type type, Boolean isPrimaryKey) {
        addColumn(name, type, null, null, null, isPrimaryKey, null, null);
    }

    public void addColumn(String name,
                          Type type,
                          Integer length,
                          Integer precision,
                          Integer scale,
                          Boolean isPrimaryKey,
                          Boolean isRequired,
                          String defaultValue)
    {
        ETDataExtensionColumn column = new ETDataExtensionColumn();
        column.setName(name.toLowerCase());
        if (type != null) {
            column.setType(type);
        } else {
            // default is text type
            column.setType(Type.TEXT);
        }
        // mimics the UI default values for length, precision, and scale
        if (column.getType() == Type.TEXT) {
            if (length != null) {
                column.setLength(length);
            } else {
                column.setLength(50);
            }
        }
        if (column.getType() == Type.DECIMAL) {
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

    public ETResponse<ETDataExtensionRow> insert(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        return insert(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> insert(List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }
        }

        return getClient().create(rows);
    }

    public ETResponse<ETDataExtensionRow> select()
        throws ETSdkException
    {
        // new String[0] = empty properties
        return select((ETFilter) null, null, null, new String[0]);
    }

    public ETResponse<ETDataExtensionRow> select(ETFilter filter,
                                                 String... columns)
        throws ETSdkException
    {
        return select(filter, null, null, columns);
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 String... columns)
        throws ETSdkException
    {
        // see also: ETClient.retrieve(type, filter, properties)
        // XXX it would be very nice if this could use this code
        ETFilter f = null;
        String[] c = columns;
        try {
            f = ETFilter.parse(filter);
        } catch (ETSdkException ex) {
            if (ex.getCause() instanceof ParseException) {
                //
                // The filter argument is actually a column. This is a bit
                // of a hack, but this method needs to handle the case of
                // both a filtered and a filterless retrieve with columns,
                // as having one method for each results in ambiguous methods.
                //

                c = new String[columns.length + 1];
                c[0] = filter.toLowerCase();
                int i = 1;
                for (String property : columns) {
                    c[i++] = property.toLowerCase();
                }
            } else {
                throw ex;
            }
        }
        if (c.length == 0) {
            c = getColumnNames();
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

    public ETResponse<ETDataExtensionRow> select(ETFilter filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        ETClient client = getClient();

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        String path = "/data/v1/customobjectdata/key/" + getKey() + "/rowset";

        StringBuilder stringBuilder = new StringBuilder(path);

        boolean firstQueryParameter = true;

        String[] properties = new String[0]; // empty by default

        if (columns.length > 0) {
            //
            // Only request those columns specified:
            //

            boolean firstField = true;
            for (String column : columns) {
                if (column.length() >= 8 &&
                    column.substring(0, 8).toLowerCase().equals("order by")) {
                    // not actually a column, an order by string
                    properties = new String[1];
                    properties[0] = columns[columns.length - 1];
                } else {
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
        }

        if (filter != null) {
            if (firstQueryParameter) {
                firstQueryParameter = false;
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append("$filter=");
            stringBuilder.append(toQueryParams(filter));
        }

        path = stringBuilder.toString();

        ETRestConnection.Response r = ETRestObject.retrieve(client, path, page, pageSize, properties);

        response.setRequestId(r.getRequestId());
        if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
            response.setStatus(ETResult.Status.OK);
        } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(r.getResponseCode().toString());
        response.setResponseMessage(r.getResponseMessage());

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(r.getResponsePayload()).getAsJsonObject();

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

            JsonElement items = jsonObject.get("items");
            if (items != null) {
                JsonArray collection = items.getAsJsonArray();

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
        }

        return response;
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        return select(ETFilter.parse(filter), page, pageSize, columns);
    }

    public ETResponse<ETDataExtensionRow> update(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        return update(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> update(List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }
        }

        return getClient().update(rows);
    }

    private List<ETDataExtensionRow> getMatchingRows(String filter)
        throws ETSdkException
    {
        List<ETDataExtensionRow> rows = new ArrayList<ETDataExtensionRow>();

        hydrate();

        //
        // Only retrieve primary key columns:
        //

        List<String> primaryKeyColumnNames = new ArrayList<String>();
        for (ETDataExtensionColumn column : columns) {
            if (column.getIsPrimaryKey()) {
                primaryKeyColumnNames.add(column.getName());
            }
        }

        int page = 1;
        int page_size = 2500;

        ETResponse<ETDataExtensionRow> response = null;
        do {
            response = select(filter, page++, page_size, primaryKeyColumnNames.toArray(new String[0]));
            rows.addAll(response.getObjects());
        } while (response.hasMoreResults() == true);

        return rows;
    }

    public ETResponse<ETDataExtensionRow> update(String filter, String... values)
        throws ETSdkException
    {
        List<ETDataExtensionRow> rows = getMatchingRows(filter);
        for (ETDataExtensionRow row : rows) {
            for (String value : values) {
                ETFilter parsedFilter = ETFilter.parse(value);
                if (parsedFilter.getOperator() != ETFilter.Operator.EQUALS) {
                    throw new ETSdkException("unsupported operator: " + parsedFilter.getOperator());
                }
                row.setColumn(parsedFilter.getProperty(), parsedFilter.getValue());
            }
        }
        return update(rows);
    }

    public ETResponse<ETDataExtensionRow> delete(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        return delete(Arrays.asList(rows));
    }

    public ETResponse<ETDataExtensionRow> delete(List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        List<APIObject> internalRows = new ArrayList<APIObject>();

        for (ETDataExtensionRow row : rows) {
            //
            // We hand construct this one, since all we need
            // to pass in is the primary keys, and we pass them
            // in to DeleteRequest differently (in the Keys
            // property) than we received it from
            // RetrieveRequest (in the Properties property):
            //

            DataExtensionObject internalRow = new DataExtensionObject();
            DataExtensionObject.Keys keys = new DataExtensionObject.Keys();
            hydrate();
            for (ETDataExtensionColumn column : columns) {
                if (column.getIsPrimaryKey()) {
                    APIProperty property = new APIProperty();
                    property.setName(column.getName());
                    property.setValue(row.getColumn(property.getName()));
                    keys.getKey().add(property);
                }
            }
            internalRow.setName(name);
            internalRow.setKeys(keys);

            internalRows.add(internalRow);
        }

        return super.delete(getClient(), internalRows, true);
    }

    public ETResponse<ETDataExtensionRow> delete(String filter)
        throws ETSdkException
    {
        List<ETDataExtensionRow> rows = getMatchingRows(filter);
        return delete(rows);
    }

    public ETResponse<ETDataExtensionRow> export(String filter,
                                                 String file)
        throws ETSdkException
    {
        return export(ETFilter.parse(filter), file);
    }

    public ETResponse<ETDataExtensionRow> export(ETFilter filter,
                                                 String fileName)
        throws ETSdkException
    {
        ETClient client = getClient();

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        String path = "/data/v1/customobjectdata/export";

        StringBuilder stringBuilder = new StringBuilder(path);

        if (filter != null) {
            stringBuilder.append("?filter=");
            stringBuilder.append(toQueryParams(filter));
        }

        path = stringBuilder.toString();

        ETRestConnection connection = client.getRestConnection();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customerKey", getKey());
        jsonObject.addProperty("fileName", fileName);
        Gson gson = connection.getGson();
        String json = gson.toJson(jsonObject);

        // XXX handle return value?
        ETRestConnection.Response r = connection.post(path, json);

        response.setRequestId(r.getRequestId());
        if (r.getResponseCode() >= 200 && r.getResponseCode() <= 299) {
            response.setStatus(ETResult.Status.OK);
        } else if (r.getResponseCode() >= 400 && r.getResponseCode() <= 599) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(r.getResponseCode().toString());
        response.setResponseMessage(r.getResponseMessage());

        return response;
    }

    public void hydrate()
        throws ETSdkException
    {
        if (isHydrated) {
            return;
        }

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

        columns = response.getObjects();

        // XXX deal with partially loaded DataExtension objects too

        isHydrated = true;
    }

    public static String toQueryParams(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();
        ETFilter.Operator operator = filter.getOperator();
        switch (operator) {
          case AND:
            stringBuilder.append(toQueryParams(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getFilters().get(1)));
            break;
          case OR:
            stringBuilder.append(toQueryParams(filter.getFilters().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("or");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getFilters().get(1)));
            break;
          case NOT:
            stringBuilder.append("not");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getFilters().get(0)));
            break;
          case EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("eq");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
            break;
          case NOT_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("neq");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
            break;
          case LESS_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lt");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("lte");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
            break;
          case GREATER_THAN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gt");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("gte");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue()));
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
                stringBuilder.append(toQueryParams(value));
            }
            stringBuilder.append(")");
            break;
          case BETWEEN:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("between");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValues().get(0)));
            stringBuilder.append("%20");
            stringBuilder.append("and");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValues().get(1)));
            break;
          case LIKE:
            stringBuilder.append(filter.getProperty());
            stringBuilder.append("%20");
            stringBuilder.append("like");
            stringBuilder.append("%20");
            stringBuilder.append(toQueryParams(filter.getValue(), true));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }

    private static String toQueryParams(String value)
        throws ETSdkException
    {
        return toQueryParams(value, false);
    }

    private static String toQueryParams(String value, boolean forceQuotes)
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

    private String[] getColumnNames()
        throws ETSdkException
    {
        hydrate();
        String c[] = new String[columns.size()];
        int i = 0;
        for (ETDataExtensionColumn column : columns) {
            c[i++] = column.getName();
        }
        return c;
    }
}
