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

import com.exacttarget.fuelsdk.internal.ComplexFilterPart;
import com.exacttarget.fuelsdk.internal.FilterPart;
import com.exacttarget.fuelsdk.internal.LogicalOperators;
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

    public String getOperator() {
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
          case AND:
            return "and";
          case OR:
            return "or";
          case NOT:
            return "not";
          default:
            return null;
        }
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

    public FilterPart toSoapFilter() {
        if (operator == Operator.AND || operator == Operator.OR) {
            ComplexFilterPart complexFilterPart = new ComplexFilterPart();
            complexFilterPart.setLeftOperand(filters.get(0).toSoapFilter());
            if (operator == Operator.AND) {
                complexFilterPart.setLogicalOperator(LogicalOperators.AND);
            } else if (operator == Operator.OR) {
                complexFilterPart.setLogicalOperator(LogicalOperators.OR);
            }
            complexFilterPart.setRightOperand(filters.get(1).toSoapFilter());
            return complexFilterPart;
        } else {
            SimpleFilterPart simpleFilterPart = new SimpleFilterPart();
            simpleFilterPart.setProperty(property);
            switch (operator) {
              case EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.EQUALS);
                break;
              case NOT_EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.NOT_EQUALS);
                break;
              case LESS_THAN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LESS_THAN);
                break;
              case LESS_THAN_OR_EQUAL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LESS_THAN_OR_EQUAL);
                break;
              case GREATER_THAN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.GREATER_THAN);
                break;
              case GREATER_THAN_OR_EQUAL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.GREATER_THAN_OR_EQUAL);
                break;
              case IS_NULL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IS_NULL);
                break;
              case IS_NOT_NULL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IS_NOT_NULL);
                break;
              case BETWEEN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.BETWEEN);
                break;
              case IN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IN);
                break;
              case LIKE:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LIKE);
                break;
              default:
                break;
            }
            for (String value : values) {
                simpleFilterPart.getValue().add(value);
            }
            return simpleFilterPart;
        }
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
