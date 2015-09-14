Fuel Java SDK
=============

The Fuel Java SDK enables developers to easily access the
Salesforce Marketing Cloud (formerly ExactTarget)
from the Java platform. Among other things, the Java SDK:

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
(http://salesforcefuel.github.io/FuelSDK-Java/).

Installation
------------

* Clone the project from GitHub:

    `git clone git@github.com:ExactTarget/fuel-java.git`

* Generate sources from the ExactTarget WSDL:

    `mvn generate-sources`

* Import project into Eclipse (optional):

    File -> Import... -> Maven -> Existing Maven Projects

* Build the jar file via the command line:

    `mvn -DskipTests package`

* Add the jar file and dependencies (<a href="http://cxf.apache.org/">Apache CXF</a>, <a href="http://commons.apache.org/proper/commons-beanutils/">Apache Commons BeanUtils</a>, <a href="http://logging.apache.org/log4j/1.2/">Apache log4j 1.x</a>, and <a href="https://code.google.com/p/google-gson/">Google Gson</a>) to your class path

* Obtain a clientId and clientSecret from App Center (see https://code.exacttarget.com/getting-started/setting-your-development-environment) and place them in `fuelsdk.properties` using `src/main/resources/fuelsdk.properties` as a starting template
