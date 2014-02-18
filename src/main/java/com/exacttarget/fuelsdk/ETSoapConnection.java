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

public class ETSoapConnection {
    private static Logger logger = Logger.getLogger(ETSoapConnection.class);

    private ETClient client = null;

    private String endpoint = null;

    private Soap soap = null;

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
        Client soapClient = ClientProxy.getClient(soap);
        Endpoint soapEndpoint = soapClient.getEndpoint();

        try {
            List<Header> headers = new ArrayList<Header>();

            SOAPFactory soapFactory = SOAPFactory.newInstance();

            SOAPElement oAuthTokenElement =
                    soapFactory.createElement(new QName(null, "oAuthToken"));
            oAuthTokenElement.addTextNode(client.getLegacyToken());
            SOAPElement oAuthElement =
                    soapFactory.createElement(new QName("http://exacttarget.com", "oAuth"));
            oAuthElement.addChildElement(oAuthTokenElement);
            Header oAuthHeader =
                    new Header(new QName("http://exacttarget.com", "oAuth"),
                            oAuthElement);
            headers.add(oAuthHeader);

            String XSD_URL = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

            SOAPElement securityElement =
                    soapFactory.createElement(new QName(XSD_URL, "Security"));
            SOAPElement usernameTokenElement =
                    soapFactory.createElement(new QName(XSD_URL, "UsernameToken"));
            SOAPElement usernameElement =
                    soapFactory.createElement(new QName(XSD_URL, "Username"));
            usernameElement.addTextNode("*");
            SOAPElement passwordElement =
                    soapFactory.createElement(new QName(XSD_URL, "Password"));
            passwordElement.addTextNode("*");
            usernameTokenElement.addChildElement(usernameElement);
            usernameTokenElement.addChildElement(passwordElement);
            securityElement.addChildElement(usernameTokenElement);
            Header securityHeader =
                    new Header(new QName("http://exacttarget.com", "Security"),
                            securityElement);
            headers.add(securityHeader);

            soapClient.getRequestContext().put(Header.HEADER_LIST, headers);
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

    // XXX should be protected
    public Soap getSoap() {
        return soap;
    }
}
