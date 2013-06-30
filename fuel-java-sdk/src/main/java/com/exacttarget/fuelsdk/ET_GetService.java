//
// ET_GetService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

public interface ET_GetService<T extends ET_Object> {
    public ET_ServiceResponse<T> get();
}
