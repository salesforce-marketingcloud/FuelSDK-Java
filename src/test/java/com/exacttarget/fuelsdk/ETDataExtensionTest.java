//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.exacttarget.fuelsdk;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETDataExtensionTest {
    private static final Integer DATA_EXTENSION_FOLDER_ID = 94511;

    private static ETClient client = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        client = new ETClient();
    }

    //@Test
    public void _01_TestRetrieveAll()
        throws ETSdkException
    {
        ETResponse<ETDataExtension> response = client.retrieve(ETDataExtension.class);
        assertEquals(0, response.getResults().size());
    }


    private static String id = null;

    @Test
    public void _02_TestCreateSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey("test1");
        dataExtension.setName("test1");
        dataExtension.addColumn("CustomerID", true);
        dataExtension.addColumn("FullName");
        dataExtension.addColumn("FirstName");
        dataExtension.addColumn("LastName");
        dataExtension.addColumn("Age", ETDataExtensionColumnType.NUMBER);
        dataExtension.addColumn("EmailAddress", ETDataExtensionColumnType.EMAIL_ADDRESS);
        ETResponse<ETDataExtension> response = client.create(dataExtension);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension created.", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        // save the ID for use in the next test
        id = result.getObjectId();
    }

    private static ETDataExtension dataExtension = null;

    @Test
    public void _03_TestRetrieveSingle()
        throws ETSdkException
    {
        ETResponse<ETDataExtension> response = client.retrieve(ETDataExtension.class, "id=" + id);
        assertEquals(1, response.getObjects().size());
        dataExtension = response.getObject();
        assertEquals(id, dataExtension.getId());
        assertEquals("test1", dataExtension.getKey());
        assertEquals("test1", dataExtension.getName());
        assertEquals("", dataExtension.getDescription());
        assertEquals(DATA_EXTENSION_FOLDER_ID, dataExtension.getFolderId());
        assertEquals(false, dataExtension.getIsSendable());
        assertEquals(false, dataExtension.getIsTestable());
    }

    // XXX check columns

    @Test
    public void _04_TestInsertSingleRow()
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
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Created DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
    }

    @Test
    public void _05_TestInsertMultipleRows()
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
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResult();
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Created DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETResult<ETDataExtensionRow> result2 = response.getResult();
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Created DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
    }

    @Test
    public void _06_TestSelectAll()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select();
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
        // XXX case issue
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
    public void _07_TestSelectAllSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
        // XXX case issue
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
    public void _08_TestSelectAllMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(3, rows.size());
        // XXX case issue
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
    public void _09_TestSelectAllPaginated()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select(1, 2);
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertTrue(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        // XXX case issue
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
    public void _10_TestSelectAllPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select(1, 2,
                                                                       "LastName");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertTrue(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        // XXX case issue
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
    }

    @Test
    public void _11_TestSelectAllPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select(1, 2,
                                                                       "LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2, response.getPageSize());
        assertEquals((Integer) 3, response.getTotalCount());
        assertTrue(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        ETDataExtensionRow row1 = rows.get(0);
        // XXX case issue
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
    }

    @Test
    public void _12_TestSelectFiltered()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        // XXX case issue
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
    public void _13_TestSelectFilteredSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        // XXX case issue
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
    public void _14_TestSelectFilteredMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 2, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(2, rows.size());
        // XXX case issue
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

    // XXX TestSelectFilteredPaginated
    // XXX TestSelectFilteredPaginatedPropertiesSubset
    // XXX TestSelectFilteredPaginatedPropertiesSubset

    @Test
    public void _15_TestSelectFilteredComplexFilter()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone and FirstName = 'Wilma'");
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 2500, response.getPageSize());
        assertEquals((Integer) 1, response.getTotalCount());
        assertFalse(response.hasMoreResults());
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(1, rows.size());
        // XXX case issue
        ETDataExtensionRow row1 = rows.get(0);
        assertEquals("3", row1.getColumn("CustomerID"));
        assertEquals("Wilma Flintstone", row1.getColumn("FullName"));
        assertEquals("Wilma", row1.getColumn("FirstName"));
        assertEquals("Flintstone", row1.getColumn("LastName"));
        assertEquals("34", row1.getColumn("Age"));
        assertEquals("wilma@flintstone.com", row1.getColumn("EmailAddress"));
    }

    @Test
    public void _16_TestUpdateSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = new ETDataExtensionRow();
        row.setColumn("CustomerID", "1");
        row.setColumn("FirstName", "IAN");
        ETResponse<ETDataExtensionRow> response = dataExtension.update(row);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Updated DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
    }

    @Test
    public void _17_TestUpdateMultipleRows()
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
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResult();
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Updated DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETResult<ETDataExtensionRow> result2 = response.getResult();
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Updated DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
    }

    @Test
    public void _18_TestUpdateMultipleRowsFiltered()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.update("LastName=Flintstone",
                                                                       "FirstName='Fred and Wilma'");
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Updated DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
        // XXX more?

    }

    @Test
    public void _19_TestDeleteSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = new ETDataExtensionRow();
        row.setColumn("CustomerID", "1");
        ETResponse<ETDataExtensionRow> response = dataExtension.delete(row);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
    }

    @Test
    public void _20_TestDeleteMultipleRows()
        throws ETSdkException
    {
        ETDataExtensionRow row1 = new ETDataExtensionRow();
        row1.setColumn("CustomerID", "2");
        ETDataExtensionRow row2 = new ETDataExtensionRow();
        row2.setColumn("CustomerID", "3");
        ETResponse<ETDataExtensionRow> response = dataExtension.delete(row1, row2);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResult();
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNull(result1.getObjectId());
        ETResult<ETDataExtensionRow> result2 = response.getResult();
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Deleted DataExtensionObject", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNull(result2.getObjectId());
    }

    @Test
    public void _21_TestDeleteSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey("test1");
        ETResponse<ETDataExtension> response = client.delete(dataExtension);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Data Extension deleted.", result.getResponseMessage());
        assertNull(result.getErrorCode());
    }
}
