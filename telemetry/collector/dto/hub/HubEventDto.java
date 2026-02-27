package ru.yandex.practicum.kafka.telemetry.collector.dto.hub;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeviceAddedEventDto.class, name = "DEVICE_ADDED"),
        @JsonSubTypes.Type(value = DeviceRemovedEventDto.class, name = "DEVICE_REMOVED"),
        @JsonSubTypes.Type(value = ScenarioAddedEventDto.class, name = "SCENARIO_ADDED"),
        @JsonSubTypes.Type(value = ScenarioRemovedEventDto.class, name = "SCENARIO_REMOVED")
})
public abstract class HubEventDto {

    @NotBlank
    private String hubId;

    @NotNull
    private Instant timestamp;

    @NotNull
    private HubEventTypeDto type;

    public String getHubId() {
        return hubId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public HubEventTypeDto getType() {
        return type;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(HubEventTypeDto type) {
        this.type = type;
    }
}