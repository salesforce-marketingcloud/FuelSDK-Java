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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * An <code>ETApiObject</code> represents an object available via
 * the Salesforce Marketing Cloud API. All API objects
 * are guaranteed to have the following properties, which
 * is enforced via abstract getter and setter methods:
 *
 * - id
 *
 * Typically, a specific API object will extend either
 * <code>ETRestObject</code> or <code>ETSoapObject</code>
 * (depending on whether it is a REST or SOAP API,
 * respectively), adding object-specific property
 * getters and setters. In a few cases (e.g., ETDataExtension),
 * API objects are hybrids of both REST and SOAP.
 */

public abstract class ETApiObject extends ETObject {
    private static Logger logger = Logger.getLogger(ETApiObject.class);

    private Map<String, Boolean> isModified = new HashMap<String, Boolean>();

    /** 
    * @return The Identifier of the ETApiObject.
    */
    public abstract String getId();

    /** 
    * @param id     The Identifier of the ETApiObject.
    */
    public abstract void setId(String id);

    //public abstract void hydrate();
    //public abstract Boolean isHydrated();
    //public abstract void refresh();

    /** 
    * @param property       The property of the ETApiObject.
    * @return               true if the property is modified, false otherwise.
    */
    public Boolean getModified(String property) {
        logger.trace("isModified[" + property + "] = " + isModified.get(property));
        return isModified.get(property);
    }

    /** 
    * @param property       The property of the ETApiObject.
    * @param value          The Boolean value of the property.
    * @return               previous value associated with the property or null if there was no mapping.
    */
    public Boolean setModified(String property, Boolean value) {
        logger.trace("isModified[" + property + "] = " + value);
        return isModified.put(property, value);
    }

    /** 
    * @return The List of properties whose values are true.
    */
    public List<String> getAllModified() {
        List<String> modified = new ArrayList<String>();
        for (Map.Entry<String, Boolean> entry : isModified.entrySet()) {
            if (entry.getValue() == true) {
                modified.add(entry.getKey());
            }
        }
        return modified;
    }
}
