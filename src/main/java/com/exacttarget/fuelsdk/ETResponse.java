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

import com.exacttarget.fuelsdk.annotations.PrettyPrint;

public class ETResponse<T extends ETApiObject> extends ETObject {
    @PrettyPrint
    private ETResult<T> batchResult = null;
    @PrettyPrint
    private List<ETResult<T>> individualResults = new ArrayList<ETResult<T>>();
    @PrettyPrint
    private Boolean moreResults = false;
    @PrettyPrint
    private Integer page = null;
    @PrettyPrint
    private Integer pageSize = null;
    @PrettyPrint
    private Integer totalCount = null;

    public ETResult.Status getStatus() {
        if (batchResult != null) {
            return batchResult.getStatus();
        }
        return null;
    }

    public void setStatus(ETResult.Status status) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setStatus(status);
    }

    public String getRequestId() {
        if (batchResult != null) {
            return batchResult.getRequestId();
        }
        return null;
    }

    public void setRequestId(String requestId) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setRequestId(requestId);
    }

    public String getResponseCode() {
        if (batchResult != null) {
            return batchResult.getResponseCode();
        }
        return null;
    }

    public void setResponseCode(String responseCode) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setResponseCode(responseCode);
    }

    public String getResponseMessage() {
        if (batchResult != null) {
            return batchResult.getResponseMessage();
        }
        return null;
    }

    public void setResponseMessage(String responseMessage) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setResponseMessage(responseMessage);
    }

    public T getObject() {
        return getResult().getObject();
    }

    public List<T> getObjects() {
        List<T> objects = new ArrayList<T>();
        for (ETResult<T> result : individualResults) {
            objects.add(result.getObject());
        }
        return objects;
    }

    public ETResult<T> getResult() {
        assert individualResults != null && individualResults.size() == 1;
        return individualResults.get(0);
    }

    public List<ETResult<T>> getResults() {
        return individualResults;
    }

    public void addResult(ETResult<T> result) {
        individualResults.add(result);
    }

    public Boolean hasMoreResults() {
        return moreResults;
    }

    public void setMoreResults(Boolean moreResults) {
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
     * Use <code>getResponseCode</code>.
     */
    @Deprecated
    public String getStatusCode() {
        return getResponseCode();
    }

    /**
     * @deprecated
     * Use <code>setResponseCode</code>.
     */
    @Deprecated
    public void setStatusCode(String statusCode) {
        setResponseCode(statusCode);
    }

    /**
     * @deprecated
     * Use <code>getResponseMessage</code>.
     */
    @Deprecated
    public String getStatusMessage() {
        return getResponseMessage();
    }

    /**
     * @deprecated
     * Use <code>setResponseMessage</code>.
     */
    @Deprecated
    public void setStatusMessage(String statusMessage) {
        setResponseMessage(statusMessage);
    }
}
