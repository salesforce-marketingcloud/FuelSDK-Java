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

public interface ETGetService
    extends ETService
{
    public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException;
}
