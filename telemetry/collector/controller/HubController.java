package ru.yandex.practicum.kafka.telemetry.collector.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.kafka.telemetry.collector.dto.hub.HubEventDto;
import ru.yandex.practicum.kafka.telemetry.collector.mapper.HubEventAvroFactory;
import ru.yandex.practicum.kafka.telemetry.collector.mapper.AvroMapper;
import ru.yandex.practicum.kafka.telemetry.collector.service.KafkaSender;

@RestController
@RequestMapping("/hubs")
public class HubController {

    private final HubEventAvroFactory factory;
    private final AvroMapper avroMapper;
    private final KafkaSender kafkaSender;

    public HubController(HubEventAvroFactory factory, AvroMapper avroMapper, KafkaSender kafkaSender) {
        this.factory = factory;
        this.avroMapper = avroMapper;
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void collect(@Valid @RequestBody HubEventDto event) {
        var avro = factory.toAvro(event);
        byte[] bytes = avroMapper.toBytes(avro);
        kafkaSender.sendHubEvent(event.getHubId(), bytes);
    }
}