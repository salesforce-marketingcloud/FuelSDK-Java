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
public class ETBounceEventTest {
    private static ETClient client = null;
    private String tid = "23e0aa49-0273-e711-80d2-1402ec6b9528";
    
    public ETBounceEventTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
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
     public void getAllBounces() throws ETSdkException {
         
        ETResponse<ETBounceEvent> response = client.retrieve(ETBounceEvent.class);
        System.out.println("resp="+ response.toString());  
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");        

        ETResult<ETBounceEvent> result = response.getResult();
        System.out.println("res="+ result.toString());           
     }
     
     @Test
     public void getOneBounce() throws ETSdkException {
        ETResponse<ETBounceEvent> response = client.retrieve(ETBounceEvent.class, "triggeredSendDefinitionObjectID="+tid);
        System.out.println("resp="+ response.toString());  
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");   
        
        ETResult<ETBounceEvent> result = response.getResult();
        System.out.println("res="+ result.toString());     
        assertEquals(result.getObject().getTriggeredSendDefinitionObjectID(), tid);   
     }
          
}
