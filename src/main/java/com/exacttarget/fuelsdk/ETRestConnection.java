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
import com.google.gson.JsonParser;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;

/**
 * An <code>ETRestConnection</code> represents an active
 * connection to the Salesforce Marketing Cloud REST API.
 */

public class ETRestConnection {
    private static Logger logger = Logger.getLogger(ETRestConnection.class);

    private ETClient client = null;

    private String endpoint = null;

    private boolean isAuthConnection = false;

    /** 
    * Class constructor, Initializes a new instance of the class.
     * @param client    The ETClient object
     * @param endpoint  The endpoint URL
     * @throws com.exacttarget.fuelsdk.ETSdkException
    */
    public ETRestConnection(ETClient client, String endpoint)
        throws ETSdkException
    {
        this(client, endpoint, false);
    }

    /** 
    * Class constructor, Initializes a new instance of the class.
     * @param client    The ETClient object
     * @param endpoint  The endpoint URL
     * @param isAuthConnection      true is it is an auth connection, false otherwise
     * @throws com.exacttarget.fuelsdk.ETSdkException
    */
    public ETRestConnection(ETClient client, String endpoint, boolean isAuthConnection)
        throws ETSdkException
    {
        this.client = client;

        this.endpoint = endpoint;

        this.isAuthConnection = isAuthConnection;
    }

    /**
     * @param   path    The path to GET the response
     * @return  The Response object
     */
    public Response get(String path)
        throws ETSdkException
    {
        HttpURLConnection connection = null;
        try {
            Response response = new Response();
            connection = sendRequest(path, Method.GET);
            String json = receiveResponse(connection);
            response.setRequestId(connection.getHeaderField("X-Mashery-Message-ID"));
            response.setResponseCode(connection.getResponseCode());
            response.setResponseMessage(connection.getResponseMessage());
            response.setResponsePayload(json);
            return response;
        } catch (IOException ex) {
            throw new ETSdkException(ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * @param   path    The path to POST or create
     * @return  The Response object
     */
    public Response post(String path, String payload)
        throws ETSdkException
    {
        HttpURLConnection connection = null;
        try {
            Response response = new Response();
            connection = sendRequest(path, Method.POST, payload);
            String json = receiveResponse(connection);
            response.setRequestId(connection.getHeaderField("X-Mashery-Message-ID"));
            response.setResponseCode(connection.getResponseCode());
            response.setResponseMessage(connection.getResponseMessage());
            response.setResponsePayload(json);
            return response;
        } catch (IOException ex) {
            throw new ETSdkException(ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * @param   path    The path to PATCH or update
     * @return  The Response object
     */
    public Response patch(String path, String payload)
        throws ETSdkException
    {
        HttpURLConnection connection = null;
        try {
            Response response = new Response();
            connection = sendRequest(path, Method.PATCH, payload);
            String json = receiveResponse(connection);
            response.setRequestId(connection.getHeaderField("X-Mashery-Message-ID"));
            response.setResponseCode(connection.getResponseCode());
            response.setResponseMessage(connection.getResponseMessage());
            response.setResponsePayload(json);
            return response;
        } catch (IOException ex) {
            throw new ETSdkException(ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * @param   path    The path to DELETE
     * @return  The Response object
     */
    public Response delete(String path)
        throws ETSdkException
    {
        HttpURLConnection connection = null;
        try {
            Response response = new Response();
            connection = sendRequest(path, Method.DELETE);
            String json = receiveResponse(connection);
            response.setRequestId(connection.getHeaderField("X-Mashery-Message-ID"));
            response.setResponseCode(connection.getResponseCode());
            response.setResponseMessage(connection.getResponseMessage());
            response.setResponsePayload(json);
            return response;
        } catch (IOException ex) {
            throw new ETSdkException(ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private final static int URL_MAX_LENGTH = 2048;

    private HttpURLConnection sendRequest(String path, Method method)
        throws ETSdkException
    {
        if (path.length() > URL_MAX_LENGTH) {
            throw new ETSdkException(path + ": URL too long");
        }
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
        Gson gson = client.getGson();

        logger.debug(method + " " + url);
        String[] token = url.getPath().split("/");
        String object = "";
        if(StringUtils.isNumeric(token[token.length-1]))
            object = token[token.length-2];
        else 
            object = token[token.length-1];
        
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestProperty("User-Agent", "FuelSDK-Java-v1.4.0-REST-"+method+"-"+object);
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
          case PATCH:
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
            OutputStream os = null;
            try {
                os = connection.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
            } catch (IOException ex) {
                throw new ETSdkException("error writing " + url, ex);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException ex) {
                        throw new ETSdkException("error closing connection after writing " + url, ex);
                    }
                }
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
        Gson gson = client.getGson();

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
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new ETSdkException("error closing " + connection.getURL(), ex);
            }
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

    /**
     *  types of HTTP method that can be used for CRUD operation
     */
    public enum Method {
        GET, POST, PATCH, DELETE
    }
    
    /**
     * A <code>Response</code> represents salesforce marketing cloud REST API response.
     */
    public class Response {
        private String requestId = null;
        private Integer responseCode = null;
        private String responseMessage = null;
        private String responsePayload = null;

        /** 
        * @return The Request Identifier of the Response object.
        */    
        public String getRequestId() {
            return requestId;
        }

        /** 
        * @param    requestId    The Request Identifier of the Response object.
        */    
        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        /** 
        * @return The response code of the Response object.
        */    
        public Integer getResponseCode() {
            return responseCode;
        }

        /** 
        * @param responseCode   The response code of the Response object.
        */    
        public void setResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
        }

        /** 
        * @return The response message of the Response object.
        */    
        public String getResponseMessage() {
            return responseMessage;
        }

        /** 
        * @param  responseMessage   The response message of the Response object.
        */    
        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        /** 
        * @return The response payload of the Response object.
        */    
        public String getResponsePayload() {
            return responsePayload;
        }

        /** 
        * @param  responsePayload   The response payload of the Response object.
        */    
        public void setResponsePayload(String responsePayload) {
            this.responsePayload = responsePayload;
        }
    }
}
