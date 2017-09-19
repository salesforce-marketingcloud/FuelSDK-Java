/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETContentAreaTest {
    private ETClient client = null;
    private ETContentArea content = null;
    
    private static String unique = "";
    private String caname = "2b4167b4-b61b-4f42-9f44-8b5c06a1ffa8";
    private String nameCreate = "";
    
    public ETContentAreaTest() throws ETSdkException{
        client = new ETClient("fuelsdk.properties");
        //unique = UUID.randomUUID().toString();        
    }
    
    @Test
    public void getAllContentArea() throws ETSdkException
    {
        ETResponse<ETContentArea> response = client.retrieve(ETContentArea.class);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ETContentArea> result = response.getResult();
        System.out.println("res="+ result.toString());
    }    
    
    @Test
    public void getOneContentArea() throws ETSdkException
    {
        ETResponse<ETContentArea> response = client.retrieve(ETContentArea.class, "name="+caname);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        assertNotNull(response.getRequestId());

        ETResult<ETContentArea> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getObject().getName(), caname);            
    }    

    @Test
    public void _01_createContentArea() throws ETSdkException
    {
        String name = "Content from Java SDK";
        String body = "<b>Some HTML Content</b>";

        content = new ETContentArea();
        content.setCustomerKey(unique);
        content.setName(unique);
        content.setContent(body);

        ETResponse<ETContentArea> response = client.create(content);
        System.out.println("resp="+ response.toString());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");

        ETResult<ETContentArea> result = response.getResult();
        System.out.println("res="+ result.toString());

        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getStatus().toString(), "OK");
        assertEquals(result.getResponseMessage(), "Content created");

        assertEquals(result.getObject().getName(), unique);
        assertEquals(result.getObject().getContent(), body);
            
    }
    
    @Test
    public void _03_deleteContentArea() throws ETSdkException
    {
        content = new ETContentArea();
        content.setCustomerKey(unique);

        ETResponse<ETContentArea> response = client.delete(content);
        System.out.println("resp="+ response.toString());
        assertNotNull(response.getRequestId());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");        

        ETResult<ETContentArea> result = response.getResult();
        System.out.println("res="+ result.toString());

        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "Content deleted");
    }
    
    @Test
    public void _02_updateContentArea() throws ETSdkException
    {
        String body = "Update content from Java SDK";
        content = new ETContentArea();
        content.setCustomerKey(unique);

        content.setContent(body);

        ETResponse<ETContentArea> response = client.update(content);
        System.out.println("resp="+ response.toString());
        assertNotNull(response.getRequestId());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");        

        ETResult<ETContentArea> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "ContentArea updated");

        assertEquals(result.getObject().getContent(), body);
    }    
    
    @BeforeClass
    public static void setUpClass() {
        unique = UUID.randomUUID().toString();        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
