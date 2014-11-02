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

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETFolderTest {
    private static ETClient client = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        client = new ETClient();
    }

    @Test
    public void _01_TestRetrieveAll()
        throws ETSdkException
    {
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class);
        for (ETFolder folder : response.getObjects()) {
            assertNotNull(folder.getId());
            assertNotNull(folder.getKey());
            assertNotNull(folder.getName());
            assertNotNull(folder.getDescription());
            assertNotNull(folder.getCreatedDate());
            assertNotNull(folder.getModifiedDate());
            assertNotNull(folder.getContentType());
            assertNotNull(folder.getIsActive());
            assertNotNull(folder.getIsEditable());
            assertNotNull(folder.getAllowChildren());
        }
    }

    @Test
    public void _02_TestRetrieveAllPropertiesSpecified()
        throws ETSdkException
    {
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        "key",
                                                        "name",
                                                        "description");
        for (ETFolder folder : response.getObjects()) {
            assertNull(folder.getId());
            assertNotNull(folder.getKey());
            assertNotNull(folder.getName());
            assertNotNull(folder.getDescription());
            assertNull(folder.getCreatedDate());
            assertNull(folder.getModifiedDate());
            assertNull(folder.getContentType());
            assertNull(folder.getParentFolderKey());
            assertNull(folder.getIsActive());
            assertNull(folder.getIsEditable());
            assertNull(folder.getAllowChildren());
        }
    }

    @Test
    public void _03_TestRetrieveFiltered()
        throws ETSdkException
    {
        // retrieve the Data Extension folder, which
        // has customer key of dataextension_default
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        "key='dataextension_default'");
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertNotNull(folder.getId());
        assertEquals("dataextension_default", folder.getKey());
        assertEquals("Data Extensions", folder.getName());
        assertEquals("", folder.getDescription());
        assertNotNull(folder.getCreatedDate());
        assertNotNull(folder.getModifiedDate());
        assertEquals("dataextension", folder.getContentType());
        assertNull(folder.getParentFolderKey());
        assertTrue(folder.getIsActive());
        assertFalse(folder.getIsEditable());
        assertTrue(folder.getAllowChildren());
    }

    @Test
    public void _04_TestRetrieveFilteredPropertiesSpecified()
        throws ETSdkException
    {
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class,
                                                        "key='dataextension_default'",
                                                        "key",
                                                        "name",
                                                        "description");
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertNull(folder.getId());
        assertEquals("dataextension_default", folder.getKey());
        assertEquals("Data Extensions", folder.getName());
        assertEquals("", folder.getDescription());
        assertNull(folder.getCreatedDate());
        assertNull(folder.getModifiedDate());
        assertNull(folder.getContentType());
        assertNull(folder.getParentFolderKey());
        assertNull(folder.getIsActive());
        assertNull(folder.getIsEditable());
        assertNull(folder.getAllowChildren());
    }

    private static String id = null;

    @Test
    public void _05_TestCreateSingle()
        throws ETSdkException
    {
        ETFolder folder = new ETFolder();
        folder.setKey("test1");
        folder.setName("test1");
        folder.setDescription("test1");
        folder.setContentType("dataextension");
        folder.setParentFolderKey("dataextension_default");
        ETResponse<ETFolder> response = client.create(folder);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETFolder> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Folder created successfully.", result.getResponseMessage());
        assertNull(result.getErrorCode());
        assertNotNull(result.getObjectId());
        // save the ID for use in the next test
        id = result.getObjectId();
    }

    private static ETFolder folder = null;

    @Test
    public void _06_TestRetrieveSingle()
        throws ETSdkException
    {
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class, "id=" + id);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getObjects().size());
        folder = response.getObject();
        assertEquals(id, folder.getId());
        assertEquals("test1", folder.getKey());
        assertEquals("test1", folder.getName());
        assertEquals("test1", folder.getDescription());
        assertNotNull(folder.getCreatedDate());
        assertNotNull(folder.getModifiedDate());
        assertEquals("dataextension", folder.getContentType());
        assertEquals("dataextension_default", folder.getParentFolderKey());
        assertTrue(folder.getIsActive());
        assertFalse(folder.getIsEditable());
        assertFalse(folder.getAllowChildren());
    }

    @Test
    public void _07_TestUpdateSingle()
        throws ETSdkException
    {
        folder.setName("TEST1");
        ETResponse<ETFolder> response = client.update(folder);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETFolder> result = response.getResults().get(0);
        assertEquals("OK", result.getResponseCode());
        assertEquals("Folder updated successfully.", result.getResponseMessage());
        assertNull(result.getErrorCode());
    }

    @Test
    public void _08_TestRetrieveSingle()
        throws ETSdkException
    {
        ETResponse<ETFolder> response = client.retrieve(ETFolder.class, "id=" + id);
        // ensure we only received 1
        assertEquals(1, response.getObjects().size());
        ETFolder folder = response.getObjects().get(0);
        assertEquals(id, folder.getId());
        assertEquals("test1", folder.getKey());
        assertEquals("TEST1", folder.getName());
        assertEquals("test1", folder.getDescription());
        assertNotNull(folder.getCreatedDate());
        assertNotNull(folder.getModifiedDate());
        assertEquals("dataextension", folder.getContentType());
        assertEquals("dataextension_default", folder.getParentFolderKey());
        assertTrue(folder.getIsActive());
        assertFalse(folder.getIsEditable());
        assertFalse(folder.getAllowChildren());
    }

    @Test
    public void _09_TestDeleteSingle()
        throws ETSdkException
    {
        ETFolder folder = new ETFolder();
        folder.setKey("test1");
        ETResponse<ETFolder> response = client.delete(folder);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(1, response.getResults().size());
        ETResult<ETFolder> result = response.getResult();
        assertEquals("OK", result.getResponseCode());
        assertEquals("Folder deleted successfully.", result.getResponseMessage());
        assertNull(result.getErrorCode());
    }

    private static String id1 = null;
    private static String id2 = null;

    @Test
    public void _10_TestCreateMultiple()
        throws ETSdkException
    {
        ETFolder folder1 = new ETFolder();
        folder1.setKey("test1");
        folder1.setName("test1");
        folder1.setDescription("test1");
        folder1.setContentType("dataextension");
        folder1.setParentFolderKey("dataextension_default");
        ETFolder folder2 = new ETFolder();
        folder2.setKey("test2");
        folder2.setName("test2");
        folder2.setDescription("test2");
        folder2.setContentType("dataextension");
        folder2.setParentFolderKey("dataextension_default");
        List<ETFolder> folders = new ArrayList<ETFolder>();
        folders.add(folder1);
        folders.add(folder2);
        ETResponse<ETFolder> response = client.create(folders);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETFolder> result1 = response.getResults().get(0);
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Folder created successfully.", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        assertNotNull(result1.getObjectId());
        // save the ID for use in the next test
        id1 = result1.getObjectId();
        ETResult<ETFolder> result2 = response.getResults().get(0);
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Folder created successfully.", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
        assertNotNull(result2.getObjectId());
        // save the ID for use in the next test
        id2 = result2.getObjectId();
    }

    @Test
    public void _11_TestRetrieveMultiple()
        throws ETSdkException
    {
        // XXX implement when we can pass the filter id = <id1> and id = <id2>
    }

    @Test
    public void _12_TestUpdateMultiple()
        throws ETSdkException
    {
        // XXX implement when we implement _11_TestRetrieveCreatedMultiple
    }

    @Test
    public void _13_TestRetrieveMultiple()
        throws ETSdkException
    {
        // XXX implement when we implement _12_TestUpdateMultiple
    }

    @Test
    public void _14_TestDeleteMultiple()
        throws ETSdkException
    {
        ETFolder folder1 = new ETFolder();
        folder1.setKey("test1");
        ETFolder folder2 = new ETFolder();
        folder2.setKey("test2");
        List<ETFolder> folders = new ArrayList<ETFolder>();
        folders.add(folder1);
        folders.add(folder2);
        ETResponse<ETFolder> response = client.delete(folders);
        assertNotNull(response.getRequestId());
        assertEquals("OK", response.getResponseCode());
        assertEquals("OK", response.getResponseMessage());
        assertNull(response.getPage());
        assertNull(response.getPageSize());
        assertNull(response.getTotalCount());
        assertFalse(response.hasMoreResults());
        assertEquals(2, response.getResults().size());
        ETResult<ETFolder> result1 = response.getResults().get(0);
        assertEquals("OK", result1.getResponseCode());
        assertEquals("Folder deleted successfully.", result1.getResponseMessage());
        assertNull(result1.getErrorCode());
        ETResult<ETFolder> result2 = response.getResults().get(0);
        assertEquals("OK", result2.getResponseCode());
        assertEquals("Folder deleted successfully.", result2.getResponseMessage());
        assertNull(result2.getErrorCode());
    }
}
