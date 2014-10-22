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

    public List<ETResult> insert(ETDataExtensionRow... rows)
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

        ETResponse<ETResult> response = client.create(Arrays.asList(rows));

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public List<ETDataExtensionRow> select()
        throws ETSdkException
    {
        return select(null);
    }

    public List<ETDataExtensionRow> select(String filter, String... columns)
        throws ETSdkException
    {
        // XXX copied and pasted from ETClient.retrieve(filter, properties)
        ETFilter f = null;
        String[] c = columns;
        if (filter != null) {
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
        }

        if (c.length == 0) {
            //
            // If columns aren't specified retrieve all columns:
            //

            // XXX this adds another API call.. is there a native way?

            List<ETDataExtensionColumn> dataExtensionColumns = retrieveColumns();
            c = new String[dataExtensionColumns.size()];
            int i = 0;
            for (ETDataExtensionColumn column : dataExtensionColumns) {
                c[i++] = column.getName();
            }
        }

        ETClient client = getClient();

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        Soap soap = connection.getSoap();

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType("DataExtensionObject[" + name + "]");
        retrieveRequest.getProperties().addAll(Arrays.asList(c));
        if (f != null) {
            retrieveRequest.setFilter(f.getSoapFilter());
        }
//        if (continueRequestId != null) {
//            retrieveRequest.setContinueRequest(continueRequestId);
//        }

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        // XXX check for errors and throw the appropriate exception

        List<ETDataExtensionRow> rows = new ArrayList<ETDataExtensionRow>();

        for (APIObject internalObject : retrieveResponseMsg.getResults()) {
            //
            // Allocate a new (external) object:
            //

            ETDataExtensionRow row = new ETDataExtensionRow();

            row.setClient(client);

            //
            // Convert from internal representation:
            //

            row.fromInternal(internalObject);

            //
            // Add result to the list of results:
            //

            rows.add(row);
        }

        return rows;
    }

    public List<ETResult> update(ETDataExtensionRow... rows)
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

        ETResponse<ETResult> response = client.update(Arrays.asList(rows));

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public List<ETResult> delete(ETDataExtensionRow... rows)
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

        ETResponse<ETResult> response = client.delete(Arrays.asList(rows));

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
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

        return response.getResults();
    }
}
