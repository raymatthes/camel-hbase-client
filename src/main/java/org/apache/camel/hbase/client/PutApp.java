package org.apache.camel.hbase.client;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class PutApp {

  private static Logger logger = Logger.getLogger(GetApp.class);

  public static void main(String[] args) throws Exception {

    logger.info("start");

    CamelContext camelContext = SpringCamelContext
        .springCamelContext("camel-context.xml");

    Put put = new Put(Bytes.toBytes("row1"));
    put.add(Bytes.toBytes("fam1"), Bytes.toBytes("qual1"),
        Bytes.toBytes("val1"));
    put.add(Bytes.toBytes("fam2"), Bytes.toBytes("qual2"),
        Bytes.toBytes("val2"));
    put.add(Bytes.toBytes("fam3"), Bytes.toBytes("qual3"),
        Bytes.toBytes("val3"));

    ProducerTemplate producer = camelContext.createProducerTemplate();
    producer.requestBody("direct:start-put", put);

    // while (true)
    // Thread.sleep(10 * 1000);

    camelContext.stop();

    logger.info("end");

  }
}
