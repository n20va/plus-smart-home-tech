package ru.yandex.practicum.kafka.telemetry.collector.dto.sensor;

import jakarta.validation.constraints.NotNull;

public class LightSensorEventDto extends SensorEventDto {

    @NotNull
    private Integer linkQuality;

    @NotNull
    private Integer luminosity;

    public Integer getLinkQuality() {
        return linkQuality;
    }

    public Integer getLuminosity() {
        return luminosity;
    }

    public void setLinkQuality(Integer linkQuality) {
        this.linkQuality = linkQuality;
    }

    public void setLuminosity(Integer luminosity) {
        this.luminosity = luminosity;
    }
}