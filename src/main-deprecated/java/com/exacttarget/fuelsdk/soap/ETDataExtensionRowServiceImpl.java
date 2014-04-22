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

package com.exacttarget.fuelsdk.soap;

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETDataExtensionRowService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;

/**
 * @deprecated
 * For information on how to interact with data extensions, please see
 * {@link com.exacttarget.fuelsdk.ETDataExtension}.
 */
@Deprecated
public class ETDataExtensionRowServiceImpl extends ETCrudServiceImpl<ETDataExtensionRow>
    implements ETDataExtensionRowService
{
    public ETResponse<ETDataExtensionRow> get(ETClient client, String name, List<String> columns)
        throws ETSdkException
    {
        return get(client, name, columns, null);
    }

    public ETResponse<ETDataExtensionRow> get(ETClient client, String name, List<String> columns, ETFilter filter)
        throws ETSdkException
    {
        // XXX cleanup

        ETResponse<ETDataExtensionRow> response = new ETResponseImpl<ETDataExtensionRow>();

        Soap soap = client.getSOAPConnection().getSoap();

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType("DataExtensionObject[" + name + "]");
        retrieveRequest.getProperties().addAll(columns);
        if (filter != null) {
            retrieveRequest.setFilter(convertFilterPart(filter));
        }

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        response.setRequestId(retrieveResponseMsg.getRequestID());
        response.setStatusMessage(retrieveResponseMsg.getOverallStatus());
        for (APIObject internalObject : retrieveResponseMsg.getResults()) {
            ETResponse<ETDataExtensionRow>.Result result = response.new Result();
            ETDataExtensionRow row = new ETDataExtensionRow();
            row.fromInternal(internalObject);
            result.setObject(row);
            response.addResult(result);
        }

        return response;
    }

    public ETResponse<ETDataExtensionRow> post(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.post(client, row);
    }

    public ETResponse<ETDataExtensionRow> post(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.post(client, rows);
    }

    public ETResponse<ETDataExtensionRow> patch(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.patch(client, row);
    }

    public ETResponse<ETDataExtensionRow> patch(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.patch(client, rows);
    }

    public ETResponse<ETDataExtensionRow> delete(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.delete(client, row);
    }

    public ETResponse<ETDataExtensionRow> delete(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.delete(client, rows);
    }
}
