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

public interface ETCrudService
    extends ETGetService
{
    public <T extends ETObject> ETServiceResponse<T> post(ETClient client, T object);
    public <T extends ETObject> ETServiceResponse<T> patch(ETClient client, T object);
    public <T extends ETObject> ETServiceResponse<T> delete(ETClient client, T object);
}
