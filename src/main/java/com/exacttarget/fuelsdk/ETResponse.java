//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.exacttarget.fuelsdk;

import java.util.ArrayList;
import java.util.List;

public class ETResponse<T> {
    private String requestId = null;
    private ETResponseStatus overallResult = new ETResponseStatus();
    private List<T> results = new ArrayList<T>();
    private boolean moreResults = false;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ETResponseStatusCode getStatusCode() {
        return overallResult.getStatusCode();
    }

    public void setStatusCode(String statusCode) {
        overallResult.setStatusCode(statusCode);
    }

    public String getStatusMessage() {
        return overallResult.getStatusMessage();
    }

    public void setStatusMessage(String statusMessage) {
        overallResult.setStatusMessage(statusMessage);
    }

    public Integer getErrorCode() {
        return overallResult.getErrorCode();
    }

    public void setErrorCode(Integer errorCode) {
        overallResult.setErrorCode(errorCode);
    }

    public List<T> getResults() {
        return results;
    }

    // XXX
    //public void addResult(T result) {
    public void addResult(Object result) {
        results.add((T) result);
    }

    public boolean hasMoreResults() {
        return moreResults;
    }

    public void setMoreResults(boolean moreResults) {
        this.moreResults = moreResults;
    }

    /**
     * @deprecated
     * Use <code>getStatusCode</code>.
     */
    @Deprecated
    public boolean getStatus() {
        if (getStatusCode() == ETResponseStatusCode.OK) {
            return true;
        }
        return false;
    }

    /**
     * @deprecated
     * Use <code>getStatusMessage</code>.
     */
    @Deprecated
    public String getMessage() {
        return getStatusMessage();
    }
}
