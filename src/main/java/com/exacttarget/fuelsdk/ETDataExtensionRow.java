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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;

/**
 * An <code>ETDataExtensionRow</code> object represents a
 * data extension row in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = DataExtensionObject.class)
public class ETDataExtensionRow extends ETSoapObject {
    @ExternalName("dataExtensionKey")
    @InternalName("customerKey")
    private String dataExtensionKey = null;
    @ExternalName("columns")
    @InternalName("properties")
    private Map<String, String> columns = new HashMap<String, String>();

    public ETDataExtensionRow() {}

    @Override
    public String getId() {
        // no ID on this object type
        return null;
    }

    @Override
    public void setId(String id) {
        // no ID on this object type
    }

    public String getDataExtensionKey() {
        return dataExtensionKey;
    }

    public void setDataExtensionKey(String dataExtensionKey) {
        this.dataExtensionKey = dataExtensionKey;
    }

    public String getColumn(String name) {
        return columns.get(name.toLowerCase());
    }

    public void setColumn(String name, String value) {
        setColumn(name, value, true);
    }

    public void setColumn(String name, String value, boolean setModified) {
        if (setModified) {
            setModified(name, true);
        }
        columns.put(name.toLowerCase(), value);
    }

    public Set<String> getColumnNames() {
        return columns.keySet();
    }

    /**
     * @deprecated
     * Use <code>getColumn</code> and <code>setColumn</code>.
     */
    @Deprecated
    public Map<String, String> getColumns() {
        return columns;
    }

    /**
     * @deprecated
     * Use <code>getColumn</code> and <code>setColumn</code>.
     */
    @Deprecated
    public void setColumns(Map<String, String> columns) {
        // note: this needs to be here because reflection needs it
        for (Map.Entry<String, String> column : columns.entrySet()) {
            setColumn(column.getKey(), column.getValue());
        }
    }
}
