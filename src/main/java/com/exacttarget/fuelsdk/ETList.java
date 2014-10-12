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

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.List;

@SoapObject(internalType = List.class)
public class ETList extends ETSoapObject {
    @ExternalName("name")
    @InternalName("listName")
    private String name = null;
    @ExternalName("description")
    @InternalName("description")
    private String description = null;
    @ExternalName("folderId")
    @InternalName("category")
    private Integer folderId = null;
    @ExternalName("classification")
    @InternalName("listClassification")
    private ETListClassification classification = null;
    @ExternalName("type")
    @InternalName("type")
    private ETListType type = null;

    public ETList() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    /**
     * @deprecated
     * Use <code>getFolderId()</code>.
     */
    public Integer getCategoryId() {
        return getFolderId();
    }

    /**
     * @deprecated
     * Use <code>setFolderId()</code>.
     */
    @Deprecated
    public void setCategoryId(Integer categoryId) {
        setFolderId(categoryId);
    }

    public ETListClassification getClassification() {
        return classification;
    }

    public void setClassification(ETListClassification classification) {
        this.classification = classification;
    }

    /**
     * @deprecated
     * Use <code>getClassification()</code>.
     */
    @Deprecated
    public ETListClassification getListClassification() {
        return getClassification();
    }

    /**
     * @deprecated
     * Use <code>setClassification()</code>.
     */
    @Deprecated
    public void setListClassification(ETListClassification listClassification) {
        setClassification(listClassification);
    }

    public ETListType getType() {
        return type;
    }

    public void setType(ETListType type) {
        this.type = type;
    }

    /**
     * @deprecated
     * Use <code>getType()</code>.
     */
    @Deprecated
    public ETListType getListType() {
        return getType();
    }

    /**
     * @deprecated
     * Use <code>setType()</code>.
     */
    @Deprecated
    public void setListType(ETListType listType) {
        setType(listType);
    }
}
