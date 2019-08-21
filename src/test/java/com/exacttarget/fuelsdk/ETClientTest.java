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

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ETClientTest {

    private static ETClient client;

    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testBackwardCompatibility1()
        throws ETSdkException
    {
        ETFilter filter = new ETFilter();
        ETExpression exp = new ETExpression();

        exp.setProperty("key");
        exp.setOperator(ETExpression.Operator.EQUALS);
        exp.addValue("dataextension_default");
        filter.setExpression(exp);
        
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        filter);
        assertIsDataExtensionFolder(response);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testBackwardCompatibility2()
        throws ETSdkException
    {
        ETFilter filter = new ETFilter();
        ETExpression exp = new ETExpression();

        exp.setProperty("key");
        exp.setOperator(ETExpression.Operator.EQUALS);
        exp.addValue("dataextension_default");

        filter.setExpression(exp);
        filter.addProperty("key");

        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        filter);//,"key");
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertNull(folder.getId());
        assertEquals("dataextension_default", folder.getKey());
        assertNull(folder.getName());
        assertNull(folder.getDescription());
        assertNull(folder.getCreatedDate());
        assertNull(folder.getModifiedDate());
        assertNull(folder.getContentType());
        assertNull(folder.getParentFolderKey());
        assertNull(folder.getIsActive());
        assertNull(folder.getIsEditable());
        assertNull(folder.getAllowChildren());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testBackwardCompatibility3()
        throws ETSdkException
    {
        ETFilter filter = new ETFilter();
        ETExpression exp = new ETExpression();

        exp.setProperty("key");
        exp.setOperator(ETExpression.Operator.EQUALS);
        exp.addValue("dataextension_default");

        filter.setExpression(exp);
        filter.addProperty("key");
        filter.addProperty("name");

        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        filter);//,"key","name");
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertNull(folder.getId());
        assertEquals("dataextension_default", folder.getKey());
        assertEquals("Data Extensions", folder.getName());
        assertNull(folder.getDescription());
        assertNull(folder.getCreatedDate());
        assertNull(folder.getModifiedDate());
        assertNull(folder.getContentType());
        assertNull(folder.getParentFolderKey());
        assertNull(folder.getIsActive());
        assertNull(folder.getIsEditable());
        assertNull(folder.getAllowChildren());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testBackwardCompatibility4()
        throws ETSdkException
    {
        ETFilter filter = new ETFilter();
        ETExpression exp = new ETExpression();

        exp.setProperty("key");
        exp.setOperator(ETExpression.Operator.EQUALS);
        exp.addValue("dataextension_default");

        filter.setExpression(exp);
        filter.addProperty("key");
        filter.addProperty("name");
        filter.addProperty("description");

        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        filter);//,"key","name", "description");
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertNull(folder.getId());
        assertEquals("dataextension_default", folder.getKey());
        assertEquals("Data Extensions", folder.getName());
        assertEquals("", folder.getDescription());
        assertNull(folder.getCreatedDate());
        assertNull(folder.getModifiedDate());
        assertNull(folder.getContentType());
        assertNull(folder.getParentFolderKey());
        assertNull(folder.getIsActive());
        assertNull(folder.getIsEditable());
        assertNull(folder.getAllowChildren());
    }

    @Test
    public void testSoapEndpointCaching()
            throws ETSdkException, NoSuchFieldException, IllegalAccessException {
        ETClient client1 = new ETClient("fuelsdk.properties");
        ETClient client2 = new ETClient("fuelsdk.properties");

        long instance1SoapEndpointExpiration = getInstanceSoapEndpointExpiration(client1);
        String instance1FetchedSoapEndpoint = getFetchedSoapEndpoint(client1);

        long instance2SoapEndpointExpiration = getInstanceSoapEndpointExpiration(client2);
        String instance2FetchedSoapEndpoint = getFetchedSoapEndpoint(client2);

        // check if cache was used
        if(instance1FetchedSoapEndpoint != null && instance2FetchedSoapEndpoint != null) {
            assertTrue(instance1SoapEndpointExpiration > 0);
            assertTrue(instance2SoapEndpointExpiration > 0);
            assertEquals(instance1SoapEndpointExpiration, instance2SoapEndpointExpiration);
        }
        else
        {
            assertTrue(instance1SoapEndpointExpiration == 0);
            assertTrue(instance2SoapEndpointExpiration == 0);
        }
    }

    @Test
    public void isNullOrBlankOrEmpty_shouldReturnTrue_whenInputIsNullPointer(){

        String nullPointer = null;
        assertTrue(ETClient.isNullOrBlankOrEmpty(nullPointer));
    }

    @Test
    public void isNullOrBlankOrEmpty_shouldReturnTrue_whenInputIsBlankString(){

        String blankString = "    ";
        assertTrue(ETClient.isNullOrBlankOrEmpty(blankString));
    }

    @Test
    public void isNullOrBlankOrEmpty_shouldReturnTrue_whenInputIsEmptyString(){

        String emptyString = "";
        assertTrue(ETClient.isNullOrBlankOrEmpty(emptyString));
    }

    @Test
    public void isNullOrBlankOrEmpty_shouldReturnFalse_whenInputIsNotNullOrBlankOrEmptyString(){

        String notNullOrBlankOrEmptyString = "NotNullOrBlankOrEmptyString";
        assertFalse(ETClient.isNullOrBlankOrEmpty(notNullOrBlankOrEmptyString));
    }

    @Test
    public void createPayload_shouldReturnPayloadWithPublicAppAttributes_whenApplicationTypeIsPublic(){
        ETConfiguration configuration = client.getConfiguration();

        configuration.set("applicationType", "public");
        configuration.set("redirectURI", "redirectURI");
        configuration.set("authorizationCode", "authorizationCode");

        JsonObject payload = client.createPayload(configuration);

        assertEquals(configuration.get("clientId"), payload.get("client_id").getAsString());
        assertEquals(configuration.get("redirectURI"), payload.get("redirect_uri").getAsString());
        assertEquals(configuration.get("authorizationCode"), payload.get("code").getAsString());
        assertEquals("authorization_code", payload.get("grant_type").getAsString());
    }

    @Test
    public void createPayload_shouldReturnPayloadWithNoClientSecret_whenApplicationTypeIsPublic(){
        ETConfiguration configuration = client.getConfiguration();

        configuration.set("applicationType", "public");
        configuration.set("redirectURI", "redirectURI");
        configuration.set("authorizationCode", "authorizationCode");

        JsonObject payload = client.createPayload(configuration);

        assertNull(payload.get("client_secret"));
    }

    @Test
    public void createPayload_shouldReturnPayloadWithWebAppAttributes_whenApplicationTypeIsWeb(){
        ETConfiguration configuration = client.getConfiguration();

        configuration.set("applicationType", "web");
        configuration.set("redirectURI", "redirectURI");
        configuration.set("authorizationCode", "authorizationCode");

        JsonObject payload = client.createPayload(configuration);

        assertEquals(configuration.get("clientId"), payload.get("client_id").getAsString());
        assertEquals(configuration.get("clientSecret"), payload.get("client_secret").getAsString());
        assertEquals(configuration.get("redirectURI"), payload.get("redirect_uri").getAsString());
        assertEquals(configuration.get("authorizationCode"), payload.get("code").getAsString());
        assertEquals("authorization_code", payload.get("grant_type").getAsString());
    }

    @Test
    public void createPayload_shouldReturnPayloadWithServerAppAttributes_whenApplicationTypeIsServer(){
        ETConfiguration configuration = client.getConfiguration();

        configuration.set("applicationType", "server");

        JsonObject payload = client.createPayload(configuration);

        assertEquals(configuration.get("clientId"), payload.get("client_id").getAsString());
        assertEquals(configuration.get("clientSecret"), payload.get("client_secret").getAsString());
        assertEquals("client_credentials", payload.get("grant_type").getAsString());
    }

    @Test
    public void createPayload_shouldReturnPayloadWithServerAppAttributes_whenApplicationTypeIsNotPassedInConfig(){
        ETConfiguration configuration = client.getConfiguration();

        JsonObject payload = client.createPayload(configuration);

        assertEquals(configuration.get("clientId"), payload.get("client_id").getAsString());
        assertEquals(configuration.get("clientSecret"), payload.get("client_secret").getAsString());
        assertEquals("client_credentials", payload.get("grant_type").getAsString());
    }

    @Test
    public void createPayload_shouldReturnPayloadWithNoRedirectURIandAuthCode_whenApplicationTypeIsServer(){
        ETConfiguration configuration = client.getConfiguration();

        configuration.set("applicationType", "server");

        JsonObject payload = client.createPayload(configuration);

        assertNull(payload.get("redirect_uri"));
        assertNull(payload.get("code"));
    }

    @Test
    public void createPayload_shouldReturnPayloadWithRefreshToken_whenClientHasRefreshToken() throws ETSdkException {
        ETClient client = new ETClient("fuelsdk.properties");
        ETConfiguration configuration = client.getConfiguration();

        client.setRefreshToken("refreshToken");

        JsonObject payload = client.createPayload(configuration);

        assertEquals(client.getRefreshToken(), payload.get("refresh_token").getAsString());
        assertEquals("refresh_token", payload.get("grant_type").getAsString());
    }

    private String getFetchedSoapEndpoint(ETClient client) throws NoSuchFieldException, IllegalAccessException {
        Field field = client.getClass().getDeclaredField("fetchedSoapEndpoint");
        field.setAccessible(true);
        return (String) field.get(null);
    }

    private long getInstanceSoapEndpointExpiration(ETClient client) throws NoSuchFieldException, IllegalAccessException {
        Field field = client.getClass().getDeclaredField("soapEndpointExpiration");
        field.setAccessible(true);
        return field.getLong(null);
    }

    private DateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private void assertIsDataExtensionFolder(ETResponse<ETFolder> response) {
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertEquals("dataextension_default", folder.getKey());
        assertEquals("Data Extensions", folder.getName());
        assertEquals("", folder.getDescription());
        assertNull(folder.getParentFolderKey());
        assertTrue(folder.getIsActive());
        assertFalse(folder.getIsEditable());
        assertTrue(folder.getAllowChildren());
    }
}
