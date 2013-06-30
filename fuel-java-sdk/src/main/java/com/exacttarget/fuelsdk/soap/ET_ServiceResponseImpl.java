//
// ET_ServiceResponseImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.ET_Object;
import com.exacttarget.fuelsdk.ET_ServiceResponse;

public class ET_ServiceResponseImpl<T extends ET_Object>
    implements ET_ServiceResponse<T>
{
    private List<T> results = new ArrayList<T>();

    public List<T> getResults() {
        return results;
    }
}
