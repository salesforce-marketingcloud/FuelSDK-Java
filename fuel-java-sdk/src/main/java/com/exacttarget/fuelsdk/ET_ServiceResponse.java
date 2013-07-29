//
// ET_ServiceResponse.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import java.util.List;

public interface ET_ServiceResponse<T extends ET_Object> {
    public String getRequestId();
    public void setRequestId(String requestId);
    public List<T> getResults();
}
