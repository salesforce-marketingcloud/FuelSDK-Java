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

package com.exacttarget.fuelsdk.audiencebuilder;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETAudienceBuilderTest {
    //
    // These tests are highly dependent on the test
    // environment, so before we run each test,
    // we check to make sure we're in the right one:
    //

    private final static String TEST_ENVIRONMENT_CLIENT_ID =
            "hwja87uzhjmmy8ykxtkesdt4";

    private static ETClient client = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        client = new ETClient();
    }

    @Test
    public void _01_TestRetrieveAllDimensions()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class);
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 719, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _02_TestRetrieveAllFilteredEquals()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count = 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 10, response.getPageSize());
        assertEquals((Integer) 10, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _03_TestRetrieveAllFilteredNotEquals()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count != 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 225, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _04_TestRetrieveAllFilteredLessThan()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count < 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 41, response.getPageSize());
        assertEquals((Integer) 41, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _05_TestRetrieveAllFilteredLessThanOrEquals()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count <= 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 51, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _06_TestRetrieveAllFilteredGreaterThan()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count > 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 184, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _07_TestRetrieveAllFilteredGreaterThanOrEquals()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count >= 3");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 194, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _08_TestRetrieveAllFilteredIn1()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6)");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 22, response.getPageSize());
        assertEquals((Integer) 22, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _09_TestRetrieveAllFilteredIn2()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6, 9)");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 28, response.getPageSize());
        assertEquals((Integer) 28, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _10_TestRetrieveAllFilteredIn3()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6, 9, 12)");
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 40, response.getPageSize());
        assertEquals((Integer) 40, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    private static String age = null;

    @Test
    public void _11_TestRetrieveSingle1()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }

        //
        // Retrieve "age" dimension and values:
        //

        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "id=528"); // age
        assertEquals(1, response.getObjects().size());
        ETDimension dimension = response.getObject();
        assertEquals("528", dimension.getId());
        assertEquals((Integer) 1, dimension.getType());
        assertEquals("d4493c60-24e8-453a-b0bc-bf111c10aa42",
                     dimension.getFieldId());
        assertEquals("name",
                     dimension.getFieldName());
        assertEquals((Integer) 9, dimension.getCount());
        assertEquals(9, dimension.getValues().size());
        ETDimensionValue value1 = dimension.getValues().get(0);
        assertEquals("1", value1.getKey());
        assertEquals("less than 14", value1.getName());
        assertEquals((Integer) 0, value1.getCount());
        ETDimensionValue value2 = dimension.getValues().get(1);
        assertEquals("3", value2.getKey());
        assertEquals("18 24", value2.getName());
        assertEquals((Integer) 2475583, value2.getCount());
        ETDimensionValue value3 = dimension.getValues().get(2);
        assertEquals("7", value3.getKey());
        assertEquals("55 - 64", value3.getName());
        assertEquals((Integer) 2167700, value3.getCount());
        ETDimensionValue value4 = dimension.getValues().get(3);
        assertEquals("-1", value4.getKey());
        assertEquals("no age available", value4.getName());
        assertEquals((Integer) 197084, value4.getCount());
        ETDimensionValue value5 = dimension.getValues().get(4);
        assertEquals("2", value5.getKey());
        assertEquals("14 - 17", value5.getName());
        assertEquals((Integer) 1230269, value5.getCount());
        ETDimensionValue value6 = dimension.getValues().get(5);
        assertEquals("8", value6.getKey());
        assertEquals("65+", value6.getName());
        assertEquals((Integer) 1066716, value6.getCount());
        ETDimensionValue value7 = dimension.getValues().get(6);
        assertEquals("6", value7.getKey());
        assertEquals("45 - 54", value7.getName());
        assertEquals((Integer) 2165739, value7.getCount());
        ETDimensionValue value8 = dimension.getValues().get(7);
        assertEquals("4", value8.getKey());
        assertEquals("25 - 34", value8.getName());
        assertEquals((Integer) 2513502, value8.getCount());
        ETDimensionValue value9 = dimension.getValues().get(8);
        assertEquals("5", value9.getKey());
        assertEquals("35 - 44", value9.getName());
        assertEquals((Integer) 2167923, value9.getCount());
        // save fieldId for later
        age = dimension.getFieldId();
    }

    private static String gender = null;

    @Test
    public void _12_TestRetrieveSingle2()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }

        //
        // Retrieve "gender" dimension and values:
        //

        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "id=278"); // gender
        assertEquals(1, response.getObjects().size());
        ETDimension dimension = response.getObject();
        assertEquals("278", dimension.getId());
        assertEquals((Integer) 1, dimension.getType());
        assertEquals("b8b49d1a-74a4-4fb1-b92d-0aed1ab522a3",
                     dimension.getFieldId());
        assertEquals("name",
                     dimension.getFieldName());
        assertEquals((Integer) 3, dimension.getCount());
        assertEquals(3, dimension.getValues().size());
        ETDimensionValue value1 = dimension.getValues().get(0);
        assertEquals("Unknown", value1.getKey());
        assertEquals("unknown", value1.getName());
        assertEquals((Integer) 119415, value1.getCount());
        ETDimensionValue value2 = dimension.getValues().get(1);
        assertEquals("M", value2.getKey());
        assertEquals("male", value2.getName());
        assertEquals((Integer) 6959736, value2.getCount());
        ETDimensionValue value3 = dimension.getValues().get(2);
        assertEquals("F", value3.getKey());
        assertEquals("female", value3.getName());
        assertEquals((Integer) 6961047, value3.getCount());
        // save fieldId for later
        gender = dimension.getFieldId();
    }

    @Test
    public void _13_TestRetrieveAudienceCount()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        Integer audienceCount = ETAudience.retrieveAudienceCount(client, age + "='25 - 34'");
        assertEquals((Integer) 2513502, audienceCount);
    }

    private static String id = null;

    @Test
    public void _14_TestCreateAudience()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETAudience audience = new ETAudience();
        audience.setName("people age 25-34");
        audience.setFilter(age + "='25 - 34'");
        audience.setPublishedDataExtensionName("fuel-java test");
        ETResponse<ETAudience> response = client.create(audience);
        assertNull(response.getRequestId());
        assertNull(response.getResponseCode());
        assertNull(response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETAudience> result = response.getResult();
        assertNotNull(result.getRequestId());
        assertEquals("201", result.getResponseCode());
        assertEquals("Created", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        // save the ID for use in the next test
        id = result.getObjectId();
    }

    @Test
    public void _15_TestPublishAudience()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }

        ETResponse<ETAudience> response = client.retrieve(ETAudience.class,
                                                          "id=" + id);
        ETAudience audience = response.getObject();

        audience.publish();

        do {
            try {
                Thread.sleep(5000); // wait 5 seconds
            } catch (Exception ex) {
                throw new ETSdkException(ex);
            }

            audience.updatePublishStatus();

            System.out.println(audience.getStatus()
                    + ": "
                    + audience.getSubscribersCopied()
                    + " copied.");
        } while (!audience.getStatus().equals("READY") &&
                 !audience.getStatus().equals("ERROR"));
    }

    @Test
    public void _16_TestDeleteAudience()
        throws ETSdkException
    {
        if (!client.getClientId().equals(TEST_ENVIRONMENT_CLIENT_ID)) {
            return;
        }
        ETResponse<ETAudience> response = client.delete(ETAudience.class,
                                                        "id=" + id);
        assertNotNull(response.getRequestId());
        assertEquals("200", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }
}
