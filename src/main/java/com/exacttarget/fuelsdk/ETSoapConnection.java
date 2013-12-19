//
// ETSoapConnection.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
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
