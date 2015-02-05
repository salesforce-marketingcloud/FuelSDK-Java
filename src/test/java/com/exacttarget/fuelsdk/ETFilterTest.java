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
    @Test
    public void testEquals1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testEquals7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  =    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'!='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testNotEquals7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo != bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  !=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.NOT_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'<'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testLessThan7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo < bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  <    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'<='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo <= bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  <=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LESS_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'>bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\">bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'>'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testGreaterThan7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo > bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  >    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'>=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\">=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'>='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo >= bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  >=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.GREATER_THAN_OR_EQUALS, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testIsNull1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo is null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NULL, filter.getOperator());
    }

    @Test
    public void testIsNull2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' is null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NULL, filter.getOperator());
    }

    @Test
    public void testIsNull3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" is null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NULL, filter.getOperator());
    }

    @Test
    public void testIsNull4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  is    null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NULL, filter.getOperator());
    }

    @Test
    public void testIsNotNull1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NOT_NULL, filter.getOperator());
    }

    @Test
    public void testIsNotNull2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NOT_NULL, filter.getOperator());
    }

    @Test
    public void testIsNotNull3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NOT_NULL, filter.getOperator());
    }

    @Test
    public void testIsNotNull4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  is    not        null");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IS_NOT_NULL, filter.getOperator());
    }

    @Test
    public void testIn1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1,2)");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testIn2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1, 2)");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testIn3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1,2,3)");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
        assertEquals("3", filter.getValues().get(2));
    }

    @Test
    public void testIn4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1,2, 3)");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
        assertEquals("3", filter.getValues().get(2));
    }

    @Test
    public void testIn5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1, 2, 3)");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
        assertEquals("3", filter.getValues().get(2));
    }

    @Test
    public void testIn6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in ('bar', 'baz')");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("bar", filter.getValues().get(0));
        assertEquals("baz", filter.getValues().get(1));
    }

    @Test
    public void testIn7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (\"bar\", \"baz\")");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.IN, filter.getOperator());
        assertEquals("bar", filter.getValues().get(0));
        assertEquals("baz", filter.getValues().get(1));
    }

    @Test
    public void testBetween1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo between 1 and 2");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.BETWEEN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testBetween2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo between  1   and    2");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.BETWEEN, filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testLike1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like 'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like \"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo' like 'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(ETFilter.Operator.LIKE, filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testAnd1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar and bar = baz");
        assertEquals(ETFilter.Operator.AND, filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("foo", filter1.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter1.getOperator());
        assertEquals("bar", filter1.getValue());
        assertEquals("bar", filter2.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter2.getOperator());
        assertEquals("baz", filter2.getValue());
    }

    @Test
    public void testOr1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar or bar = baz");
        assertEquals(ETFilter.Operator.OR, filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("foo", filter1.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter1.getOperator());
        assertEquals("bar", filter1.getValue());
        assertEquals("bar", filter2.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter2.getOperator());
        assertEquals("baz", filter2.getValue());
    }

    @Test
    public void testAndOr1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar and bar = baz or baz = foo");
        assertEquals(ETFilter.Operator.OR, filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals(ETFilter.Operator.AND, filter1.getOperator());
        ETFilter filter3 = filter1.getFilters().get(0);
        ETFilter filter4 = filter1.getFilters().get(1);
        assertEquals("baz", filter2.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter2.getOperator());
        assertEquals("foo", filter2.getValue());
        assertEquals("foo", filter3.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter3.getOperator());
        assertEquals("bar", filter3.getValue());
        assertEquals("bar", filter4.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter4.getOperator());
        assertEquals("baz", filter4.getValue());
    }

    @Test
    public void testOrAnd1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar or bar = baz and baz = foo");
        assertEquals(ETFilter.Operator.OR, filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("foo", filter1.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter1.getOperator());
        assertEquals("bar", filter1.getValue());
        assertEquals(ETFilter.Operator.AND, filter2.getOperator());
        ETFilter filter3 = filter2.getFilters().get(0);
        ETFilter filter4 = filter2.getFilters().get(1);
        assertEquals("bar", filter3.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter3.getOperator());
        assertEquals("baz", filter3.getValue());
        assertEquals("baz", filter4.getProperty());
        assertEquals(ETFilter.Operator.EQUALS, filter4.getOperator());
        assertEquals("foo", filter4.getValue());
    }
}
