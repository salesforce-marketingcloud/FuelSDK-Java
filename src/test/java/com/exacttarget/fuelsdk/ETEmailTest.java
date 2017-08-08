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
public class ETEmailTest
{
    private static ETClient client = null;
    private ETEmail email = null;
    
    private static String unique = "";
    private String eid = "184222";
    private static String eidCreate = "";
    
//    public ETEmailTest(){
    @BeforeClass
    public static void setUpBeforeClass() throws ETSdkException
    {
//        try {
            client = new ETClient("fuelsdk.properties");
            unique = UUID.randomUUID().toString();
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void getAllEmail() throws ETSdkException
    {
//        try {
            ETResponse<ETEmail> response = client.retrieve(ETEmail.class);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
                        
            ETResult<ETEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void getEmail() throws ETSdkException
    {
//        try {
            ETResponse<ETEmail> response = client.retrieve(ETEmail.class, "id="+eid);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getObjectId(), eid);            
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void createEmail() throws ETSdkException
    {
//        try{
            String name = "Email from Java SDK";
            String body = "This is a test email";
            String sub = "Test Subject from Java SDK";
        
            email = new ETEmail();
            email.setName(name);
            email.setKey(unique);
            //email.setHtmlBody("");
            email.setTextBody(body);
            email.setSubject(sub);
            
            ETResponse<ETEmail> response = client.create(email);
            System.out.println("resp="+ response.toString());

            ETResult<ETEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Created Email.");
           
            assertEquals(result.getObject().getName(), name);
            assertEquals(result.getObject().getTextBody(), body);
            assertEquals(result.getObject().getSubject(), sub);
            
            System.out.println(unique + "===" + result.getObject().getKey());

            assertEquals(result.getObject().getKey(), unique);
            
//            eidCreate = result.getObjectId();
//            System.out.println("id="+eidCreate);
            
//        } catch(ETSdkException ex){ ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void deleteEmail() throws ETSdkException
    {
//        try{
            email = new ETEmail();
            email.setKey(unique);
            
            ETResponse<ETEmail> response = client.delete(email);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Email deleted");
            
//         } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")     
    public void updateEmail() throws ETSdkException
    {
//        try{
            String name = "Update Email name from Java SDK";
            email = new ETEmail();
            //email.setKey(unique);
            email.setId(eid);
            email.setName(name);

            ETResponse<ETEmail> response = client.update(email);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETEmail> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Updated Email.");
            
            assertEquals(result.getObject().getName(), name);
            assertEquals(result.getObject().getId(), eid);            
            
//         } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
 /*   public static void main(String[] args){
        System.out.println("Hello World");
        ETEmailTest etet = new ETEmailTest();
        etet.getAllEmail();
        //etet.getEmail();
//        etet.createEmail();
//        etet.deleteEmail();
        //etet.updateEmail();
        //etet.getEmail();
    }*/
}
