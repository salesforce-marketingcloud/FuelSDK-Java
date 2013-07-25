//
// ET_Connection.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ET_Configuration;
import com.exacttarget.fuelsdk.ET_SDKException;

public class ET_Connection {
    private static Logger logger = Logger.getLogger(ET_Connection.class);

    // set endpoint and authEndpoint to production default values
    private String endpoint = "https://www.exacttargetapis.com";
    private String authEndpoint = "https://auth.exacttargetapis.com";
    private String clientId = null;
    private String clientSecret = null;

    private static final String PATH_REQUESTTOKEN = "/v1/requestToken";

    private String accessToken = null;

//    private Gson gson = null;

    public ET_Connection(ET_Configuration configuration) {
        if (configuration.getEndpoint() != null) {
            endpoint = configuration.getEndpoint();
        }
        if (configuration.getAuthEndpoint() != null) {
            authEndpoint = configuration.getAuthEndpoint();
        }
        clientId = configuration.getClientId();
        clientSecret = configuration.getClientSecret();

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gson = gsonBuilder.create();
//        // XXX make this an option
//        //gson = gsonBuilder.setPrettyPrinting().create();
    }

    public String get(String path)
        throws ET_SDKException
    {
        HttpURLConnection connection = sendRequest(path, "GET");
        String response = receiveResponse(connection);
        connection.disconnect();
        return response;
    }

    public String post(String path, String payload)
        throws ET_SDKException
    {
        return post(path, payload, true);
    }

    private String post(String path, String payload, boolean isAuthenticated)
        throws ET_SDKException
    {
        HttpURLConnection connection = sendRequest(path, "POST", payload,
                isAuthenticated);
        String response = receiveResponse(connection);
        connection.disconnect();
        return response;
    }

//    public <T> T get(String url, Class<T> entityClass)
//        throws ET_SDKException
//    {
//        String json = get(url);
//        return gson.fromJson(json, entityClass);
//    }

//    public void post(String url, ET_Object object)
//        throws ET_SDKException
//    {
//        post(url, gson.toJson(object));
//    }

//    public String get(String url, String... queryParams)
//        throws ET_SDKException
//    {
//        url += "?access_token" + accessToken;
//        for (String queryParam : queryParams) {
//            url += "&" + queryParam;
//        }
//        return get(new URL(url));
//    }

//    public void post(String url, String payload, String... queryParams)
//        throws ET_SDKException
//    {
//        url += "?access_token" + accessToken;
//        for (String queryParam : queryParams) {
//            url += "&" + queryParam;
//        }
//        post(new URL(url), payload);
//    }

    private String requestToken(String clientId, String clientSecret)
        throws ET_SDKException
    {
        logger.trace("requesting access token...");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clientId", clientId);
        jsonObject.addProperty("clientSecret", clientSecret);
        // XXX put this in a method?
        URL url = null;
        try {
            url = new URL(authEndpoint + PATH_REQUESTTOKEN);
        } catch (MalformedURLException ex) {
            throw new ET_SDKException(authEndpoint + PATH_REQUESTTOKEN + ": bad URL",
                    ex);
        }
        String payload = jsonObject.toString();
        HttpURLConnection connection = sendRequest(url, "POST", payload, false);
        String response = receiveResponse(connection);
        connection.disconnect();
        JsonParser jsonParser = new JsonParser();
        jsonObject = jsonParser.parse(response).getAsJsonObject();
        String accessToken = jsonObject.get("accessToken").getAsString();
        logger.debug("refreshed accessToken: " + accessToken);
        return accessToken;
    }

    private HttpURLConnection sendRequest(String path, String method)
        throws ET_SDKException
    {
        return sendRequest(path, method, null);
    }

    private HttpURLConnection sendRequest(String path, String method,
            String payload)
        throws ET_SDKException
    {
        return sendRequest(path, method, payload, true);
    }

    private HttpURLConnection sendRequest(String path, String method,
            String payload, boolean isAuthenticated)
        throws ET_SDKException
    {
        URL url = null;
        try {
            url = new URL(endpoint + path);
        } catch (MalformedURLException ex) {
            throw new ET_SDKException(endpoint + path + ": bad URL", ex);
        }
        return sendRequest(url, method, payload, true);
    }

    private HttpURLConnection sendRequest(URL url, String method,
            String payload, boolean isAuthenticated)
        throws ET_SDKException
    {
        if (isAuthenticated) {
            if (accessToken == null) {
                accessToken = requestToken(clientId, clientSecret);
            }
        }

        logger.trace(method + " " + url);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            throw new ET_SDKException("error opening " + url, ex);
        }

        if (method.equals("GET")) {
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException ex) {
                throw new ET_SDKException("error setting request method: GET", ex);
            }
            connection.setDoInput(true);
            connection.setRequestProperty("Accept",
                    "application/json");
        } else if (method.equals("POST")) {
            try {
                connection.setRequestMethod("POST");
            } catch (ProtocolException ex) {
                throw new ET_SDKException("error setting request method: POST", ex);
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/json");
        } else {
            throw new ET_SDKException("unsupported request method: " + method);
        }

        if (isAuthenticated) {
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        }

        if (logger.isTraceEnabled()) {
            for (String key : connection.getRequestProperties().keySet()) {
                logger.trace(key + ": " + connection.getRequestProperty(key));
            }
        }

        if (payload != null) {
            if (logger.isTraceEnabled()) {
                for (String line : payload.split("\\n")) {
                    logger.trace(line);
                }
            }
            try {
                OutputStream os = connection.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
            } catch (IOException ex) {
                throw new ET_SDKException("error writing " + url, ex);
            }
        }

        try {
            logger.trace(connection.getResponseCode() + " "
                    + connection.getResponseMessage());
        } catch (IOException ex) {
            throw new ET_SDKException("error getting response code / message", ex);
        }

        return connection;
    }

    private String receiveResponse(HttpURLConnection connection)
        throws ET_SDKException
    {
        InputStream is = null;
        try {
            is = connection.getInputStream();
        } catch (IOException ex) {
            throw new ET_SDKException("error opening " + connection.getURL(), ex);
        }

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(is));
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ex) {
            throw new ET_SDKException("error reading " + connection.getURL(), ex);
        }

        try {
            reader.close();
        } catch (IOException ex) {
            throw new ET_SDKException("error closing " + connection.getURL(), ex);
        }

        String response = stringBuilder.toString();

        if (logger.isTraceEnabled()) {
            for (String line : response.split("\\n")) {
                logger.trace(line);
            }
        }

        return response;
    }
}
