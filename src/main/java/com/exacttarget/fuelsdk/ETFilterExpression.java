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

//
// <name> = <value>
// <name> != <value>
// <name> < <value>
// <name> <= <value>
// <name> > <value>
// <name> >= <value>
// <name> is null
// <name> is not null
// <name> like <pattern>
//
// <name> between <value1> and <value2>
// <name> in (<value1>, <value2>, ...)
//
// <expression> and <expression>
// <expression> or <expression>
//

public class ETFilterExpression {
    private enum Operator {
        EQUALS("="),
        NOT_EQUALS("!="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        IS_NULL("is null"),
        IS_NOT_NULL("is not null"),
        IN("IN"),
        BETWEEN("between"),
        LIKE("like");
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
    private String column = null;
    private Operator operator = null;
    private List<String> values = new ArrayList<String>();

    public ETFilterExpression(String expression)
        throws ETSdkException
    {
        String tokens[] = expression.split("\\s");
        column = tokens[0];
        if (tokens[1].equals("is")) {
            if (tokens[2].equals("null")) {
                operator = Operator.IS_NULL;
            } else if (tokens[2].equals("not") && tokens[3].equals("null")) {
                operator = Operator.IS_NOT_NULL;
            }
        } else if (tokens[1].equals("in")) {
            operator = Operator.IN;
            // XXX cleanup
            if ((tokens[2].charAt(0) != '(') ||
                (tokens[tokens.length - 1].charAt(tokens[tokens.length - 1].length() - 1) != ')'))
            {
                throw new ETSdkException("parse error: \"" + expression + "\"");
            }
            tokens[2] = tokens[2].substring(1);
            tokens[tokens.length - 1] = tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].length() - 1);
            for (int i = 2; i < tokens.length; i++) {
                values.add(tokens[i]);
            }
        } else if (tokens[1].equals("between")) {
            operator = Operator.BETWEEN;
            // XXX check there is only two
            for (int i = 2; i < tokens.length; i++) {
                values.add(tokens[i]);
            }
        } else {
            try {
                operator = Operator.fromValue(tokens[1]);
            } catch (IllegalArgumentException ex) {
                throw new ETSdkException("parse error: \"" + expression + "\"");
            }
            // XXX check there is only one
            for (int i = 2; i < tokens.length; i++) {
                values.add(tokens[i]);
            }
        }
    }

    public String getColumn() {
        return column;
    }

    public Operator getOperator() {
        return operator;
    }

    public List<String> getValues() {
        return values;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("column = " + column);
        stringBuilder.append(", ");
        stringBuilder.append("operator = " + operator);
        stringBuilder.append(", ");
        stringBuilder.append("values =");
        for (String value : values) {
            stringBuilder.append(" " + value);
        }
        return stringBuilder.toString();
    }
}
