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


public class ETSentEventTest {
    private ETClient client = null;    
    private String email = "smunuswami@salesforce.com";
    
    public ETSentEventTest() throws ETSdkException {
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
     public void getAllSents() throws ETSdkException {
        ETResponse<ETSentEvent> response = client.retrieve(ETSentEvent.class);
        System.out.println("resp="+ response.toString());    
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");   
        assertNotNull(response.getRequestId());         
     }
     
     @Test
     public void getOneSent() throws ETSdkException {
        ETResponse<ETSentEvent> response = client.retrieve(ETSentEvent.class, "subscriberKey="+email);
        System.out.println("resp="+ response.toString());  
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");   
        
        ETResult<ETSentEvent> result = response.getResult();
        System.out.println("res="+ result.toString());     
        assertEquals(result.getObject().getSubscriberKey(), email);   
     }     
}
