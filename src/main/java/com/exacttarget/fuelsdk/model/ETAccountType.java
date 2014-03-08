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

package com.exacttarget.fuelsdk.model;

public enum ETAccountType {
    NONE("None"), // default response type for AsyncResponseType XXX
    EXACTTARGET("EXACTTARGET"),               // Core
    CONNECT("CONNECT"),                       // Advanced
    CHANNEL_CONNECT("CHANNEL_CONNECT"),       // Enterprise 1
    ENTERPRISE_2("ENTERPRISE_2"),             // Enterprise 2
    BUSINESS_UNIT("BUSINESS_UNIT"),           // Enterprise 2 business unit
    PRO_CONNECT("PRO_CONNECT"),               // Reseller
    PRO_CONNECT_CLIENT("PRO_CONNECT_CLIENT"), // Reseller client
    LP_MEMBER("LP_MEMBER"),                   // Lock & publish
    DOTO_MEMBER("DOTO_MEMBER");               // On your behalf
    private final String value;

    ETAccountType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ETAccountType fromValue(String value) {
        for (ETAccountType v : ETAccountType.values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
