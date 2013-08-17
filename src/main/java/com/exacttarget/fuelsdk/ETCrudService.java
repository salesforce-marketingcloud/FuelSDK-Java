//
// ETCrudService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.model.ETObject;

public interface ETCrudService
    extends ETGetService
{
    public <T extends ETObject> ETServiceResponse<T> post(ETClient client, T object)
    	throws ETSdkException;
    public <T extends ETObject> ETServiceResponse<T> patch(ETClient client, T object)
    	throws ETSdkException;
    public <T extends ETObject> ETServiceResponse<T> delete(ETClient client, T object)
    	throws ETSdkException;
}
