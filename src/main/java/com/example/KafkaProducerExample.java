package com.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        String bootstrapServers = "pkc-4j8dq.southeastasia.azure.confluent.cloud:9092"; // From Confluent Cloud
        String apiKey = "JP3QCABCZQA5P5QB";
        String secretKey = "faEWzymncFX+J2cHOdB6dhRpRdN9PpVLaEub4dkbgLMNvxR2ZXhhUbpJg3/TJ+aE";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty("sasl.jaas.config", String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';", apiKey, secretKey));
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>("POC1", "Hello", "World");
        producer.send(record);
        producer.flush();
        producer.close();

        System.out.println("Message sent to Kafka!");
    }
}

