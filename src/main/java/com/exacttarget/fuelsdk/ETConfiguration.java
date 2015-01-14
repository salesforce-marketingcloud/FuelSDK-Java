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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ETConfiguration {
    private static Logger logger = Logger.getLogger(ETConfiguration.class);

    private Properties properties = new Properties();

    public ETConfiguration() {}

    public ETConfiguration(String file)
        throws ETSdkException
    {
        logger.trace("reading configuration from " + file);
        InputStream is = getClass().getResourceAsStream(file);
        if (is == null) {
            throw new ETSdkException("error opening " + file);
        }
        try {
            properties.load(is);
        } catch (IOException ex) {
            throw new ETSdkException("error reading " + file, ex);
        }
        if (logger.isTraceEnabled()) {
            for (String property : properties.stringPropertyNames()) {
                logger.trace("  " + property + " = " + get(property));
            }
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * @deprecated
     * Use <code>get("clientId")</code>.
     */
    @Deprecated
    public String getClientId() {
        return get("clientId");
    }

    /**
     * @deprecated
     * Use <code>set("clientId", clientId)</code>.
     */
    @Deprecated
    public void setClientId(String clientId) {
        set("clientId", clientId);
    }

    /**
     * @deprecated
     * Use <code>get("clientSecret")</code>.
     */
    @Deprecated
    public String getClientSecret() {
        return get("clientSecret");
    }

    /**
     * @deprecated
     * Use <code>set("clientSecret", clientSecret)</code>.
     */
    @Deprecated
    public void setClientSecret(String clientSecret) {
        set("clientSecret", clientSecret);
    }

    /**
     * @deprecated
     * Use <code>get("endpoint")</code>.
     */
    @Deprecated
    public String getEndpoint() {
        return get("endpoint");
    }

    /**
     * @deprecated
     * Use <code>set("endpoint", endpoint)</code>.
     */
    @Deprecated
    public void setEndpoint(String endpoint) {
        set("endpoint", endpoint);
    }

    /**
     * @deprecated
     * Use <code>get("authEndpoint")</code>.
     */
    @Deprecated
    public String getAuthEndpoint() {
        return get("authEndpoint");
    }

    /**
     * @deprecated
     * Use <code>set("authEndpoint", authEndpoint)</code>.
     */
    @Deprecated
    public void setAuthEndpoint(String authEndpoint) {
        set("authEndpoint", authEndpoint);
    }

    /**
     * @deprecated
     * Use <code>get("soapEndpoint")</code>.
     */
    @Deprecated
    public String getSoapEndpoint() {
        return get("soapEndpoint");
    }

    /**
     * @deprecated
     * Use <code>set("soapEndpoint", soapEndpoint)</code>.
     */
    @Deprecated
    public void setSoapEndpoint(String soapEndpoint) {
        set("soapEndpoint", soapEndpoint);
    }
}
