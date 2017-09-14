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


public class ETTriggeredSendSummaryTest {
    
    private static ETClient client = null;
    private String tid = "cc4fba86-e25c-e711-80d2-1402ec6b9528";
    
    public ETTriggeredSendSummaryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
    }
    
    @Test
    public void getAllSummary() throws ETSdkException
    {
        ETResponse<ETTriggeredSendSummary> response = client.retrieve(ETTriggeredSendSummary.class);
        //System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());         
        
        //ETResult<ETTriggeredSendSummary> result = response.getResult();
        //System.out.println("res="+ result.toString());
//        assertNotNull(result.getObject());
        
    }     
    
    @Test
    public void getOneSummary() throws ETSdkException
    {
        ETResponse<ETTriggeredSendSummary> response = client.retrieve(ETTriggeredSendSummary.class, "id="+tid);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());         
        
        ETResult<ETTriggeredSendSummary> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getObject().getId(), tid);
//        assertNotNull(result.getObject());
        
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
