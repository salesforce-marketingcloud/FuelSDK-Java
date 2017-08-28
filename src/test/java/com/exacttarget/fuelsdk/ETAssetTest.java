/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETAssetTest {
    private static ETClient client = null;
    private static String unique = "";

    private ETAsset asset = null;
    private String aid = "90840";

    public ETAssetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }
    
    @Test
    public void getAllAsset() throws ETSdkException
    {
        ETResponse<ETAsset> response = client.retrieve(ETAsset.class);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "200");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());         
        
        ETResult<ETAsset> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertNotNull(result.getObject());
        
    }    
    
    @Test
    public void getAsset() throws ETSdkException
    {
        ETResponse<ETAsset> response = client.retrieve(ETAsset.class, "id="+aid);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "200");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());        
        
        ETResult<ETAsset> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getObjectId(), aid);

    }
    
}
