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
import java.util.Date;
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
 * id -
 * key -
 * name -
 * createdDate -
 * modifiedDate -
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

    public abstract String getId();
    public abstract void setId(String id);
    public abstract String getKey();
    public abstract void setKey(String key);
    public abstract Date getCreatedDate();
    public abstract void setCreatedDate(Date createdDate);
    public abstract Date getModifiedDate();
    public abstract void setModifiedDate(Date modifiedDate);

    //public abstract void hydrate();
    //public abstract Boolean isHydrated();
    //public abstract void refresh();

    public Boolean getModified(String property) {
        logger.trace("isModified[" + property + "] = " + isModified.get(property));
        return isModified.get(property);
    }

    public Boolean setModified(String property, Boolean value) {
        logger.trace("isModified[" + property + "] = " + value);
        return isModified.put(property, value);
    }

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
