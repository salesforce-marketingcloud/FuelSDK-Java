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

import org.junit.Test;

import static org.junit.Assert.*;

public class ETFilterTest {
    @Test
    public void testEquals1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testEquals7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testEquals8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  =    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo!=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"!=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'!='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testNotEquals7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo != bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testNotEquals8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  !=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("!=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"<bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'<'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testLessThan7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo < bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThan8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  <    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo<=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\"<=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'<='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo <= bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLessThanOrEqual8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  <=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("<=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'>bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\">bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'>'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testGreaterThan7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo > bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThan8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  >    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>='bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo>=\"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo'>=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\">=bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo'>='bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo >= bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testGreaterThanOrEqual8()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  >=    bar");
        assertEquals("foo", filter.getProperty());
        assertEquals(">=", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testIsNull1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo is null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is null", filter.getOperator());
    }

    @Test
    public void testIsNull2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' is null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is null", filter.getOperator());
    }

    @Test
    public void testIsNull3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" is null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is null", filter.getOperator());
    }

    @Test
    public void testIsNull4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  is    null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is null", filter.getOperator());
    }

    @Test
    public void testIsNotNull1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is not null", filter.getOperator());
    }

    @Test
    public void testIsNotNull2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is not null", filter.getOperator());
    }

    @Test
    public void testIsNotNull3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" is not null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is not null", filter.getOperator());
    }

    @Test
    public void testIsNotNull4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo  is    not        null");
        assertEquals("foo", filter.getProperty());
        assertEquals("is not null", filter.getOperator());
    }

    @Test
    public void testIn1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1,2)");
        assertEquals("foo", filter.getProperty());
        assertEquals("in", filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testIn2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1, 2)");
        assertEquals("foo", filter.getProperty());
        assertEquals("in", filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testIn3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (1,2,3)");
        assertEquals("foo", filter.getProperty());
        assertEquals("in", filter.getOperator());
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
        assertEquals("in", filter.getOperator());
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
        assertEquals("in", filter.getOperator());
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
        assertEquals("in", filter.getOperator());
        assertEquals("bar", filter.getValues().get(0));
        assertEquals("baz", filter.getValues().get(1));
    }

    @Test
    public void testIn7()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo in (\"bar\", \"baz\")");
        assertEquals("foo", filter.getProperty());
        assertEquals("in", filter.getOperator());
        assertEquals("bar", filter.getValues().get(0));
        assertEquals("baz", filter.getValues().get(1));
    }

    @Test
    public void testBetween1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo between 1 and 2");
        assertEquals("foo", filter.getProperty());
        assertEquals("between", filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testBetween2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo between  1   and    2");
        assertEquals("foo", filter.getProperty());
        assertEquals("between", filter.getOperator());
        assertEquals("1", filter.getValues().get(0));
        assertEquals("2", filter.getValues().get(1));
    }

    @Test
    public void testLike1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike2()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like 'bar'");
        assertEquals("foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike3()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo like \"bar\"");
        assertEquals("foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike4()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo' like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike5()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("\"foo\" like bar");
        assertEquals("foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar", filter.getValue());
    }

    @Test
    public void testLike6()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("'foo foo' like 'bar bar'");
        assertEquals("foo foo", filter.getProperty());
        assertEquals("like", filter.getOperator());
        assertEquals("bar bar", filter.getValue());
    }

    @Test
    public void testAnd1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar and bar = baz");
        assertEquals("and", filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("foo", filter1.getProperty());
        assertEquals("=", filter1.getOperator());
        assertEquals("bar", filter1.getValue());
        assertEquals("bar", filter2.getProperty());
        assertEquals("=", filter2.getOperator());
        assertEquals("baz", filter2.getValue());
    }

    @Test
    public void testOr1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar or bar = baz");
        assertEquals("or", filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("foo", filter1.getProperty());
        assertEquals("=", filter1.getOperator());
        assertEquals("bar", filter1.getValue());
        assertEquals("bar", filter2.getProperty());
        assertEquals("=", filter2.getOperator());
        assertEquals("baz", filter2.getValue());
    }

    @Test
    public void testAndOr1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar and bar = baz or baz = foo");
        assertEquals("or", filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("and", filter1.getOperator());
        ETFilter filter3 = filter1.getFilters().get(0);
        ETFilter filter4 = filter1.getFilters().get(1);
        assertEquals("baz", filter2.getProperty());
        assertEquals("=", filter2.getOperator());
        assertEquals("foo", filter2.getValue());
        assertEquals("foo", filter3.getProperty());
        assertEquals("=", filter3.getOperator());
        assertEquals("bar", filter3.getValue());
        assertEquals("bar", filter4.getProperty());
        assertEquals("=", filter4.getOperator());
        assertEquals("baz", filter4.getValue());
    }

    @Test
    public void testOrAnd1()
        throws ETSdkException
    {
        ETFilter filter = ETFilter.parse("foo = bar or bar = baz and baz = foo");
        assertEquals("and", filter.getOperator());
        ETFilter filter1 = filter.getFilters().get(0);
        ETFilter filter2 = filter.getFilters().get(1);
        assertEquals("or", filter1.getOperator());
        ETFilter filter3 = filter1.getFilters().get(0);
        ETFilter filter4 = filter1.getFilters().get(1);
        assertEquals("baz", filter2.getProperty());
        assertEquals("=", filter2.getOperator());
        assertEquals("foo", filter2.getValue());
        assertEquals("foo", filter3.getProperty());
        assertEquals("=", filter3.getOperator());
        assertEquals("bar", filter3.getValue());
        assertEquals("bar", filter4.getProperty());
        assertEquals("=", filter4.getOperator());
        assertEquals("baz", filter4.getValue());
    }
}
