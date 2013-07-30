//
// ET_RESTConnectionTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.rest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.exacttarget.fuelsdk.ET_Client;
import com.exacttarget.fuelsdk.ET_Configuration;
import com.exacttarget.fuelsdk.ET_SDKException;

public class ET_RESTConnectionTest {
    private ET_Client client = null;
    private ET_Configuration configuration = null;

    @Before
    public void setUp()
        throws ET_SDKException
    {
        configuration = new ET_Configuration("/fuelsdk-test.properties");
        client = new ET_Client(configuration);
    }

    @Test
    public void testAuth()
        throws ET_SDKException
    {
        // XXX these values should be specified via properties too
        int EID = 10212759;
        int OID = 10212759;
        int UID = 10737950;
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
        throws ET_SDKException
    {
        thrown.expect(ET_SDKException.class);
        thrown.expectCause(isA(MalformedURLException.class));
        ET_Configuration configuration = new ET_Configuration();
        configuration.setEndpoint("INVALID");
        client = new ET_Client(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }

    @Test
    public void testBadEndpoint2()
        throws ET_SDKException
    {
        thrown.expect(ET_SDKException.class);
        thrown.expectCause(isA(IOException.class));
        configuration.setEndpoint("https://ww.exacttargetapis.com");
        client = new ET_Client(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }

    @Test
    public void testBadAuthEndpoint1()
        throws ET_SDKException
    {
        thrown.expect(ET_SDKException.class);
        thrown.expectCause(isA(MalformedURLException.class));
        ET_Configuration configuration = new ET_Configuration();
        configuration.setAuthEndpoint("INVALID");
        client = new ET_Client(configuration);
        client.getRESTConnection().get("/platform/v1/tokenContext");
    }
}
