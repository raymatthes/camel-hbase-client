package org.apache.camel.hbase.client;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class GetApp {

  private static Logger logger = Logger.getLogger(GetApp.class);

  public static void main(String[] args) throws Exception {

    logger.info("start");

    CamelContext camelContext = SpringCamelContext
        .springCamelContext("camel-context.xml");

    Get get = new Get(Bytes.toBytes("row1"));

    ProducerTemplate producer = camelContext.createProducerTemplate();

    Object object = producer.requestBody("direct:start-get", get);
    KeyValue[] keyValues = (KeyValue[]) object;

    for (KeyValue keyValue : keyValues) {
      logger.info("key: " + Bytes.toString(keyValue.getKey()) + " value: "
          + Bytes.toString(keyValue.getValue()));
    }

    // while (true)
    // Thread.sleep(10 * 1000);

    camelContext.stop();

    logger.info("end");

  }
}
