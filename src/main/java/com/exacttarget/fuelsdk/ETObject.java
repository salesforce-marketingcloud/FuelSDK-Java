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

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class ETObject {
    private StringBuilder stringBuilder = new StringBuilder();
    private boolean toStringMultiLine = false;
    private int toStringMultiLineIndentAmount = 4;
    // default to true if toStringMultiLine is true
    private boolean toStringSpaceAroundEquals = toStringMultiLine;

    private boolean first = true;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    protected String getToString() {
        return stringBuilder.toString();
    }

    protected void toStringAppend(String string) {
        toStringAppend(string, true);
    }

    protected void toStringAppend(String string, boolean newline) {
        if (string != null) {
            stringBuilder.append(string);
            if (toStringMultiLine && newline) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
    }

    protected void toStringAppend(String property, Object value) {
        if (value != null) {
            if (toStringMultiLine) {
                for (int i = 0; i < toStringMultiLineIndentAmount; i++) {
                    stringBuilder.append(" ");
                }
            } else {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
            }
            String v = null;
            if (value.getClass().equals(String.class)) {
                v = "\"" + value + "\"";
            }
            if (toStringSpaceAroundEquals) {
                stringBuilder.append(property + " = " + v);
            } else {
                stringBuilder.append(property + "=" + v);
            }
            if (toStringMultiLine) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
    }

    protected void toStringReset() {
        stringBuilder.setLength(0); first = true;
    }
}
