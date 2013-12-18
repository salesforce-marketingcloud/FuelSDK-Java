//
// ETDataExtensionRowServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.List;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETDataExtensionRowService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.FilterPart;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.model.ETDataExtensionRow;
import com.exacttarget.fuelsdk.model.converter.ObjectConverter;

public class ETDataExtensionRowServiceImpl extends ETCrudServiceImpl<ETDataExtensionRow> implements
		ETDataExtensionRowService {


	public ETResponse<ETDataExtensionRow> post(ETClient client,
			ETDataExtensionRow row) throws ETSdkException {
		
		return super.post(client, row);
	}

	public ETResponse<ETDataExtensionRow> patch(ETClient client,
			ETDataExtensionRow row) throws ETSdkException {
		
		return super.patch(client, row);
	}

	public ETResponse<ETDataExtensionRow> delete(ETClient client,
			ETDataExtensionRow row) throws ETSdkException {
		
		return super.delete(client, row);
	}
	
	public ETResponse<ETDataExtensionRow> post(ETClient client,
			List<ETDataExtensionRow> rows) throws ETSdkException {
		
		return super.post(client, rows);
	}

	public ETResponse<ETDataExtensionRow> patch(ETClient client,
			List<ETDataExtensionRow> rows) throws ETSdkException {
		
		return super.patch(client, rows);
	}

	public ETResponse<ETDataExtensionRow> delete(ETClient client,
			List<ETDataExtensionRow> rows) throws ETSdkException {
		
		return super.delete(client, rows);
	}

	public ETResponse<ETDataExtensionRow> get(ETClient client, String dataExtensionName, List<String> columns)
			throws ETSdkException {
		
		return this.get(client, dataExtensionName, columns, null);
	}
	
	public ETResponse<ETDataExtensionRow> get(ETClient client, String dataExtensionName, List<String> columns, ETFilter filter)
			throws ETSdkException {
		
		Soap soap = client.getSOAPConnection().getSoap();
		
		RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType("DataExtensionObject[" + dataExtensionName + "]");
        retrieveRequest.getProperties().addAll(columns);
        
        if (filter != null) {
        	FilterPart filterPart = convertFilterPart(filter);
            retrieveRequest.setFilter(filterPart);
        }
		
		RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        ETResponse<ETDataExtensionRow> response = new ETServiceResponseImpl<ETDataExtensionRow>();
        response.setRequestId(retrieveResponseMsg.getRequestID());
        
        try {
            for (APIObject apiObject : retrieveResponseMsg.getResults()) {
                response.getResults().add(ObjectConverter.convertToEtObject(apiObject, ETDataExtensionRow.class, false));
            }
        }
        catch (Exception ex) {
            throw new ETSdkException("Error instantiating object", ex);
        }

        return response;
		
	}

}
