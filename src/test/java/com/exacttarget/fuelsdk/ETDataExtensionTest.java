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

import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static com.exacttarget.fuelsdk.ETResult.Status.OK;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETDataExtensionTest {
    private static ETClient client = null;
    private static String DE_KEY = "TestDE_" + UUID.randomUUID().toString();
    private static String DE_NAME = "TestDE_Name_" + UUID.randomUUID().toString();

    private static List<ETDataExtensionColumn> dataStructure;
    private static ETDataExtensionRow[] testRecords;

    private static Integer dataExtensionFolderId = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        Assume.assumeNotNull(ETDataExtensionTest.class
                .getResource("/fuelsdk-test.properties"));
        client = new ETClient("/fuelsdk-test.properties");
        dataExtensionFolderId = new Integer(client.getConfiguration()
                .get("dataExtensionFolderId"));

        dataStructure = new ArrayList<ETDataExtensionColumn>();
        dataStructure.add(createETDataExtensionColumn("CustomerID", ETDataExtensionColumn.Type.TEXT, 50, true, true));
        dataStructure.add(createETDataExtensionColumn("FullName", ETDataExtensionColumn.Type.TEXT, 100, false, false));
        dataStructure.add(createETDataExtensionColumn("FirstName", ETDataExtensionColumn.Type.TEXT, 100, false, false));
        dataStructure.add(createETDataExtensionColumn("LastName", ETDataExtensionColumn.Type.TEXT, 100, false, false));
        dataStructure.add(createETDataExtensionColumn("Age", ETDataExtensionColumn.Type.NUMBER, null, false, false));
        dataStructure.add(createETDataExtensionColumn("EmailAddress", ETDataExtensionColumn.Type.EMAIL_ADDRESS, null, false, true));

        testRecords = new ETDataExtensionRow[4];
        int index = 0;
        testRecords[index] = createETDataExtensionRow(String.valueOf(++index),
                "Ian Murdock", "Ian", "Murdock", "41", "imurdock@example.com");
        testRecords[index] = createETDataExtensionRow(String.valueOf(++index),
                "Fred Flintstone", "Fred", "Flintstone", "36", "fred@flintstone.com");
        testRecords[index] = createETDataExtensionRow(String.valueOf(++index),
                "Wilma Flintstone", "Wilma", "Flintstone", "34", "wilma@flintstone.com");
        testRecords[index] = createETDataExtensionRow(String.valueOf(++index),
                "Kid Flintstone", "Kid", "Flintstone", "7", "kid@flintstone.com");
    }


    private static String id = null;

    @Test
    public void _01_TestCreateSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey(DE_KEY);
        dataExtension.setName(DE_NAME);
        for (ETDataExtensionColumn dec : dataStructure) {
            dataExtension.addColumn(dec);
        }
        ETResponse<ETDataExtension> response = client.create(dataExtension);
        testGeneralResponseInfo(response);
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        testGeneralResultInfo(result, OK, "OK", "Data Extension created.");
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        ETDataExtension de = result.getObject();
        assertNotNull(de.getId());
        assertEquals(DE_KEY, de.getKey());
        assertEquals(DE_NAME, de.getName());
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
        ETResponse<ETDataExtension> response = client.retrieve(ETDataExtension.class, "id=" + id);
        testGeneralResponseInfo(response);
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
        assertEquals(DE_KEY, de.getKey());
        assertEquals(DE_NAME, de.getName());
        assertEquals("", de.getDescription());
        assertEquals(dataExtensionFolderId, de.getFolderId());
        assertEquals(false, de.getIsSendable());
        assertEquals(false, de.getIsTestable());
        // save the object for use in subsequent tests
        dataExtension = de;
    }

    @Test
    public void _03_TestInsertSingleRow()
        throws ETSdkException
    {
        ETDataExtensionRow row = getTestETDataExtensionRow(testRecords, 0);
        ETResponse<ETDataExtensionRow> response = dataExtension.insert(row);
        testGeneralResponseInfo(response);
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        testGeneralResultInfo(result, OK, "OK", "Created DataExtensionObject");
        assertNull(result.getErrorCode());
        assertNull(result.getObjectId());
        ETDataExtensionRow r = result.getObject();
        assertEquals(row, r);
    }

    @Test
    public void _04_TestInsertMultipleRows()
        throws ETSdkException
    {
        ETDataExtensionRow[] rows = new ETDataExtensionRow[testRecords.length - 1];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = getTestETDataExtensionRow(testRecords, i + 1);
        }
        ETResponse<ETDataExtensionRow> response = dataExtension.insert(rows);
        testGeneralResponseInfo(response);
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(rows.length, response.getResults().size());

        for (int i = 0; i < rows.length; i++) {
            ETResult<ETDataExtensionRow> result1 = response.getResults().get(i);
            testGeneralResultInfo(result1, OK, "OK", "Created DataExtensionObject");
            assertNull(result1.getErrorCode());
            assertNull(result1.getObjectId());
            ETDataExtensionRow r1 = result1.getObject();
            assertEquals(rows[i], r1);
        }
    }

    @Test
    public void _05_TestSelectAll()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select();
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _06_TestSelectAllStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY);
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _07_TestSelectAllSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _08_TestSelectAllSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _09_TestSelectAllMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _10_TestSelectAllMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, populateDefautExpectedSequence());
    }

    @Test
    public void _11_TestSelectAllSorted()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _12_TestSelectAllSortedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test(expected=ETSdkException.class)
    public void _13_TestSelectAllSortedSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _13_1_TestSelectAllSortedTwoColumnsSpecified()
            throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("firstname", "lastname",
                "ORDER BY firstname");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test(expected=ETSdkException.class)
    public void _14_TestSelectAllSortedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName",
                                                                         "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _14_1_TestSelectAllSortedTwoColumnsSpecifiedStatic()
            throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY,
                "FirstName", "LastName",
                "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _15_TestSelectAllSortedMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName",
                                                                       "FirstName",
                                                                       "EmailAddress",
                                                                       "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _16_TestSelectAllSortedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress",
                                                                         "ORDER BY FirstName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _17_TestSelectAllPaginated()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize);
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize);
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _18_TestSelectAllPaginatedStatic()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize);
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize);
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _19_TestSelectAllPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize, "LastName");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize, "LastName");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _20_TestSelectAllPaginatedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _21_TestSelectAllPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                                                                        "LastName",
                                                                        "FirstName",
                                                                        "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _22_TestSelectAllPaginatedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        int pageSize = 2;
        int pageNumber = 1;
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, true, 0, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, testRecords.length, false, 2, 3);
    }

    @Test
    public void _23_TestSelectFiltered()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _24_TestSelectFilteredStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName=Flintstone");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _25_TestSelectFilteredSingleColumnSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _26_TestSelectFilteredSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName=Flintstone",
                                                                         "LastName");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _27_TestSelectFilteredMultipleColumnsSpecified()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                                                                       "LastName",
                                                                       "FirstName",
                                                                       "EmailAddress");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _28_TestSelectFilteredMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName=Flintstone",
                                                                         "LastName",
                                                                         "FirstName",
                                                                         "EmailAddress");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _29_TestSelectFilteredNotEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName != Flintstone");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 0);
    }

    @Test
    public void _30_TestSelectFilteredNotEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName != Flintstone");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 0);
    }

    @Test
    public void _31_TestSelectFilteredLessThan()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age < 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 2, 3);
    }

    @Test
    public void _32_TestSelectFilteredLessThanStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age < 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 2, 3);
    }

    @Test
    public void _33_TestSelectFilteredLessThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age <= 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _34_TestSelectFilteredLessThanOrEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age <= 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _35_TestSelectFilteredGreaterThan()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age > 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 0);
    }

    @Test
    public void _36_TestSelectFilteredGreaterThanStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age > 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 0);
    }

    @Test
    public void _37_TestSelectFilteredGreaterThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age >= 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 0, 1);
    }

    @Test
    public void _38_TestSelectFilteredGreaterThanOrEqualsStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age >= 36");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 0, 1);
    }

    @Test
    public void _39_TestSelectFilteredIn1()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 2);
    }

    @Test
    public void _40_TestSelectFilteredInStatic1()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age in (34, 35)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 2);
    }

    @Test
    public void _41_TestSelectFilteredIn2()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Test
    public void _42_TestSelectFilteredInStatic2()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age in (34, 35, 36)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Test
    public void _43_TestSelectFilteredIn3()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age in (34, 35, 36, 37)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Test
    public void _44_TestSelectFilteredInStatic3()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age in (34, 35, 36, 37)");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Test
    public void _45_TestSelectFilteredBetween()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("Age between 30 and 40");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Test
    public void _46_TestSelectFilteredBetweenStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "Age between 30 and 40");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 2, false, 1, 2);
    }

    @Ignore
    public void _47_TestSelectFilteredLike()
        throws ETSdkException
    {
        // TODO - check why the service returns "Error: Object reference not set to an instance of an object."
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName like 'Flint%'");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Ignore
    public void _48_TestSelectFilteredLikeStatic()
        throws ETSdkException
    {
        // TODO - check why the service returns "Error: Object reference not set to an instance of an object."
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName like 'Flint%'");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 3, false, 1, 2, 3);
    }

    @Test
    public void _49_TestSelectFilteredAnd()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName='Flintstone' and FirstName = 'Wilma'");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 2);
    }

    @Test
    public void _50_TestSelectFilteredAndStatic()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                                                                         "key=" + DE_KEY,
                                                                         "LastName='Flintstone' and FirstName = 'Wilma'");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 1, false, 2);
    }

    @Test
    public void _51_TestSelectFilteredPaginated()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize,
                                                                        "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                                                                        "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _52_TestSelectFilteredPaginatedStatic()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _53_TestSelectFilteredPaginatedSingleColumnSpecified()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize,
                "LastName=Flintstone", "LastName");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                "LastName=Flintstone", "LastName");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName='Flintstone'");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _54_TestSelectFilteredPaginatedSingleColumnSpecifiedStatic()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _55_TestSelectFilteredPaginatedMultipleColumnsSpecified()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = dataExtension.select(pageNumber, pageSize,
                "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = dataExtension.select(pageNumber, pageSize,
                "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _56_TestSelectFilteredPaginatedMultipleColumnsSpecifiedStatic()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, pageNumber, pageSize, "LastName=Flintstone", "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void _57_TestSelectDeprecated1()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=Flintstone",
                pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = dataExtension.select("LastName=Flintstone",
                pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = dataExtension.select("LastName=Flintstone",
                pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void _58_TestSelectDeprecatedStatic1()
        throws ETSdkException
    {
        int pageSize = 1;
        int pageNumber = 1;

        ETResponse<ETDataExtensionRow> response = ETDataExtension.select(client,
                "key=" + DE_KEY, "LastName=Flintstone", pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 1);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, "LastName=Flintstone", pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, true, 2);
        pageNumber++;
        response = ETDataExtension.select(client,
                "key=" + DE_KEY, "LastName=Flintstone", pageNumber, pageSize, "LastName", "FirstName", "EmailAddress");
        testSelectFiltered(response, pageNumber, pageSize, 3, false, 3);
    }

    @Test
    public void _59_TestSelectCaseIndependence()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("lastname",
                                                                       "firstname",
                                                                       "emailaddress",
                                                                       "order by firstname");
        testSelectFiltered(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, testRecords.length, false, 1, 0, 3, 2);
    }

    @Test
    public void _60_TestSelectZeroRowsReturned()
        throws ETSdkException
    {
        ETResponse<ETDataExtensionRow> response = dataExtension.select("LastName=fnord");
        testGeneralDataRowResponse(response, 1, ETDataExtension.DEFAULT_PAGE_SIZE, 0, false);
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
        // TODO - check why is this response not paged???
        testGeneralResponseInfo(response, OK, "OK", "OK");
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        testGeneralResultInfo(result, OK, "OK", "Updated DataExtensionObject");
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
        // TODO - check why is this response not paged???
        testGeneralResponseInfo(response, OK, "OK", "OK");
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        testGeneralResultInfo(result1, OK, "OK", "Updated DataExtensionObject");
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
        // TODO - check why is this response not paged???
        testGeneralResponseInfo(response);
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(3, response.getResults().size());
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
        testGeneralResponseInfo(response);
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
        testGeneralResponseInfo(response);
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETDataExtensionRow> result1 = response.getResults().get(0);
        testGeneralResultInfo(result1, ETResult.Status.OK, "OK", "Deleted DataExtensionObject");
        assertNull(result1.getObject());
        assertNull(result1.getObjectId());
        ETResult<ETDataExtensionRow> result2 = response.getResults().get(1);
        testGeneralResultInfo(result2, ETResult.Status.OK, "OK", "Deleted DataExtensionObject");
        assertNull(result2.getObject());
        assertNull(result2.getObjectId());
    }

    @Ignore
    public void _67_TestDeleteRowsByFilter()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey(DE_KEY);
        ETResponse<ETDataExtensionRow> response = dataExtension.delete("FirstName=Kid");
        testGeneralDataRowResponse(response, null, null, null, false);
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtensionRow> result = response.getResult();
        testGeneralResultInfo(result, ETResult.Status.OK, "OK", "Data Extension deleted.");
    }

    @Ignore
    public void _68_TestDeleteRowsByFilterInvalidOperator()
        throws ETSdkException
    {
    }

    @Test
    public void _69_TestDeleteSingle()
        throws ETSdkException
    {
        ETDataExtension dataExtension = new ETDataExtension();
        dataExtension.setKey(DE_KEY);
        ETResponse<ETDataExtension> response = client.delete(dataExtension);
        testGeneralDataRowResponse(response, null, null, null, false);
        assertEquals(1, response.getResults().size());
        ETResult<ETDataExtension> result = response.getResult();
        testGeneralResultInfo(result, ETResult.Status.OK, "OK", "Data Extension deleted.");
    }


    // Helper methods
    private int[] populateDefautExpectedSequence(int ... expectedSequence) {
        if (expectedSequence.length == 0) {
            expectedSequence = new int[testRecords.length];
            for (int i = 0; i < expectedSequence.length; i++) {
                expectedSequence[i] = i;
            }
        }
        return expectedSequence;
    }

    private void testGeneralResultInfo(ETResult<? extends ETSoapObject> result, ETResult.Status status,
                                       String responseCode, String responseMessage) {
        assertNull(result.getRequestId());
        assertEquals(result.toString(), status, result.getStatus());
        assertEquals(responseCode, result.getResponseCode());
        assertEquals(responseMessage, result.getResponseMessage());
        assertNull(result.getErrorCode());
    }

    private void testGeneralResponseInfo(ETResponse<? extends ETSoapObject> response) {
        testGeneralResponseInfo(response, ETResult.Status.OK);
    }

    private void testGeneralResponseInfo(ETResponse<? extends ETSoapObject> response, ETResult.Status status) {
        testGeneralResponseInfo(response, status, "OK");
    }

    private void testGeneralResponseInfo(ETResponse<? extends ETSoapObject> response, ETResult.Status status,
                                         String responseCode) {
        testGeneralResponseInfo(response, status, responseCode, "OK");
    }

    private void testGeneralResponseInfo(ETResponse<? extends ETSoapObject> response, ETResult.Status status,
                                         String responseCode, String responseMessage) {
        assertNotNull(response.getRequestId());
        assertEquals(response.toString(), status, response.getStatus());
        assertEquals(responseCode, response.getResponseCode());
        assertEquals(responseMessage, response.getResponseMessage());
    }

    private void testGeneralDataRowResponse(ETResponse<? extends ETSoapObject> response,
                                            Integer currentPage, Integer pageSize, Integer totalResults, boolean hasMoreResults) {
        testGeneralResponseInfo(response);
        assertEquals(/*response.toString(), */currentPage, response.getPage());
        assertEquals(pageSize, response.getPageSize());
        assertEquals(totalResults, response.getTotalCount());
        assertEquals(hasMoreResults, response.hasMoreResults());
    }

    private void testSelectFiltered(ETResponse<ETDataExtensionRow> response,
                                    Integer currentPage, Integer pageSize, Integer totalResults, boolean hasMoreResults,
                                    int ... expectedSequence) {
        testGeneralDataRowResponse(response, currentPage, pageSize, totalResults, hasMoreResults);
        List<ETDataExtensionRow> rows = response.getObjects();
        assertEquals(expectedSequence.length, rows.size());
        int i = 0;
        for (int ei : expectedSequence) {
            ETDataExtensionRow row1 = rows.get(i++);
            ETDataExtensionRow testRow = getTestETDataExtensionRow(testRecords, ei);
            assertEquals(testRow, row1);
        }
    }

    // Setup methods
    private static ETDataExtensionColumn createETDataExtensionColumn(String name, ETDataExtensionColumn.Type type, Integer size, boolean primaryKey, boolean required) {
        ETDataExtensionColumn dec = new ETDataExtensionColumn();
        dec.setName(name);
        dec.setType(type);
        if (size != null)   dec.setLength(size);
        dec.setIsPrimaryKey(primaryKey);
        dec.setIsRequired(required);
        return dec;
    }

    private ETDataExtensionRow getTestETDataExtensionRow(ETDataExtensionRow[] testRecords, int index) {
        return testRecords[index];
    }

    private static ETDataExtensionRow createETDataExtensionRow(String ... values) {
        ETDataExtensionRow row = new ETDataExtensionRow();
        int i = 0;
        for (ETDataExtensionColumn dec : dataStructure) {
            row.setColumn(dec.getName(), values[i++]);
        }
        return row;
    }

}
