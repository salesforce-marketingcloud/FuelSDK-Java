//
// ET_Object.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ET_Object;

public interface ET_SOAPObject extends ET_Object {
    public String getObjectType();
    public String[] getProperties();
}
