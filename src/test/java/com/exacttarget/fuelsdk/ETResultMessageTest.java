/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;


public class ETResultMessageTest {
    
    private static ETClient client = null;
    
    public ETResultMessageTest(){}
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
    }

    @Test
    public void getAllResultMessage() throws ETSdkException
    {
        ETResponse<ETResultMessage> response = client.retrieve(ETResultMessage.class);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertEquals(response.getStatus().toString(), "OK");

        assertNotNull(response.getRequestId());         
        
    }     
    
}
