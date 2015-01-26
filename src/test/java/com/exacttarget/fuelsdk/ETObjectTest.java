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

import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class ETObjectTest {
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
        assertEquals("test", modified.get(0));
    }

    @Test
    public void testGetAllModified2() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertEquals("test1", modified.get(0));
        assertEquals("test2", modified.get(1));
    }

    @Test
    public void testGetAllModified3() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(3, modified.size());
        assertEquals("test1", modified.get(0));
        assertEquals("test2", modified.get(1));
        assertEquals("test3", modified.get(2));
    }

    @Test
    public void testGetAllModified4() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", false));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertEquals("test1", modified.get(0));
        assertEquals("test3", modified.get(1));
    }

    @Test
    public void testGetAllModified5() {
        TestObject testObject = new TestObject();
        assertNull(testObject.setModified("test1", true));
        assertNull(testObject.setModified("test2", true));
        assertNull(testObject.setModified("test3", true));
        List<String> modified = testObject.getAllModified();
        assertEquals(3, modified.size());
        assertEquals("test1", modified.get(0));
        assertEquals("test2", modified.get(1));
        assertEquals("test3", modified.get(2));
        assertTrue(testObject.setModified("test2", false));
        modified = testObject.getAllModified();
        assertEquals(2, modified.size());
        assertEquals("test1", modified.get(0));
        assertEquals("test3", modified.get(1));
    }

    class TestObject extends ETObject {
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

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Date getCreatedDate() {
            return createdDate;
        }

        @Override
        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        @Override
        public Date getModifiedDate() {
            return modifiedDate;
        }

        @Override
        public void setModifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
        }
    }
}
