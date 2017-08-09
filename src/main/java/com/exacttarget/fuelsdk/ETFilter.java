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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.PrettyPrint;

/**
 * An <code>ETFilter</code> object represents a filter that can be used to filter properties
 * in the Salesforce Marketing Cloud.
 */
public class ETFilter extends ETObject {
    @PrettyPrint
    private ETExpression expression = new ETExpression();
    @PrettyPrint
    private List<String> orderBy = new ArrayList<String>();
    @PrettyPrint
    private List<String> properties = new ArrayList<String>();
    @PrettyPrint
    private Boolean orderByAsc = true;

    /** 
    * @return     The ETExpression of the ETFilter object.
    */    
    public ETExpression getExpression() {
        return expression;
    }

    /** 
    * @param    expression    The ETExpression of the ETFilter object.
    */    
    public void setExpression(ETExpression expression) {
        this.expression = expression;
    }

    /** 
    * @return     The list of order by String of the ETFilter object.
    */    
    public List<String> getOrderBy() {
        return orderBy;
    }

    /** 
    * @param    orderBy     The list of order by String to set of the ETFilter object.
    */    
    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    /** 
    * @return     true if order by ascending, false otherwise. by default true.
    */    
    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    /** 
    * @param    orderByAsc     true if order by ascending, false otherwise. by default true.
    */    
    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    /** 
    * @return     The list of properties of the ETFilter object.
    */    
    public List<String> getProperties() {
        return properties;
    }

    /** 
    * @param properties       The list of properties to set for the ETFilter object.
    */
    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    /** 
    * @param property       The property of the ETFilter object.
    */
    public void addProperty(String property) {
        this.properties.add(property);
    }

    /** 
     * parse the String to create ETFilter object.
    * @param s       The String to be parsed to create the ETFilter object.
    */
    public static ETFilter parse(String... s)
        throws ETSdkException
    {
        ETFilter filter = new ETFilter();

        for (String t : s) {
            try {
                filter.setExpression(ETExpression.parse(t));
            } catch (ETSdkException ex) {
                if (ex.getCause() instanceof ParseException) {
                    //
                    // It's not an expression, so it's either
                    // an order by clause or it's a property:
                    //

                    if (t.length() >= 8
                            && t.substring(0, 8).toLowerCase().equals("order by")) {
                        //
                        // Order by clause:
                        //

                        //t = t.toLowerCase();
                        String tokens[] = t.substring(9).split(" ");

                        if (tokens.length > 1
                                && tokens[1].toLowerCase().equals("desc")) {
                            filter.setOrderByAsc(false);
                        }

                        filter.setOrderBy(Arrays.asList(tokens[0].split(",")));
                    } else {
                        //
                        // Property:
                        //

                        filter.addProperty(t);
                    }
                } else {
                    throw ex;
                }
            }
        }

        return filter;
    }

    /**
     * @deprecated
     * Use <code>ETExpression.getProperty</code>.
     */
    @Deprecated
    public String getProperty() {
        return expression.getProperty();
    }

    /**
     * @deprecated
     * Use <code>ETExpression.setProperty</code>.
     */
    @Deprecated
    public void setProperty(String property) {
        expression.setProperty(property);
    }

    /**
     * @deprecated
     * Use <code>ETExpression.getOperator</code>.
     */
    @Deprecated
    public Operator getOperator() {
        ETExpression.Operator operator = expression.getOperator();
        switch (operator) {
          case AND:
            return Operator.AND;
          case BETWEEN:
            return Operator.BETWEEN;
          case EQUALS:
            return Operator.EQUALS;
          case GREATER_THAN:
            return Operator.GREATER_THAN;
          case GREATER_THAN_OR_EQUALS:
            return Operator.GREATER_THAN_OR_EQUALS;
          case IN:
            return Operator.IN;
          case IS_NULL:
            return Operator.IS_NULL;
          case IS_NOT_NULL:
            return Operator.IS_NOT_NULL;
          case LESS_THAN:
            return Operator.LESS_THAN;
          case LESS_THAN_OR_EQUALS:
            return Operator.LESS_THAN_OR_EQUALS;
          case LIKE:
            return Operator.LIKE;
          case NOT:
            return Operator.NOT;
          case NOT_EQUALS:
            return Operator.NOT_EQUALS;
          case OR:
            return Operator.OR;
        }
        return null;
    }

    /**
     * @deprecated
     * Use <code>ETExpression.setOperator</code>.
     */
    @Deprecated
    public void setOperator(Operator operator) {
        switch (operator) {
          case AND:
            expression.setOperator(ETExpression.Operator.AND);
            break;
          case BETWEEN:
            expression.setOperator(ETExpression.Operator.BETWEEN);
            break;
          case EQUALS:
            expression.setOperator(ETExpression.Operator.EQUALS);
            break;
          case GREATER_THAN:
            expression.setOperator(ETExpression.Operator.GREATER_THAN);
            break;
          case GREATER_THAN_OR_EQUALS:
            expression.setOperator(ETExpression.Operator.GREATER_THAN_OR_EQUALS);
            break;
          case IN:
            expression.setOperator(ETExpression.Operator.IN);
            break;
          case IS_NULL:
            expression.setOperator(ETExpression.Operator.IS_NULL);
            break;
          case IS_NOT_NULL:
            expression.setOperator(ETExpression.Operator.IS_NOT_NULL);
            break;
          case LESS_THAN:
            expression.setOperator(ETExpression.Operator.LESS_THAN);
            break;
          case LESS_THAN_OR_EQUALS:
            expression.setOperator(ETExpression.Operator.LESS_THAN_OR_EQUALS);
            break;
          case LIKE:
            expression.setOperator(ETExpression.Operator.LIKE);
            break;
          case NOT:
            expression.setOperator(ETExpression.Operator.NOT);
            break;
          case NOT_EQUALS:
            expression.setOperator(ETExpression.Operator.NOT_EQUALS);
            break;
          case OR:
            expression.setOperator(ETExpression.Operator.OR);
            break;
        }
    }

    /**
     * @deprecated
     * Use <code>ETExpression.getValue</code>.
     */
    @Deprecated
    public String getValue() {
        return expression.getValue();
    }

    /**
     * @deprecated
     * Use <code>ETExpression.getValues</code>.
     */
    @Deprecated
    public List<String> getValues() {
        return expression.getValues();
    }

    /**
     * @deprecated
     * Use <code>ETExpression.addValue</code>.
     */
    @Deprecated
    public void addValue(String value) {
        expression.addValue(value);
    }

    /**
     * @deprecated
     * Use <code>ETExpression.getSubexpressions</code>.
     */
    @Deprecated
    public List<ETFilter> getFilters() {
        // look at the lengths we go to to preserve backward compatibility!
        List<ETFilter> filters = new ArrayList<ETFilter>();
        List<ETExpression> subexpressions = expression.getSubexpressions();
        for (ETExpression subexpression : subexpressions) {
            ETFilter filter = new ETFilter();
            filter.setExpression(subexpression);
            filters.add(filter);
        }
        return filters;
    }

    /**
     * @deprecated
     * Use <code>ETExpression.addSubexpression</code>.
     */
    @Deprecated
    public void addFilter(ETFilter filter) {
        expression.addSubexpression(filter.getExpression());
    }

    //
    // Alas, enums can't be subclassed, so we have to make a copy:
    //

    /**
     * @deprecated
     * Use <code>ETExpression.Operator</code>.
     */
    @Deprecated
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
}
