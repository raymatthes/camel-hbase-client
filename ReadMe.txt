Camel HBase Client
====================

To run came to test the route definitions only:

    mvn camel:run

For more help see the Apache Camel documentation

    http://camel.apache.org/

-=-=-=-=-=

To run the specific examples:

    mvn exec:java -Dexec.mainClass="org.apache.camel.hbase.client.PutApp"

    mvn exec:java -Dexec.mainClass="org.apache.camel.hbase.client.GetApp"

    mvn exec:java -Dexec.mainClass="org.apache.camel.hbase.client.UnitedGetPutApp"
