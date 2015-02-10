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

package com.exacttarget.fuelsdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.ETDataExtensionColumn.Type;
import com.exacttarget.fuelsdk.ETRestConnection.Response;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;

/**
 * An <code>ETDataExtension</code> object represents a data extension
 * in the Salesforce Marketing Cloud.
 */

@RestObject(path = "/data/v1/customobjectdata/key/{key}/rowset",
            primaryKey = "key",
            collection = "items",
            totalCount = "count")
@SoapObject(internalType = DataExtension.class, unretrievable = {
    "ID", "Fields"
})
public class ETDataExtension extends ETSoapObject {
    private static Logger logger = Logger.getLogger(ETDataExtension.class);

    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
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
     * Use <code>getKey()</code>.
     */
    @Deprecated
    public String getCustomerKey() {
        return getKey();
    }

    /**
     * @deprecated
     * Use <code>setKey()</code>.
     */
    @Deprecated
    public void setCustomerKey(String customerKey) {
        setKey(customerKey);
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

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension)
        throws ETSdkException
    {
        // new String[0] = empty properties
        return select(client, dataExtension, (ETFilter) null, new String[0]);
    }

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension,
                                                        ETFilter filter,
                                                        String... columns)
        throws ETSdkException
    {
        String name = null;

        // XXX hack
        for (String column : columns) {
            if (column.length() >= 8 && column.substring(0, 8).toLowerCase().equals("order by")) {
                return select(client, dataExtension, filter, null, null, columns);
            }
        }

        ETFilter f = ETFilter.parse(dataExtension);
        if (f.getProperty().toLowerCase().equals("key")
                && f.getOperator() == ETFilter.Operator.EQUALS) {
            name = f.getValue();
        } else if (f.getProperty().toLowerCase().equals("name")
                && f.getOperator() == ETFilter.Operator.EQUALS) {
            name = f.getValue();
        } else {
            throw new ETSdkException("invalid data extension filter string");
        }

        // XXX can we do something more intelligent when
        // columns.length == 0 in the static select call?

        if (columns.length == 0) {
            throw new ETSdkException("invalid column array");
        }

        return ETSoapObject.retrieve(client,
                                     "DataExtensionObject[" + name + "]",
                                     filter,
                                     null,
                                     null,
                                     ETDataExtensionRow.class,
                                     columns);
    }

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension,
                                                        String filter,
                                                        String... columns)
        throws ETSdkException
    {
        // see also: ETClient.retrieve(type, filter, properties)
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
        return select(client, dataExtension, f, c);
    }

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension,
                                                        Integer page,
                                                        Integer pageSize,
                                                        String... columns)
        throws ETSdkException
    {
        return select(client, dataExtension, (ETFilter) null, page, pageSize, columns);
    }

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension,
                                                        ETFilter filter,
                                                        Integer page,
                                                        Integer pageSize,
                                                        String... columns)
        throws ETSdkException
    {
        String path = "/data/v1/customobjectdata";

        ETFilter f = ETFilter.parse(dataExtension);
        if (f.getProperty().toLowerCase().equals("id")
                && f.getOperator() == ETFilter.Operator.EQUALS) {
            path += "/" + f.getValue() + "/rowset";
        } else if (f.getProperty().toLowerCase().equals("key")
                && f.getOperator() == ETFilter.Operator.EQUALS) {
            path += "/key/" + f.getValue() + "/rowset";
        } else {
            throw new ETSdkException("invalid data extension filter string");
        }

        Response r = ETRestObject.retrieve(client,
                                           path,
                                           null,
                                           filter,
                                           page,
                                           pageSize,
                                           ETRestObject.class,
                                           columns);

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        // XXX still too much duplicate code here

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

            JsonElement elements = jsonObject.get("items");
            if (elements != null) {
                for (JsonElement element : elements.getAsJsonArray()) {
                    JsonObject object = element.getAsJsonObject();
                    ETDataExtensionRow row = new ETDataExtensionRow();
                    JsonObject keys = object.get("keys").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : keys.entrySet()) {
                        row.setColumn(entry.getKey(), entry.getValue().getAsString(), false);
                    }
                    JsonObject values = object.get("values").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : values.entrySet()) {
                        row.setColumn(entry.getKey(), entry.getValue().getAsString(), false);
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

    public static ETResponse<ETDataExtensionRow> select(ETClient client,
                                                        String dataExtension,
                                                        String filter,
                                                        Integer page,
                                                        Integer pageSize,
                                                        String... columns)
        throws ETSdkException
    {
        return select(client, dataExtension, ETFilter.parse(filter), page, pageSize, columns);
    }

    public ETResponse<ETDataExtensionRow> select()
        throws ETSdkException
    {
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      (ETFilter) null,
                                      getColumnNames());
    }

    public ETResponse<ETDataExtensionRow> select(ETFilter filter,
                                                 String... columns)
        throws ETSdkException
    {
        if (columns.length == 0) {
            columns = getColumnNames();
        }
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      filter,
                                      columns);
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 String... columns)
        throws ETSdkException
    {
        // see also: ETClient.retrieve(type, filter, properties)
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
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      f,
                                      c);
    }

    public ETResponse<ETDataExtensionRow> select(Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        if (columns.length == 0) {
            columns = getColumnNames();
        }
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      page,
                                      pageSize,
                                      columns);
    }

    public ETResponse<ETDataExtensionRow> select(ETFilter filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        if (columns.length == 0) {
            columns = getColumnNames();
        }
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      filter,
                                      page,
                                      pageSize,
                                      columns);
    }

    public ETResponse<ETDataExtensionRow> select(String filter,
                                                 Integer page,
                                                 Integer pageSize,
                                                 String... columns)
        throws ETSdkException
    {
        if (columns.length == 0) {
            columns = getColumnNames();
        }
        return ETDataExtension.select(getClient(),
                                      "key=" + getKey(),
                                      filter,
                                      page,
                                      pageSize,
                                      columns);
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

        return super.create(getClient(), rows);
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

        return super.update(getClient(), rows);
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
            // to pass in are the primary keys, and we pass them
            // in to DeleteRequest differently (in the Keys
            // property) than we received it from
            // RetrieveRequest (in the Properties property):
            //

            DataExtensionObject internalRow = new DataExtensionObject();
            DataExtensionObject.Keys keys = new DataExtensionObject.Keys();
            hydrate(); // make sure we've retrieved all columns
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

        // call delete method that operates on internal objects
        return super.delete(getClient(), internalRows, true);
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

    public ETResponse<ETDataExtensionRow> delete(String filter)
        throws ETSdkException
    {
        List<ETDataExtensionRow> rows = getMatchingRows(filter);
        return delete(rows);
    }

    public ETResponse<ETDataExtensionRow> export(ETFilter filter,
                                                 String fileName)
        throws ETSdkException
    {
        return export(filter, fileName, true, false, null);
    }

    public ETResponse<ETDataExtensionRow> export(ETFilter filter,
                                                 String fileName,
                                                 Boolean includeHeader,
                                                 Boolean compress,
                                                 Integer encryptionKey)
        throws ETSdkException
    {
        ETClient client = getClient();

        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        String path = "/data/v1/customobjectdata/export";

        StringBuilder stringBuilder = new StringBuilder(path);

        if (filter != null) {
            stringBuilder.append("?" + ETRestObject.toFilterString(filter));
        }

        path = stringBuilder.toString();

        ETRestConnection connection = client.getRestConnection();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customerKey", getKey());
        jsonObject.addProperty("fileName", fileName);
        if (includeHeader != null) {
            jsonObject.addProperty("includeHeader", includeHeader);
        }
        if (compress != null) {
            jsonObject.addProperty("compress", compress);
        }
        if (encryptionKey != null) {
            jsonObject.addProperty("encryptionKey", encryptionKey);
        }
        Gson gson = connection.getGson();
        String json = gson.toJson(jsonObject);

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

    public ETResponse<ETDataExtensionRow> export(String filter,
                                                 String fileName)
        throws ETSdkException
    {
        return export(ETFilter.parse(filter), fileName);
    }

    public ETResponse<ETDataExtensionRow> export(String filter,
                                                 String fileName,
                                                 Boolean includeHeader,
                                                 Boolean compress,
                                                 Integer encryptionKey)
        throws ETSdkException
    {
        return export(ETFilter.parse(filter),
                      fileName,
                      includeHeader,
                      compress,
                      encryptionKey);
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

    private List<ETDataExtensionRow> getMatchingRows(String filter)
        throws ETSdkException
    {
        List<ETDataExtensionRow> rows = new ArrayList<ETDataExtensionRow>();

        hydrate(); // make sure we've retrieved all columns

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

    private String[] getColumnNames()
        throws ETSdkException
    {
        hydrate(); // make sure we've retrieved all columns
        String c[] = new String[columns.size()];
        int i = 0;
        for (ETDataExtensionColumn column : columns) {
            c[i++] = column.getName();
        }
        return c;
    }
}
