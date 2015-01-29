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
