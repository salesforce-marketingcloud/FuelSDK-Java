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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ETFilter {
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

    private String property = null;
    private Operator operator = null;
    private List<String> values = new ArrayList<String>();
    private List<ETFilter> filters = new ArrayList<ETFilter>();

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

    public List<ETFilter> getFilters() {
        return filters;
    }

    public void addFilter(ETFilter filter) {
        filters.add(filter);
    }

    public static ETFilter parse(String filter)
        throws ETSdkException
    {
        ETFilterParser parser = new ETFilterParser(new ByteArrayInputStream(filter.getBytes()));
        ETFilter parsedFilter = null;
        try {
            parsedFilter = parser.parse();
        } catch (ParseException ex) {
            throw new ETSdkException("could not parse filter: " + filter, ex);
        }
        return parsedFilter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

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
            stringBuilder.append(filters.get(0));
            stringBuilder.append(" ");
            stringBuilder.append(operator.toString());
            stringBuilder.append(" ");
            stringBuilder.append(filters.get(1));
            break;
          case NOT:
            stringBuilder.append(operator.toString());
            stringBuilder.append(" ");
            stringBuilder.append(filters.get(0));
            break;
        }

        return stringBuilder.toString();
    }
}
