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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.PrettyPrint;

public class ETFilter extends ETObject {
    @PrettyPrint
    private ETExpression expression = null;
    @PrettyPrint
    private List<String> orderBy = new ArrayList<String>();
    @PrettyPrint
    private List<String> properties = new ArrayList<String>();
    @PrettyPrint
    private Boolean orderByAsc = true;

    public ETExpression getExpression() {
        return expression;
    }

    public void setExpression(ETExpression expression) {
        this.expression = expression;
    }

    public List<String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void addProperty(String property) {
        this.properties.add(property);
    }

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
}
