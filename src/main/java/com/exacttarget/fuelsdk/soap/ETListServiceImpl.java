//
// ETListServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETList;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;

public class ETListServiceImpl extends ETCrudServiceImpl
    implements ETListService
{
    public ETServiceResponse<ETList> get(ETClient client)
        throws ETSdkException
    {
        return super.get(client, ETList.class);
    }
}
