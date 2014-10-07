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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.exacttarget.fuelsdk.annotations.ExternalName;

public abstract class ETObject {
    private Boolean toStringMultiLine = false;
    private Integer toStringMultiLineIndentAmount = 4;
    // default to true if toStringMultiLine is true
    private Boolean toStringSpaceAroundEquals = toStringMultiLine;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getClass().getName());
        stringBuilder.append("[");
        if (toStringMultiLine) {
            stringBuilder.append(System.getProperty("line.separator"));
        }

        boolean first = true;
        for (Field field : getAllFields()) {
            ExternalName externalNameAnnotation =
                    field.getAnnotation(ExternalName.class);
            if (externalNameAnnotation == null) {
                continue;
            }

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
            try {
                // briefly change accessibility so we can get value
                boolean isAccessible = field.isAccessible();
                if (!isAccessible) {
                    field.setAccessible(true);
                }
                v = field.get(this).toString();
                field.setAccessible(isAccessible);
            } catch (IllegalAccessException ex) {
                throw new AssertionError("should never ever get here");
            }
            if (toStringSpaceAroundEquals) {
                stringBuilder.append(externalNameAnnotation.value() + " = " + v);
            } else {
                stringBuilder.append(externalNameAnnotation.value() + "=" + v);
            }
            if (toStringMultiLine) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    protected List<Field> getAllFields() {
        List<Field> fields = new ArrayList<Field>();

        // account for fields of superclasses too

        List<Class<?>> types = new ArrayList<Class<?>>();
        for (Class<?> t = getClass(); t != null; t = t.getSuperclass()) {
            types.add(t);
        }

        // make sure superclass fields are first, to
        // enhance readability of the SOAP envelopes

        ListIterator<Class<?>> li = types.listIterator(types.size());
        while (li.hasPrevious()) {
            Class<?> t = li.previous();
            for (Field field : t.getDeclaredFields()) {
                fields.add(field);
            }
        }

        return fields;
    }
}
