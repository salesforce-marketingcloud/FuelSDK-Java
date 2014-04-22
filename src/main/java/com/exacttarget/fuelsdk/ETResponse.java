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
import java.util.Iterator;
import java.util.List;

public class ETResponse<T> implements Iterable<ETResponse<T>.Result> {
    private String requestId = null;
    private StatusCode statusCode = null;
    private String statusMessage = null;
    private Integer errorCode = null;
    private List<Result> results = new ArrayList<Result>();
    private boolean moreResults = false;

    public enum StatusCode {
        OK, ERROR, UNKNOWN;
    }

    public class Result {
        private StatusCode statusCode = null;
        private String statusMessage = null;
        private Integer errorCode = null;
        private T object = null;
        private Integer id = null;

        public StatusCode getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            if (statusCode == null) {
                return;
            }
            if (statusCode.equals("OK")) {
                this.statusCode = ETResponse.StatusCode.OK;
            } else if (statusCode.equals("Error")) {
                this.statusCode = ETResponse.StatusCode.ERROR;
            } else {
                this.statusCode = ETResponse.StatusCode.UNKNOWN;
            }
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        if (statusMessage.equals("OK")) {
            statusCode = ETResponse.StatusCode.OK;
        } else if (statusMessage.equals("Error")) {
            statusCode = ETResponse.StatusCode.ERROR;
        } else {
            statusCode = ETResponse.StatusCode.UNKNOWN;
        }
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public boolean hasMoreResults() {
        return moreResults;
    }

    public void setMoreResults(boolean moreResults) {
        this.moreResults = moreResults;
    }

    public List<T> getObjects() {
        List<T> objects = new ArrayList<T>();
        for (Result result : results) {
            objects.add(result.getObject());
        }
        return objects;
    }

    @Override
    public Iterator<ETResponse<T>.Result> iterator() {
        // XXX is it OK that callers can change the list returned?
        return results.iterator();
    }

    // XXX should be protected
    public void addResult(Result result) {
        results.add(result);
    }

    /**
     * @deprecated
     * Use <code>getStatusCode</code>.
     */
    @Deprecated
    public boolean getStatus() {
        if (statusCode == StatusCode.OK) {
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
        return statusMessage;
    }

    /**
     * @deprecated
     * Use <code>getObjects</code>.
     */
    @Deprecated
    public List<T> getResults() {
        return getObjects();
    }
}
