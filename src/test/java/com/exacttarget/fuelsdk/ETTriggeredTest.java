/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import com.exacttarget.fuelsdk.internal.*;
import com.exacttarget.fuelsdk.annotations.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETTriggeredTest {
    
    private static ETClient client = null;
    private static ETTriggeredEmail ette = null;
    private ETEmail email = null;
    private String tsName = "TEXTEXT";
    private String emailID = "200188";
    private String tsCustomerKey = "ab75c996-a6d9-4f7b-b96b-5fd46b8d53a1";
    private String key = "5966835c9ade3";

    private String addresses = "sharif.ahmed@salesforce.com";
    private static String unique = "";
    
    private ETTriggeredEmail etts = null;
    private SendClassification sc = null;
    
//    public ETTriggeredTest(){
    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {        try {
            client = new ETClient("fuelsdk.properties");
            ette = new ETTriggeredEmail();
            unique = UUID.randomUUID().toString();
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @Test
    public void a1_getAllTSD(){
        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("name");
            etf.addProperty("description");
            etf.addProperty("folderId");
            etf.addProperty("email");
            etf.addProperty("subject");
            etf.addProperty("priority");
            etf.addProperty("status");
            
            ETResponse<ETTriggeredEmail> response = client.retrieve(ETTriggeredEmail.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());            
            
            
            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @Test
    public void a6_getTSD(){
        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("name");
            etf.addProperty("description");
            etf.addProperty("folderId");
            etf.addProperty("email");
            etf.addProperty("subject");
            etf.addProperty("priority");
            etf.addProperty("status");

            ETExpression exp = new ETExpression();
            exp.setProperty("customerKey");
            exp.setOperator("=");
            exp.setValue(unique);
            etf.setExpression(exp);            
            
            ETResponse<ETTriggeredEmail> response = client.retrieve(ETTriggeredEmail.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());            
//            assertEquals(response.getObject().getEmail().getSubject(), "Test Subject from Java SDK");
            
            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getObject().getKey(), unique);
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }    
    
    @Test
    public void a2_pauseTSD(){
        try {
            ette.setKey(tsName);
            ette.setStatus(ETTriggeredEmail.Status.INACTIVE);
            ETResponse<ETTriggeredEmail> response = client.update(ette);
            System.out.println("resp="+ response.getResponseMessage());
            assertEquals(response.getObject().getStatus(), ETTriggeredEmail.Status.INACTIVE);
            
            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Test
    public void a3_startTSD(){
        try {
            ette.setKey(tsName);
            ette.setStatus(ETTriggeredEmail.Status.ACTIVE);
            ETResponse<ETTriggeredEmail> response = client.update(ette);
            System.out.println("resp="+ response.getResponseMessage());
            assertEquals(response.getObject().getStatus(), ETTriggeredEmail.Status.ACTIVE);
            
            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Test
    public void a4_sendTSD(){
        try {
            ette.setKey(tsName);
            ette.setClient(client);
            ETResponse<ETTriggeredEmail> response = ette.send(addresses);
            System.out.println("resp="+ response.getResponseMessage());

            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Created TriggeredSend");            
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
        
    @Test
    public void a5_createTSD(){
        try {
            
            etts = new ETTriggeredEmail();

            etts.setKey(unique);
            etts.setName(unique);

            sc = new SendClassification();
            sc.setCustomerKey("Default Commercial");
            etts.setSendClassification(sc);

            email = new ETEmail();
            email.setId(emailID);
            etts.setEmail(email);
            
            etts.setClient(client);

            ETResponse<ETTriggeredEmail> response = client.create(etts);
            System.out.println("resp="+ response.toString());

            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "TriggeredSendDefinition created");
            
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @Test
    public void a7_deleteTSD(){
        
        try{
            ette.setKey(unique);
            ETResponse<ETTriggeredEmail> response = client.delete(ette);
            System.out.println("resp="+ response.toString());
            ETResult<ETTriggeredEmail> result = response.getResult();
            System.out.println("res="+ result.toString());

            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "TriggeredSendDefinition deleted");            
        } catch (ETSdkException ex) {
            Logger.getLogger(ETTriggeredTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }        
    }
    
/*    public static void main(String[] args){
        System.out.println("Hello World");
        ETTriggeredTest et3 = new ETTriggeredTest();
        et3.createTSD();
        et3.deleteTSD();
        et3.pauseTSD();
        et3.getAllTSD();
        et3.getTSD();
        et3.startTSD();
        et3.sendTSD();
    }    */
}
