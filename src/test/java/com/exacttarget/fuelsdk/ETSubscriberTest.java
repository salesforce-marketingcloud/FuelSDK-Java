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

    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void getAllSubscribers() throws ETSdkException
    {
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
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void getSubscriber() throws ETSdkException
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
    public void createSubscriber() throws ETSdkException
    {
        System.out.println(unique);
        etsub = new ETSubscriber();
        etsub.setKey(unique);
        etsub.setEmailAddress(email);
        etsub.setStatus(ETSubscriber.Status.ACTIVE);
        ETProfileAttribute attrib = new ETProfileAttribute();
        attrib.setName("TestName");
        attrib.setValue("TestValue");
        ArrayList<ETProfileAttribute> al = new ArrayList<ETProfileAttribute>();
        al.add(attrib);

        etsub.setAttributes(al);

        ETResponse<ETSubscriber> response = client.create(etsub);
        System.out.println("resp="+ response.toString());

        ETResult<ETSubscriber> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "Created Subscriber.");

        assertEquals(result.getObject().getKey(), unique);
        assertEquals(result.getObject().getEmailAddress(), email);
        assertEquals(result.getObject().getStatus(), ETSubscriber.Status.ACTIVE);
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void deleteSubscriber() throws ETSdkException
    {
        etsub = new ETSubscriber();
        etsub.setKey(unique);

        ETResponse<ETSubscriber> response = client.delete(etsub);
        System.out.println("resp="+ response.toString());
        assertNotNull(response.getRequestId());

        ETResult<ETSubscriber> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "Subscriber deleted");
            
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void cupdateSubscriber() throws ETSdkException
    {
            etsub = new ETSubscriber();
            etsub.setKey(unique);
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
    }

}
