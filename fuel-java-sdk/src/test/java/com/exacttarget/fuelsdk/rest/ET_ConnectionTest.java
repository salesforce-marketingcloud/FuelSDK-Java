//
// ET_ConnectionTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.rest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.exacttarget.fuelsdk.ET_Configuration;
import com.exacttarget.fuelsdk.ET_SDKException;

public class ET_ConnectionTest {
    private ET_Connection connection = null;

    @Before
    public void setUp()
        throws ET_SDKException
    {
        connection = new ET_Connection(new ET_Configuration("/fuelsdk-test.properties"));
    }

    @Test
    public void testAuth()
        throws ET_SDKException
    {
        int EID = 10212759;
        int OID = 10212759;
        int UID = 10737950;
        String json = connection.get("/platform/v1/tokenContext");
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
    public void testBadURL()
        throws ET_SDKException
    {
        thrown.expect(ET_SDKException.class);
        thrown.expectMessage("INVALID/platform/v1/tokenContext: bad URL");
        ET_Configuration configuration = new ET_Configuration();
        configuration.setEndpoint("INVALID");
        connection = new ET_Connection(configuration);
        connection.get("/platform/v1/tokenContext");
    }
}
