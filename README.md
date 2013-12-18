Fuel Java SDK
=============

Copyright (C) 2013 ExactTarget

Installation
------------

* Clone the project from GitHub:

    git clone git@github.com:ExactTarget/FuelSDK-Java.git

* Generate sources from the ExactTarget WSDL:

    mvn generate-sources

* Import project into Eclipse (optional):

    File -> Import... -> Maven -> Existing Maven Projects

* Build the jar file via the command line:

    mvn package

* Add the jar file and dependencies (<a href="http://commons.apache.org/proper/commons-beanutils/">Apache Commons BeanUtils</a>, <a href="http://logging.apache.org/log4j/1.2/">Apache log4j 1.x</a>, and <a href="https://code.google.com/p/google-gson/">Google Gson</a>) to your class path

* Obtain a clientId and clientSecret from App Center (see https://code.exacttarget.com/getting-started/setting-your-development-environment) and place them in `fuelsdk.properties` using `src/main/resources/fuelsdk.properties` as a starting template
