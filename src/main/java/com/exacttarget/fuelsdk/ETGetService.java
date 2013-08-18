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

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETObject;

public interface ETGetService
    extends ETService
{
    public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException;
    
    public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type, ETFilter filter)
            throws ETSdkException;
}
