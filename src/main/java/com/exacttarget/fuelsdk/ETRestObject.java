//
// EtRestObject.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.InternalRestField;

public abstract class ETRestObject extends ETObject {
    @InternalRestField(jsonKey = "id")
    protected String id;
    @InternalRestField(jsonKey = "createdDate")
    protected String createdDate;
    @InternalRestField(jsonKey = "modifiedDate")
    protected String modifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
