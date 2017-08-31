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

public class ETSubscriberListTest {
    private static ETClient client = null;
    private ETSubscriber etsub = null;
    private ETSubscriber ets;
    private ETList list = null;
    private ETSubscriberList etsublist = null;
    
    private static String unique = "";
    private String email = "sharif.ahmed@gmail.com";
    private String sid = "247972618";    
    
    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
//    public ETSubscriberListTest(){
//        try {
            client = new ETClient("fuelsdk.properties");
            //unique = UUID.randomUUID().toString();
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
        
    }
/*    
    public ETSubscriber createSubscriber() throws ETSdkException
    {
//        try{
            etsub = new ETSubscriber();
            unique = UUID.randomUUID().toString();
            etsub.setKey(unique);
            etsub.setEmailAddress(email);
            etsub.setStatus(ETSubscriber.Status.UNSUBSCRIBED);

            ETProfileAttribute attrib = new ETProfileAttribute();
            attrib.setName("Test Name");
            attrib.setValue(UUID.randomUUID().toString());
            ArrayList<ETProfileAttribute> al = new ArrayList<ETProfileAttribute>();
            al.add(attrib);
            al.add(new ETProfileAttribute("FirstName", UUID.randomUUID().toString()));
            etsub.setAttributes(al);
            
            ETResponse<ETSubscriber> response = client.create(etsub);
            System.out.println("resp="+ response.toString());
            
            ETResult<ETSubscriber> result = response.getResult();
            System.out.println("res="+ result.getObject());
            
            return result.getObject();
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    public ETList createList() throws ETSdkException
    {
//        try {
            String name = "List from Java SDK";
            String desc = "List test description";
            unique = UUID.randomUUID().toString();
            
            list = new ETList();
            list.setKey(unique);
            list.setName(name);
            list.setDescription(desc);
            list.setType(ETList.Type.PRIVATE);
            
            ets = createSubscriber();
//            list.subscribers = new ArrayList<ETSubscriber>();
//            list.subscribers.add(ets);
            
            ArrayList<ETSubscriber> al = new ArrayList<ETSubscriber>();
            al.add(ets);
            list.subscribers = al;            
            
            ETResponse<ETList> response = client.create(list);
            System.out.println("resp="+ response.toString());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.getObject());
            
            return result.getObject();
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    public void createSubscriberList() throws ETSdkException
    {
        ETList etl = createList();
        System.out.println("etl="+etl);
        
        if(etl == null) return;
        //ets = createSubscriber();
//        
//        ArrayList<ETSubscriber> al = new ArrayList<ETSubscriber>();
//        al.add(ets);
//        etl.subscribers = al;
//        
//        System.out.println("List="+etl);
//        System.out.println("SUb="+ets);

        etsublist = new ETSubscriberList();
        etsublist.setList(etl);
//        etsublist.setSubscriber(ets);
        
//        ArrayList<ETSubscriberList> al2 = new ArrayList<ETSubscriberList>();
//        al2.add(etsublist);
//        ets.lists = al2;
        
        etsublist.setStatus(ETSubscriber.Status.ACTIVE);
        
        ETResponse<ETSubscriberList> response = client.create(etsublist);
        System.out.println("resp="+ response.toString());

        ETResult<ETSubscriberList> result = response.getResult();
        System.out.println("res="+ result.toString());        
    }
*/    
    @Test
    public void getAllSubscriberList() throws ETSdkException
    {
        ETFilter etf = new ETFilter();
        etf.addProperty("id");
//        etf.addProperty("action");
//        etf.addProperty("emailAddress");
        etf.addProperty("status");
//        etf.addProperty("preferredEmailType");            

        ETResponse<ETSubscriberList> response = client.retrieve(ETSubscriberList.class, etf);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ETSubscriberList> result = response.getResult();
        System.out.println("res="+ result.toString());        
    }
    
    @Test
    public void getSubscriberList() throws ETSdkException
    {
        ETFilter etf = new ETFilter();
        etf.addProperty("id");
//        etf.addProperty("action");
//        etf.addProperty("list");
        etf.addProperty("status");
//        etf.addProperty("preferredEmailType");   

        ETExpression exp = new ETExpression();
        exp.setProperty("id");
        exp.setOperator("=");
        exp.setValue(sid);
        etf.setExpression(exp);

        ETResponse<ETSubscriberList> response = client.retrieve(ETSubscriberList.class, etf);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ETSubscriberList> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getObjectId(), sid);
    }
    
/*    public void deleteSubscriberList() throws ETSdkException
    {
        etsublist = new ETSubscriberList();
        etsublist.setId(sid);
        
        ETResponse<ETSubscriberList> response = client.delete(etsublist);
        System.out.println("resp="+ response.toString());

        ETResult<ETSubscriberList> result = response.getResult();
        System.out.println("res="+ result.toString());         
    }

    public static void main(String[] args) throws ETSdkException{
        //System.out.println("Hello World");
        ETSubscriberListTest etst = new ETSubscriberListTest();
//        etst.getAllSubscribers();
        //etst.createSubscriberList();
        etst.getAllSubscriberList();
        //etst.deleteSubscriberList();
//        etst.getSubscriberList();
//        etst.cupdateSubscriber();
//        etst.getSubscriber();
    }    
*/    
}
