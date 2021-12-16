//
// This file is part of the Fuel Java SDK.
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
import org.apache.log4j.Logger;
import com.exacttarget.fuelsdk.annotations.PrettyPrint;

/**
 * An <code>ETResponse</code> object represents response from SOAP/REST call
 * in the Salesforce Marketing Cloud.
 */
public class ETResponse<T extends ETApiObject> extends ETObject {
    private static Logger logger = Logger.getLogger(ETResponse.class);

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

    /** 
    * @return The Status of the ETResponse object.
    */    
    public ETResult.Status getStatus() {
        if (batchResult != null) {
            return batchResult.getStatus();
        }
        return null;
    }

    /** 
    * @param    status   The Status of the ETResponse object.
    */    
    public void setStatus(ETResult.Status status) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setStatus(status);
    }

    /** 
    * @return The Request Identifier of the ETResponse object.
    */    
    public String getRequestId() {
        if (batchResult != null) {
            return batchResult.getRequestId();
        }
        return null;
    }

    /** 
    * @param    requestId    The Request Identifier of the ETResponse object.
    */    
    public void setRequestId(String requestId) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setRequestId(requestId);
    }

    /** 
    * @return The response code of the ETResponse object.
    */    
    public String getResponseCode() {
        if (batchResult != null) {
            return batchResult.getResponseCode();
        }
        return null;
    }

    /** 
    * @param responseCode   The response code of the ETResponse object.
    */    
    public void setResponseCode(String responseCode) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setResponseCode(responseCode);
    }

    /** 
    * @return The response message of the ETResponse object.
    */    
    public String getResponseMessage() {
        if (batchResult != null) {
            return batchResult.getResponseMessage();
        }
        return null;
    }

    /** 
    * @param  responseMessage   The response message of the ETResponse object.
    */    
    public void setResponseMessage(String responseMessage) {
        if (batchResult == null) {
            batchResult = new ETResult<T>();
        }
        batchResult.setResponseMessage(responseMessage);
    }

    /** 
    * @return The Object from the ETResult.
    */    
    public T getObject() {
        return getResult().getObject();
    }

    /** 
    * @return The List of individual Results Objects.
    */    
    public List<T> getObjects() {
        List<T> objects = new ArrayList<T>();
        for (ETResult<T> result : individualResults) {
            objects.add(result.getObject());
        }
        return objects;
    }

    /** 
    * @return The ETResult Object associated with individual result of the ETResponse object.
    */    
    public ETResult<T> getResult() {
        if (individualResults.size() == 0) {
            return null;
        }
        if (individualResults.size() > 1) {
            logger.warn("getResult() called on response with results > 1");
        }
        return individualResults.get(0);
    }

    /** 
    * @return The List of ETResult Objects.
    */    
    public List<ETResult<T>> getResults() {
        return individualResults;
    }

    /** 
    * @param  result The ETResult object to add.
    */    
    public void addResult(ETResult<T> result) {
        individualResults.add(result);
    }

    /** 
    * @return true if there are more results, false otherwise.
    */    
    public Boolean hasMoreResults() {
        return moreResults;
    }

    /** 
    * @param moreResults     true if there are more results, false otherwise.
    */    
    public void setMoreResults(Boolean moreResults) {
        this.moreResults = moreResults;
    }

    /** 
    * @return The page number of the ETResponse object.
    */    
    public Integer getPage() {
        return page;
    }

    /** 
    * @param  page The page number of the ETResponse object.
    */    
    public void setPage(Integer page) {
        this.page = page;
    }

    /** 
    * @return The page size of the ETResponse object.
    */    
    public Integer getPageSize() {
        return pageSize;
    }

    /** 
    * @param  pageSize The page size of the ETResponse object.
    */    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /** 
    * @return The total count of the ETResponse object.
    */    
    public Integer getTotalCount() {
        return totalCount;
    }

    /** 
    * @param  totalCount The total count of the ETResponse object.
    */    
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
