/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.*;
import com.exacttarget.fuelsdk.annotations.*;
import com.exacttarget.fuelsdk.internal.*;

import org.joda.time.DateTime;
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
    //private String testSubscriberId = "242583795";
    private String testSubscriberId = "242584399";

    private ETClient defClient;
    String[] subscriberProperties = { "id", "key", "emailAddress", "status", "preferredEmailType" };

    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
        client = new ETClient("fuelsdk.properties");
        unique = UUID.randomUUID().toString();
    }

    @Test
    public void testGapBug() throws ETSdkException
    {
        testGetSubscriberEmail(testSubscriberId, "");
    }

//    private ETClient getClient() throws ETSdkException {
//        if (defClient == null) {
//            System.out.println("Creating client");
//            String clientId = config.getClientIds().get("PARENT");
//            String clientSecret = config.getClientSecret().get("PARENT");
//            ETConfiguration configuration = new ETConfiguration();
//            configuration.set("clientId", clientId);
//            configuration.set("clientSecret", clientSecret);
//            configuration.set("cxfReceiveTimeout", "120000");
//            configuration.set("cxfConnectTimeout", "120000");
//            configuration.set("accessType", "offline");
//            defClient = new ETClient(configuration);
//        }
//        return defClient;
//    }

    @Test
    public void testGapBugPerformance()
    {
        for (int i = 0; i < 4000; i++) {
            long before = DateTime.now().getMillis();
            long after = 0;
            try{
                //ETResponse<ETSubscriber> response = client.retrieve(ETSubscriber.class, filter);
                //result = response.getResult();
                testGetSubscriberEmail(testSubscriberId, "");
                after = DateTime.now().getMillis();
                System.out.println("Successful fuelsdk call, took "+ (after-before));
            }
            catch(ETSdkException e){
                System.out.println(e);
                after = DateTime.now().getMillis();
                System.out.println("Exception in fuelsdk call, took "+ (after-before));
            }
        }
    }


    public String testGetSubscriberEmail(String subscriberId, String brandMarket) throws ETSdkException {
        //ETClient client = getClient();
        ETExpression expression = new ETExpression();
        expression.setProperty("id");
        expression.setOperator(ETExpression.Operator.EQUALS);
        expression.addValue(subscriberId);
        ETFilter filter = new ETFilter();
        for (String property : subscriberProperties) {
            filter.addProperty(property);
        }
        filter.setExpression(expression);
        ETSubscriber sub = client.retrieveObject(ETSubscriber.class, filter);
        return sub.getEmailAddress();
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
