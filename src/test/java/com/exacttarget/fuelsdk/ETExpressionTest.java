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

import java.util.List;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ETExpressionTest {
    @BeforeClass
    public static void setUpBeforeClass()
        throws ETSdkException
    {
//        Assume.assumeNotNull(ETExpressionTest.class
//                .getResource("/fuelsdk-test.properties"));
    }

    @Test
    public void testEquals1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo='bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo=\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\"=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'='bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testEquals7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo = bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testEquals8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  =    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo!=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo!='bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo!=\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'!=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\"!=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'!='bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testNotEquals7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo != bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testNotEquals8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  !=    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.NOT_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<'bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'<bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\"<bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'<'bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testLessThan7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo < bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThan8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  <    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<='bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo<=\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'<=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\"<=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'<='bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo <= bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLessThanOrEqual8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  <=    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LESS_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>'bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'>bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\">bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'>'bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testGreaterThan7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo > bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThan8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  >    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>='bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo>=\"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo'>=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\">=bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo'>='bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo >= bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testGreaterThanOrEqual8()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  >=    bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.GREATER_THAN_OR_EQUALS, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testIsNull1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo is null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NULL, expression.getOperator());
    }

    @Test
    public void testIsNull2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo' is null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NULL, expression.getOperator());
    }

    @Test
    public void testIsNull3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\" is null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NULL, expression.getOperator());
    }

    @Test
    public void testIsNull4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  is    null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NULL, expression.getOperator());
    }

    @Test
    public void testIsNotNull1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo is not null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NOT_NULL, expression.getOperator());
    }

    @Test
    public void testIsNotNull2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo' is not null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NOT_NULL, expression.getOperator());
    }

    @Test
    public void testIsNotNull3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\" is not null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NOT_NULL, expression.getOperator());
    }

    @Test
    public void testIsNotNull4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo  is    not        null");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IS_NOT_NULL, expression.getOperator());
    }

    @Test
    public void testIn1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (1,2)");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
    }

    @Test
    public void testIn2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (1, 2)");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
    }

    @Test
    public void testIn3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (1,2,3)");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
        assertEquals("3", expression.getValues().get(2));
    }

    @Test
    public void testIn4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (1,2, 3)");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
        assertEquals("3", expression.getValues().get(2));
    }

    @Test
    public void testIn5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (1, 2, 3)");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
        assertEquals("3", expression.getValues().get(2));
    }

    @Test
    public void testIn6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in ('bar', 'baz')");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("bar", expression.getValues().get(0));
        assertEquals("baz", expression.getValues().get(1));
    }

    @Test
    public void testIn7()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo in (\"bar\", \"baz\")");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.IN, expression.getOperator());
        assertEquals("bar", expression.getValues().get(0));
        assertEquals("baz", expression.getValues().get(1));
    }

    @Test
    public void testBetween1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo between 1 and 2");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.BETWEEN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
    }

    @Test
    public void testBetween2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo between  1   and    2");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.BETWEEN, expression.getOperator());
        assertEquals("1", expression.getValues().get(0));
        assertEquals("2", expression.getValues().get(1));
    }

    @Test
    public void testLike1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo like bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLike2()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo like 'bar'");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLike3()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo like \"bar\"");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLike4()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo' like bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLike5()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("\"foo\" like bar");
        assertEquals("foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar", expression.getValue());
    }

    @Test
    public void testLike6()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("'foo foo' like 'bar bar'");
        assertEquals("foo foo", expression.getProperty());
        assertEquals(ETExpression.Operator.LIKE, expression.getOperator());
        assertEquals("bar bar", expression.getValue());
    }

    @Test
    public void testAnd1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo = bar and bar = baz");
        assertEquals(ETExpression.Operator.AND, expression.getOperator());
        ETExpression expression1 = expression.getSubexpressions().get(0);
        ETExpression expression2 = expression.getSubexpressions().get(1);
        assertEquals("foo", expression1.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression1.getOperator());
        assertEquals("bar", expression1.getValue());
        assertEquals("bar", expression2.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression2.getOperator());
        assertEquals("baz", expression2.getValue());
    }

    @Test
    public void testOr1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo = bar or bar = baz");
        assertEquals(ETExpression.Operator.OR, expression.getOperator());
        ETExpression expression1 = expression.getSubexpressions().get(0);
        ETExpression expression2 = expression.getSubexpressions().get(1);
        assertEquals("foo", expression1.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression1.getOperator());
        assertEquals("bar", expression1.getValue());
        assertEquals("bar", expression2.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression2.getOperator());
        assertEquals("baz", expression2.getValue());
    }

    @Test
    public void testAndOr1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo = bar and bar = baz or baz = foo");
        assertEquals(ETExpression.Operator.OR, expression.getOperator());
        ETExpression expression1 = expression.getSubexpressions().get(0);
        ETExpression expression2 = expression.getSubexpressions().get(1);
        assertEquals(ETExpression.Operator.AND, expression1.getOperator());
        ETExpression expression3 = expression1.getSubexpressions().get(0);
        ETExpression expression4 = expression1.getSubexpressions().get(1);
        assertEquals("baz", expression2.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression2.getOperator());
        assertEquals("foo", expression2.getValue());
        assertEquals("foo", expression3.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression3.getOperator());
        assertEquals("bar", expression3.getValue());
        assertEquals("bar", expression4.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression4.getOperator());
        assertEquals("baz", expression4.getValue());
    }

    @Test
    public void testOrAnd1()
        throws ETSdkException
    {
        ETExpression expression = ETExpression.parse("foo = bar or bar = baz and baz = foo");
        assertEquals(ETExpression.Operator.OR, expression.getOperator());
        ETExpression expression1 = expression.getSubexpressions().get(0);
        ETExpression expression2 = expression.getSubexpressions().get(1);
        assertEquals("foo", expression1.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression1.getOperator());
        assertEquals("bar", expression1.getValue());
        assertEquals(ETExpression.Operator.AND, expression2.getOperator());
        ETExpression expression3 = expression2.getSubexpressions().get(0);
        ETExpression expression4 = expression2.getSubexpressions().get(1);
        assertEquals("bar", expression3.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression3.getOperator());
        assertEquals("baz", expression3.getValue());
        assertEquals("baz", expression4.getProperty());
        assertEquals(ETExpression.Operator.EQUALS, expression4.getOperator());
        assertEquals("foo", expression4.getValue());
    }

    @Test
    public void testSetValue() {
        ETExpression expression = new ETExpression();
        expression.addValue("foo");
        expression.addValue("bar");
        List<String> values1 = expression.getValues();
        assertEquals(2, values1.size());
        assertEquals("foo", values1.get(0));
        assertEquals("bar", values1.get(1));
        expression.setValue("baz");
        List<String> values2 = expression.getValues();
        assertEquals(1, values2.size());
        assertEquals("baz", values2.get(0));
    }
}
