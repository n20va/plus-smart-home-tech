package ru.yandex.practicum.kafka.telemetry.collector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final String sensorsTopic;
    private final String hubsTopic;

    public KafkaSender(KafkaTemplate<String, byte[]> kafkaTemplate,
                       @Value("${collector.kafka.topics.sensors}") String sensorsTopic,
                       @Value("${collector.kafka.topics.hubs}") String hubsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.sensorsTopic = sensorsTopic;
        this.hubsTopic = hubsTopic;
    }

    public void sendSensorEvent(String key, byte[] payload) {
        kafkaTemplate.send(sensorsTopic, key, payload);
    }

    public void sendHubEvent(String key, byte[] payload) {
        kafkaTemplate.send(hubsTopic, key, payload);
    }
}