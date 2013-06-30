//
// ET_Client.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.soap.ET_ListServiceImpl;

public class ET_Client {
    public ET_Client(String clientId, String clientSecret) {
    }
    
    public ET_ListServiceImpl getListService() {
        return new ET_ListServiceImpl();
    }
}
