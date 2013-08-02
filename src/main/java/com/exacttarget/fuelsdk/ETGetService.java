//
// ETGetService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

public interface ETGetService<T extends ETObject>
    extends ETService<T>
{
    public ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException;
}
