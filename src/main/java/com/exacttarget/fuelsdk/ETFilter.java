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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.exacttarget.fuelsdk.internal.FilterPart;
import com.exacttarget.fuelsdk.internal.SimpleFilterPart;
import com.exacttarget.fuelsdk.internal.SimpleOperators;

public class ETFilter {
    public enum Operator {
        EQUALS("="),
        NOT_EQUALS("!="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        IS_NULL("is null"),
        IS_NOT_NULL("is not null"),
        IN("in"),
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

    private SimpleFilterPart filter = new SimpleFilterPart();

    public String getProperty() {
        return filter.getProperty();
    }

    public void setProperty(String property) {
        filter.setProperty(property);
    }

    public String getOperator() {
        SimpleOperators operator = filter.getSimpleOperator();
        switch (operator) {
          case EQUALS:
            return "=";
          case NOT_EQUALS:
            return "!=";
          case LESS_THAN:
            return "<";
          case LESS_THAN_OR_EQUAL:
            return "<=";
          case GREATER_THAN:
            return ">";
          case GREATER_THAN_OR_EQUAL:
            return ">=";
          case IS_NULL:
            return "is null";
          case IS_NOT_NULL:
            return "is not null";
          case BETWEEN:
            return "between";
          case IN:
            return "in";
          case LIKE:
            return "like";
          default:
            return null;
        }
    }

    public void setOperator(String operator) {
        if (operator.equals("=")) {
            filter.setSimpleOperator(SimpleOperators.EQUALS);
        } else if (operator.equals("!=")) {
            filter.setSimpleOperator(SimpleOperators.NOT_EQUALS);
        } else if (operator.equals("<")) {
            filter.setSimpleOperator(SimpleOperators.LESS_THAN);
        } else if (operator.equals("<=")) {
            filter.setSimpleOperator(SimpleOperators.LESS_THAN_OR_EQUAL);
        } else if (operator.equals(">")) {
            filter.setSimpleOperator(SimpleOperators.GREATER_THAN);
        } else if (operator.equals(">=")) {
            filter.setSimpleOperator(SimpleOperators.GREATER_THAN_OR_EQUAL);
        } else if (operator.equals("is null")) {
            filter.setSimpleOperator(SimpleOperators.IS_NULL);
        } else if (operator.equals("is not null")) {
            filter.setSimpleOperator(SimpleOperators.IS_NOT_NULL);
        } else if (operator.equals("between")) {
            filter.setSimpleOperator(SimpleOperators.BETWEEN);
        } else if (operator.equals("in")) {
            filter.setSimpleOperator(SimpleOperators.IN);
        } else if (operator.equals("like")) {
            filter.setSimpleOperator(SimpleOperators.LIKE);
        }
    }

    public String getValue() {
        assert filter.getValue().size() == 1;
        return filter.getValue().get(0);
    }

    public List<String> getValues() {
        return filter.getValue();
    }

    public void addValue(String value) {
        filter.getValue().add(value);
    }

    public FilterPart getSoapFilter() {
        return filter;
    }

    public void setSoapFilter(FilterPart filter) {
        this.filter = (SimpleFilterPart) filter;
    }

    public static ETFilter parse(String filter)
        throws ETSdkException
    {
        return parse(filter, null);
    }

    public static ETFilter parse(String filter, Class<? extends ETSoapObject> type)
        throws ETSdkException
    {
        ETFilterParser parser = new ETFilterParser(new ByteArrayInputStream(filter.getBytes()));
        ETFilter parsedFilter = null;
        try {
            parsedFilter = parser.parse(type);
        } catch (ParseException ex) {
            throw new ETSdkException("could not parse filter: " + filter, ex);
        }
        return parsedFilter;
    }

    public AudienceBuilderFilter toAudienceBuilderFilter() {
        AudienceBuilderFilter filter = new AudienceBuilderFilter();
        AudienceBuilderFilter.Condition condition = filter.new Condition();
        condition.setId(getProperty());
        // XXX others?
        String operator = getOperator();
        if (operator.equals("=")) {
            condition.setOperator("Equals");
        } else {
            condition.setOperator(operator);
        }
        condition.setConditionValue(getValue());
        filter.addCondition(condition);

        return filter;
    }

    public class AudienceBuilderFilter {
        @Expose
        @SerializedName("UseEnterprise")
        private Boolean useEnterprise = false;
        @Expose
        @SerializedName("UseAlsoEngine")
        private Boolean useAlsoEngine = true;
        @Expose
        @SerializedName("Source")
        private String source = "AudienceBuilder";
        @Expose
        @SerializedName("ConditionSet")
        private ConditionSet conditionSet = new ConditionSet();

        public AudienceBuilderFilter() {
            conditionSet.setOperator("INC"); // XXX?
            conditionSet.setConditionSetName("");
        }

        public ConditionSet getConditionSet() {
            return conditionSet;
        }

        public void addCondition(Condition condition) {
            conditionSet.addCondition(condition);
        }

        public class Condition {
            @Expose
            @SerializedName("ID")
            private String id = null;
            @Expose
            @SerializedName("Operator")
            private String operator = null;
            @Expose
            @SerializedName("ConditionValue")
            private String conditionValue = null;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getConditionValue() {
                return conditionValue;
            }

            public void setConditionValue(String conditionValue) {
                this.conditionValue = conditionValue;
            }
        }

        public class ConditionSet {
            @Expose
            @SerializedName("Operator")
            private String operator = null;
            @Expose
            @SerializedName("ConditionSetName")
            private String conditionSetName = null;
            @Expose
            @SerializedName("Condition")
            private List<Condition> conditions = new ArrayList<Condition>();

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getConditionSetName() {
                return conditionSetName;
            }

            public void setConditionSetName(String conditionSetName) {
                this.conditionSetName = conditionSetName;
            }

            public List<Condition> getConditions() {
                return conditions;
            }

            public void addCondition(Condition condition) {
                conditions.add(condition);
            }
        }
    }
}
