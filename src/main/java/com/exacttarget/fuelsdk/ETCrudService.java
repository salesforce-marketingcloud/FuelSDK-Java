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

public interface ETCrudService<T extends ETObject>
    extends ETGetService<T>
{
    public ETServiceResponse<T> post(ETClient client, T object);
    public ETServiceResponse<T> patch(ETClient client, T object);
    public ETServiceResponse<T> delete(ETClient client, T object);
}
