//
// ETCrudServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETServiceResponse;

public class ETCrudServiceImpl<T extends ETObject>
    extends ETGetServiceImpl<T> implements ETCrudService<T>
{
    public ETServiceResponse<T> post(ETClient client, T object) {
        return null; // XXX
    }

    public ETServiceResponse<T> patch(ETClient client, T object) {
        return null; // XXX
    }

    public ETServiceResponse<T> delete(ETClient client, T object) {
        return null; // XXX
    }
}
