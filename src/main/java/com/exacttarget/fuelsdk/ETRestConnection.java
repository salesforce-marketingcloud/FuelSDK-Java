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

public class ETRestConnection {
    private static Logger logger = Logger.getLogger(ETRestConnection.class);

    private ETClient client = null;

    private String endpoint = null;

    private boolean isAuthConnection = false;

    private int responseCode = -1;

    private Gson gson = null;

    private enum Method {
        GET, POST, DELETE
    }

    public ETRestConnection(ETClient client, String endpoint)
        throws ETSdkException
    {
        this(client, endpoint, false);
    }

    public ETRestConnection(ETClient client, String endpoint, boolean isAuthConnection)
        throws ETSdkException
    {
        this.client = client;

        this.endpoint = endpoint;

        this.isAuthConnection = isAuthConnection;

        //
        // If log level is set to DEBUG, configure Gson to do pretty printing:
        //

        if (logger.isDebugEnabled()) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
    }

    public String get(String path)
        throws ETSdkException
    {
        responseCode = -1;
        HttpURLConnection connection = sendRequest(path, Method.GET);
        String response = receiveResponse(connection);
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new ETSdkException(e);
        }
        connection.disconnect();
        return response;
    }

    public String post(String path, String payload)
        throws ETSdkException
    {
        responseCode = -1;
        HttpURLConnection connection = sendRequest(path, Method.POST, payload);
        String response = receiveResponse(connection);
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new ETSdkException(e);
        }
        connection.disconnect();
        return response;
    }

    public String delete(String path)
        throws ETSdkException
    {
        responseCode = -1;
        HttpURLConnection connection = sendRequest(path, Method.DELETE);
        String response = receiveResponse(connection);
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new ETSdkException(e);
        }
        connection.disconnect();
        return response;
    }

    public String post(String path, JsonObject jsonObject)
        throws ETSdkException
    {
        return post(path, jsonObject.toString());
    }

    public String getEndpoint() {
        return endpoint;
    }

    public boolean isAuthConnection() {
        return isAuthConnection;
    }

    public int getResponseCode() {
        return responseCode;
    }

    private HttpURLConnection sendRequest(String path, Method method)
        throws ETSdkException
    {
        return sendRequest(path, method, null);
    }

    private HttpURLConnection sendRequest(String path, Method method, String payload)
        throws ETSdkException
    {
        URL url = null;
        try {
            url = new URL(endpoint + path);
        } catch (MalformedURLException ex) {
            throw new ETSdkException(endpoint + path + ": bad URL", ex);
        }
        return sendRequest(url, method, payload);
    }

    private HttpURLConnection sendRequest(URL url, Method method, String payload)
        throws ETSdkException
    {
        logger.debug(method + " " + url);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
        } catch (ProtocolException ex) {
            throw new ETSdkException("error setting request method: " + method.toString(), ex);
        } catch (IOException ex) {
            throw new ETSdkException("error opening " + url, ex);
        }

        switch(method) {
        case GET:
            connection.setDoInput(true);
            connection.setRequestProperty("Accept", "application/json");
            break;
        case POST:
        case DELETE:
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            break;
        default:
            throw new ETSdkException("unsupported request method: " + method.toString());
        }

        if (!isAuthConnection) {
            connection.setRequestProperty("Authorization", "Bearer " + client.refreshToken());
        }

        if (logger.isDebugEnabled()) {
            for (String key : connection.getRequestProperties().keySet()) {
                logger.debug(key + ": " + connection.getRequestProperty(key));
            }
        }

        if (payload != null) {
            if (logger.isDebugEnabled()) {
                JsonParser jsonParser = new JsonParser();
                String payloadPrettyPrinted =
                        gson.toJson(jsonParser.parse(payload));
                for (String line : payloadPrettyPrinted.split("\\n")) {
                    logger.debug(line);
                }
            }
            try {
                OutputStream os = connection.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
            } catch (IOException ex) {
                throw new ETSdkException("error writing " + url, ex);
            }
        }

        try {
            logger.debug(connection.getResponseCode() + " " + connection.getResponseMessage());
        } catch (IOException ex) {
            throw new ETSdkException("error getting response code / message", ex);
        }

        return connection;
    }

    private String receiveResponse(HttpURLConnection connection)
        throws ETSdkException
    {
        InputStream is = null;
        try {
            if (connection.getResponseCode() < 400) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
        } catch (IOException ex) {
            throw new ETSdkException("error opening " + connection.getURL(), ex);
        }

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ex) {
            throw new ETSdkException("error reading " + connection.getURL(), ex);
        }

        try {
            reader.close();
        } catch (IOException ex) {
            throw new ETSdkException("error closing " + connection.getURL(), ex);
        }

        String response = stringBuilder.toString();

        if (logger.isDebugEnabled()) {
            JsonParser jsonParser = new JsonParser();
            String responsePrettyPrinted = gson.toJson(jsonParser.parse(response));
            for (String line : responsePrettyPrinted.split("\\n")) {
                logger.debug(line);
            }
        }

        return response;
    }
}
