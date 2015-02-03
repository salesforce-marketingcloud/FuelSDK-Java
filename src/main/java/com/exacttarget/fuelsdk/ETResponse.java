//
// This file is part of the Salesforce Marketing Cloud Java client library.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
// 
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
