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
import com.exacttarget.fuelsdk.ETEmailSendDefinitionService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.internal.EmailSendDefinition;
import com.exacttarget.fuelsdk.internal.InteractionBaseObject;
import com.exacttarget.fuelsdk.internal.PerformRequestMsg;
import com.exacttarget.fuelsdk.internal.PerformRequestMsg.Definitions;
import com.exacttarget.fuelsdk.internal.PerformResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.model.ETEmailSendDefinition;
import com.exacttarget.fuelsdk.model.converter.ObjectConverter;

public class ETEmailSendDefinitionServiceImpl extends ETCrudServiceImpl<ETEmailSendDefinition>
		implements ETEmailSendDefinitionService {

	public ETResponse<ETEmailSendDefinition> get(ETClient client)
			throws ETSdkException {

		return super.get(client, ETEmailSendDefinition.class);
	}

	public ETResponse<ETEmailSendDefinition> get(ETClient client,
			ETFilter filter) throws ETSdkException {

		return super.get(client, ETEmailSendDefinition.class, filter);
	}

	public ETResponse<ETEmailSendDefinition> post(ETClient client,
			ETEmailSendDefinition emailSendDefinition) throws ETSdkException {

		return super.post(client, emailSendDefinition);
	}

	public ETResponse<ETEmailSendDefinition> patch(ETClient client,
			ETEmailSendDefinition emailSendDefinition) throws ETSdkException {

		return super.patch(client, emailSendDefinition);
	}

	public ETResponse<ETEmailSendDefinition> delete(ETClient client,
			ETEmailSendDefinition emailSendDefinition) throws ETSdkException {

		return super.delete(client, emailSendDefinition);
	}

	public ETResponse<ETEmailSendDefinition> post(ETClient client,
			List<ETEmailSendDefinition> emailSendDefinitions) throws ETSdkException {

		return super.post(client, emailSendDefinitions);
	}

	public ETResponse<ETEmailSendDefinition> patch(ETClient client,
			List<ETEmailSendDefinition> emailSendDefinitions) throws ETSdkException {

		return super.patch(client, emailSendDefinitions);
	}

	public ETResponse<ETEmailSendDefinition> delete(ETClient client,
			List<ETEmailSendDefinition> emailSendDefinitions) throws ETSdkException {

		return super.delete(client, emailSendDefinitions);
	}

	public ETResponse<ETEmailSendDefinition> send(ETClient client,
			ETEmailSendDefinition emailSendDefinition) throws ETSdkException {

		Soap soap = client.getSOAPConnection().getSoap();

		InteractionBaseObject apiObject;
		try {
            apiObject = ObjectConverter.convertFromEtObject(emailSendDefinition, EmailSendDefinition.class, false);
		}
        catch(Exception e) {
            throw new ETSdkException("Error instantiating object", e);
        }

		Definitions definitions = new Definitions();
		definitions.getDefinition().add(apiObject);

		PerformRequestMsg performRequestMsg = new PerformRequestMsg();
		performRequestMsg.setAction("start");
		performRequestMsg.setDefinitions(definitions);

		PerformResponseMsg performResponseMsg = soap.perform(performRequestMsg);

        ETResponse<ETEmailSendDefinition> response = new ETServiceResponseImpl<ETEmailSendDefinition>();
        response.setRequestId(performResponseMsg.getRequestID());

        return response;
	}

}
