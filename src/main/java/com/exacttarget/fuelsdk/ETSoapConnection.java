//
// This file is part of the Fuel Java SDK.
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
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.internal.PartnerAPI;
import com.exacttarget.fuelsdk.internal.Soap;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.transport.http.Headers;

/**
 * An <code>ETSoapConnection</code> represents an active
 * connection to the SOAP API.
 */

public class ETSoapConnection {
    private static Logger logger = Logger.getLogger(ETSoapConnection.class);

    private static final String WSSE_NAMESPACE_URI =
            "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

    private String endpoint = null;

    private Soap soap = null;
    private Client soapClient = null;
    private SOAPFactory soapFactory = null;
    private SOAPElement accessTokenElement = null;

    /** 
    * Class constructor, Initializes a new instance of the class.
     * @param client    The ETClient object
     * @param endpoint  The endpoint URL
     * @throws com.exacttarget.fuelsdk.ETSdkException
    */
    public ETSoapConnection(ETClient client, String endpoint)
        throws ETSdkException
    {
        this.endpoint = endpoint;

        //
        // Initialize the SOAP proxy:
        //

        try {
            PartnerAPI service = new PartnerAPI();
            soap = service.getSoap();
            soapClient = ClientProxy.getClient(soap);
            soapClient.getInInterceptors().add(new ClearAttachmentsOutInterceptor());
            soapClient.getOutInterceptors().add(new ClearAttachmentsOutInterceptor());  //

            Endpoint soapEndpoint = soapClient.getEndpoint();
            soapFactory = SOAPFactory.newInstance();
            soapClient.getRequestContext().put(Message.ENDPOINT_ADDRESS,
                    endpoint);
            HTTPConduit conduit = (HTTPConduit) soapClient.getConduit();
            Integer cxfConnectTimeout = null;
            Integer cxfReceiveTimeout = null;
            try {
                cxfConnectTimeout = new Integer(
                        client.getConfiguration().get("cxfConnectTimeout"));
            } catch (NumberFormatException ex) {
                // Ignore--this just means the value specified in
                // the fuelsdk.properties file is not an integer.
            }
            try {
                cxfReceiveTimeout = new Integer(
                        client.getConfiguration().get("cxfReceiveTimeout"));
            } catch (NumberFormatException ex) {
                // Ignore--this just means the value specified in
                // the fuelsdk.properties file is not an integer.
            }
            HTTPClientPolicy clientPolicy = new HTTPClientPolicy();
            if (cxfConnectTimeout != null) {
                clientPolicy.setConnectionTimeout(cxfConnectTimeout);
            }
            if (cxfReceiveTimeout != null) {
                clientPolicy.setReceiveTimeout(cxfReceiveTimeout);
            }
            conduit.setClient(clientPolicy);
            if (client.getConfiguration().isTrue("cxfDisableCNCheck")) {
                TLSClientParameters tlsClientParameters = new TLSClientParameters();
                tlsClientParameters.setDisableCNCheck(true);
                conduit.setTlsClientParameters(tlsClientParameters);
            }
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

    /** 
    * Class constructor, Initializes a new instance of the class.
     * @param client        The ETClient object
     * @param endpoint      The endpoint URL
     * @param username      The username
     * @param password      The password
     * @throws com.exacttarget.fuelsdk.ETSdkException
    */
    public ETSoapConnection(ETClient client, String endpoint,
                            String username,
                            String password)
        throws ETSdkException
    {
        this(client, endpoint);

        try {
            List<Header> headers = new ArrayList<Header>();

            SOAPElement usernameElement = soapFactory.createElement(
                    new QName(WSSE_NAMESPACE_URI, "Username", "wsse"));
            usernameElement.addTextNode(username);

            SOAPElement passwordElement = soapFactory.createElement(
                    new QName(WSSE_NAMESPACE_URI, "Password", "wsse"));
            passwordElement.addTextNode(password);

            SOAPElement usernameTokenElement = soapFactory.createElement(
                    new QName(WSSE_NAMESPACE_URI, "UsernameToken", "wsse"));
            usernameTokenElement.addChildElement(usernameElement);
            usernameTokenElement.addChildElement(passwordElement);

            SOAPElement securityElement = soapFactory.createElement(
                    new QName(WSSE_NAMESPACE_URI, "Security", "wsse"));
            securityElement.addChildElement(usernameTokenElement);

            headers.add(new Header(new QName(WSSE_NAMESPACE_URI, "Security", "wsse"),
                    securityElement));

            soapClient.getRequestContext().put(Header.HEADER_LIST, headers);
        } catch (SOAPException ex) {
            throw new ETSdkException("could not initialize SOAP proxy", ex);
        }
    }

    /** 
    * Class constructor, Initializes a new instance of the class.
     * @param client        The ETClient object
     * @param endpoint      The endpoint URL
     * @param accessToken   The access token
     * @throws com.exacttarget.fuelsdk.ETSdkException
    */
    public ETSoapConnection(ETClient client, String endpoint, String accessToken)
        throws ETSdkException
    {
        this(client, endpoint);

        try {
            List<Header> headers = new ArrayList<Header>();

            accessTokenElement =
                    soapFactory.createElement(new QName(null, "fueloauth"));
            if (accessToken != null) {
                setAccessToken(accessToken);
            }

            headers.add(new Header(new QName(null, "fueloauth"), accessTokenElement));
            
            soapClient.getRequestContext().put(Header.HEADER_LIST, headers);
        } catch (SOAPException ex) {
            throw new ETSdkException("could not initialize SOAP proxy", ex);
        }
    }

    /**
     * @return  The Soap object
     */
    public Soap getSoap() {
        return soap;
    }

    public Soap getSoap(String m) {
        soapClient.getRequestContext().put("HTTP_HEADER_USER_AGENT", "FuelSDK-Java-v1.4.0-SOAP-"+m);
        return soap;
    }
    
    public Soap getSoap(String m, String o) {
        soapClient.getRequestContext().put("HTTP_HEADER_USER_AGENT", "FuelSDK-Java-v1.4.0-SOAP-"+m+"-"+o);
        return soap;
    }    
    
    /**
     * @return  The end point URL
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param accessToken       The access token
     * @throws ETSdkException 
     */
    public void setAccessToken(String accessToken)
        throws ETSdkException
    {
        if (accessTokenElement != null) {
            accessTokenElement.removeContents();
            try {
                accessTokenElement.addTextNode(accessToken);
            } catch (SOAPException ex) {
                throw new ETSdkException("could not set access token", ex);
            }
            logger.debug("updated SOAP header with new access token "
                    + accessToken);
        }
    }
}
