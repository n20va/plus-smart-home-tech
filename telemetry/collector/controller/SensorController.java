package ru.yandex.practicum.kafka.telemetry.collector.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.kafka.telemetry.collector.dto.sensor.SensorEventDto;
import ru.yandex.practicum.kafka.telemetry.collector.mapper.SensorEventAvroFactory;
import ru.yandex.practicum.kafka.telemetry.collector.mapper.AvroMapper;
import ru.yandex.practicum.kafka.telemetry.collector.service.KafkaSender;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorEventAvroFactory factory;
    private final AvroMapper avroMapper;
    private final KafkaSender kafkaSender;

    public SensorController(SensorEventAvroFactory factory, AvroMapper avroMapper, KafkaSender kafkaSender) {
        this.factory = factory;
        this.avroMapper = avroMapper;
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void collect(@Valid @RequestBody SensorEventDto event) {
        var avro = factory.toAvro(event);
        byte[] bytes = avroMapper.toBytes(avro);
        kafkaSender.sendSensorEvent(event.getHubId(), bytes);
    }
}