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

package com.exacttarget.fuelsdk.audiencebuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ETAudienceFilter {
    @Expose
    @SerializedName("filterDefinitionID")
    private String id = null;
    @Expose
    private String name = null;
    @Expose
    @SerializedName("categoryID")
    private Integer folderId = null;
    @Expose
    @SerializedName("derivedFromObjectID")
    private String derivedFromObjectId = null;
    @Expose
    @SerializedName("ownerID")
    private Integer ownerId = null;
    @Expose
    @SerializedName("statusID")
    private Integer statusId = null;
    @Expose
    @SerializedName("filterDefinitionXML")
    private String filterDefinitionXml = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getDerivedFromObjectId() {
        return derivedFromObjectId;
    }

    public void setDerivedFromObjectId(String derivedFromObjectId) {
        this.derivedFromObjectId = derivedFromObjectId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getFilterDefinitionXml() {
        return filterDefinitionXml;
    }

    public void setFilterDefinitionXml(String filterDefinitionXml) {
        this.filterDefinitionXml = filterDefinitionXml;
    }
}
