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

/**
 *
 * @author sharif.ahmed
 */
public class ETClickEventTest {
    private static ETClient client = null;
    
    public ETClickEventTest() throws ETSdkException {
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
     public void getAllClicks() throws ETSdkException {
        ETResponse<ETClickEvent> response = client.retrieve(ETClickEvent.class);
        System.out.println("resp="+ response.toString());    
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");   
        assertNotNull(response.getRequestId());         

     }
}
