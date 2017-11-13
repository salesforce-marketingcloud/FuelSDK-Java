//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk;

import java.util.List;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static com.exacttarget.fuelsdk.ETResult.Status.OK;
import com.exacttarget.fuelsdk.internal.Attribute;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETDataExtensionTest {
    private static ETClient client = null;

    private static Integer dataExtensionFolderId = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
//        Assume.assumeNotNull(ETDataExtensionTest.class
//                .getResource("/fuelsdk-test.properties"));
        client = new ETClient("fuelsdk.properties");
        dataExtensionFolderId = new Integer(client.getConfiguration()
                .get("dataExtensionFolderId"));
//        System.out.println("folder="+dataExtensionFolderId);
    }

    private static String id = null;
    private static String sdeid = null;

    @Test
    public void _01_TestCreateSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey("test1");
        dataExtension.setName("test1");
        dataExtension.addColumn("CustomerID", true);
        dataExtension.addColumn("FullName");
        dataExtension.addColumn("FirstName");
        dataExtension.addColumn("LastName");
        dataExtension.addColumn("Age", ETDataExtensionColumn.Type.NUMBER);
        dataExtension.addColumn("EmailAddress", ETDataExtensionColumn.Type.EMAIL_ADDRESS);
        ETResponse<ETDataExtension> response = client.create(dataExtension);
        //System.out.println("resp="+response);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension created.", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        ETDataExtension de = result.getObject();
        assertNotNull(de.getId());
        assertEquals("test1", de.getKey());
        assertEquals("test1", de.getName());
        assertNull(de.getDescription());
        assertNull(de.getFolderId());
//        assertNull(de.getIsSendable());
        assertNull(de.getIsTestable());
        // save the ID for use in subsequent tests
        id = result.getObjectId();
        //System.out.println("id="+id);
    }
   /* 
    @Test
    public void _001_TestCreateSendable()
        throws ETSdkException
    {
        ETDataExtension sendableDataExtension = new ETDataExtension();
        sendableDataExtension.setKey("sendtest1");
        sendableDataExtension.setName("sendtest1");
        sendableDataExtension.setIsSendable(true);
        Attribute at = new Attribute();
        at.setName("Subscriber Key");
        at.setValue(null);
        sendableDataExtension.setSendableSubscriberField(at);
        
//        sendableDataExtension.addColumn("CustomerID", true);
//        sendableDataExtension.addColumn("FullName");
//        sendableDataExtension.addColumn("FirstName");
//        sendableDataExtension.addColumn("LastName");
//        sendableDataExtension.addColumn("Age", ETDataExtensionColumn.Type.NUMBER);
        sendableDataExtension.addColumn("EmailAddress", ETDataExtensionColumn.Type.EMAIL_ADDRESS, 100, null, null, true, true, "");
        sendableDataExtension.addColumn("FirstName", ETDataExtensionColumn.Type.TEXT);
        ETResponse<ETDataExtension> response = client.create(sendableDataExtension);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension created.", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        ETDataExtension de = result.getObject();
        assertNotNull(de.getId());
        assertEquals("test1", de.getKey());
        assertEquals("test1", de.getName());
        assertNull(de.getDescription());
        assertNull(de.getFolderId());
        assertNull(de.getIsSendable());
        assertNull(de.getIsTestable());
        // save the ID for use in subsequent tests
        id = result.getObjectId();
    }    */

    private static ETDataExtension dataExtension = null;

    @Test
    public void _02_TestRetrieveSingle()
        throws ETSdkException
    {
        ETResponse<ETDataExtension> response = client.retrieve(ETDataExtension.class, "id=" + id);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertNull(result.getRequestId());
        assertNull(result.getStatus());
        assertNull(result.getResponseCode());
        assertNull(result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertEquals(id, result.getObjectId());
        ETDataExtension de = result.getObject();
        assertEquals(id, de.getId());
        assertEquals("test1", de.getKey());
        assertEquals("test1", de.getName());
        assertEquals("", de.getDescription());
//        assertEquals(dataExtensionFolderId, de.getFolderId());
//        assertEquals(false, de.getIsSendable());
        assertEquals(false, de.getIsTestable());
        // save the object for use in subsequent tests
        dataExtension = de;
    }

    @Test
    public void _03_TestInsertSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = new ETDataExtensionRow();
        row.setColumn("CustomerID", "1");
        row.setColumn("FullName", "Ian Murdock");
        row.setColumn("FirstName", "Ian");
        row.setColumn("LastName", "Murdock");
        row.setColumn("Age", "41");
        row.setColumn("EmailAddress", "imurdock@example.com");
        ETResponse<ETDataExtensionRow> response = dataExtension.insert(row);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Created DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
        ETDataExtensionRow r = result.getObject();
        assertEquals("1", r.getColumn("CustomerID"));
        assertEquals("Ian Murdock", r.getColumn("FullName"));
        assertEquals("Ian", r.getColumn("FirstName"));
        assertEquals("Murdock", r.getColumn("LastName"));
        assertEquals("41", r.getColumn("Age"));
        assertEquals("imurdock@example.com", r.getColumn("EmailAddress"));
    }

    @Test
    public void _04_TestInsertMultipleRows()
        throws ETSdkException
    {
        ETDataExtensionRow row1 = new ETDataExtensionRow();
        row1.setColumn("CustomerID", "2");
        row1.setColumn("FullName", "Fred Flintstone");
        row1.setColumn("FirstName", "Fred");
        row1.setColumn("LastName", "Flintstone");
        row1.setColumn("Age", "36");
        row1.setColumn("EmailAddress", "fred@flintstone.com");
        ETDataExtensionRow row2 = new ETDataExtensionRow();
        row2.setColumn("CustomerID", "3");
        row2.setColumn("FullName", "Wilma Flintstone");
        row2.setColumn("FirstName", "Wilma");
        row2.setColumn("LastName", "Flintstone");
        row2.setColumn("Age", "34");
        row2.setColumn("EmailAddress", "wilma@flintstone.com");
        ETResponse<ETDataExtensionRow> response = dataExtension.insert(row1, row2);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        assertNull(result1.getRequestId());
        assertEquals(OK, result1.getStatus());
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Created DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETDataExtensionRow r1 = result1.getObject();
        assertEquals("2", r1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", r1.getColumn("FullName"));
        assertEquals("Fred", r1.getColumn("FirstName"));
        assertEquals("Flintstone", r1.getColumn("LastName"));
        assertEquals("36", r1.getColumn("Age"));
        assertEquals("fred@flintstone.com", r1.getColumn("EmailAddress"));
        ETResult<ETDataExtensionRow> result2 = response.getResults().get(1);
        assertNull(result2.getRequestId());
        assertEquals(OK, result2.getStatus());
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Created DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
        ETDataExtensionRow r2 = result2.getObject();
        assertEquals("3", r2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", r2.getColumn("FullName"));
        assertEquals("Wilma", r2.getColumn("FirstName"));
        assertEquals("Flintstone", r2.getColumn("LastName"));
        assertEquals("34", r2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", r2.getColumn("EmailAddress"));
    }

    private void testSelectAll(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
//        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
//        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("1", row1.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertEquals("41", row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("2", row2.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row2.getColumn("FullName"));
        assertEquals("Fred", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("36", row2.getColumn("Age"));
        assertEquals("fred@flintstone.com", row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertEquals("3", row3.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertEquals("34", row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _05_TestSelectAll()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select();
        testSelectAll(response);
    }

    @Test
    public void _06_TestSelectAllStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1");
        testSelectAll(response);
    }

    private void testSelectAllSingleColumnSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
//        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
//        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertNull(row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertNull(row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertNull(row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertNull(row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertNull(row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertNull(row3.getColumn("EmailAddress"));
    }

    @Test
    public void _07_TestSelectAllSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName");
        testSelectAllSingleColumnSpecified(response);
    }

    @Test
    public void _08_TestSelectAllSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName");
        testSelectAllSingleColumnSpecified(response);
    }

    private void testSelectAllMultipleColumnsSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
//        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
//        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertEquals("Fred", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertEquals("fred@flintstone.com", row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _09_TestSelectAllMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        testSelectAllMultipleColumnsSpecified(response);
    }

    @Test
    public void _10_TestSelectAllMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress");
        testSelectAllMultipleColumnsSpecified(response);
    }

    private void testSelectAllSorted(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
//        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
//        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("1", row2.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row2.getColumn("FullName"));
        assertEquals("Ian", row2.getColumn("FirstName"));
        assertEquals("Murdock", row2.getColumn("LastName"));
        assertEquals("41", row2.getColumn("Age"));
        assertEquals("imurdock@example.com", row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertEquals("3", row3.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertEquals("34", row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _11_TestSelectAllSorted()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("ORDER BY firstname");
        testSelectAllSorted(response);
    }

    @Test
    public void _12_TestSelectAllSortedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "ORDER BY firstname");
        testSelectAllSorted(response);
    }

    private void testSelectAllSortedSingleColumnSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertNull(row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertNull(row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertNull(row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertNull(row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertNull(row3.getColumn("FirstName"));
        assertEquals("Murdock", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertNull(row3.getColumn("EmailAddress"));
    }

    @Test
    public void _13_TestSelectAllSortedSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "ORDER BY LastName");
        testSelectAllSortedSingleColumnSpecified(response);
    }

    @Test
    public void _14_TestSelectAllSortedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName",
                                                                         "ORDER BY LastName");
        testSelectAllSortedSingleColumnSpecified(response);
    }

    private void testSelectAllSortedMultipleColumnsSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertEquals("Ian", row2.getColumn("FirstName"));
        assertEquals("Murdock", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertEquals("imurdock@example.com", row2.getColumn("EmailAddress"));
        ETDataExtensionRow row3 = rows.get(2);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _15_TestSelectAllSortedMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress",
                                                                       "ORDER BY FirstName");
        testSelectAllSortedMultipleColumnsSpecified(response);
    }

    @Test
    public void _16_TestSelectAllSortedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress",
                                                                         "ORDER BY FirstName");
        testSelectAllSortedMultipleColumnsSpecified(response);
    }

    private void testSelectAllPaginated(ETResponse<ETDataExtensionRow> response1,
                                        ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 2, response1.getPageSize());
        assertEquals((Integer) 3, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(2, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertEquals("1", row1.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertEquals("41", row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows1.get(1);
        assertEquals("2", row2.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row2.getColumn("FullName"));
        assertEquals("Fred", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("36", row2.getColumn("Age"));
        assertEquals("fred@flintstone.com", row2.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 2, response2.getPageSize());
        assertEquals((Integer) 3, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row3 = rows2.get(0);
        assertEquals("3", row3.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertEquals("34", row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _17_TestSelectAllPaginated()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2);
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2);
        testSelectAllPaginated(response1, response2);
    }

    @Test
    public void _18_TestSelectAllPaginatedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 2);
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 2);
        testSelectAllPaginated(response1, response2);
    }

    private void testSelectAllPaginatedSingleColumnSpecified(ETResponse<ETDataExtensionRow> response1,
                                                             ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 2, response1.getPageSize());
        assertEquals((Integer) 3, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(2, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertNull(row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertNull(row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows1.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertNull(row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertNull(row2.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 2, response2.getPageSize());
        assertEquals((Integer) 3, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row3 = rows2.get(0);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertNull(row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertNull(row3.getColumn("EmailAddress"));
    }

    @Test
    public void _19_TestSelectAllPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2,
                                                                        "LastName");
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2,
                                                                        "LastName");
        testSelectAllPaginatedSingleColumnSpecified(response1, response2);
    }

    @Test
    public void _20_TestSelectAllPaginatedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 2,
                                                                          "LastName");
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 2,
                                                                          "LastName");
        testSelectAllPaginatedSingleColumnSpecified(response1, response2);
    }

    private void testSelectAllPaginatedMultipleColumnsSpecified(ETResponse<ETDataExtensionRow> response1,
                                                                ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 2, response1.getPageSize());
        assertEquals((Integer) 3, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(2, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows1.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertEquals("Fred", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertEquals("fred@flintstone.com", row2.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 2, response2.getPageSize());
        assertEquals((Integer) 3, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row3 = rows2.get(0);
        assertNull(row3.getColumn("CustomerID"));
        assertNull(row3.getColumn("FullName"));
        assertEquals("Wilma", row3.getColumn("FirstName"));
        assertEquals("Flintstone", row3.getColumn("LastName"));
        assertNull(row3.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row3.getColumn("EmailAddress"));
    }

    @Test
    public void _21_TestSelectAllPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        testSelectAllPaginatedMultipleColumnsSpecified(response1, response2);
    }

    @Test
    public void _22_TestSelectAllPaginatedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 2,
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 2,
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        testSelectAllPaginatedMultipleColumnsSpecified(response1, response2);
    }

    private void testSelectFiltered(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _23_TestSelectFiltered()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone");
        testSelectFiltered(response);
    }

    @Test
    public void _24_TestSelectFilteredStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName=Flintstone");
        testSelectFiltered(response);
    }

    private void testSelectFilteredSingleColumnSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertNull(row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertNull(row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertNull(row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertNull(row2.getColumn("EmailAddress"));
    }

    @Test
    public void _25_TestSelectFilteredSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName");
        testSelectFilteredSingleColumnSpecified(response);
    }

    @Test
    public void _26_TestSelectFilteredSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName=Flintstone",
                                                                         "LastName");
        testSelectFilteredSingleColumnSpecified(response);
    }

    private void testSelectFilteredMultipleColumnsSpecified(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _27_TestSelectFilteredMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        testSelectFilteredMultipleColumnsSpecified(response);
    }

    @Test
    public void _28_TestSelectFilteredMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName=Flintstone",
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress");
        testSelectFilteredMultipleColumnsSpecified(response);
    }

    private void testSelectFilteredNotEquals(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("1", row1.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertEquals("41", row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _29_TestSelectFilteredNotEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName != Flintstone");
        testSelectFilteredNotEquals(response);
    }

    @Test
    public void _30_TestSelectFilteredNotEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName != Flintstone");
        testSelectFilteredNotEquals(response);
    }

    private void testSelectFilteredLessThan(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("3", row1.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row1.getColumn("FullName"));
        assertEquals("Wilma", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("34", row1.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _31_TestSelectFilteredLessThan()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age < 36");
        testSelectFilteredLessThan(response);
    }

    @Test
    public void _32_TestSelectFilteredLessThanStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age < 36");
        testSelectFilteredLessThan(response);
    }

    private void testSelectFilteredLessThanOrEquals(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _33_TestSelectFilteredLessThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age <= 36");
        testSelectFilteredLessThanOrEquals(response);
    }

    @Test
    public void _34_TestSelectFilteredLessThanOrEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age <= 36");
        testSelectFilteredLessThanOrEquals(response);
    }

    private void testSelectFilteredGreaterThan(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("1", row1.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertEquals("41", row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _35_TestSelectFilteredGreaterThan()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age > 36");
        testSelectFilteredGreaterThan(response);
    }

    @Test
    public void _36_TestSelectFilteredGreaterThanStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age > 36");
        testSelectFilteredGreaterThan(response);
    }

    private void testSelectFilteredGreaterThanOrEquals(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("1", row1.getColumn("CustomerID"));
        assertEquals("Ian Murdock", row1.getColumn("FullName"));
        assertEquals("Ian", row1.getColumn("FirstName"));
        assertEquals("Murdock", row1.getColumn("LastName"));
        assertEquals("41", row1.getColumn("Age"));
        assertEquals("imurdock@example.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("2", row2.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row2.getColumn("FullName"));
        assertEquals("Fred", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("36", row2.getColumn("Age"));
        assertEquals("fred@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _37_TestSelectFilteredGreaterThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age >= 36");
        testSelectFilteredGreaterThanOrEquals(response);
    }

    @Test
    public void _38_TestSelectFilteredGreaterThanOrEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age >= 36");
        testSelectFilteredGreaterThanOrEquals(response);
    }

    private void testSelectFilteredIn1(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("3", row1.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row1.getColumn("FullName"));
        assertEquals("Wilma", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("34", row1.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _39_TestSelectFilteredIn1()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35)");
        testSelectFilteredIn1(response);
    }

    @Test
    public void _40_TestSelectFilteredInStatic1()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age in (34, 35)");
        testSelectFilteredIn1(response);
    }

    private void testSelectFilteredIn2(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _41_TestSelectFilteredIn2()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36)");
        testSelectFilteredIn2(response);
    }

    @Test
    public void _42_TestSelectFilteredInStatic2()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age in (34, 35, 36)");
        testSelectFilteredIn2(response);
    }

    private void testSelectFilteredIn3(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _43_TestSelectFilteredIn3()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36, 37)");
        testSelectFilteredIn3(response);
    }

    @Test
    public void _44_TestSelectFilteredInStatic3()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age in (34, 35, 36, 37)");
        testSelectFilteredIn3(response);
    }

    private void testSelectFilteredBetween(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _45_TestSelectFilteredBetween()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age between 30 and 40");
        testSelectFilteredBetween(response);
    }

    @Test
    public void _46_TestSelectFilteredBetweenStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "Age between 30 and 40");
        testSelectFilteredBetween(response);
    }

    private void testSelectFilteredLike(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        ETDataExtensionRow row2 = rows.get(1);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));

    }

    @Test
    public void _47_TestSelectFilteredLike()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("lastname like 'Flint%'");
        System.out.println("resp="+ response.toString());
        testSelectFilteredLike(response);
    }

    @Test
    public void _48_TestSelectFilteredLikeStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "lastname like 'Flint%'");
        System.out.println("resp="+ response.toString());
        testSelectFilteredLike(response);
    }

    private void testSelectFilteredAnd(ETResponse<ETDataExtensionRow> response) {
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("3", row1.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row1.getColumn("FullName"));
        assertEquals("Wilma", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("34", row1.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _49_TestSelectFilteredAnd()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName = 'Flintstone' and FirstName = 'Wilma'");
        testSelectFilteredAnd(response);
    }

    @Test
    public void _50_TestSelectFilteredAndStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=test1",
                                                                         "LastName = 'Flintstone' and FirstName = 'Wilma'");
        testSelectFilteredAnd(response);
    }

    private void testSelectFilteredPaginated(ETResponse<ETDataExtensionRow> response1,
                                             ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 1, response1.getPageSize());
        assertEquals((Integer) 2, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(1, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertEquals("2", row1.getColumn("CustomerID"));
        assertEquals("Fred Flintstone", row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("36", row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 1, response2.getPageSize());
        assertEquals((Integer) 2, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row2 = rows2.get(0);
        assertEquals("3", row2.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertEquals("34", row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _51_TestSelectFilteredPaginated()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 1,
                                                                        "LastName=Flintstone");
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 1,
                                                                        "LastName=Flintstone");
        testSelectFilteredPaginated(response1, response2);
    }

    @Test
    public void _52_TestSelectFilteredPaginatedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 1,
                                                                          "LastName=Flintstone");
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 1,
                                                                          "LastName=Flintstone");
        testSelectFilteredPaginated(response1, response2);
    }

    private void testSelectFilteredPaginatedSingleColumnSpecified(ETResponse<ETDataExtensionRow> response1,
                                                                  ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 1, response1.getPageSize());
        assertEquals((Integer) 2, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(1, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertNull(row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertNull(row1.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 1, response2.getPageSize());
        assertEquals((Integer) 2, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row2 = rows2.get(0);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertNull(row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertNull(row2.getColumn("EmailAddress"));
    }

    @Test
    public void _53_TestSelectFilteredPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 1,
                                                                        "LastName=Flintstone",
                                                                        "LastName");
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 1,
                                                                        "LastName=Flintstone",
                                                                        "LastName");
        testSelectFilteredPaginatedSingleColumnSpecified(response1, response2);
    }

    @Test
    public void _54_TestSelectFilteredPaginatedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 1,
                                                                          "LastName=Flintstone",
                                                                          "LastName");
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 1,
                                                                          "LastName=Flintstone",
                                                                          "LastName");
        testSelectFilteredPaginatedSingleColumnSpecified(response1, response2);
    }

    private void testSelectFilteredPaginatedMultipleColumnsSpecified(ETResponse<ETDataExtensionRow> response1,
                                                                     ETResponse<ETDataExtensionRow> response2)
    {
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("OK", response1.getResponseCode());
        assertEquals("OK", response1.getResponseMessage());
        assertEquals((Integer) 1, response1.getPage());
        assertEquals((Integer) 1, response1.getPageSize());
        assertEquals((Integer) 2, response1.getTotalCount());
        assertTrue(response1.hasMoreResults());
        List<ETDataExtensionRow> rows1 = response1.getObjects();
        assertEquals(1, rows1.size());
        ETDataExtensionRow row1 = rows1.get(0);
        assertNull(row1.getColumn("CustomerID"));
        assertNull(row1.getColumn("FullName"));
        assertEquals("Fred", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertNull(row1.getColumn("Age"));
        assertEquals("fred@flintstone.com", row1.getColumn("EmailAddress"));
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("OK", response2.getResponseCode());
        assertEquals("OK", response2.getResponseMessage());
        assertEquals((Integer) 2, response2.getPage());
        assertEquals((Integer) 1, response2.getPageSize());
        assertEquals((Integer) 2, response2.getTotalCount());
        assertFalse(response2.hasMoreResults());
        List<ETDataExtensionRow> rows2 = response2.getObjects();
        assertEquals(1, rows2.size());
        ETDataExtensionRow row2 = rows2.get(0);
        assertNull(row2.getColumn("CustomerID"));
        assertNull(row2.getColumn("FullName"));
        assertEquals("Wilma", row2.getColumn("FirstName"));
        assertEquals("Flintstone", row2.getColumn("LastName"));
        assertNull(row2.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row2.getColumn("EmailAddress"));
    }

    @Test
    public void _55_TestSelectFilteredPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 1,
                                                                        "LastName=Flintstone",
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 1,
                                                                        "LastName=Flintstone",
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        testSelectFilteredPaginatedMultipleColumnsSpecified(response1, response2);
    }

    @Test
    public void _56_TestSelectFilteredPaginatedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          1, 1,
                                                                          "LastName=Flintstone",
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          2, 1,
                                                                          "LastName=Flintstone",
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        testSelectFilteredPaginatedMultipleColumnsSpecified(response1, response2);
    }

/*    @Test
    public void _57_TestSelectDeprecated1()
        throws ETSdkException
    {
        @SuppressWarnings("deprecation")
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select("LastName=Flintstone",
                                                                        1, 1,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        @SuppressWarnings("deprecation")
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select("LastName=Flintstone",
                                                                        2, 1,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        testSelectFilteredPaginatedMultipleColumnsSpecified(response1, response2);
    }

    @Test
    public void _58_TestSelectDeprecatedStatic1()
        throws ETSdkException
    {
        @SuppressWarnings("deprecation")
        ETResponse<ETDataExtensionRow> response1 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          "LastName=Flintstone",
                                                                          1, 1,
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        @SuppressWarnings("deprecation")
        ETResponse<ETDataExtensionRow> response2 = ETDataExtension.select(client,
                                                                          "key=test1",
                                                                          "LastName=Flintstone",
                                                                          2, 1,
                                                                          "LastName",
                                                                          "FirstName",
                                                                          "EmailAddress");
        testSelectFilteredPaginatedMultipleColumnsSpecified(response1, response2);
    }
*/
    @Test
    public void _59_TestSelectCaseIndependence()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("lastname",
                                                                       "firstname",
                                                                       "emailaddress",
                                                                       "order by firstname");
//        testSelectAllSortedMultipleColumnsSpecified(response);
    }

    @Test
    public void _60_TestSelectZeroRowsReturned()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=fnord");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 0, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(0, rows.size());
    }

    @Test
    public void _61_TestUpdateSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = new ETDataExtensionRow();
        row.setColumn("CustomerID", "1");
        row.setColumn("FirstName", "IAN");
        ETResponse<ETDataExtensionRow> response = dataExtension.update(row);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Updated DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
        ETDataExtensionRow r = result.getObject();
        assertEquals("1", r.getColumn("CustomerID"));
        assertNull(r.getColumn("FullName"));
        assertEquals("IAN", r.getColumn("FirstName"));
        assertNull(r.getColumn("LastName"));
        assertNull(r.getColumn("Age"));
        assertNull(r.getColumn("EmailAddress"));
    }

    @Test
    public void _62_TestUpdateMultipleRows()
        throws ETSdkException
    {
        ETDataExtensionRow row1 = new ETDataExtensionRow();
        row1.setColumn("CustomerID", "2");
        row1.setColumn("FirstName", "FRED");
        ETDataExtensionRow row2 = new ETDataExtensionRow();
        row2.setColumn("CustomerID", "3");
        row2.setColumn("FirstName", "WILMA");
        ETResponse<ETDataExtensionRow> response = dataExtension.update(row1, row2);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        assertNull(result1.getRequestId());
        assertEquals(OK, result1.getStatus());
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Updated DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETDataExtensionRow r1 = result1.getObject();
        assertEquals("2", r1.getColumn("CustomerID"));
        assertNull(r1.getColumn("FullName"));
        assertEquals("FRED", r1.getColumn("FirstName"));
        assertNull(r1.getColumn("LastName"));
        assertNull(r1.getColumn("Age"));
        assertNull(r1.getColumn("EmailAddress"));
        ETResult<ETDataExtensionRow> result2 = response.getResults().get(1);
        assertNull(result2.getRequestId());
        assertEquals(OK, result2.getStatus());
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Updated DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
        ETDataExtensionRow r2 = result2.getObject();
        assertEquals("3", r2.getColumn("CustomerID"));
        assertNull(r2.getColumn("FullName"));
        assertEquals("WILMA", r2.getColumn("FirstName"));
        assertNull(r2.getColumn("LastName"));
        assertNull(r2.getColumn("Age"));
        assertNull(r2.getColumn("EmailAddress"));
    }

    @Test
    public void _63_TestUpdateRowsByFilter()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.update("LastName=Flintstone",
                                                                       "FirstName='Fred and Wilma'");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        assertNull(result1.getRequestId());
        assertEquals(OK, result1.getStatus());
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Updated DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETDataExtensionRow r1 = result1.getObject();
        assertEquals("2", r1.getColumn("CustomerID"));
        assertEquals(null, r1.getColumn("FullName"));
        assertEquals("Fred and Wilma", r1.getColumn("FirstName"));
        assertEquals(null, r1.getColumn("LastName"));
        assertEquals(null, r1.getColumn("Age"));
        assertEquals(null, r1.getColumn("EmailAddress"));
        ETResult<ETDataExtensionRow> result2 = response.getResults().get(1);
        assertNull(result2.getRequestId());
        assertEquals(OK, result2.getStatus());
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Updated DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
        ETDataExtensionRow r2 = result2.getObject();
        assertEquals("3", r2.getColumn("CustomerID"));
        assertEquals(null, r2.getColumn("FullName"));
        assertEquals("Fred and Wilma", r2.getColumn("FirstName"));
        assertEquals(null, r2.getColumn("LastName"));
        assertEquals(null, r2.getColumn("Age"));
        assertEquals(null, r2.getColumn("EmailAddress"));
    }

    @Test(expected = ETSdkException.class)
    public void _64_TestUpdateRowsByFilterInvalidOperator()
        throws ETSdkException
    {
        @SuppressWarnings("unused")
        ETResponse<ETDataExtensionRow> response = dataExtension.update("LastName=Flintstone",
                                                                       "FirstName != 'Fred and Wilma'");
    }

    @Test
    public void _65_TestDeleteSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = new ETDataExtensionRow();
        row.setColumn("CustomerID", "1");
        ETResponse<ETDataExtensionRow> response = dataExtension.delete(row);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObject());
        assertNull(result.getObjectId());
    }

    @Test
    public void _66_TestDeleteMultipleRows()
        throws ETSdkException
    {
        ETDataExtensionRow row1 = new ETDataExtensionRow();
        row1.setColumn("CustomerID", "2");
        ETDataExtensionRow row2 = new ETDataExtensionRow();
        row2.setColumn("CustomerID", "3");
        ETResponse<ETDataExtensionRow> response = dataExtension.delete(row1, row2);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        assertNull(result1.getRequestId());
        assertEquals(OK, result1.getStatus());
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObject());
        assertNull(result1.getObjectId());
        ETResult<ETDataExtensionRow> result2 = response.getResults().get(1);
        assertNull(result2.getRequestId());
        assertEquals(OK, result2.getStatus());
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObject());
        assertNull(result2.getObjectId());
    }

    //@Test
    public void _67_TestDeleteRowsByFilter()
        throws ETSdkException
    {
    }

    //@Test
    public void _68_TestDeleteRowsByFilterInvalidOperator()
        throws ETSdkException
    {
    }

    @Test
    public void _69_TestDeleteSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey("test1");
        ETResponse<ETDataExtension> response = client.delete(dataExtension);
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension deleted.", result.getResponseMessage());
        assertNull(result.getErrorCode());
    }
    
    @Test
    public void _70_TestSelectFilteredByDateTime()
        throws ETSdkException
    {
        @SuppressWarnings("unused")
        ETResponse<ETDataExtensionRow> response = dataExtension.select("EventDate > '2016-01-01T01:01:01Z'");
    }
    
    @Test
    public void _71_createSendableDataExtension() throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey(UUID.randomUUID().toString());
        dataExtension.setName(UUID.randomUUID().toString());
        dataExtension.setIsSendable(true);

        Attribute at = new Attribute();
        at.setName("Subscriber Key");
        at.setValue(null);
        dataExtension.setSendableSubscriberField(at);

        dataExtension.addColumn("EmailAddress", ETDataExtensionColumn.Type.EMAIL_ADDRESS, 100, null, null, true, true, null);
        dataExtension.addColumn("FirstName", ETDataExtensionColumn.Type.TEXT);

        dataExtension.setSendableDataExtensionField(dataExtension.getColumn("EmailAddress"));

        ETResponse<ETDataExtension> response = client.create(dataExtension);
        System.out.println("Resp="+response);

        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());

        ETResult<ETDataExtension> result = response.getResult();
        assertNull(result.getRequestId());
        assertEquals(OK, result.getStatus());
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension created.", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());

        sdeid = result.getObjectId();
        System.out.println("id="+sdeid);
    }    
    
    @Test
    public void _72_getSendableDataExtension() throws ETSdkException
    {
        ETFilter etf = new ETFilter();
        etf.addProperty("id");
        etf.addProperty("key");
        etf.addProperty("name");
        etf.addProperty("description");
        etf.addProperty("folderId");
        etf.addProperty("isSendable");
        etf.addProperty("isTestable");

        ETExpression exp = new ETExpression();
        exp.setProperty("id");
        exp.setOperator("=");
        exp.setValue(sdeid);
        etf.setExpression(exp);         
        
        ETResponse<ETDataExtension> response = client.retrieve(ETDataExtension.class, etf);
        System.out.println("resp="+response);
        
        assertNotNull(response.getRequestId());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        
        ETResult<ETDataExtension> result = response.getResult();
        System.out.println("res="+ result.toString());
        
        assertEquals(result.getObject().getId(), sdeid);  
        assertTrue(result.getObject().getIsSendable());        
    }    
    
    @Test
    public void _73_updateSendableDataExtension() throws ETSdkException
    {
        ETDataExtension etsde = new ETDataExtension();
        etsde.setId(sdeid);
        etsde.setIsSendable(true);
       
        
        etsde.setSendableDataExtensionField(etsde.getColumn("FirstName"));
        
        ETResponse<ETDataExtension> response = client.update(etsde);
        System.out.println("resp="+ response.toString());
        assertNotNull(response.getRequestId());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");
        
        ETResult<ETDataExtension> result = response.getResult();
        System.out.println("res="+ result.toString());
        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "Data Extension updated.");        
        
        assertEquals(result.getObject().getId(), sdeid);  
        assertTrue(result.getObject().getIsSendable());
    }    
    
    @Test
    public void _74_deleteSendableDataExtension() throws ETSdkException
    {
        ETDataExtension etsde = new ETDataExtension();
        etsde.setId(sdeid);        
        
        ETResponse<ETDataExtension> response = client.delete(etsde);   
        System.out.println("resp="+ response.toString());
        assertNotNull(response.getRequestId());
        assertEquals(response.getResponseCode(), "OK");
        assertEquals(response.getResponseMessage(), "OK");        

        ETResult<ETDataExtension> result = response.getResult();
        System.out.println("res="+ result.toString());

        assertEquals(result.getResponseCode(), "OK");
        assertEquals(result.getResponseMessage(), "Data Extension deleted.");        
    }    
    
}
