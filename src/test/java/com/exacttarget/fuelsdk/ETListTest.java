/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.*;
import com.exacttarget.fuelsdk.annotations.*;

import org.junit.*;
import org.junit.runners.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETListTest
{
    private static ETClient client = null;
    private ETList list = null;
    private String lid = "411309";
    private static String unique = "";
    
//    public ETListTest(){
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
    public void getAllList() throws ETSdkException
    {
//        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("name");
            etf.addProperty("description");
            etf.addProperty("type");
            
            ETResponse<ETList> response = client.retrieve(ETList.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.toString());
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }
    
    @Test
    @SuppressWarnings("deprecation")    
    public void getList() throws ETSdkException
    {
//        try {
            ETFilter etf = new ETFilter();
            etf.addProperty("id");
            etf.addProperty("key");
            etf.addProperty("name");
            etf.addProperty("description");
            etf.addProperty("type");

            ETExpression exp = new ETExpression();
            exp.setProperty("id");
            exp.setOperator("=");
            exp.setValue(lid);
            etf.setExpression(exp);
            
            ETResponse<ETList> response = client.retrieve(ETList.class, etf);
            System.out.println("resp="+ response.toString());
            assertEquals(response.getResponseCode(), "OK");
            assertEquals(response.getResponseMessage(), "OK");
            assertNotNull(response.getRequestId());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getObjectId(), lid);
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void a1_createList() throws ETSdkException
    {
//        try {
            String name = "List from Java SDK";
            String desc = "List test description";
            
            list = new ETList();
            list.setKey(unique);
            list.setName(name);
            list.setDescription(desc);
            list.setType(ETList.Type.PRIVATE);
            
            ETResponse<ETList> response = client.create(list);
            System.out.println("resp="+ response.toString());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Created List.");
            
            assertEquals(result.getObject().getName(), name);
            assertEquals(result.getObject().getDescription(), desc);
            
            assertEquals(result.getObject().getType(), ETList.Type.PRIVATE);
            assertEquals(result.getObject().getKey(), unique);
//        } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void a3_deleteList() throws ETSdkException
    {
//        try{
            list = new ETList();
            list.setKey(unique);
            
            ETResponse<ETList> response = client.delete(list);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "List deleted");            
//         } catch (ETSdkException ex) { ex.printStackTrace(); }
    }

    @Test
    @SuppressWarnings("deprecation")    
    public void a2_updateList() throws ETSdkException
    {
//        try {
            String name = "Update list name from Java SDK";
            list = new ETList();
            list.setKey(unique);
            list.setName(name);
        
            ETResponse<ETList> response = client.update(list);
            System.out.println("resp="+ response.toString());
            assertNotNull(response.getRequestId());
            
            ETResult<ETList> result = response.getResult();
            System.out.println("res="+ result.toString());
            assertEquals(result.getResponseCode(), "OK");
            assertEquals(result.getResponseMessage(), "Updated List.");
            
            assertEquals(result.getObject().getName(), name);
            assertEquals(result.getObject().getKey(), unique);
//         } catch (ETSdkException ex) { ex.printStackTrace(); }
        
    }
    
 /*   public static void main(String[] args){
        System.out.println("Hello World");
        ETListTest etlt = new ETListTest();
        //etlt.getAllList();
        etlt.createList();
        etlt.getList();
//        etlt.deleteList();
        //etlt.updateList();
        //etlt.getList();
//        etlt.getEmail();
    }
*/
}
