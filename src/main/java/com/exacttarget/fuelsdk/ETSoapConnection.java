//
// This file is part of the Salesforce Marketing Cloud Java client library.
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
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.internal.PartnerAPI;
import com.exacttarget.fuelsdk.internal.Soap;

/**
 * An <code>ETSoapConnection</code> represents an active
 * connection to the SOAP API.
 */

public class ETSoapConnection {
    private static Logger logger = Logger.getLogger(ETSoapConnection.class);

    private ETClient client = null;

    private String endpoint = null;

    private Soap soap = null;
    private Client soapClient = null;
    private SOAPFactory soapFactory = null;

    public ETSoapConnection(ETClient client, String endpoint)
        throws ETSdkException
    {
        this.client = client;

        this.endpoint = endpoint;

        //
        // Initialize the SOAP proxy:
        //

        PartnerAPI service = new PartnerAPI();
        soap = service.getSoap();
        soapClient = ClientProxy.getClient(soap);
        Endpoint soapEndpoint = soapClient.getEndpoint();

        try {
            soapFactory = SOAPFactory.newInstance();

            updateHeaders();

            soapClient.getRequestContext().put(Message.ENDPOINT_ADDRESS,
                    endpoint);
            soapClient.getRequestContext().put(Message.ENCODING, "UTF-8");

            LoggingInInterceptor loggingInInterceptor =
                    new LoggingInInterceptor();
            loggingInInterceptor.setPrettyLogging(true);
            LoggingOutInterceptor loggingOutInterceptor =
                    new LoggingOutInterceptor();
            loggingOutInterceptor.setPrettyLogging(true);
            soapEndpoint.getInInterceptors().add(loggingInInterceptor);
            soapEndpoint.getOutInterceptors().add(loggingOutInterceptor);
        } catch (SOAPException ex) {
            throw new ETSdkException("could not initialize SOAP proxy", ex);
        }
    }

    // XXX this is kind of a hack--ideally just
    // the fueloauth element would be updated..

    public void updateHeaders()
        throws ETSdkException
    {
        try {
            List<Header> headers = new ArrayList<Header>();

            SOAPElement oauthElement =
                    soapFactory.createElement(new QName(null, "fueloauth"));
            oauthElement.addTextNode(client.getAccessToken());
            Header oauthHeader =
                    new Header(new QName("http://exacttarget.com", "fueloauth"),
                               oauthElement);
            headers.add(oauthHeader);

            soapClient.getRequestContext().put(Header.HEADER_LIST, headers);

            logger.debug("updated SOAP header with new access token "
                    + client.getAccessToken());
        } catch (SOAPException ex) {
            throw new ETSdkException("could not update SOAP headers", ex);
        }
    }

    public Soap getSoap() {
        return soap;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
