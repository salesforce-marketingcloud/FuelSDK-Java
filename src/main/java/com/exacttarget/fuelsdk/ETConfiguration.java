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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ETConfiguration {
    private static Logger logger = Logger.getLogger(ETConfiguration.class);

    private static final String DEFAULT_FILE_NAME = "/fuelsdk.properties";
    private String endpoint = null;
    private String authEndpoint = null;
    private String soapEndpoint = null;
    private String clientId = null;
    private String clientSecret = null;

    public ETConfiguration()
        throws ETSdkException
    {
        this(DEFAULT_FILE_NAME);
    }

    public ETConfiguration(String file)
        throws ETSdkException
    {
        if (file == null) {
            file = DEFAULT_FILE_NAME;
        }
        logger.trace("reading configuration from " + file);
        InputStream is = getClass().getResourceAsStream(file);
        if (is == null) {
            throw new ETSdkException("error opening " + file);
        }
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException ex) {
            throw new ETSdkException("error reading " + file, ex);
        }
        endpoint = properties.getProperty("endpoint");
        authEndpoint = properties.getProperty("authEndpoint");
        soapEndpoint = properties.getProperty("soapEndpoint");
        clientId = properties.getProperty("clientId");
        clientSecret = properties.getProperty("clientSecret");
        if (logger.isTraceEnabled()) {
            logger.trace("endpoint = " + endpoint);
            logger.trace("authEndpoint = " + authEndpoint);
            logger.trace("soapEndpoint = " + soapEndpoint);
            logger.trace("clientId = " + clientId);
            logger.trace("clientSecret = " + clientSecret);
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public void setAuthEndpoint(String authEndpoint) {
        this.authEndpoint = authEndpoint;
    }

    public String getSoapEndpoint() {
        return soapEndpoint;
    }

    public void setSoapEndpoint(String soapEndpoint) {
        this.soapEndpoint = soapEndpoint;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
