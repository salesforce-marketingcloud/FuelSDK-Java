//
// This file is part of the Salesforce Marketing Cloud Java client library.
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

/**
 * <code>ETClient</code> is the central object in the Java
 * client library.
 */

public class ETClient {
    private static Logger logger = Logger.getLogger(ETClient.class);

    private static final String DEFAULT_PROPERTIES_FILE_NAME =
            "/fuelsdk.properties";
    private static final String DEFAULT_ENDPOINT =
            "https://www.exacttargetapis.com";
    private static final String DEFAULT_AUTH_ENDPOINT =
            "https://auth.exacttargetapis.com";
    private static final String PATH_REQUESTTOKEN =
            "/v1/requestToken";
    private static final String PATH_ENDPOINTS_SOAP =
            "/platform/v1/endpoints/soap";

    private ETConfiguration configuration = null;

    private String clientId = null;
    private String clientSecret = null;
    private String endpoint = null;
    private String authEndpoint = null;
    private String soapEndpoint = null;

    private ETRestConnection authConnection = null;
    private ETRestConnection restConnection = null;
    private ETSoapConnection soapConnection = null;

    private String accessToken = null;
    private int expiresIn = 0;
    private String refreshToken = null;

    private long tokenExpirationTime = 0;

    public ETClient()
        throws ETSdkException
    {
        this(DEFAULT_PROPERTIES_FILE_NAME);
    }

    public ETClient(String file)
        throws ETSdkException
    {
        this(new ETConfiguration(file));
    }

    public ETClient(ETConfiguration configuration)
        throws ETSdkException
    {
        this.configuration = configuration;

        clientId = configuration.get("clientId");
        if (clientId == null || clientId.equals("")) {
            throw new ETSdkException("clientId not specified");
        }
        clientSecret = configuration.get("clientSecret");
        if (clientSecret == null || clientSecret.equals("")) {
            throw new ETSdkException("clientSecret not specified");
        }

        endpoint = configuration.get("endpoint");
        if (endpoint == null || endpoint.equals("")) {
            endpoint = DEFAULT_ENDPOINT;
        }
        authEndpoint = configuration.get("authEndpoint");
        if (authEndpoint == null || authEndpoint.equals("")) {
            authEndpoint = DEFAULT_AUTH_ENDPOINT;
        }

        authConnection = new ETRestConnection(this, authEndpoint, true);

        restConnection = new ETRestConnection(this, endpoint);

        refreshToken();

        soapEndpoint = configuration.get("soapEndpoint");
        if (soapEndpoint == null || soapEndpoint.equals("")) {
            //
            // If a SOAP endpoint isn't specified automatically determine it:
            //

            ETRestConnection.Response response = restConnection.get(PATH_ENDPOINTS_SOAP);
            String responsePayload = response.getResponsePayload();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(responsePayload).getAsJsonObject();
            soapEndpoint = jsonObject.get("url").getAsString();
        }

        soapConnection = new ETSoapConnection(this, soapEndpoint);

        if (logger.isTraceEnabled()) {
            logger.trace("ETClient initialized:");
            logger.trace("  clientId = " + clientId);
            logger.trace("  clientSecret = " + clientSecret);
            logger.trace("  endpoint = " + endpoint);
            logger.trace("  authEndpoint = " + authEndpoint);
            logger.trace("  soapEndpoint = " + soapEndpoint);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ETConfiguration getConfiguration() {
        return configuration;
    }

    public ETRestConnection getRestConnection() {
        return restConnection;
    }

    public ETSoapConnection getSoapConnection() {
        return soapConnection;
    }

    /**
     * @deprecated
     * Legacy tokens are no longer supported.
     */
    @Deprecated
    public String getLegacyToken() {
        return null;
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

    public synchronized String refreshToken()
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

        Gson gson = restConnection.getGson();

        String requestPayload = gson.toJson(jsonObject);

        ETRestConnection.Response response = authConnection.post(PATH_REQUESTTOKEN, requestPayload);

        if (response.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new ETSdkException("error obtaining access token "
                                     + "("
                                     + response.getResponseCode()
                                     + " "
                                     + response.getResponseMessage()
                                     + ")");
        }

        //
        // Parse the JSON response into the appropriate instance
        // variables:
        //

        String responsePayload = response.getResponsePayload();

        JsonParser jsonParser = new JsonParser();
        jsonObject = jsonParser.parse(responsePayload).getAsJsonObject();
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

    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type)
        throws ETSdkException
    {
        // new String[0] = empty properties
        return retrieve(type, (ETFilter) null, null, null, new String[0]);
    }

    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type,
                                                          ETFilter filter,
                                                          String... properties)
        throws ETSdkException
    {
        return retrieve(type, filter, null, null, properties);
    }

    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type,
                                                          String filter,
                                                          String... properties)
        throws ETSdkException
    {
        // see also: ETDataExtension.select(filter, columns)
        ETFilter f = null;
        String[] p = properties;
        try {
            f = ETFilter.parse(filter);
        } catch (ETSdkException ex) {
            if (ex.getCause() instanceof ParseException) {
                //
                // The filter argument is actually a property. This is a bit
                // of a hack, but this method needs to handle the case of
                // both a filtered and a filterless retrieve with properties,
                // as having one method for each results in ambiguous methods.
                //

                p = new String[properties.length + 1];
                p[0] = filter;
                int i = 1;
                for (String property : properties) {
                    p[i++] = property;
                }
            } else {
                throw ex;
            }
        }
        return retrieve(type, f, null, null, p);
    }

    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type,
                                                          Integer page,
                                                          Integer pageSize,
                                                          String... properties)
        throws ETSdkException
    {
        return retrieve(type, (ETFilter) null, page, pageSize, properties);
    }

    @SuppressWarnings("unchecked")
    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type,
                                                          ETFilter filter,
                                                          Integer page,
                                                          Integer pageSize,
                                                          String... properties)
        throws ETSdkException
    {
        //
        // Get the retrieve method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) type.getSuperclass();

        Method retrieve = getMethod(superClass,
                                    "retrieve",
                                    ETClient.class,  // client
                                    ETFilter.class,  // filter
                                    Integer.class,   // page
                                    Integer.class,   // pageSize
                                    Class.class,     // type
                                    String[].class); // properties

        ETResponse<T> response = null;
        try {
            // first argument of null means method is static
            response = (ETResponse<T>) retrieve.invoke(null,
                                                       this,
                                                       filter,
                                                       page,
                                                       pageSize,
                                                       type,
                                                       properties);
        } catch (Exception ex) {
            throw new ETSdkException("error invoking retrieve method of type " + type, ex);
        }

        return response;
    }

    public <T extends ETApiObject> ETResponse<T> retrieve(Class<T> type,
                                                          String filter,
                                                          Integer page,
                                                          Integer pageSize,
                                                          String... properties)
        throws ETSdkException
    {
        return retrieve(type, ETFilter.parse(filter), page, pageSize, properties);
    }

    public <T extends ETApiObject> T retrieveObject(Class<T> type,
                                                    ETFilter filter,
                                                    String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObject();
    }

    public <T extends ETApiObject> T retrieveObject(Class<T> type,
                                                    String filter,
                                                    String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObject();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type);
        return response.getObjects();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type,
                                                           ETFilter filter,
                                                           String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObjects();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type,
                                                           String filter,
                                                           String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObjects();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type,
                                                           Integer page,
                                                           Integer pageSize,
                                                           String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, page, pageSize, properties);
        return response.getObjects();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type,
                                                           ETFilter filter,
                                                           Integer page,
                                                           Integer pageSize,
                                                           String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, page, pageSize, properties);
        return response.getObjects();
    }

    public <T extends ETApiObject> List<T> retrieveObjects(Class<T> type,
                                                           String filter,
                                                           Integer page,
                                                           Integer pageSize,
                                                           String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, page, pageSize, properties);
        return response.getObjects();
    }

    public <T extends ETApiObject> ETResponse<T> create(T... objects)
        throws ETSdkException
    {
        return createUpdateDelete("create", Arrays.asList(objects));
    }

    public <T extends ETApiObject> ETResponse<T> create(List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete("create", objects);
    }

    public <T extends ETApiObject> ETResponse<T> update(T... objects)
        throws ETSdkException
    {
        return createUpdateDelete("update", Arrays.asList(objects));
    }

    public <T extends ETApiObject> ETResponse<T> update(List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete("update", objects);
    }

    public <T extends ETApiObject> ETResponse<T> delete(T... objects)
        throws ETSdkException
    {
        return createUpdateDelete("delete", Arrays.asList(objects));
    }

    public <T extends ETApiObject> ETResponse<T> delete(List<T> objects)
        throws ETSdkException
    {
        return createUpdateDelete("delete", objects);
    }

    public <T extends ETApiObject> ETResponse<T> update(Class<T> type,
                                                        String filter,
                                                        String... values)
        throws ETSdkException
    {
        // XXX optimize

        ETResponse<T> response = retrieve(type, filter);

        // XXX assert operators is "="?

        List<T> objects = response.getObjects();
        for (T object : objects) {
            for (String value : values) {
                ETFilter parsedFilter = ETFilter.parse(value);
                Field field = object.getField(parsedFilter.getProperty());
                try {
                    // briefly change accessibility so we can set value
                    boolean isAccessible = field.isAccessible();
                    if (!isAccessible) {
                        field.setAccessible(true);
                    }
                    field.set(object, parsedFilter.getValue());
                    field.setAccessible(isAccessible);
                } catch (Exception ex) {
                    throw new ETSdkException("could not update property \""
                            + parsedFilter.getProperty()
                            + "\" to value \""
                            + value
                            + "\" on object "
                            + object,
                            ex);
                }
            }
        }

        return update(objects);
    }

    public <T extends ETApiObject> ETResponse<T> delete(Class<T> type,
                                                        String filter)
        throws ETSdkException
    {
        // XXX optimize
        ETResponse<T> response = retrieve(type, filter);
        return delete(response.getObjects());
    }

    private <T extends ETApiObject> ETResponse<T> createUpdateDelete(String method,
                                                                     List<T> objects)
        throws ETSdkException
    {
        //
        // Get the appropriate method from the superclass
        // of T (all methods are found in the superclass):
        //

        @SuppressWarnings("unchecked")
        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method create = getMethod(superClass, method, ETClient.class, List.class);

        return invokeMethod(create, objects);
    }

    private <T extends ETApiObject> Method getMethod(Class<T> type, String name, Class<?>... arguments)
        throws ETSdkException
    {
        Method method = null;

        try {
            method = type.getDeclaredMethod(name, arguments);
        } catch (Exception ex) {
            throw new ETSdkException("error getting "
                                     + name
                                     + " method of type "
                                     + type, ex);
        }

        return method;
    }

    @SuppressWarnings("unchecked")
    private <T extends ETApiObject> ETResponse<T> invokeMethod(Method method, List<T> arguments)
        throws ETSdkException
    {
        ETResponse<T> response = null;

        try {
            response = (ETResponse<T>) method.invoke(null, this, arguments);
        } catch (Exception ex) {
            throw new ETSdkException("error invoking "
                                     + method.getName()
                                     + " method of object "
                                     + arguments, ex);
        }

        return response;
    }
}
