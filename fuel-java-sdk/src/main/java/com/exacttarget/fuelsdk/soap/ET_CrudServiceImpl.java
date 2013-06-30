//
// ET_CrudServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ET_CrudService;
import com.exacttarget.fuelsdk.ET_Object;
import com.exacttarget.fuelsdk.ET_ServiceResponse;

public class ET_CrudServiceImpl<T extends ET_Object>
    extends ET_GetServiceImpl<T> implements ET_CrudService<T>
{
    public ET_ServiceResponse<T> post(T object) {
        return null; // XXX
    }
    
    public ET_ServiceResponse<T> patch(T object) {
        return null; // XXX
    }
    
    public ET_ServiceResponse<T> delete(T object) {
        return null; // XXX
    }
}
