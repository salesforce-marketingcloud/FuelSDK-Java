//
// ETFilterOperators.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
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
