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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETCampaignAssetTest {
    private static ETClient client = null;
    private ETCampaign etc = null;
    private ETCampaignAsset etca = null;
    
    private static String unique = "";
    private String cid = "25349";

    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {    
            client = new ETClient("fuelsdk.properties");
            unique = UUID.randomUUID().toString();        
    }
    
    @Test
    public void getAllCampaign() throws ETSdkException
    {
            ETResponse<ETCampaignAsset> response = client.retrieve(ETCampaignAsset.class);
            
            ETResult<ETCampaignAsset> result = response.getResult();
            assertNotNull(result.getObject());
    }    
    
    @Test
    public void createCampaignAsset() throws ETSdkException
    {
            String name = "JavaSDK";
            String desc = "Campaign created from Fuel Java SDK";
            System.out.println("in create: uniq="+unique);
            
            etc = new ETCampaign();
            etc.setName(name);
            etc.setDescription(desc);
            etc.setCode(unique);
            ETResponse<ETCampaign> resp = client.create(etc);
            //System.out.println("resp="+ response.toString());
            String campid = resp.getObject().getId();
    
            etca = new ETCampaignAsset();
            etca.setCampaignId(campid);
            etca.setId("32798");
            etca.setType("Email");

            ETResponse<ETCampaignAsset> response = client.create(etca);

            ETResult<ETCampaignAsset> result = response.getResult();
            assertNotNull(result.getObject());
    }    
 
}
