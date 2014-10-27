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

/**
 * @deprecated
 * Use {@link com.exacttarget.fuelsdk.ETFilter}.
 */
@Deprecated
public enum ETFilterOperators {
    EQUALS("equals"),
    NOT_EQUALS("notEquals"),
    LESS_THAN("lessThan"),
    LESS_THAN_OR_EQUAL("lessThanOrEqual"),
    GREATER_THAN("greaterThan"),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual"),
    IS_NULL("isNull"),
    IS_NOT_NULL("isNotNull"),
    BETWEEN("between"),
    IN("IN"),
    LIKE("like"),
    BEGINS_WITH("beginsWith"),
    CONTAINS("contains"),
    ENDS_WITH("endsWith"),
    EXISTS_IN_STRING("existsInString"),
    EXISTS_IN_STRING_AS_A_WORD("existsInStringAsAWord"),
    GREATER_THAN_ANNIVERSARY("greaterThanAnniversary"),
    IS_ANNIVERSARY("isAnniversary"),
    IS_NOT_ANNIVERSARY("isNotAnniversary"),
    LESS_THAN_ANNIVERSARY("lessThanAnniversary"),
    NOT_CONTAINS("notContains"),
    NOT_EXISTS_IN_STRING("notExistsInString");
    private final String value;

    ETFilterOperators(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ETFilterOperators fromValue(String value) {
        for (ETFilterOperators v : ETFilterOperators.values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
