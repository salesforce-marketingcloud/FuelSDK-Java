/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.*;
import com.exacttarget.fuelsdk.annotations.*;
import com.exacttarget.fuelsdk.internal.*;

import org.junit.*;
import org.junit.runners.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETSubscriberTest
{
    private static ETClient client = null;
    private ETSubscriber etsub = null;
    
    private static String unique = "";
    private String email = "sharif.ahmed@salesforce.com";
    private String sid = "247972618";

//    public ETSubscriberTest(){
    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
        try {
            client = new ETClient("fuelsdk.properties");
            unique = UUID.randomUUID().toString();
        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void getAllSubscribers() // throws ETSdkException
    {
        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("emailAddress");
            etf.addProperty("status");
            etf.addProperty("preferredEmailType");            
            
            ETResponse<ETSubscriber> response = client.retrieve(ETSubscriber.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.toString());
        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void getSubscriber() //throws ETSdkException
    {
        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("emailAddress");
            etf.addProperty("status");
            etf.addProperty("preferredEmailType");
            
            ETExpression exp = new ETExpression();
            exp.setProperty("id");
            exp.setOperator("=");
            exp.setValue(sid);
            etf.setExpression(exp);
            
            ETResponse<ETSubscriber> response = client.retrieve(ETSubscriber.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getObjectId(), sid);
        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void createSubscriber() //throws ETSdkException
    {
        try{
            System.out.println(unique);
            etsub = new ETSubscriber();
            etsub.setKey(unique);
            etsub.setEmailAddress(email);
            //etsub.setPreferredEmailType(ETEmail.Type.TEXT);
            etsub.setStatus(ETSubscriber.Status.ACTIVE);
            ETProfileAttribute attrib = new ETProfileAttribute();
//            Attribute attrib = new Attribute();
            attrib.setName("TestName");
            attrib.setValue("TestValue");
            ArrayList<ETProfileAttribute> al = new ArrayList<ETProfileAttribute>();
//            ArrayList<Attribute> al = new ArrayList<Attribute>();
            al.add(attrib);
            
//            Attribute[] at = new Attribute[1];
//            at[0] = attrib;

            etsub.setAttributes(al);
//            etsub.setClient(client);
//            etsub.attributes = new ArrayList<Attribute>(); //al;
//            etsub.attributes.add(new Attribute());
            
            ETResponse<ETSubscriber> response = client.create(etsub);
            System.out.println("resp="+ response.toString());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Created Subscriber.");

            assertEquals(result.getObject().getKey(), unique);
            assertEquals(result.getObject().getEmailAddress(), email);
            assertEquals(result.getObject().getStatus(), ETSubscriber.Status.ACTIVE);
        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void deleteSubscriber() throws ETSdkException
    {
//        try{
            etsub = new ETSubscriber();
            etsub.setKey(unique);
            //etsub.setKey("ceb035e6-5cf1-4beb-a4db-118e274a562c");
            //etsub.setId("247972618");
            
            ETResponse<ETSubscriber> response = client.delete(etsub);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Subscriber deleted");
            
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    
    
    @Test
    @SuppressWarnings("deprecation")    
    public void cupdateSubscriber() throws ETSdkException
    {
//        try{
            etsub = new ETSubscriber();
            etsub.setKey(unique);
//            etsub.setKey("01bbe391-b33a-404f-934c-11faca683243");
            //etsub.setId("247972618");
            etsub.setStatus(ETSubscriber.Status.UNSUBSCRIBED);
            
            ETProfileAttribute attrib = new ETProfileAttribute();
            attrib.setName("TestName");
            attrib.setValue("TestValue");
            ArrayList<ETProfileAttribute> al = new ArrayList<ETProfileAttribute>();
            al.add(attrib);

            etsub.setAttributes(al);            
            
            ETResponse<ETSubscriber> response = client.update(etsub);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.toString());
            
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Updated Subscriber.");

            assertEquals(result.getObject().getKey(), unique);
            assertEquals(result.getObject().getStatus(), ETSubscriber.Status.UNSUBSCRIBED);            
            
//         } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
/*    public static void main(String[] args){
        //System.out.println("Hello World");
        ETSubscriberTest etst = new ETSubscriberTest();
//        etst.getAllSubscribers();
        etst.createSubscriber();
        //etst.getAllSubscribers();
        //etst.deleteSubscriber();
//        etst.getSubscriber();
//        etst.cupdateSubscriber();
//        etst.getSubscriber();
    }
*/
}
