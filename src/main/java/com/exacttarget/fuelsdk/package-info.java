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

/**
 * The Java client library enables developers to easily
 * access the Salesforce Marketing Cloud (formerly
 * ExactTarget) API from the Java platform. Among other
 * things, the Java client library:
 *
 * <ul>
 * <li>
 * automatically acquires and refreshes Marketing Cloud
 * access tokens
 * </li>
 * <li>
 * enables developers to access both Marketing Cloud
 * SOAP and REST APIs in the same session
 * </li>
 * <li>
 * exposes simplified versions of the most commonly
 * used Marketing Cloud objects and methods as Java
 * native objects
 * </li>
 * <li>
 * provides passthroughs so developers can access the full
 * REST and SOAP APIs directly when they need to go beyond
 * the simplified interfaces
 * </li>
 * <li>
 * adds "sugar" methods for the most commonly used
 * Marketing Cloud features that make it easy to use
 * those features (e.g., the client library
 * provides an SQL like interface to data extensions)
 * </li>
 * </ul>
 *
 * To use the Java client library, you first need to
 * instantiate an <code>ETClient</code> object:
 *
 * <pre>
 * ETClient client = new ETClient();
 * </pre>
 *
 * The Java client library is highly configurable. By
 * default, configuration is read from a properties file
 * named <code>fuelsdk.properties</code>, which should be
 * located in your class path. Alternatively, you can
 * pass in the path of the file to be read as an
 * argument to the <code>ETClient</code> constructor:
 *
 * <pre>
 * ETClient client = new ETClient("/Users/imurdock/fuel.properties");
 * </pre>
 *
 * You can also pass in a programmatically constructed
 * <code>ETConfiguration</code> object:
 *
 * <pre>
 * ETConfiguration configuration = new ETConfiguration();
 * configuration.setClientId("clientIdGoesHere");
 * configuration.setClientSecret("clientSecretGoesHere");
 *
 * ETClient client = new ETClient(ETConfiguration);
 * </pre>
 *
 * <p>
 * <i>XXX include full list of configuration options</i>
 * </p>
 *
 * <p>
 * <i>XXX include overview of logging options</i>
 * </p>
 *
 * Note: For historical reasons, the Java client library
 * uses the term "Fuel" and "SDK" in several places, e.g., the
 * package name <code>com.exacttarget.fuelsdk</code> and
 * exception class name <code>ETSdkException</code>. The
 * Fuel client libraries were initially called SDKs and
 * implemented before ExactTarget was acquired by
 * Salesforce and became the Salesforce Marketing Cloud.
 *
 * Once the client has been initialized, you interacted
 * with the API using its <code>create</code>, <code>retrieve</code>,
 * <code>update</code>, and <code>delete</code> methods.
 *
 * <p>
 * <i>XXX include full list of generic methods and common
 * properties, e.g. id and key</i>
 * </p>
 *
 * In addition, some objects (e.g., <code>ETDataExtension</code>
 * and <code>ETTriggeredEmail</code> contain object native
 * methods (e.g., <code>insert</code>, <code>select</code>,
 * <code>update</code>, and <code>delete</code>
 * for <code>ETDataExtension</code> and <code>send</code> for
 * <code>TriggeredEmail). For information on these
 * object native methods please see the class documentation..
 *
 * <p>
 * <i>XXX include overview of filter strings</i>
 * </p>
 *
 * <p>
 * <i>XXX include overview of ETResponse / ETResult</i>
 * </p>
 *
 * <p>
 * <i>XXX include how to use API passthrough</i>
 * </p>
 */

package com.exacttarget.fuelsdk;
