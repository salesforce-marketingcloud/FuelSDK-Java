//
// This file is part of the Fuel Java client library.
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

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import static com.exacttarget.fuelsdk.ETResult.Status.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETDataExtensionTest {
    private static final String CLIENT_ID = "m2x67xmfr4vassc6hskv359r";
    private static final Integer DATA_EXTENSION_FOLDER_ID = 94511;

    private static ETClient client = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        client = new ETClient();
    }

    private static String id = null;

    @Test
    public void _01_TestCreateSingle()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    }

    private static ETDataExtension dataExtension = null;

    @Test
    public void _02_TestRetrieveSingle()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
        assertEquals(DATA_EXTENSION_FOLDER_ID, de.getFolderId());
        assertEquals(false, de.getIsSendable());
        assertEquals(false, de.getIsTestable());
        // save the object for use in subsequent tests
        dataExtension = de;
    }

    @Test
    public void _03_TestInsertSingleRow()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
        assertEquals(CLIENT_ID, client.getClientId());
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

    @Test
    public void _05_TestSelectAll()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select();
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
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
    public void _06_TestSelectAllSingleColumnSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _07_TestSelectAllMultipleColumnsSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _08_TestSelectAllSorted()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("ORDER BY FirstName");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
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
    public void _09_TestSelectAllSortedSingleColumnSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "ORDER BY FirstName");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
        assertEquals("Murdock", row2.getColumn("LastName"));
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
    public void _10_TestSelectAllSortedMultipleColumnsSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress",
                                                                       "ORDER BY FirstName");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _11_TestSelectAllPaginated()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2);
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2);
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _12_TestSelectAllPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2,
                                                                        "LastName");
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2,
                                                                        "LastName");
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _13_TestSelectAllPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select(1, 2,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select(2, 2,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _14_TestSelectFiltered()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _15_TestSelectFilteredSingleColumnSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _16_TestSelectFilteredMultipleColumnsSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _17_TestSelectFilteredNotEquals()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName != Flintstone");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _18_TestSelectFilteredLessThan()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age < 36");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _19_TestSelectFilteredLessThanOrEquals()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age <= 36");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _20_TestSelectFilteredGreaterThan()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age > 36");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _21_TestSelectFilteredGreaterThanOrEquals()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age >= 36");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _22_TestSelectFilteredIn1()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35)");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _23_TestSelectFilteredIn2()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36)");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _24_TestSelectFilteredIn3()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36, 37)");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _25_TestSelectFilteredBetween()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age between 30 and 40");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _26_TestSelectFilteredLike()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName like 'Flint%'");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _27_TestSelectFilteredAnd()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone and FirstName = 'Wilma'");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
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
    public void _28_TestSelectFilteredPaginated()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select("LastName=Flintstone",
                                                                        1, 1);
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select("LastName=Flintstone",
                                                                        2, 1);
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _29_TestSelectFilteredPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select("LastName=Flintstone",
                                                                        1, 1,
                                                                        "LastName");
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select("LastName=Flintstone",
                                                                        2, 1,
                                                                        "LastName");
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _30_TestSelectFilteredPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response1 = dataExtension.select("LastName=Flintstone",
                                                                        1, 1,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        assertNotNull(response1.getRequestId());
        assertEquals(OK, response1.getStatus());
        assertEquals("200", response1.getResponseCode());
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
        ETResponse<ETDataExtensionRow> response2 = dataExtension.select("LastName=Flintstone",
                                                                        2, 1,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        assertNotNull(response2.getRequestId());
        assertEquals(OK, response2.getStatus());
        assertEquals("200", response2.getResponseCode());
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
    public void _31_TestSelectZeroRowsReturned()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=fnord");
        assertNotNull(response.getRequestId());
        assertEquals(OK, response.getStatus());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 0, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(0, rows.size());
    }

    @Test
    public void _32_TestUpdateSingleRow()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    public void _33_TestUpdateMultipleRows()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    public void _34_TestUpdateRowsByFilter()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    public void _35_TestUpdateRowsByFilterInvalidOperator()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
        @SuppressWarnings("unused")
        ETResponse<ETDataExtensionRow> response = dataExtension.update("LastName=Flintstone",
                                                                       "FirstName != 'Fred and Wilma'");
    }

    @Test
    public void _36_TestDeleteSingleRow()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    public void _37_TestDeleteMultipleRows()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
    public void _38_TestDeleteRowsByFilter()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
    }

    //@Test
    public void _39_TestDeleteRowsByFilterInvalidOperator()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
    }

    @Test
    public void _40_TestDeleteSingle()
        throws ETSdkException
    {
        assertEquals(CLIENT_ID, client.getClientId());
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
}
