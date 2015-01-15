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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

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

        refreshToken();

        restConnection = new ETRestConnection(this, endpoint);

        soapEndpoint = configuration.get("soapEndpoint");
        if (soapEndpoint == null || soapEndpoint.equals("")) {
            //
            // If a SOAP endpoint isn't specified automatically determine it:
            //

            String response = restConnection.get(PATH_ENDPOINTS_SOAP);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
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

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> create(T object)
        throws ETSdkException
    {
        return create(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> create(List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the create method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method create = getMethod(superClass, "create", ETClient.class, List.class);

        response = invokeMethod(create, objects);

        return response;
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type)
        throws ETSdkException
    {
        // new String[0] = empty properties
        return retrieve(type, (ETFilter) null, null, null, new String[0]);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
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

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       ETFilter filter,
                                                       String... properties)
        throws ETSdkException
    {
        return retrieve(type, filter, null, null, properties);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       Integer page,
                                                       Integer pageSize,
                                                       String... properties)
        throws ETSdkException
    {
        return retrieve(type, (ETFilter) null, page, pageSize, properties);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       String filter,
                                                       Integer page,
                                                       Integer pageSize,
                                                       String... properties)
        throws ETSdkException
    {
        return retrieve(type, ETFilter.parse(filter), page, pageSize, properties);
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
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

    public <T extends ETObject> T retrieveObject(Class<T> type,
                                                 String filter,
                                                 String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObject();
    }

    public <T extends ETObject> List<T> retrieveObjects(Class<T> type)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type);
        return response.getObjects();
    }

    public <T extends ETObject> List<T> retrieveObjects(Class<T> type,
                                                        String filter,
                                                        String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, properties);
        return response.getObjects();
    }

    public <T extends ETObject> List<T> retrieveObjects(Class<T> type,
                                                        String filter,
                                                        Integer page,
                                                        Integer pageSize,
                                                        String... properties)
        throws ETSdkException
    {
        ETResponse<T> response = retrieve(type, filter, page, pageSize, properties);
        return response.getObjects();
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> update(T object)
        throws ETSdkException
    {
        return update(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> update(List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the update method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method update = getMethod(superClass, "update", ETClient.class, List.class);

        response = invokeMethod(update, objects);

        return response;
    }

    public <T extends ETObject> ETResponse<T> update(Class<T> type,
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

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> delete(T object)
        throws ETSdkException
    {
        return delete(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<T> delete(List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the delete method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method delete = getMethod(superClass, "delete", ETClient.class, List.class);

        response = invokeMethod(delete, objects);

        return response;
    }

    public <T extends ETObject> ETResponse<T> delete(Class<T> type,
                                                     String filter)
        throws ETSdkException
    {
        // XXX optimize
        ETResponse<T> response = retrieve(type, filter);
        return delete(response.getObjects());
    }

    private <T extends ETObject> Method getMethod(Class<T> type, String name, Class<?>... arguments)
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
    private <T extends ETObject> ETResponse<T> invokeMethod(Method method, List<T> arguments)
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
}
