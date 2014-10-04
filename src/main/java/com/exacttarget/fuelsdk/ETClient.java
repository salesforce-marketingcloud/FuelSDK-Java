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

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.internal.FilterPart;

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

    /**
     * @deprecated
     * Use getRestConnection().
     */
    @Deprecated
    public ETRestConnection getRESTConnection() {
        return getRestConnection();
    }

    public ETSoapConnection getSoapConnection() {
        return soapConnection;
    }

    /**
     * @deprecated
     * Use getSoapConnection().
     */
    @Deprecated
    public ETSoapConnection getSOAPConnection() {
        return getSoapConnection();
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> create(T object)
        throws ETSdkException
    {
        return create(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> create(List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the create method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method create = getMethod(superClass, "create", ETClient.class, List.class);

        return response = (ETResponse<ETResult>) invokeMethod(create, objects);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type)
        throws ETSdkException
    {
        // new String[0] = empty properties
        return retrieve(type, (FilterPart) null, null, null, new String[0]);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       String filter,
                                                       String... properties)
        throws ETSdkException
    {
        FilterPart f = null;
        String[] p = properties;
        try {
            f = parseFilter(filter);
        } catch (ETSdkException ex) {
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
        }
        return retrieve(type, f, null, null, p);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       Integer page,
                                                       Integer pageSize,
                                                       String... properties)
        throws ETSdkException
    {
        return retrieve(type, (FilterPart) null, page, pageSize, properties);
    }

    public <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                       String filter,
                                                       Integer page,
                                                       Integer pageSize,
                                                       String... properties)
        throws ETSdkException
    {
        return retrieve(type, parseFilter(filter), page, pageSize, properties);
    }

    @SuppressWarnings("unchecked")
    private <T extends ETObject> ETResponse<T> retrieve(Class<T> type,
                                                        FilterPart filter,
                                                        Integer page,
                                                        Integer pageSize,
                                                        String... properties)
        throws ETSdkException
    {
        //
        // Get the retrieve method from the superclass of
        // the superclass of T (unlike create, update, and
        // delete, which are implemented in the ETRestObject
        // or ETSoapObject class, retrieve is implemented
        // in ETRestObjectImmutable or ETSoapObjectImmutable):
        //

        Class<T> superClass = (Class<T>) type.getSuperclass();
        Class<T> superSuperClass = (Class<T>) superClass.getSuperclass();

        Method retrieve = getMethod(superSuperClass,
                                    "retrieve",
                                    ETClient.class,   // client
                                    FilterPart.class, // filter
                                    Integer.class,    // page
                                    Integer.class,    // pageSize
                                    Class.class,      // type
                                    String[].class);  // properties

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
        } catch (InvocationTargetException ex) {
            // throw ex.getCause() since this exception
            // type means the caller threw an exception
            throw (ETSdkException) ex.getCause();
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke retrieve method on class " + type, ex);
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> update(T object)
        throws ETSdkException
    {
        return update(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> update(List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the update method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method update = getMethod(superClass, "update", ETClient.class, List.class);

        return response = (ETResponse<ETResult>) invokeMethod(update, objects);
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> delete(T object)
        throws ETSdkException
    {
        return delete(Arrays.asList(object));
    }

    @SuppressWarnings("unchecked")
    public <T extends ETObject> ETResponse<ETResult> delete(List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
            return response;
        }

        //
        // Get the delete method from the superclass of
        // T (all methods are found in the superclass):
        //

        Class<T> superClass = (Class<T>) objects.get(0).getClass().getSuperclass();

        Method delete = getMethod(superClass, "delete", ETClient.class, List.class);

        return response = (ETResponse<ETResult>) invokeMethod(delete, objects);
    }

    /**
     * @deprecated
     * Use XXX
     */
    public ETDataExtension retrieveDataExtension(ETFilter filter)
        throws ETSdkException
    {
        // XXX
        return null;
    }

    /**
     * @deprecated
     * Use XXX
     */
    public List<ETDataExtension> retrieveDataExtensions()
        throws ETSdkException
    {
        // XXX
        return null;
    }

    /**
     * @deprecated
     * Use XXX
     */
    public List<ETDataExtension> retrieveDataExtensions(ETFilter filter)
        throws ETSdkException
    {
        // XXX
        return null;
    }

    private <T extends ETObject> Method getMethod(Class<T> type, String name, Class<?>... arguments)
        throws ETSdkException
    {
        Method method = null;

        try {
            method = type.getDeclaredMethod(name, arguments);
        } catch (Exception ex) {
            throw new ETSdkException("could not get "
                                     + name
                                     + " method of "
                                     + type, ex);
        }

        return method;
    }

    @SuppressWarnings("unchecked")
    private <T extends ETObject> ETResponse<?> invokeMethod(Method method, List<T> arguments)
        throws ETSdkException
    {
        ETResponse<?> response = null;

        try {
            response = (ETResponse<ETResult>) method.invoke(null, this, arguments);
        } catch (Exception ex) {
            throw new ETSdkException("could not invoke "
                                     + method.getName()
                                     + " method on object "
                                     + arguments, ex);
        }

        return response;
    }

    private FilterPart parseFilter(String filter)
        throws ETSdkException
    {
        ETFilterParser parser = new ETFilterParser(new ByteArrayInputStream(filter.getBytes()));
        FilterPart filterPart = null;
        try {
            filterPart = parser.parse();
        } catch (ParseException ex) {
            throw new ETSdkException("could not parse filter: " + filter, ex);
        }
        return filterPart;
    }
}
