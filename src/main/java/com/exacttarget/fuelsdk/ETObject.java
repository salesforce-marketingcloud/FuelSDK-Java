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
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;

public abstract class ETObject {
    private Boolean toStringMultiLine = false;
    private Integer toStringMultiLineIndentAmount = 4;
    // default to true if toStringMultiLine is true
    private Boolean toStringSpaceAroundEquals = toStringMultiLine;

    //
    // All objects must have the following properties: id, key, name,
    // description, createdDate, and modifiedDate:
    //

    public abstract String getId();
    public abstract void setId(String id);
    public abstract String getKey();
    public abstract void setKey(String key);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getDescription();
    public abstract void setDescription(String description);
    public abstract Date getCreatedDate();
    public abstract void setCreatedDate(Date createdDate);
    public abstract Date getModifiedDate();
    public abstract void setModifiedDate(Date createdDate);

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
                if (field.get(this) != null) {
                    v = field.get(this).toString();
                }
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

    protected static String getInternalProperty(Class<? extends ETObject> type,
                                                String name)
        throws ETSdkException
    {
        String internalProperty = null;

        Class<? extends ETObject> externalType = type; // for code readability

        Field externalField = getField(externalType, name);

        InternalName internalNameAnnotation =
                externalField.getAnnotation(InternalName.class);

        if (internalNameAnnotation != null) {
            internalProperty = internalNameAnnotation.value();
        } else {
            // internal name is the same as external name
            internalProperty = externalField.getName();
        }

        return internalProperty;
    }

    protected Field getField(String property) {
        for (Field field : getAllFields()) {
            ExternalName externalName =
                    field.getAnnotation(ExternalName.class);
            if (externalName != null && externalName.value().equals(property)) {
                return field;
            }
        }
        return null;
    }

    protected static Field getField(Class<?> type, String name)
        throws ETSdkException
    {
        Field field = null;

        for (Class<?> t = type; t != null; t = t.getSuperclass()) {
            try {
                field = t.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException ex) {
                continue;
            }
        }

        if (field == null) {
            throw new ETSdkException("field \""
                    + name
                    + "\" does not exist in class "
                    + type.getName());
        }

        return field;
    }

    protected List<Field> getAllFields() {
        return getAllFields(getClass());
    }

    protected static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();

        // account for fields of superclasses too

        List<Class<?>> types = new ArrayList<Class<?>>();
        for (Class<?> t = type; t != null; t = t.getSuperclass()) {
            types.add(t);
        }

        // make sure superclass fields are first for readability

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
