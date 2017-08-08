Salesforce Marketing Cloud Java SDK
===================================

The Salesforce Marketing Cloud Java SDK enables developers to easily
access the Salesforce Marketing Cloud (formerly ExactTarget) via the
Java platform. Among other things, the SDK:

* automatically acquires and refreshes Marketing Cloud
  access tokens

* enables developers to access both Marketing Cloud SOAP
  and REST APIs in the same session

* exposes simplified versions of the most commonly used Marketing
  Cloud objects and methods as Java native objects

* provides passthroughs so developers can access the full
  REST and SOAP APIs directly when they need to go beyond
  the simplified interfaces

* adds "sugar" methods for the most commonly used Marketing
  Cloud features that make it easy to use those features (e.g.,
  the SDK provides a SQL-like interface to data extensions)

For more information about the Java SDK and how to use it, please see
the Javadocs at http://salesforce-marketingcloud.github.io/FuelSDK-Java/.

New Features in Version 1.2.0
* Project tree structure 
    * Source Packages       : SDK package (src/main/java/com/exacttarget/fuelsdk/)
    * Test Packages         : JUnit test package (src/test/java/com/exacttarget/fuelsdk/)
    * Annotation Packages   : The annotation package (src/main/java/com/exacttarget/fuelsdk/annotations/)
    * Documentation		: SDK API HTML documentation (docs/)

* New addition to the source packages
    - Added ETProfileAttribute.java to create new Subcriber
    - Added SendClassification on ETTriggeredEmail to create new Triggered Send Definition

* JUnit test case : This covers basic happy path testing. All the test cases use “ET” classes. Advanced and more comprehensive test cases will be added in future releases. Added new JUnit test cases.

* API docs : added API documentation using doxygen documentation framework. (under docs/ directory)

Installation
------------

The easiest way to install the Java SDK is via Maven&mdash;simply add the following dependency to your project's `pom.xml`:

    <dependency>
      <groupId>com.exacttarget</groupId>
      <artifactId>fuelsdk</artifactId>
      <version>1.1.0</version>
    </dependency>

Maven will automatically resolve, download, and install all dependencies for you.

You can also download a jar file from the [Releases](https://github.com/salesforce-marketingcloud/FuelSDK-Java/releases) page or clone the repository and build a jar file yourself in the standard way. If you go this route, you'll need to ensure you have manually downloaded and installed all dependencies ([Apache CXF](http://cxf.apache.org), [Apache Commons BeanUtils](http://commons.apache.org/proper/commons-beanutils), [Apache log4j 1.x](http://logging.apache.org/log4j/1.2/), and [Google Gson](https://code.google.com/p/google-gson)) to your class path.

Once you have the SDK installed, you'll need to obtain a client ID and client secret from App Center and place them in `fuelsdk.properties` using `src/main/resources/fuelsdk.properties.template` as a starting template. Theses values authenticate you to the Saleforce Marketing Cloud API.
