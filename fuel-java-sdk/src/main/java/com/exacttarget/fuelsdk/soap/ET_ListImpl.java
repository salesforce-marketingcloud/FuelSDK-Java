//
// ET_ListImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ET_List;
import com.exacttarget.fuelsdk.internal.List;

public class ET_ListImpl implements ET_List {
    public static final String OBJECT_TYPE = "List";
    public static final String[] PROPERTIES = {
        "ListName"
    };

    private String name = null;

    public ET_ListImpl() {
        // XXX
    }

    public ET_ListImpl(List list) {
        name = list.getListName();
    }

    public String getName() {
        return name;
    }

    public String getObjectType() {
        return OBJECT_TYPE;
    }

    public String[] getProperties() {
        return PROPERTIES;
    }
}
