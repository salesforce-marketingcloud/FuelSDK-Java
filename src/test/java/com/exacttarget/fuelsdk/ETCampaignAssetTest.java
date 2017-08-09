/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import org.junit.runners.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;

public class ETCampaignAssetTest {
    private static ETClient client = null;
    private ETCampaign etc = null;
    private ETCampaignAsset etca = null;
    
    private static String unique = "";
    private String cid = "25349";

    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {    
//    public ETCampaignAssetTest() throws ETSdkException{
            client = new ETClient("fuelsdk.properties");
//            etc = new ETCampaign();
            unique = UUID.randomUUID().toString();        
    }
    
    @Test
    public void getAllCampaign() throws ETSdkException
    {
//        try {
            ETResponse<ETCampaignAsset> response = client.retrieve(ETCampaignAsset.class);
            //System.out.println("resp="+ response.toString());
//            assertEquals(response.getResponseCode(), "200");
//            assertEquals(response.getResponseMessage(), "OK");
//            assertNotNull(response.getRequestId());            
            
            ETResult<ETCampaignAsset> result = response.getResult();
            //System.out.println("res="+ result.toString());
            assertNotNull(result.getObject());
            //assertEquals(result.getObjectId(), cid);
//        } catch (ETSdkException ex) {
//            ex.printStackTrace();
//        }
    }    
    
    @Test
    public void createCampaignAsset() throws ETSdkException
    {
//        try{
            String name = "JavaSDK";
            String desc = "Campaign created from Fuel Java SDK";
            System.out.println("in create: uniq="+unique);
            
            etc = new ETCampaign();
            etc.setName(name);
            etc.setDescription(desc);
            etc.setCode(unique);
    //        etts.setClient(client);
            ETResponse<ETCampaign> resp = client.create(etc);
            //System.out.println("resp="+ response.toString());
            String campid = resp.getObject().getId();
    
            etca = new ETCampaignAsset();
            etca.setCampaignId(campid);
            etca.setId("32798");
            etca.setType("Email");
            //etca.setKey("");


            ETResponse<ETCampaignAsset> response = client.create(etca);
            //System.out.println("resp="+ response.toString());
            
            
//            assertEquals(response.getResponseCode(), "200");
//            assertEquals(response.getResponseMessage(), "OK");
//            assertNotNull(response.getRequestId());            

            ETResult<ETCampaignAsset> result = response.getResult();
            //System.out.println("res="+ result.toString());
            //System.out.println("asset="+result.getObject());
            assertNotNull(result.getObject());
            
//            cidCreate = result.getObjectId();
//            cidCreate = result.getField("");
//            System.out.println("cid="+cidCreate);
            //System.out.println(unique + "===" + result.getObject().getCode());
            
//            assertEquals(result.getResponseCode(), "200");
//            assertEquals(result.getResponseMessage(), "OK");
//            assertEquals(result.getObject().getName(), name);
//            assertEquals(result.getObject().getDescription(), desc);
            
//         } catch (ETSdkException ex) {
//            ex.printStackTrace();
//        }
    }    
    
/*    public static void main(String[] args){
        try {
            System.out.println("Hello World");
            ETCampaignAssetTest etct = new ETCampaignAssetTest();
            //etct.getAllCampaign();
            //etct.getCampaign();
            etct.createCampaignAsset();
            //etct.deleteCampaign();
            //etct.updateCampaign();
        } catch (ETSdkException ex) {
            Logger.getLogger(ETCampaignAssetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
*/    
}
