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

package com.exacttarget.fuelsdk.filter;

public enum ETFilterOperators {

    EQUALS("equals"),
    NOT_EQUALS("notEquals"),
    GREATER_THAN("greaterThan"),
    LESS_THAN("lessThan"),
    IS_NULL("isNull"),
    IS_NOT_NULL("isNotNull"),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual"),
    LESS_THAN_OR_EQUAL("lessThanOrEqual"),
    BETWEEN("between"),
    IN("IN"),
    LIKE("like"),
    EXISTS_IN_STRING("existsInString"),
    EXISTS_IN_STRING_AS_A_WORD("existsInStringAsAWord"),
    NOT_EXISTS_IN_STRING("notExistsInString"),
    BEGINS_WITH("beginsWith"),
    ENDS_WITH("endsWith"),
    CONTAINS("contains"),
    NOT_CONTAINS("notContains"),
    IS_ANNIVERSARY("isAnniversary"),
    IS_NOT_ANNIVERSARY("isNotAnniversary"),
    GREATER_THAN_ANNIVERSARY("greaterThanAnniversary"),
    LESS_THAN_ANNIVERSARY("lessThanAnniversary");
    private final String value;

    ETFilterOperators(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETFilterOperators fromValue(String v) {
        for (ETFilterOperators c: ETFilterOperators.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
