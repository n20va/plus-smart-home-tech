package ru.yandex.practicum.kafka.telemetry.collector.dto.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeviceActionDto {

    @NotBlank
    private String sensorId;

    @NotNull
    private ActionTypeDto type;

    private Integer value;

    public String getSensorId() {
        return sensorId;
    }

    public ActionTypeDto getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setType(ActionTypeDto type) {
        this.type = type;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}