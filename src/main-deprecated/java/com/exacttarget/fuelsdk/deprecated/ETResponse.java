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

package com.exacttarget.fuelsdk.deprecated;

import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.PrettyPrint;

/**
 * @deprecated
 * Use {@link com.exacttarget.fuelsdk.ETResponse}.
 */
@Deprecated
public class ETResponse<T> extends ETObject {
    @PrettyPrint
    private String requestId = null;
    @PrettyPrint
    private ETResult overallResult = new ETResult();
    @PrettyPrint
    private List<T> results = new ArrayList<T>();
    @PrettyPrint
    private boolean moreResults = false;
    @PrettyPrint
    private Integer page = null;
    @PrettyPrint
    private Integer pageSize = null;
    @PrettyPrint
    private Integer totalCount = null;

    public String getRequestId() {
        return overallResult.getRequestId();
    }

    public void setRequestId(String requestId) {
        overallResult.setRequestId(requestId);
    }

    public String getStatusCode() {
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

    public void addResult(T result) {
        results.add(result);
    }

    public boolean hasMoreResults() {
        return moreResults;
    }

    public void setMoreResults(boolean moreResults) {
        this.moreResults = moreResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @deprecated
     * Use <code>getStatusCode</code>.
     */
    @Deprecated
    public boolean getStatus() {
        if (getStatusCode().equals("OK")) {
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
