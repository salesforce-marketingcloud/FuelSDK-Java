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

import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.List;

@InternalType(type = List.class)
public class ETList implements ETObject
{
    @InternalField(name="ListName")
    protected String name;

    public ETList(List list) {
        name = list.getListName();
    }

    public String getName() {
        return name;
    }
}
