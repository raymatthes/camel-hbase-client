package org.apache.camel.hbase.client;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class UnitedGetPutApp {

  private static Logger logger = Logger.getLogger(UnitedGetPutApp.class);

  public static void main(String[] args) throws Exception {

    logger.info("start");

    CamelContext camelContext = SpringCamelContext
        .springCamelContext("camel-context.xml");

    for (int index = 0; index < 3; index++) {
      logger.info("get " + index);
      get(camelContext);

      logger.info("put " + index);
      put(camelContext);
    }

    camelContext.stop();

    logger.info("end");

  }

  private static void put(CamelContext camelContext) {
    Put put = new Put(Bytes.toBytes("row1"));
    put.add(Bytes.toBytes("fam1"), Bytes.toBytes("qual1"),
        Bytes.toBytes("val1"));
    put.add(Bytes.toBytes("fam2"), Bytes.toBytes("qual2"),
        Bytes.toBytes("val2"));
    put.add(Bytes.toBytes("fam3"), Bytes.toBytes("qual3"),
        Bytes.toBytes("val3"));

    ProducerTemplate producer = camelContext.createProducerTemplate();
    producer.requestBody("direct:start-put", put);
  }

  private static void get(CamelContext camelContext) {
    Get get = new Get(Bytes.toBytes("row1"));

    ProducerTemplate producer = camelContext.createProducerTemplate();

    Object object = producer.requestBody("direct:start-get", get);
    KeyValue[] keyValues = (KeyValue[]) object;

    for (KeyValue keyValue : keyValues) {
      logger.info("key: " + Bytes.toString(keyValue.getKey()) + " value: "
          + Bytes.toString(keyValue.getValue()));
    }
  }
}
