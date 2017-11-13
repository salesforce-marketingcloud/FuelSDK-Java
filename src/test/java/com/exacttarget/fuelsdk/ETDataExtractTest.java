/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.internal.ExtractOptions;
import com.exacttarget.fuelsdk.internal.ExtractParameter;
import com.exacttarget.fuelsdk.internal.ExtractRequest;
import com.exacttarget.fuelsdk.internal.ExtractRequestMsg;
import com.exacttarget.fuelsdk.internal.ExtractResponseMsg;
import com.exacttarget.fuelsdk.internal.ExtractResult;
import com.exacttarget.fuelsdk.internal.Soap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;


public class ETDataExtractTest {
    private static ETClient client = null;
    private static String unique = "";
    private static SimpleDateFormat sdf;
    
    private String extractName = "Data Extension Extract";
    private String extractName2 = "Tracking Extract";

    private String deCustKey = "017dce26-b61f-43c2-bb15-0e46de82d177";
    
    @BeforeClass
    public static void setUpClass() throws ETSdkException {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    }    
    
   @Test
    public void ExtractDataExtensionTest() throws Exception
    {
        try {
            ETDataExtract etde = new ETDataExtract(client);
            
            etde.setDECustomerKey(deCustKey);
            etde.setOutputFileName("java-DE-"+unique+".csv");
            ExtractResponseMsg resp = etde.extractDataExtension();
            assertNotNull(resp.getRequestID());
            assertEquals(resp.getOverallStatus(), "OK");
            
//            for(ExtractResponseMsg.Results r: resp.getResults()){
//                ExtractResult er = r.getExtractResult();
//                assertEquals(er.getRequest().getID(), etde.extractType.get(extractName));
//                System.out.println("id="+er.getRequest().getID());
//            }
            
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }        
    }    

    @Test
    public void ExtractTrackingDataTest() throws Exception
    {
            ETDataExtract etde = new ETDataExtract(client);
            String start = "2017-05-01 12:00 AM";
            String end = "2017-09-01 12:00 AM";

        
            etde.setOutputFileName("java-track-"+unique+".zip");
            etde.setStartDate(sdf.parse(start));
            etde.setEndDate(sdf.parse(end));
            ExtractResponseMsg resp = etde.extractTrackingData();
            assertNotNull(resp.getRequestID());
            assertEquals(resp.getOverallStatus(), "OK");
            System.out.println("req id="+resp.getRequestID());
            System.out.println("status="+resp.getOverallStatus());
            
//            for(ExtractResponseMsg.Results r: resp.getResults()){
//                ExtractResult er = r.getExtractResult();
//                assertEquals(er.getRequest().getID(), etde.extractType.get(extractName2));
//                System.out.println("id="+er.getRequest().getID());
//            }
            
    }     

}
