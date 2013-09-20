//
// ETServiceResponse.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import java.util.List;

import com.exacttarget.fuelsdk.model.ETObject;

public interface ETServiceResponse<T extends ETObject> {
    public String getRequestId();
    public void setRequestId(String requestId);
    public List<T> getResults();
    public boolean hasMoreResults();
    public void setMoreResults(boolean moreResults);
    public boolean getStatus();
    public void setStatus(boolean status);
}
