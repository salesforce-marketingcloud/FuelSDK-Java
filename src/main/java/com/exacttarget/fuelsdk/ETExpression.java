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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.PrettyPrint;

public class ETExpression extends ETObject {
    public enum Operator {
        EQUALS("="),
        NOT_EQUALS("!="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUALS("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUALS(">="),
        IS_NULL("is null"),
        IS_NOT_NULL("is not null"),
        IN("in"),
        BETWEEN("between"),
        LIKE("like"),
        AND("and"),
        OR("or"),
        NOT("not");
        private final String value;

        Operator(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static Operator fromValue(String value) {
            for (Operator v : Operator.values()) {
                if (v.value.equals(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }

    @PrettyPrint
    private String property = null;
    @PrettyPrint
    private Operator operator = null;
    @PrettyPrint
    private List<String> values = new ArrayList<String>();
    @PrettyPrint
    private List<ETExpression> expressions = new ArrayList<ETExpression>();

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setOperator(String operator) {
        this.operator = Operator.fromValue(operator);
    }

    public String getValue() {
        assert values.size() == 1;
        return values.get(0);
    }

    public List<String> getValues() {
        return values;
    }

    public void addValue(String value) {
        values.add(value);
    }

    public List<ETExpression> getExpressions() {
        return expressions;
    }

    public void addExpression(ETExpression expression) {
        expressions.add(expression);
    }

    public static ETExpression parse(String s)
        throws ETSdkException
    {
        ETExpressionParser parser =
                new ETExpressionParser(new ByteArrayInputStream(s.getBytes()));
        ETExpression expression = null;
        try {
            expression = parser.parse();
        } catch (ParseException ex) {
            throw new ETSdkException("could not parse expression: " + s, ex);
        }
        return expression;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (operator != null) {
            switch(operator) {
              case EQUALS:
              case NOT_EQUALS:
              case LESS_THAN:
              case LESS_THAN_OR_EQUALS:
              case GREATER_THAN:
              case GREATER_THAN_OR_EQUALS:
              case IN:
              case BETWEEN:
              case LIKE:
                stringBuilder.append(property);
                stringBuilder.append(" ");
                stringBuilder.append(operator.toString());
                stringBuilder.append(" ");
                stringBuilder.append(values);
                break;
              case IS_NULL:
              case IS_NOT_NULL:
                stringBuilder.append(property);
                stringBuilder.append(" ");
                stringBuilder.append(operator.toString());
                break;
              case AND:
              case OR:
                stringBuilder.append(expressions.get(0));
                stringBuilder.append(" ");
                stringBuilder.append(operator.toString());
                stringBuilder.append(" ");
                stringBuilder.append(expressions.get(1));
                break;
              case NOT:
                stringBuilder.append(operator.toString());
                stringBuilder.append(" ");
                stringBuilder.append(expressions.get(0));
                break;
            }
        } else {
            stringBuilder.append("(");
            stringBuilder.append(expressions.get(0));
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }
}
