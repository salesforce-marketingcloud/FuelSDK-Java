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

import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.soap.ETDataExtensionServiceImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

public class ETClient {
    private static final String PATH_REQUESTTOKEN =
            "/v1/requestToken";
    private static final String PATH_ENDPOINTS_SOAP =
            "/platform/v1/endpoints/soap";

    private static Logger logger = Logger.getLogger(ETClient.class);

    // set endpoint and authEndpoint to production default values
    private String endpoint = "https://www.exacttargetapis.com";
    private String authEndpoint = "https://auth.exacttargetapis.com";
    private String soapEndpoint = null;
    private String clientId = null;
    private String clientSecret = null;

    private String accessToken = null;
    private String legacyToken = null;
    private int expiresIn = 0;
    private String refreshToken = null;

    private long tokenExpirationTime = 0;

    private ETRestConnection authConnection = null;
    private ETRestConnection restConnection = null;
    private ETSoapConnection soapConnection = null;

    public ETClient()
        throws ETSdkException
    {
        this(new ETConfiguration());
    }

    public ETClient(String file)
        throws ETSdkException
    {
        this(new ETConfiguration(file));
    }

    public ETClient(ETConfiguration configuration)
        throws ETSdkException
    {
        if (configuration.getEndpoint() != null
            && !configuration.getEndpoint().equals(""))
        {
            endpoint = configuration.getEndpoint();
        }
        if (configuration.getAuthEndpoint() != null
            && !configuration.getAuthEndpoint().equals(""))
        {
            authEndpoint = configuration.getAuthEndpoint();
        }
        if (configuration.getSoapEndpoint() != null
            && !configuration.getSoapEndpoint().equals(""))
        {
            soapEndpoint = configuration.getSoapEndpoint();
        }

        clientId = configuration.getClientId();
        if (clientId == null || clientId.equals("")) {
            throw new ETSdkException("clientId not specified");
        }

        clientSecret = configuration.getClientSecret();
        if (clientSecret == null || clientSecret.equals("")) {
            throw new ETSdkException("clientSecret not specified");
        }

        if (logger.isTraceEnabled()) {
            logger.trace("endpoint = " + endpoint);
            logger.trace("authEndpoint = " + authEndpoint);
            logger.trace("soapEndpoint = " + soapEndpoint);
            logger.trace("clientId = " + clientId);
            logger.trace("clientSecret = " + clientSecret);
        }

        authConnection = new ETRestConnection(this, authEndpoint, true);

        restConnection = new ETRestConnection(this, endpoint);

        //
        // If a SOAP endpoint isn't specified automatically determine it:
        //

        // XXX use Endpoints object

        if (soapEndpoint == null) {
            String response = restConnection.get(PATH_ENDPOINTS_SOAP);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
            soapEndpoint = jsonObject.get("url").getAsString();
            logger.debug("SOAP endpoint: " + soapEndpoint);
        }

        soapConnection = new ETSoapConnection(this, soapEndpoint);
    }

    public String refreshToken()
        throws ETSdkException
    {
        if (tokenExpirationTime == 0) {
            logger.debug("requesting new access token...");
        } else {
            logger.debug("access token expires at "
                    + new Date(tokenExpirationTime));

            //
            // If the current token expires more than five
            // minutes from now, we don't need to refresh
            // (tokenExpirationTime and System.currentTimeMills()
            // are in milliseconds so we multiply by 1000):
            //

            if (tokenExpirationTime - System.currentTimeMillis() > 5*60*1000) {
                logger.debug("not refreshing access token");
                return accessToken;
            }

            logger.debug("refreshing access token...");

            assert refreshToken != null;
        }

        //
        // Construct the JSON payload. Set accessType to offline so
        // we get a refresh token. Pass in current refresh token if
        // we have one:
        //

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clientId", clientId);
        jsonObject.addProperty("clientSecret", clientSecret);
        jsonObject.addProperty("accessType", "offline");
        if (refreshToken != null) {
            jsonObject.addProperty("refreshToken", refreshToken);
        }

        String response = authConnection.post(PATH_REQUESTTOKEN, jsonObject);

        if (response == null) {
            throw new ETSdkException("failed to obtain access token");
        }

        //
        // Parse the JSON response into the appropriate instance
        // variables:
        //

        JsonParser jsonParser = new JsonParser();
        jsonObject = jsonParser.parse(response).getAsJsonObject();
        logger.debug("received token:");
        accessToken = jsonObject.get("accessToken").getAsString();
        logger.debug("  accessToken: " + accessToken);
        expiresIn = jsonObject.get("expiresIn").getAsInt();
        logger.debug("  expiresIn: " + expiresIn);
        refreshToken = jsonObject.get("refreshToken").getAsString();
        logger.debug("  refreshToken: " + refreshToken);

        //
        // Calculate the token expiration time. As before,
        // System.currentTimeMills() is in milliseconds so
        // we multiply expiresIn by 1000:
        //

        tokenExpirationTime = System.currentTimeMillis() + (expiresIn * 1000);

        logger.debug("new access token expires at "
                + new Date(tokenExpirationTime));

        // XXX not sure i like this
        if (soapConnection != null) {
            soapConnection.updateHeaders();
        }

        return accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @deprecated
     * Legacy tokens are no longer supported.
     */
    @Deprecated
    public String getLegacyToken() {
        return legacyToken;
    }

    public ETRestConnection getRestConnection() {
        return restConnection;
    }

    public ETSoapConnection getSoapConnection() {
        return soapConnection;
    }

    /**
     * @deprecated
     * Use getRestConnection().
     */
    @Deprecated
    public ETRestConnection getRESTConnection() {
        return getRestConnection();
    }

    /**
     * @deprecated
     * Use getSoapConnection().
     */
    @Deprecated
    public ETSoapConnection getSOAPConnection() {
        return getSoapConnection();
    }

    public ETDataExtension retrieveDataExtension(ETFilter filter)
        throws ETSdkException
    {
        List<ETDataExtension> dataExtensions = retrieveDataExtensions(filter);
        if (dataExtensions == null) {
            return null;
        }
        // XXX check if more than one row is returned
        return dataExtensions.get(0);
    }

    public List<ETDataExtension> retrieveDataExtensions()
        throws ETSdkException
    {
        return retrieveDataExtensions(null);
    }

    public List<ETDataExtension> retrieveDataExtensions(ETFilter filter)
        throws ETSdkException
    {
        ETDataExtensionService service = new ETDataExtensionServiceImpl();
        ETResponse<ETDataExtension> response = service.get(this, filter);
        // XXX check for errors and throw the appropriate exception
        for (ETDataExtension dataExtension : response.getResults()) {
            dataExtension.setClient(this); // XXX hack
        }
        return response.getResults();
    }
}
