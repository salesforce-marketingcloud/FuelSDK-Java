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

import java.util.Date;
import java.util.List;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ETObjectTest {
    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
        Assume.assumeNotNull(ETObjectTest.class
                .getResource("/fuelsdk-test.properties"));
    }

    @Test
    public void testGetModified1() {
        TestObject testObject = new TestObject();
        assertNull(testObject.getModified("test"));
    }

    @Test
    public void testGetModified2() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test", true));
        assertTrue(testObject.getModified("test"));
    }

    @Test
    public void testGetModified3() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test", false));
        assertFalse(testObject.getModified("test"));
    }

    @Test
    public void testGetModified4() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test", true));
        assertTrue(testObject.setModified("test", false));
        assertFalse(testObject.getModified("test"));
    }

    @Test
    public void testGetModified5() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test", false));
        assertFalse(testObject.setModified("test", true));
        assertTrue(testObject.getModified("test"));
    }

    @Test
    public void testGetAllModified1() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(1, modified.size());
        assertTrue(modified.contains("test"));
    }

    @Test
    public void testGetAllModified2() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertTrue(modified.contains("test1"));
        assertTrue(modified.contains("test2"));
    }

    @Test
    public void testGetAllModified3() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(3, modified.size());
        assertTrue(modified.contains("test1"));
        assertTrue(modified.contains("test2"));
        assertTrue(modified.contains("test3"));
    }

    @Test
    public void testGetAllModified4() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", false));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertTrue(modified.contains("test1"));
        assertTrue(modified.contains("test3"));
    }

    @Test
    public void testGetAllModified5() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(3, modified.size());
        assertTrue(modified.contains("test1"));
        assertTrue(modified.contains("test2"));
        assertTrue(modified.contains("test3"));
        assertTrue(testObject.setModified("test2", false));
        modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertTrue(modified.contains("test1"));
        assertTrue(modified.contains("test3"));
    }

    class TestObject extends ETApiObject {
        private String id = null;
        private String key = null;
        private String name = null;
        private Date createdDate = null;
        private Date modifiedDate = null;

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Date getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
        }
    }
}
