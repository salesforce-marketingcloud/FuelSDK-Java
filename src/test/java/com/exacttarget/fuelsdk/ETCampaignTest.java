/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.*;
//import com.exacttarget.fuelsdk.annotations.*;

import org.junit.*;
import org.junit.runners.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETCampaignTest {
    private static ETClient client = null;
    private ETCampaign etc = null;
    
    private static String unique = "";
    private String cid = "25349";
    private static String cidCreate = "";
    
//    public ETCampaignTest(){
    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
//        try {
            client = new ETClient("fuelsdk.properties");
//            etc = new ETCampaign();
            unique = UUID.randomUUID().toString();
            unique = unique.substring(0, unique.length()-2);
//        } catch (ETSdkException ex) {
//            ex.printStackTrace();
//        }        
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void getAllCampaign() throws ETSdkException
    {
//        try {
            ETResponse<ETCampaign> response = client.retrieve(ETCampaign.class);
//            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "200");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());            
            
            ETResult<ETCampaign> result = response.getResult();
//            System.out.println("res="+ result.toString());
            //assertEquals(result.getObjectId(), cid);
//        } catch (ETSdkException ex) {
//            ex.printStackTrace();
//        }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void getCampaign() throws ETSdkException
    {
//        try {
            ETResponse<ETCampaign> response = client.retrieve(ETCampaign.class, "id="+cid);
//            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "200");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETCampaign> result = response.getResult();
//            System.out.println("res="+ result.toString());
            assertEquals(result.getObjectId(), cid);
//        } catch (ETSdkException ex) {
//            ex.printStackTrace();
//        }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void createCampaign() throws ETSdkException
    {
        try{
            String name = "JavaSDK";
            String desc = "Campaign created from Fuel Java SDK";
//            System.out.println("in create: uniq="+unique);
            
            etc = new ETCampaign();
            etc.setName(name);
            etc.setDescription(desc);
            etc.setCode(unique);

    //        etts.setClient(client);

            ETResponse<ETCampaign> response = client.create(etc);
//            System.out.println("resp="+ response.toString());
//            assertEquals(response.getResponseCode(), "200");
//            assertEquals(response.getResponseMessage(), "OK");
//            assertNotNull(response.getRequestId());            

            ETResult<ETCampaign> result = response.getResult();
//            System.out.println("res="+ result.toString());
            cidCreate = result.getObjectId();
//            cidCreate = result.getField("");
//            System.out.println("cid="+cidCreate);
//            System.out.println(unique + "===" + result.getObject().getCode());
            
            assertEquals(result.getResponseCode(), "200");
            assertEquals(result.getResponseMessage(), "OK");
           
            assertEquals(result.getObject().getName(), name);
            assertEquals(result.getObject().getDescription(), desc);
            //assertThat(unique, containsString(result.getObject().getCode()));
            
         } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void deleteCampaign() throws ETSdkException
    {
        try{
//            System.out.println("on delete: cid="+cidCreate);
//            System.out.println(junit.runner.Version.id());
            etc = new ETCampaign();
            etc.setId(cidCreate);
            
            ETResponse<ETCampaign> response = client.delete(etc);
//            System.out.println("resp="+ response.toString());
            
            ETResult<ETCampaign> result = response.getResult();
//            System.out.println("res="+ result.toString());            
            
            assertEquals(result.getResponseCode(), "200");
            assertEquals(result.getResponseMessage(), "OK");
            
         } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }
    
 /*
    @Test
    @SuppressWarnings("deprecation")     
    public void updateCampaign() throws ETSdkException
    {
        try{
            System.out.println("in update: uniq="+unique);
            System.out.println("on update: cid="+cidCreate);
            etc = new ETCampaign();
            //etc.setCode(unique);
            etc.setId(cidCreate);
            //etc.setCode("CSharpSDKCreatedForTEST");
            etc.setDescription("Description changed");
            //etc.setName("name change");
            //etc.setClient(client);
            
//            ETResponse<ETCampaign> response = client.update(ETCampaign.class, "id="+cidCreate, "description=changed");
            ETResponse<ETCampaign> response = client.update(etc);
            //etc.update(client, etc);

            System.out.println("resp="+ response.toString());
            
            ETResult<ETCampaign> result = response.getResult();
            System.out.println("res="+ result.toString());            
         } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }
   
    public static void main(String[] args){
        System.out.println("Hello World");
        ETCampaignTest etct = new ETCampaignTest();
        //etct.getAllCampaign();
        etct.getCampaign();
        //etct.createCampaign();
        //etct.deleteCampaign();
        etct.updateCampaign();
    }    
 */   
}
