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

import com.exacttarget.fuelsdk.annotations.PrettyPrint;

/**
 * An <code>ETResult</code> object represents result from the SOAP/REST response 
 * in the Salesforce Marketing Cloud.
 */
public class ETResult<T extends ETApiObject> extends ETObject {
    public enum Status {
        OK, ERROR
    }

    @PrettyPrint
    private Status status = null;
    @PrettyPrint
    private String requestId = null;
    @PrettyPrint
    private String responseCode = null;
    @PrettyPrint
    private String responseMessage = null;
    @PrettyPrint
    private Integer errorCode = null;
    @PrettyPrint
    private String errorMessage = null;
    @PrettyPrint
    private T object = null;

    /** 
    * @return The Status of the ETResult object.
    */    
    public Status getStatus() {
        return status;
    }

    /** 
    * @param    status   The Status of the ETResult object.
    */    
    public void setStatus(Status status) {
        this.status = status;
    }

    /** 
    * @return The Request Identifier of the ETResult object.
    */    
    public String getRequestId() {
        return requestId;
    }

    /** 
    * @param    requestId    The Request Identifier of the ETResult object.
    */    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /** 
    * @return The response code of the ETResult object.
    */    
    public String getResponseCode() {
        return responseCode;
    }

    /** 
    * @param responseCode   The response code of the ETResult object.
    */    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /** 
    * @return The response message of the ETResult object.
    */    
    public String getResponseMessage() {
        return responseMessage;
    }

    /** 
    * @param  responseMessage   The response message of the ETResult object.
    */    
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /** 
    * @return The error code of the ETResult object.
    */    
    public Integer getErrorCode() {
        return errorCode;
    }

    /** 
    * @param  errorCode   The error code of the ETResult object.
    */    
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /** 
    * @return The error message of the ETResult object.
    */    
    public String getErrorMessage() {
        return errorMessage;
    }

    /** 
    * @param  errorMessage   The error message of the ETResult object.
    */    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /** 
    * @return The Object of generic type.
    */    
    public T getObject() {
        return object;
    }

    /** 
    * @param object The Object of generic type.
    */    
    public void setObject(T object) {
        this.object = object;
    }

    /** 
    * @return The Object Identifier of the ETResult object.
    */    
    public String getObjectId() {
        if (object != null) {
            return object.getId();
        }
        return null;
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

    /**
     * @deprecated
     * Use <code>getObjectId</code>.
     */
    @Deprecated
    public String getId() {
        return getObjectId();
    }
}
