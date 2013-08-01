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

import com.exacttarget.fuelsdk.internal.List;

public class ETList implements ETObject {
    public static final String OBJECT_TYPE = "List";
    public static final String[] PROPERTIES = {
        "ListName"
    };

    private String name = null;

    public ETList(List list) {
        name = list.getListName();
    }

    public String getName() {
        return name;
    }
}
