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

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateResult;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.DeleteResult;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;
import com.exacttarget.fuelsdk.internal.UpdateResult;

public abstract class ETCrudServiceImpl<T extends ETSoapObject>
    extends ETGetServiceImpl<T> implements ETCrudService
{
    private static Logger logger = Logger.getLogger(ETCrudServiceImpl.class);

    @SuppressWarnings("unchecked")
    public ETResponse<Integer> post(ETClient client, T object)
        throws ETSdkException
    {
        return post(client, Arrays.asList(object));
    }

    public ETResponse<Integer> post(ETClient client, List<T> objects)
        throws ETSdkException
    {
        ETResponse<Integer> response = new ETResponse<Integer>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP create:
        //

        Soap soap = client.getSOAPConnection().getSoap();

        CreateRequest createRequest = new CreateRequest();
        createRequest.setOptions(new CreateOptions());
        for (T object : objects) {
            createRequest.getObjects().add(object.toInternal());
        }

        if (logger.isTraceEnabled()) {
            logger.trace("CreateRequest:");
            logger.trace("  objects = {");
            for (APIObject object : createRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.create...");

        CreateResponse createResponse = soap.create(createRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("CreateResponse:");
            logger.trace("  requestId = " + createResponse.getRequestID());
            logger.trace("  overallStatus = " + createResponse.getOverallStatus());
            logger.trace("  results = {");
            for (CreateResult result : createResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(createResponse.getRequestID());
        response.setStatus(createResponse.getOverallStatus().equals("OK"));
        response.setMessage(createResponse.getOverallStatus());
        for (CreateResult createResult : createResponse.getResults()) {
            response.getResults().add(createResult.getNewID());
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    public ETResponse<T> patch(ETClient client, T object)
        throws ETSdkException
    {
        return patch(client, Arrays.asList(object));
    }

    public ETResponse<T> patch(ETClient client, List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP update:
        //

        Soap soap = client.getSOAPConnection().getSoap();

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setOptions(new UpdateOptions());
        for (T object : objects) {
            updateRequest.getObjects().add(object.toInternal());
        }

        if (logger.isTraceEnabled()) {
            logger.trace("UpdateRequest:");
            logger.trace("  objects = {");
            for (APIObject object : updateRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.update...");

        UpdateResponse updateResponse = soap.update(updateRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("UpdateResponse:");
            logger.trace("  requestId = " + updateResponse.getRequestID());
            logger.trace("  overallStatus = " + updateResponse.getOverallStatus());
            logger.trace("  results = {");
            for (UpdateResult result : updateResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(updateResponse.getRequestID());
        response.setStatus(updateResponse.getOverallStatus().equals("OK"));
        response.setMessage(updateResponse.getOverallStatus());
        for (UpdateResult createResult : updateResponse.getResults()) {
            // XXX
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    public ETResponse<T> delete(ETClient client, T object)
        throws ETSdkException
    {
        return delete(client, Arrays.asList(object));
    }

    public ETResponse<T> delete(ETClient client, List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP delete:
        //

        Soap soap = client.getSOAPConnection().getSoap();

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setOptions(new DeleteOptions());
        for (T object : objects) {
            deleteRequest.getObjects().add(object.toInternal());
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
        response.setStatus(deleteResponse.getOverallStatus().equals("OK"));
        response.setMessage(deleteResponse.getOverallStatus());
        for (DeleteResult deleteResult : deleteResponse.getResults()) {
            // XXX
        }

        return response;
    }
}
