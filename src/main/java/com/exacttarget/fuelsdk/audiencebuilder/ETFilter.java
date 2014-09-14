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

import com.google.gson.annotations.SerializedName;

public class ETFilter {
    @SerializedName("filterDefinitionID")
    private String id = null;
    private String name = null;
    private String description = null;
    @SerializedName("categoryID")
    private String folderId = null;
    @SerializedName("clientID")
    private String clientId = null;
    @SerializedName("derivedFromObjectID")
    private String derivedFromObjectId = null;
    @SerializedName("ownerID")
    private String ownerId = null;
    @SerializedName("statusID")
    private String statusId = null;
    @SerializedName("filterDefinitionJson")
    private ETFilterDefinition filterDefinition = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDerivedFromObjectId() {
        return derivedFromObjectId;
    }

    public void setDerivedFromObjectId(String derivedFromObjectId) {
        this.derivedFromObjectId = derivedFromObjectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public ETFilterDefinition getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(ETFilterDefinition filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
