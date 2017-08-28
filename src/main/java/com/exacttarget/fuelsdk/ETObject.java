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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.PrettyPrint;

/**
 * An <code>ETObject</code> represents an object in the Java
 * client library.
 */

public abstract class ETObject {
    private ETClient client = null;
    private Boolean toStringMultiLine = true;
    private Integer toStringMultiLineIndentAmount = 4;
    private Boolean toStringSpaceAroundEquals = toStringMultiLine;

    private static int currentIndentLevel = 0;

    /**
     * Gets the client handle associated with this object.
     * @return  The ETClient object
     */
    public ETClient getClient() {
        return client;
    }

    /**
     * Sets the client handle associated with this object.
     * @param client    The ETClient object
     */
    public void setClient(ETClient client) {
        this.client = client;
    }

    /** 
    * @return     The String representing the ETObject.
    */    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getClass().getName());
        stringBuilder.append("[");
        if (toStringMultiLine) {
            stringBuilder.append(System.getProperty("line.separator"));
        }

        currentIndentLevel += toStringMultiLineIndentAmount;

        boolean first = true;
        for (Field field : getAllFields()) {
            PrettyPrint prettyPrintAnnotation =
                    field.getAnnotation(PrettyPrint.class);

            // @ExternalName implies @PrettyPrint
            ExternalName externalNameAnnotation =
                    field.getAnnotation(ExternalName.class);

            if (prettyPrintAnnotation == null && externalNameAnnotation == null) {
                continue;
            }

            String name = null;
            if (externalNameAnnotation != null) {
                name = externalNameAnnotation.value();
            } else {
                name = field.getName();
            }
            String value = null;
            try {
                // briefly change accessibility so we can get value
                boolean isAccessible = field.isAccessible();
                if (!isAccessible) {
                    field.setAccessible(true);
                }
                if (field.get(this) != null) {
                    value = field.get(this).toString();
                }
                field.setAccessible(isAccessible);
            } catch (IllegalAccessException ex) {
                throw new AssertionError("should never ever get here");
            }
            if (value == null) {
                continue;
            }
            if (toStringMultiLine) {
                for (int i = 0; i < currentIndentLevel; i++) {
                    stringBuilder.append(" ");
                }
            } else {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
            }
            if (toStringSpaceAroundEquals) {
                stringBuilder.append(name + " = " + value);
            } else {
                stringBuilder.append(name + "=" + value);
            }
            if (toStringMultiLine) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }

        currentIndentLevel -= toStringMultiLineIndentAmount;

        if (toStringMultiLine) {
            for (int i = 0; i < currentIndentLevel; i++) {
                stringBuilder.append(" ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /** 
    * @param      property  The property name.
    * @return     The Field of the ETObject.
    */    
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

    /** 
    * @param type   The class type whose Field will be retrieved.
    * @param name   The name of the Field to be retrieved.
    * @return       The Field of the class type.
    */    
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

    /** 
    * @return     The List of Field.
    */    
    protected List<Field> getAllFields() {
        return getAllFields(getClass());
    }

    /** 
    * @param type   The class type whose Fields will be retrieved.
    * @return       The List of Field of the class type.
    */    
    protected static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();

        // XXX this needs to account for overrides

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
