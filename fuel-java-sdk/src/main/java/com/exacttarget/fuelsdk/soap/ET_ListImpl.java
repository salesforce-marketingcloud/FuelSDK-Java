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

public class ET_ListImpl implements ET_List {
    private com.exacttarget.fuelsdk.internal.List list = null;

    // XXX populate list
    
    public String getName() {
        return list.getListName();
    }
}
