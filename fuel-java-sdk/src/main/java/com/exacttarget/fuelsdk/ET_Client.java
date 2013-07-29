//
// ET_Client.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.rest.ET_RESTConnection;
import com.exacttarget.fuelsdk.soap.ET_ListServiceImpl;
import com.exacttarget.fuelsdk.soap.ET_SOAPConnection;

public class ET_Client {
    private static final String PATH_REQUESTTOKEN =
            "/v1/requestToken?legacy=1";
    private static final String PATH_ENDPOINTS_SOAP =
            "/platform/v1/endpoints/soap";

    private static Logger logger = Logger.getLogger(ET_Client.class);

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

    private ET_RESTConnection authConnection = null;
    private ET_RESTConnection restConnection = null;
    private ET_SOAPConnection soapConnection = null;

    public ET_Client(ET_Configuration configuration)
        throws ET_SDKException
    {
        if (configuration.getEndpoint() != null) {
            endpoint = configuration.getEndpoint();
        }
        if (configuration.getAuthEndpoint() != null) {
            authEndpoint = configuration.getAuthEndpoint();
        }
        // XXX make sure clientId is specified
        clientId = configuration.getClientId();
        // XXX make sure clientSecret is specified
        clientSecret = configuration.getClientSecret();

        authConnection = new ET_RESTConnection(this, authEndpoint);

        refreshToken();

        restConnection = new ET_RESTConnection(this, endpoint);

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

        soapConnection = new ET_SOAPConnection(this, soapEndpoint);
    }

    public void refreshToken()
        throws ET_SDKException
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

    public ET_RESTConnection getRESTConnection() {
        return restConnection;
    }

    public ET_SOAPConnection getSOAPConnection() {
        return soapConnection;
    }

    public ET_ListServiceImpl getListService() {
        return new ET_ListServiceImpl();
    }
}
