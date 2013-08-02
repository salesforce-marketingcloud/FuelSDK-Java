//
// ETList.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.List;

@InternalType(type = List.class, fields = {"ListName", "Description"})
public class ETList implements ETObject
{
    protected String name;
    protected String description;

    public ETList(List list) {
        name = list.getListName();
        description = list.getDescription();
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
