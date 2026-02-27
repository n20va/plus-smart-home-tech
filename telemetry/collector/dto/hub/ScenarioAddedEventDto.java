package ru.yandex.practicum.kafka.telemetry.collector.dto.hub;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ScenarioAddedEventDto extends HubEventDto {

    @NotBlank
    private String name;

    @Valid
    @NotEmpty
    private List<ScenarioConditionDto> conditions;

    @Valid
    @NotEmpty
    private List<DeviceActionDto> actions;

    public String getName() {
        return name;
    }

    public List<ScenarioConditionDto> getConditions() {
        return conditions;
    }

    public List<DeviceActionDto> getActions() {
        return actions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConditions(List<ScenarioConditionDto> conditions) {
        this.conditions = conditions;
    }

    public void setActions(List<DeviceActionDto> actions) {
        this.actions = actions;
    }
}