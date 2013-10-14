//
// ETClient.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.rest.ETRestConnection;
import com.exacttarget.fuelsdk.soap.ETListServiceImpl;
import com.exacttarget.fuelsdk.soap.ETSoapConnection;

public class ETClient {
    private static final String PATH_REQUESTTOKEN =
            "/v1/requestToken?legacy=1";
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

    public ETClient(ETConfiguration configuration)
        throws ETSdkException
    {
        if (configuration.getEndpoint() != null && !"".equals(configuration.getEndpoint())) {
            endpoint = configuration.getEndpoint();
        }
        if (configuration.getAuthEndpoint() != null && !"".equals(configuration.getAuthEndpoint())) {
            authEndpoint = configuration.getAuthEndpoint();
        }
        
        clientId = configuration.getClientId();
        if (null == clientId || "".equals(clientId)) {
        	throw new ETSdkException("Required clientId is missing from the SDK Configuration File");
        }
        
        clientSecret = configuration.getClientSecret();
        if (null == clientSecret || "".equals(clientSecret)) {
        	throw new ETSdkException("Required clientSecret is missing from the SDK Configuration File");
        }
        
        authConnection = new ETRestConnection(this, authEndpoint);

        refreshToken();

        restConnection = new ETRestConnection(this, endpoint);

        //
        // If a SOAP endpoint isn't specified automatically determine it:
        //
        if (soapEndpoint == null) {
            String response = restConnection.get(PATH_ENDPOINTS_SOAP);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
            soapEndpoint = jsonObject.get("url").getAsString();
            logger.debug("SOAP endpoint: " + soapEndpoint);
        }

        soapConnection = new ETSoapConnection(this, soapEndpoint);
    }

    public void refreshToken()
        throws ETSdkException
    {
        //
        // If the current token expires more than five
        // minutes from now, we don't need to refresh
        // (tokenExpirationTime and System.currentTimeMills()
        // are in milliseconds so we multiply by 1000):
        //

        if (tokenExpirationTime - System.currentTimeMillis() > 5*60*1000) {
            return;
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
            logger.debug("refreshing access token...");
            jsonObject.addProperty("refreshToken", refreshToken);
        } else {
            logger.debug("requesting new access token...");
        }

        String response = authConnection.post(PATH_REQUESTTOKEN, jsonObject);

        //
        // Parse the JSON response into the appropriate instance
        // variables:
        //

        JsonParser jsonParser = new JsonParser();
        jsonObject = jsonParser.parse(response).getAsJsonObject();
        logger.debug("received token:");
        accessToken = jsonObject.get("accessToken").getAsString();
        logger.debug("  accessToken: " + accessToken);
        legacyToken = jsonObject.get("legacyToken").getAsString();
        logger.debug("  legacyToken: " + legacyToken);
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

        logger.debug("token expires at " + new Date(tokenExpirationTime));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getLegacyToken() {
        return legacyToken;
    }

    public ETRestConnection getRESTConnection() {
        return restConnection;
    }

    public ETSoapConnection getSOAPConnection() {
        return soapConnection;
    }

    public ETListServiceImpl getListService() {
        return new ETListServiceImpl();
    }
}
