//
// ET_RESTConnection.java -
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ET_Client;
import com.exacttarget.fuelsdk.ET_SDKException;

public class ET_RESTConnection {
    private static Logger logger = Logger.getLogger(ET_RESTConnection.class);

    private ET_Client client = null;

    private String endpoint = null;

    private Gson gson = null;

    public ET_RESTConnection(ET_Client client, String endpoint)
        throws ET_SDKException
    {
        this.client = client;

        this.endpoint = endpoint;

        //
        // If log level is set to TRACE, configure Gson to do pretty printing:
        //

        if (logger.isTraceEnabled()) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
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
        HttpURLConnection connection = sendRequest(path, "POST", payload);
        String response = receiveResponse(connection);
        connection.disconnect();
        return response;
    }

    public String post(String path, JsonObject jsonObject)
        throws ET_SDKException
    {
        return post(path, jsonObject.toString());
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

    private HttpURLConnection sendRequest(String path, String method)
        throws ET_SDKException
    {
        return sendRequest(path, method, null);
    }

    private HttpURLConnection sendRequest(String path, String method,
            String payload)
        throws ET_SDKException
    {
        URL url = null;
        try {
            url = new URL(endpoint + path);
        } catch (MalformedURLException ex) {
            throw new ET_SDKException(endpoint + path + ": bad URL", ex);
        }
        return sendRequest(url, method, payload);
    }

    private HttpURLConnection sendRequest(URL url, String method,
            String payload)
        throws ET_SDKException
    {
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

        String accessToken = client.getAccessToken();
        if (accessToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        }

        if (logger.isTraceEnabled()) {
            for (String key : connection.getRequestProperties().keySet()) {
                logger.trace(key + ": " + connection.getRequestProperty(key));
            }
        }

        if (payload != null) {
            if (logger.isTraceEnabled()) {
                JsonParser jsonParser = new JsonParser();
                String payloadPrettyPrinted =
                        gson.toJson(jsonParser.parse(payload));
                for (String line : payloadPrettyPrinted.split("\\n")) {
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
            JsonParser jsonParser = new JsonParser();
            String responsePrettyPrinted =
                    gson.toJson(jsonParser.parse(response));
            for (String line : responsePrettyPrinted.split("\\n")) {
                logger.trace(line);
            }
        }

        return response;
    }
}
