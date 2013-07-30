//
// ETServiceResponse.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

import java.util.List;

public interface ETServiceResponse<T extends ETObject> {
    public String getRequestId();
    public void setRequestId(String requestId);
    public List<T> getResults();
}
