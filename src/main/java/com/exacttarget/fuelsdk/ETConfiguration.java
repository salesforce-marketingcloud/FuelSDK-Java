//
// ETConfiguration.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import java.io.IOException;
import java.util.Properties;

public class ETConfiguration {
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
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(file));
        } catch (IOException ex) {
            throw new ETSdkException("error opening " + file, ex);
        }
        endpoint = properties.getProperty("endpoint");
        authEndpoint = properties.getProperty("authEndpoint");
        soapEndpoint = properties.getProperty("soapEndpoint");
        clientId = properties.getProperty("clientId");
        clientSecret = properties.getProperty("clientSecret");
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
