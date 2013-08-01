//
// ETListService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

public interface ETListService extends ETCrudService<ETList>
{
    public ETServiceResponse<ETList> get(ETClient client)
        throws ETSdkException;
}
