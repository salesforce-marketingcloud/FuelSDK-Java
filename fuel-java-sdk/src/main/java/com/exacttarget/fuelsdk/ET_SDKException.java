//
// ET_SDKException.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk;

@SuppressWarnings("serial")
public class ET_SDKException extends Exception {
    public ET_SDKException() {}

    public ET_SDKException(Exception ex) {
        super(ex);
    }

    public ET_SDKException(String message) {
        super(message);
    }

    public ET_SDKException(String message, Throwable cause) {
        super(message, cause);
    }
}
