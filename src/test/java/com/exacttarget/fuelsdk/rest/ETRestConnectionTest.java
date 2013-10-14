//
// ETRestConnectionTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETSdkException;

public class ETRestConnectionTest {
    private ETClient client = null;
    private ETConfiguration configuration = null;
    private String propertiesFile = "/fuelsdk.properties";

    @Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration(propertiesFile);
        client = new ETClient(configuration);
    }

    @Test
    public void testAuth()
        throws ETSdkException, IOException
    {
    	Properties properties = new Properties();
    	properties.load(getClass().getResourceAsStream(propertiesFile));
        
        int EID = Integer.parseInt(properties.getProperty("enterpriseId"));
        int OID = Integer.parseInt(properties.getProperty("organizationId"));
        int UID = Integer.parseInt(properties.getProperty("userId"));
        
        String json = client.getRESTConnection().get("/platform/v1/tokenContext");
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        int eid = jsonObject.get("enterprise").
                getAsJsonObject().get("id").getAsInt();
        assertEquals(EID, eid);
        int oid = jsonObject.get("organization").
                getAsJsonObject().get("id").getAsInt();
        assertEquals(OID, oid);
        int uid = jsonObject.get("user").
                getAsJsonObject().get("id").getAsInt();
        assertEquals(UID, uid);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBadEndpoint1()
        throws ETSdkException
    {
        thrown.expect(ETSdkException.class);
        thrown.expectCause(isA(MalformedURLException.class));
        ETConfiguration configuration = new ETConfiguration();
        configuration.setEndpoint("INVALID");
        client = new ETClient(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }

    @Test
    public void testBadEndpoint2()
        throws ETSdkException
    {
        thrown.expect(ETSdkException.class);
        thrown.expectCause(isA(IOException.class));
        configuration.setEndpoint("https://ww.exacttargetapis.com");
        client = new ETClient(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }

    @Test
    public void testBadAuthEndpoint1()
        throws ETSdkException
    {
        thrown.expect(ETSdkException.class);
        thrown.expectCause(isA(MalformedURLException.class));
        ETConfiguration configuration = new ETConfiguration();
        configuration.setAuthEndpoint("INVALID");
        client = new ETClient(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }
}
