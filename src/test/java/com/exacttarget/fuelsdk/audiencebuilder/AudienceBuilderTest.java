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

package com.exacttarget.fuelsdk.audiencebuilder;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETResult;
import com.exacttarget.fuelsdk.ETSdkException;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudienceBuilderTest {
    private static ETClient client = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        Assume.assumeNotNull(AudienceBuilderTest.class.getResource("/fuelsdk-test.properties"));

        ETConfiguration configuration = new ETConfiguration("/fuelsdk-test.properties");
        configuration.set("clientId", configuration.get("audienceBuilderClientId"));
        configuration.set("clientSecret", configuration.get("audienceBuilderClientSecret"));
        configuration.set("soapEndpoint", configuration.get("audienceBuilderSoapEndpoint"));
        client = new ETClient(configuration);
    }

    @Test
    public void _01_TestRetrieveDimensions()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class);
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 719, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _02_TestRetrieveDimensionsFilteredEquals()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count = 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 10, response.getPageSize());
        assertEquals((Integer) 10, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _03_TestRetrieveDimensionsFilteredNotEquals()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count != 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 225, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _04_TestRetrieveDimensionsFilteredLessThan()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count < 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 41, response.getPageSize());
        assertEquals((Integer) 41, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _05_TestRetrieveDimensionsFilteredLessThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count <= 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 51, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _06_TestRetrieveDimensionsFilteredGreaterThan()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count > 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 184, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _07_TestRetrieveDimensionsFilteredGreaterThanOrEquals()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count >= 3");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 50, response.getPageSize());
        assertEquals((Integer) 194, response.getTotalCount());
        assertTrue(response.hasMoreResults());
    }

    @Test
    public void _08_TestRetrieveDimensionsFilteredIn1()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6)");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 22, response.getPageSize());
        assertEquals((Integer) 22, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _09_TestRetrieveDimensionsFilteredIn2()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6, 9)");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 28, response.getPageSize());
        assertEquals((Integer) 28, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    @Test
    public void _10_TestRetrieveDimensionsFilteredIn3()
        throws ETSdkException
    {
        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "count in (3, 6, 9, 12)");
        assertNotNull(response.getRequestId());
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
        assertEquals((Integer) 1, response.getPage());
        assertEquals((Integer) 40, response.getPageSize());
        assertEquals((Integer) 40, response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

    //@Test
    public void _11_TestRetrieveDimensionsOrderBy()
        throws ETSdkException
    {
    }

    //@Test
    public void _12_TestRetrieveDimensionsFields()
        throws ETSdkException
    {
    }

    private static String age = null;

    @Test
    public void _13_TestRetrieveDimension1()
        throws ETSdkException
    {
        //
        // Retrieve "age" dimension and values:
        //

        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "id=528"); // age
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
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
    public void _14_TestRetrieveDimension2()
        throws ETSdkException
    {
        //
        // Retrieve "gender" dimension and values:
        //

        ETResponse<ETDimension> response = client.retrieve(ETDimension.class,
                                                           "id=278"); // gender
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", response.getResponseCode());
        } else {
            assertEquals("200", response.getResponseCode());
        }
        assertEquals("OK", response.getResponseMessage());
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
    public void _15_TestRetrieveAudienceCount()
        throws ETSdkException
    {
        Integer audienceCount = ETAudience.retrieveAudienceCount(client, age + "='25 - 34'");
        assertEquals((Integer) 2513502, audienceCount);
    }

    private static Integer totalCount = null;

//    @Test
//    public void _16_TestRetrieveAudiences1()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals((Integer) 1, response.getPage());
//        assertEquals((Integer) 50, response.getPageSize());
//        // totalCount changes frequently, so we don't check it
//        totalCount = response.getTotalCount();
//        assertTrue(response.hasMoreResults());
//    }

    private static ETAudience audience = null;

    @Test
    public void _17_TestCreateAudience()
        throws ETSdkException
    {
        audience = client.instantiate(ETAudience.class);
        audience.setName("people age 25-34");
        audience.setFilter(age + "='25 - 34'");
        audience.setPublishedDataExtensionName("fuel-java test");
        ETResponse<ETAudience> response = client.create(audience);
        audience = response.getObject();
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
        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
            assertEquals("OK", result.getResponseCode());
            //assertEquals("Audience created.", result.getResponseMessage());
            assertEquals("OK", result.getResponseMessage());
        } else {
            assertEquals("201", result.getResponseCode());
            assertEquals("Created", result.getResponseMessage());
        }
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
    }

//    @Test
//    public void _18_TestRetrieveAudiences2()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals((Integer) 1, response.getPage());
//        assertEquals((Integer) 50, response.getPageSize());
//        assertEquals((Integer) (totalCount + 1), response.getTotalCount());
//        assertTrue(response.hasMoreResults());
//    }

//    @Test
//    public void _19_TestRetrieveAudience1()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class,
//                                                          "id=" + id);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals(1, response.getObjects().size());
//        ETAudience audience = response.getObject();
//        assertEquals(id, audience.getId());
//        assertEquals("people age 25-34", audience.getName());
//        assertNull(audience.getDescription());
//        assertEquals("people age 25-34", audience.getAudienceCode());
//        assertEquals((Integer) 0, audience.getPublishCount());
//        assertNotNull(audience.getPublishCountDate());
//        assertEquals(1, audience.getSegments().size());
//        ETSegment segment = audience.getSegments().get(0);
//        assertNotNull(segment.getId());
//        assertEquals(audience.getId(), segment.getAudienceId());
//        assertEquals("Remainder", segment.getName());
//        assertEquals((Integer) Integer.MAX_VALUE, segment.getPriority());
//        assertEquals((Integer) 0, segment.getCap());
//        assertEquals((Integer) 0, segment.getCapPercent());
//        assertEquals(true, segment.getIncludeInPublish());
//    }

//    @Test
//    public void _20_TestUpdateAudience()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.update(ETAudience.class,
//                                                        "id=" + id,
//                                                        "description='people age 25-34'");
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals(1, response.getObjects().size());
//        ETAudience audience = response.getObject();
//        assertEquals(id, audience.getId());
//        assertEquals("people age 25-34", audience.getName());
//        assertEquals("people age 25-34", audience.getDescription());
//        assertEquals("people age 25-34", audience.getAudienceCode());
//        assertEquals((Integer) 0, audience.getPublishCount());
//        assertNotNull(audience.getPublishCountDate());
//        assertEquals(1, audience.getSegments().size());
//        ETSegment segment = audience.getSegments().get(0);
//        assertNotNull(segment.getId());
//        assertEquals(audience.getId(), segment.getAudienceId());
//        assertEquals("Remainder", segment.getName());
//        assertEquals((Integer) Integer.MAX_VALUE, segment.getPriority());
//        assertEquals((Integer) 0, segment.getCap());
//        assertEquals((Integer) 0, segment.getCapPercent());
//        assertEquals(true, segment.getIncludeInPublish());
//    }

//    @Test
//    public void _21_TestRetrieveAudience2()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class,
//                                                          "id=" + id);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals(1, response.getObjects().size());
//        ETAudience audience = response.getObject();
//        assertEquals(id, audience.getId());
//        assertEquals("people age 25-34", audience.getName());
//        assertEquals("people age 25-34", audience.getDescription());
//        assertEquals("people age 25-34", audience.getAudienceCode());
//        assertEquals((Integer) 0, audience.getPublishCount());
//        assertNotNull(audience.getPublishCountDate());
//        assertEquals(1, audience.getSegments().size());
//        ETSegment segment = audience.getSegments().get(0);
//        assertNotNull(segment.getId());
//        assertEquals(audience.getId(), segment.getAudienceId());
//        assertEquals("Remainder", segment.getName());
//        assertEquals((Integer) Integer.MAX_VALUE, segment.getPriority());
//        assertEquals((Integer) 0, segment.getCap());
//        assertEquals((Integer) 0, segment.getCapPercent());
//        assertEquals(true, segment.getIncludeInPublish());
//    }

    @Test
    public void _22_TestPublishAudience()
        throws ETSdkException
    {
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

        // XXX this doesn't actually test anything..
    }

//    @Test
//    public void _23_TestRetrieveAudience3()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class,
//                                                          "id=" + id);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals(1, response.getObjects().size());
//        ETAudience audience = response.getObject();
//        assertEquals(id, audience.getId());
//        assertEquals("people age 25-34", audience.getName());
//        assertEquals("people age 25-34", audience.getDescription());
//        assertNull(audience.getDescription());
//        assertEquals("people age 25-34", audience.getAudienceCode());
//        assertEquals((Integer) 0, audience.getPublishCount());
//        assertNotNull(audience.getPublishCountDate());
//        assertEquals(1, audience.getSegments().size());
//        ETSegment segment = audience.getSegments().get(0);
//        assertNotNull(segment.getId());
//        assertEquals(audience.getId(), segment.getAudienceId());
//        assertEquals("Remainder", segment.getName());
//        assertEquals((Integer) Integer.MAX_VALUE, segment.getPriority());
//        assertEquals((Integer) 0, segment.getCap());
//        assertEquals((Integer) 0, segment.getCapPercent());
//        assertEquals(true, segment.getIncludeInPublish());
//    }

    //@Test
    public void _24_TestExportDataExtension1()
        throws ETSdkException
    {
    }

    //@Test
    public void _25_TestExportDataExtension2()
        throws ETSdkException
    {
    }

    @Test
    public void _26_TestDeleteAudience()
        throws ETSdkException
    {
        ETResponse<ETAudience> response = client.delete(audience);
        assertNull(response.getRequestId());
        assertNull(response.getResponseCode());
        assertNull(response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
    }

//    @Test
//    public void _27_TestRetrieveAudiences3()
//        throws ETSdkException
//    {
//        ETResponse<ETAudience> response = client.retrieve(ETAudience.class);
//        assertNotNull(response.getRequestId());
//        if (client.getConfiguration().equals("audienceBuilderApi", "soap")) {
//            assertEquals("OK", response.getResponseCode());
//        } else {
//            assertEquals("200", response.getResponseCode());
//        }
//        assertEquals("OK", response.getResponseMessage());
//        assertEquals((Integer) 1, response.getPage());
//        assertEquals((Integer) 50, response.getPageSize());
//        assertEquals(totalCount, response.getTotalCount());
//        assertTrue(response.hasMoreResults());
//    }

    //@Test
    public void _28_TestCube1()
        throws ETSdkException
    {

    }

    //@Test
    public void _29_TestCube2()
        throws ETSdkException
    {

    }
}
