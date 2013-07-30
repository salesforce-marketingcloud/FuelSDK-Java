//
// ET_List.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.internal.List;

public class ET_List implements ET_Object {
    public static final String OBJECT_TYPE = "List";
    public static final String[] PROPERTIES = {
        "ListName"
    };

    private String name = null;

    public ET_List(List list) {
        name = list.getListName();
    }

    public String getName() {
        return name;
    }
}
