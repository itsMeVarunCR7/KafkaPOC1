package com.example;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        String bootstrapServers = "pkc-4j8dq.southeastasia.azure.confluent.cloud:9092";
        String apiKey = "JP3QCABCZQA5P5QB";
        String secretKey = "faEWzymncFX+J2cHOdB6dhRpRdN9PpVLaEub4dkbgLMNvxR2ZXhhUbpJg3/TJ+aE";
        String groupId = "test-group2";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty("sasl.jaas.config", String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';", apiKey, secretKey));
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("POC1"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Consumed record with key %s and value %s%n", record.key(), record.value());
                System.out.println("Consumer Received message");
            }
        }
    }
}

