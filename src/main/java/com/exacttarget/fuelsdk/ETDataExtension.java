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

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.soap.ETDataExtensionColumnServiceImpl;
import com.exacttarget.fuelsdk.soap.ETDataExtensionRowServiceImpl;
import com.exacttarget.fuelsdk.soap.ETDataExtensionServiceImpl;

/**
 * The <code>ETDataExtension</code> class represents an ExactTarget
 * data extension.
 */
@InternalSoapType(type = DataExtension.class, ignoredFields = { "ID", "Fields" })
public class ETDataExtension extends ETSoapObject {
    private ETClient client = null;

    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "categoryID")
    private Long categoryID = null;
    @InternalSoapField(name = "fields")
    private List<ETDataExtensionColumn> columns = new ArrayList<ETDataExtensionColumn>();
    @InternalSoapField(name = "dataRetentionPeriodLength", ignoreOnPatch = true)
    private Integer dataRetentionPeriodLength = null;
    @InternalSoapField(name = "dataRetentionPeriodUnitOfMeasure", ignoreOnPatch = true)
    private Integer dataRetentionPeriodUnitOfMeasure = null;
    @InternalSoapField(name = "deleteAtEndOfRetentionPeriod", ignoreOnPatch = true)
    private Boolean deleteAtEndOfRetentionPeriod = null;
    @InternalSoapField(name = "isSendable")
    private Boolean isSendable = null;
    @InternalSoapField(name = "isTestable")
    private Boolean isTestable = null;
    @InternalSoapField(name = "resetRetentionPeriodOnImport", ignoreOnPatch = true)
    private Boolean resetRetentionPeriodOnImport = null;
    @InternalSoapField(name = "retainUntil", ignoreOnPatch = true)
    private String retainUntil = null;
    @InternalSoapField(name = "rowBasedRetention", ignoreOnPatch = true)
    private Boolean rowBasedRetention = null;
    @InternalSoapField(name = "status")
    private String status = null;

    public ETDataExtension() {}

    public ETClient getClient() {
        return client;
    }

    public void setClient(ETClient client) {
        this.client = client;
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

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public List<ETDataExtensionColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ETDataExtensionColumn> columns) {
        this.columns = columns;
    }

    public void addColumn(ETDataExtensionColumn column) {
        this.columns.add(column);
    }

    public Integer getDataRetentionPeriodLength() {
        return dataRetentionPeriodLength;
    }

    public void setDataRetentionPeriodLength(Integer dataRetentionPeriodLength) {
        this.dataRetentionPeriodLength = dataRetentionPeriodLength;
    }

    public Integer getDataRetentionPeriodUnitOfMeasure() {
        return dataRetentionPeriodUnitOfMeasure;
    }

    public void setDataRetentionPeriodUnitOfMeasure(
            Integer dataRetentionPeriodUnitOfMeasure) {
        this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
    }

    public Boolean getDeleteAtEndOfRetentionPeriod() {
        return deleteAtEndOfRetentionPeriod;
    }

    public void setDeleteAtEndOfRetentionPeriod(Boolean deleteAtEndOfRetentionPeriod) {
        this.deleteAtEndOfRetentionPeriod = deleteAtEndOfRetentionPeriod;
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

    public Boolean getResetRetentionPeriodOnImport() {
        return resetRetentionPeriodOnImport;
    }

    public void setResetRetentionPeriodOnImport(Boolean resetRetentionPeriodOnImport) {
        this.resetRetentionPeriodOnImport = resetRetentionPeriodOnImport;
    }

    public String getRetainUntil() {
        return retainUntil;
    }

    public void setRetainUntil(String retainUntil) {
        this.retainUntil = retainUntil;
    }

    public Boolean getRowBasedRetention() {
        return rowBasedRetention;
    }

    public void setRowBasedRetention(Boolean rowBasedRetention) {
        this.rowBasedRetention = rowBasedRetention;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ETResult> delete(ETFilter filter)
        throws ETSdkException
    {
        ETDataExtensionRowService service = new ETDataExtensionRowServiceImpl();

        List<ETDataExtensionRow> rows = select(filter);

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }

            assert row.getName() == name; // something's wrong...
        }

        ETResponse<ETResult> response = service.delete(client, rows);

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public List<ETResult> insert(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETDataExtensionRowService service = new ETDataExtensionRowServiceImpl();

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }

            assert row.getName() == name; // something's wrong...
        }

        ETResponse<ETResult> response = service.post(client, Arrays.asList(rows));

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public List<ETDataExtensionRow> select(String... columns)
        throws ETSdkException
    {
        return select(null, columns);
    }

    // XXX hack
    private String requestId = null;
    private boolean moreResults = false;

    public List<ETDataExtensionRow> select(ETFilter filter, String... columns)
        throws ETSdkException
    {
        ETDataExtensionRowService service = new ETDataExtensionRowServiceImpl();

        if (columns.length == 0) {
            //
            // If columns aren't specified retrieve all columns:
            //

            // XXX this adds another API call.. is there a native way?

            List<ETDataExtensionColumn> dataExtensionColumns = retrieveColumns();
            columns = new String[dataExtensionColumns.size()];
            int i = 0;
            for (ETDataExtensionColumn column : dataExtensionColumns) {
                columns[i++] = column.getName();
            }
        }

        ETResponse<ETDataExtensionRow> response
            = service.get(client, name, Arrays.asList(columns), filter);

        // XXX hack
        if (response.hasMoreResults()) {
            requestId = response.getRequestId();
            moreResults = true;
        } else {
            requestId = null;
            moreResults = false;
        }

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    // XXX hack
    public List<ETDataExtensionRow> selectContinueRequest()
        throws ETSdkException
    {
        ETDataExtensionRowService service = new ETDataExtensionRowServiceImpl();
        ETResponse<ETDataExtensionRow> response
            = service.get(client, requestId);

        // XXX hack
        if (response.hasMoreResults()) {
            requestId = response.getRequestId();
            moreResults = true;
        } else {
            requestId = null;
            moreResults = false;
        }

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public boolean selectHasMoreResults() {
        return moreResults;
    }

    public List<ETResult> update(ETDataExtensionRow... rows)
        throws ETSdkException
    {
        ETDataExtensionRowService service = new ETDataExtensionRowServiceImpl();

        for (ETDataExtensionRow row : rows) {
            //
            // Set the data extension name if it isn't already set:
            //

            if (row.getName() == null) {
                row.setName(name);
            }

            assert row.getName() == name; // something's wrong...
        }

        ETResponse<ETResult> response = service.patch(client, Arrays.asList(rows));

        // XXX check for errors and throw the appropriate exception

        return response.getResults();
    }

    public List<ETDataExtensionColumn> retrieveColumns()
        throws ETSdkException
    {
         ETDataExtensionColumnService service = new ETDataExtensionColumnServiceImpl();
         ETFilter filter = new ETSimpleFilter("DataExtension.CustomerKey",
                                              ETFilterOperators.EQUALS,
                                              getCustomerKey());
         ETResponse<ETDataExtensionColumn> response = service.get(client, filter);
         // XXX check for errors and throw the appropriate exception
         return response.getResults();
    }
}
