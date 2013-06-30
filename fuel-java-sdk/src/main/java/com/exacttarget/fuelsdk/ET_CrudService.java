//
// ET_CrudService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

public interface ET_CrudService<T extends ET_Object>
    extends ET_GetService<T>
{
    public ET_ServiceResponse<T> post(T object);
    public ET_ServiceResponse<T> patch(T object);
    public ET_ServiceResponse<T> delete(T object);
}
