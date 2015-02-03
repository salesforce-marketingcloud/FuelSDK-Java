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
