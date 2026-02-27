package ru.yandex.practicum.kafka.telemetry.collector.dto.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeviceAddedEventDto extends HubEventDto {

    @NotBlank
    private String id;

    @NotNull
    private DeviceTypeDto deviceType;

    public String getId() {
        return id;
    }

    public DeviceTypeDto getDeviceType() {
        return deviceType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceType(DeviceTypeDto deviceType) {
        this.deviceType = deviceType;
    }
}