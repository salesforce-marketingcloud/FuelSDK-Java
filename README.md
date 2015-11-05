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
http://salesforcefuel.github.io/FuelSDK-Java/.

Installation
------------

* Clone the project from GitHub:

    `git clone git@github.com:salesforce-marketingcloud/FuelSDK-Java.git`

* Generate sources from the ExactTarget WSDL:

    `mvn generate-sources`

* Import project into Eclipse (optional):

    File -> Import... -> Maven -> Existing Maven Projects

* Build the jar file via the command line:

    `mvn -DskipTests package`

* Add the jar file and dependencies ([Apache CXF](http://cxf.apache.org), [Apache Commons BeanUtils](http://commons.apache.org/proper/commons-beanutils), [Apache log4j 1.x](http://logging.apache.org/log4j/1.2/), and [Google Gson](https://code.google.com/p/google-gson)) to your class path

* Obtain a client ID and client secret from App Center (see https://code.exacttarget.com/getting-started/setting-your-development-environment) and place them in `fuelsdk.properties` using `src/main/resources/fuelsdk.properties` as a starting template
