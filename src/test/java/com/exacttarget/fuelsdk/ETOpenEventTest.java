/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ETOpenEventTest {
    private ETClient client = null;    
    
    public ETOpenEventTest() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void getAllOpens() throws ETSdkException {
        ETResponse<ETUnsubEvent> response = client.retrieve(ETUnsubEvent.class);
        System.out.println("resp="+ response.toString());    
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");   
        assertNotNull(response.getRequestId());         
     }
}
