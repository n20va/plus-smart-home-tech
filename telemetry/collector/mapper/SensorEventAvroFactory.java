package ru.yandex.practicum.kafka.telemetry.collector.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.collector.dto.sensor.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

@Component
public class SensorEventAvroFactory {

    public SensorEventAvro toAvro(SensorEventDto dto) {
        Object payload = switch (dto.getType()) {
            case CLIMATE_SENSOR_EVENT -> new ClimateSensorAvro(
                    ((ClimateSensorEventDto) dto).getTemperatureC(),
                    ((ClimateSensorEventDto) dto).getHumidity(),
                    ((ClimateSensorEventDto) dto).getCo2Level()
            );
            case LIGHT_SENSOR_EVENT -> new LightSensorAvro(
                    ((LightSensorEventDto) dto).getLinkQuality(),
                    ((LightSensorEventDto) dto).getLuminosity()
            );
            case MOTION_SENSOR_EVENT -> new MotionSensorAvro(
                    ((MotionSensorEventDto) dto).getLinkQuality(),
                    ((MotionSensorEventDto) dto).getMotion(),
                    ((MotionSensorEventDto) dto).getVoltage()
            );
            case SWITCH_SENSOR_EVENT -> new SwitchSensorAvro(
                    ((SwitchSensorEventDto) dto).getState()
            );
            case TEMPERATURE_SENSOR_EVENT -> new TemperatureSensorAvro(
                    ((TemperatureSensorEventDto) dto).getTemperatureC(),
                    ((TemperatureSensorEventDto) dto).getTemperatureF()
            );
        };

        SensorEventAvro avro = new SensorEventAvro();
        avro.setId(dto.getId());
        avro.setHubId(dto.getHubId());
        avro.setTimestamp(dto.getTimestamp().toEpochMilli());
        avro.setPayload(payload);
        return avro;
    }
}