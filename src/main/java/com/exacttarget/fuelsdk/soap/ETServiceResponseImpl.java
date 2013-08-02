//
// ETServiceResponseImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETServiceResponse;

public class ETServiceResponseImpl<T extends ETObject>
    implements ETServiceResponse<T>
{
    private String requestId = null;
    private List<T> results = new ArrayList<T>();

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<T> getResults() {
        return results;
    }
}
