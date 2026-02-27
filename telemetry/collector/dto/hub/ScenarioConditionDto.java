package ru.yandex.practicum.kafka.telemetry.collector.dto.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ScenarioConditionDto {

    @NotBlank
    private String sensorId;

    @NotNull
    private ConditionTypeDto type;

    @NotNull
    private ConditionOperationDto operation;

    private Object value;

    public String getSensorId() {
        return sensorId;
    }

    public ConditionTypeDto getType() {
        return type;
    }

    public ConditionOperationDto getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setType(ConditionTypeDto type) {
        this.type = type;
    }

    public void setOperation(ConditionOperationDto operation) {
        this.operation = operation;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}