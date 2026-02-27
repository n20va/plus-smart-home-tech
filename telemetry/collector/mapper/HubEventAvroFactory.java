package ru.yandex.practicum.kafka.telemetry.collector.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.collector.dto.hub.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

@Component
public class HubEventAvroFactory {

    public HubEventAvro toAvro(HubEventDto dto) {
        Object payload = switch (dto.getType()) {
            case DEVICE_ADDED -> {
                DeviceAddedEventDto e = (DeviceAddedEventDto) dto;
                yield new DeviceAddedEventAvro(e.getId(), DeviceTypeAvro.valueOf(e.getDeviceType().name()));
            }
            case DEVICE_REMOVED -> {
                DeviceRemovedEventDto e = (DeviceRemovedEventDto) dto;
                yield new DeviceRemovedEventAvro(e.getId());
            }
            case SCENARIO_ADDED -> {
                ScenarioAddedEventDto e = (ScenarioAddedEventDto) dto;

                var conditions = e.getConditions().stream()
                        .map(c -> new ScenarioConditionAvro(
                                c.getSensorId(),
                                ConditionTypeAvro.valueOf(c.getType().name()),
                                ConditionOperationAvro.valueOf(c.getOperation().name()),
                                mapConditionValue(c.getValue())
                        ))
                        .toList();

                var actions = e.getActions().stream()
                        .map(a -> new DeviceActionAvro(
                                a.getSensorId(),
                                ActionTypeAvro.valueOf(a.getType().name()),
                                a.getValue()
                        ))
                        .toList();

                yield new ScenarioAddedEventAvro(e.getName(), conditions, actions);
            }
            case SCENARIO_REMOVED -> {
                ScenarioRemovedEventDto e = (ScenarioRemovedEventDto) dto;
                yield new ScenarioRemovedEventAvro(e.getName());
            }
        };

        HubEventAvro avro = new HubEventAvro();
        avro.setHubId(dto.getHubId());
        avro.setTimestamp(dto.getTimestamp().toEpochMilli());
        avro.setPayload(payload);
        return avro;
    }

    private Object mapConditionValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean b) {
            return b;
        }
        if (value instanceof Number n) {
            return n.intValue();
        }
        if (value instanceof String s) {
            if ("true".equalsIgnoreCase(s)) return true;
            if ("false".equalsIgnoreCase(s)) return false;
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException ignored) {
            }
        }
        throw new IllegalArgumentException("Unsupported condition value type: " + value.getClass());
    }
}