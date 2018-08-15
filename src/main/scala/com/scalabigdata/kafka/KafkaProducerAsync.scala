package com.scalabigdata.kafka

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import scala.collection.mutable.ListBuffer


class ProducerThread(producer: KafkaProducer[String, String], record: ProducerRecord[String, String])
  extends Thread {
  override def run(): Unit = producer.send(record)
}


class KafkaProducerAsync(brokers: String) {

  val kafkaProps = new Properties
  kafkaProps.put("bootstrap.servers", brokers)

  kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProps.put("acks", "1")
  //kafkaProps.put("retries", "3")
  //kafkaProps.put("linger.ms", "5")

  val producer = new KafkaProducer[String, String](kafkaProps)

  def sendAsync(topic: String, topics: ListBuffer[String]) {
    topics.foreach(value =>
      new ProducerThread(this.producer, new ProducerRecord[String, String](topic, value)).start())
  }

}

/**
  *
  * @param topicPreContent: Json or another structured content to receive a sequencial id after it
  * @param topicPostContent: the end of Json or structured content after the sequencia id
  */
class Topic(val topicPreContent: String, val topicPostContent: String) {
  private var topics = ListBuffer[String]()

  /**
    *
    * @param quantity: the number of the topic will be the same as the sequencialId from 1 to infinite
    * @return all topics. They havea sequancial id to be sent
    */
  def createTopics(quantity: Int): ListBuffer[String] = {
    for (i <- 1 to quantity) topics += (topicPreContent + i + topicPostContent)
    topics
  }
}


object KafkaSender extends App {
  val topic1PreContent = "{\"NM_CLI\":\"PADARIA LTDA\",\"CD_CLI_CPF\":\"344444444\",\"OBJETO\":\"CLI\",\"CD_CLI\":\""
  val topic1PostContent = "\"}"
  val topic2PreContent = "{\"CD_ICCLI_TEL_CEL\":\"11998844331\",\"ST_ICCLI_EML\":\"S\",\"ST_ICCLI_EML_PFR\":\" \",\"DS_ICCLI_EML\":\"tiodopao@padarialtda.com.br\",\"ST_ICCLI_SMS_PFR\":\" \",\"OBJETO\":\"INF_CPM_CLI\",\"ST_ICCLI_SMS\":\"S\",\"CD_CLI\":\""
  val topic2PostContent = "\",\"CD_ICCLI_DDD_CEL\":\"    \"}"

  val quantity = 500
  val topic1 = new Topic(topic1PreContent, topic1PostContent)
  val topic2 = new Topic(topic2PreContent, topic2PostContent)
  val brokers = "localhost:9092"
  val producer = new KafkaProducerAsync(brokers)

  //Thread.sleep(10000)
  producer.sendAsync("topic1", topic1.createTopics(quantity))
  producer.sendAsync("topic2", topic2.createTopics(quantity))
}