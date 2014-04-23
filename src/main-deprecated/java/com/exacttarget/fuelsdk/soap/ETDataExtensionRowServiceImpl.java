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

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionRow;
import com.exacttarget.fuelsdk.ETDataExtensionRowService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResponseStatus;
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
    private static Logger logger = Logger.getLogger(ETDataExtensionRowServiceImpl.class);

    public ETResponse<ETDataExtensionRow> get(ETClient client, String name, List<String> columns)
        throws ETSdkException
    {
        return get(client, name, columns, null, null);
    }

    public ETResponse<ETDataExtensionRow> get(ETClient client, String name, List<String> columns, ETFilter filter)
        throws ETSdkException
    {
        return get(client, name, columns, filter, null);
    }

    public ETResponse<ETDataExtensionRow> get(ETClient client, String continueRequestId)
        throws ETSdkException
    {
        return get(client, null, null, null, continueRequestId);

    }

    private ETResponse<ETDataExtensionRow> get(ETClient client, String name, List<String> columns, ETFilter filter, String continueRequestId)
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = new ETResponse<ETDataExtensionRow>();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP retrieve:
        //

        Soap soap = client.getSOAPConnection().getSoap();

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        if (name != null) {
            retrieveRequest.setObjectType("DataExtensionObject[" + name + "]");
        }
        if (columns != null) {
            retrieveRequest.getProperties().addAll(columns);
        }
        if (filter != null) {
            retrieveRequest.setFilter(convertFilterPart(filter));
        }
        if (continueRequestId != null) {
            retrieveRequest.setContinueRequest(continueRequestId);
        }

        if (logger.isTraceEnabled()) {
            logger.trace("RetrieveRequest:");
            logger.trace("  objectType = " + retrieveRequest.getObjectType());
            String line = null;
            for (String property : retrieveRequest.getProperties()) {
                if (line == null) {
                    line = "  properties = { " + property;
                } else {
                    line += ", " + property;
                }
            }
            logger.trace(line + " }");
            // XXX print filter too
        }

        logger.trace("calling soap.retrieve...");

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        if (logger.isTraceEnabled()) {
            logger.trace("RetrieveResponseMsg:");
            logger.trace("  requestId = " + retrieveResponseMsg.getRequestID());
            logger.trace("  overallStatus = " + retrieveResponseMsg.getOverallStatus());
            logger.trace("  results = {");
            for (APIObject result : retrieveResponseMsg.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(retrieveResponseMsg.getRequestID());
        response.setStatusMessage(retrieveResponseMsg.getOverallStatus());
        for (APIObject internalObject : retrieveResponseMsg.getResults()) {
            //
            // Allocate a new (external) object:
            //

            ETDataExtensionRow row = new ETDataExtensionRow();

            //
            // Convert from internal representation:
            //

            row.fromInternal(internalObject);

            //
            // Add result to the list of results:
            //

            response.getResults().add(row);
        }

        if (retrieveResponseMsg.getOverallStatus().equals("MoreDataAvailable")) {
            response.setMoreResults(true);
        }

        return response;
    }

    public ETResponse<ETResponseStatus> post(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.post(client, row);
    }

    public ETResponse<ETResponseStatus> post(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.post(client, rows);
    }

    public ETResponse<ETResponseStatus> patch(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.patch(client, row);
    }

    public ETResponse<ETResponseStatus> patch(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.patch(client, rows);
    }

    public ETResponse<ETResponseStatus> delete(ETClient client, ETDataExtensionRow row)
        throws ETSdkException
    {
        return super.delete(client, row);
    }

    public ETResponse<ETResponseStatus> delete(ETClient client, List<ETDataExtensionRow> rows)
        throws ETSdkException
    {
        return super.delete(client, rows);
    }
}
