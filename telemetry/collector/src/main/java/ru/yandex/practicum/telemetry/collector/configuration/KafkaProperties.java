package ru.yandex.practicum.telemetry.collector.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "collector.kafka")
public class KafkaProperties {

    private int retries;
    private int deliveryTimeoutMs;
    private int requestTimeoutMs;

    private Topics topics;

    @Getter
    @Setter
    public static class Topics {
        private String hubsEvents;
        private String sensorsEvents;
        private String snapshotsEvents;
    }
}