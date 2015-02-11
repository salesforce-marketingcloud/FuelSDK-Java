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

import org.junit.Test;

import static org.junit.Assert.*;

public class ETFilterTest {
    //
    // Test order by clause:
    //

    @Test
    public void testOrderByAscending1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo");
        assertNull(filter.getExpression());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testOrderByAscending2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo,bar");
        assertNull(filter.getExpression());
        assertEquals(2, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals("bar", filter.getOrderBy().get(1));
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testOrderByAscending3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo,bar,baz");
        assertNull(filter.getExpression());
        assertEquals(3, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals("bar", filter.getOrderBy().get(1));
        assertEquals("baz", filter.getOrderBy().get(2));
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testOrderByDescending1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo desc");
        assertNull(filter.getExpression());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(0, filter.getProperties().size());
        assertFalse(filter.getOrderByAsc());
    }

    @Test
    public void testOrderByDescending2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo,bar desc");
        assertNull(filter.getExpression());
        assertEquals(2, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals("bar", filter.getOrderBy().get(1));
        assertEquals(0, filter.getProperties().size());
        assertFalse(filter.getOrderByAsc());
    }

    @Test
    public void testOrderByDescending3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo,bar,baz desc");
        assertNull(filter.getExpression());
        assertEquals(3, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals("bar", filter.getOrderBy().get(1));
        assertEquals("baz", filter.getOrderBy().get(2));
        assertEquals(0, filter.getProperties().size());
        assertFalse(filter.getOrderByAsc());
    }

    //
    // Test multiple properties:
    //

    @Test
    public void testMultipleProperties1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "bar");
        assertNull(filter.getExpression());
        assertEquals(0, filter.getOrderBy().size());
        assertEquals(2, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertEquals("bar", filter.getProperties().get(1));
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testMultipleProperties2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "bar", "baz");
        assertNull(filter.getExpression());
        assertEquals(0, filter.getOrderBy().size());
        assertEquals(3, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertEquals("bar", filter.getProperties().get(1));
        assertEquals("baz", filter.getProperties().get(2));
        assertTrue(filter.getOrderByAsc());
    }

    //
    // Test that order doesn't matter:
    //

    @Test
    public void testFiltered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar");
        assertEquals("foo", filter.getExpression().getProperty());
        assertEquals(ETExpression.Operator.EQUALS, filter.getExpression().getOperator());
        assertEquals("bar", filter.getExpression().getValue());
        assertEquals(0, filter.getOrderBy().size());
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testFilteredOrdered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar", "order by foo");
        assertSuccessFilteredOrdered(filter);
    }

    @Test
    public void testFilteredSubset()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar", "foo");
        assertSuccessFilteredSubset(filter);
    }

    @Test
    public void testFilteredOrderedSubset()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar", "order by foo", "foo");
        assertSuccessFilteredOrderedSubset(filter);
    }

    @Test
    public void testFilteredSubsetOrdered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar", "foo", "order by foo");
        assertSuccessFilteredOrderedSubset(filter);
    }

    @Test
    public void testOrdered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo");
        assertNull(filter.getExpression());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testOrderedFiltered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo", "foo=bar");
        assertSuccessFilteredOrdered(filter);
    }

    @Test
    public void testOrderedSubset()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo", "foo");
        assertSuccessOrderedSubset(filter);
    }

    @Test
    public void testOrderedFilteredSubset()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo", "foo=bar", "foo");
        assertSuccessFilteredOrderedSubset(filter);
    }

    @Test
    public void testOrderedSubsetFiltered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("order by foo", "foo", "foo=bar");
        assertSuccessFilteredOrderedSubset(filter);
    }

    @Test
    public void testSubset()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo");
        assertNull(filter.getExpression());
        assertEquals(0, filter.getOrderBy().size());
        assertEquals(1, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertTrue(filter.getOrderByAsc());
    }

    @Test
    public void testSubsetFiltered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "foo=bar");
        assertSuccessFilteredSubset(filter);
    }

    @Test
    public void testSubsetOrdered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "order by foo");
        assertSuccessOrderedSubset(filter);
    }

    @Test
    public void testSubsetFilteredOrdered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "foo=bar", "order by foo");
        assertSuccessFilteredOrderedSubset(filter);
    }

    @Test
    public void testSubsetOrderedFiltered()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo", "order by foo", "foo=bar");
        assertSuccessFilteredOrderedSubset(filter);
    }

    private void assertSuccessFilteredOrdered(ETFilter filter) {
        assertEquals("foo", filter.getExpression().getProperty());
        assertEquals(ETExpression.Operator.EQUALS, filter.getExpression().getOperator());
        assertEquals("bar", filter.getExpression().getValue());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(0, filter.getProperties().size());
        assertTrue(filter.getOrderByAsc());
    }

    private void assertSuccessFilteredSubset(ETFilter filter) {
        assertEquals("foo", filter.getExpression().getProperty());
        assertEquals(ETExpression.Operator.EQUALS, filter.getExpression().getOperator());
        assertEquals("bar", filter.getExpression().getValue());
        assertEquals(0, filter.getOrderBy().size());
        assertEquals(1, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertTrue(filter.getOrderByAsc());
    }

    private void assertSuccessOrderedSubset(ETFilter filter) {
        assertNull(filter.getExpression());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(1, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertTrue(filter.getOrderByAsc());
    }

    private void assertSuccessFilteredOrderedSubset(ETFilter filter) {
        assertEquals("foo", filter.getExpression().getProperty());
        assertEquals(ETExpression.Operator.EQUALS, filter.getExpression().getOperator());
        assertEquals("bar", filter.getExpression().getValue());
        assertEquals(1, filter.getOrderBy().size());
        assertEquals("foo", filter.getOrderBy().get(0));
        assertEquals(1, filter.getProperties().size());
        assertEquals("foo", filter.getProperties().get(0));
        assertTrue(filter.getOrderByAsc());
    }
}
