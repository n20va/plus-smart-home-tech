package ru.yandex.practicum.kafka.telemetry.collector.dto.sensor;

import jakarta.validation.constraints.NotNull;

public class MotionSensorEventDto extends SensorEventDto {

    @NotNull
    private Integer linkQuality;

    @NotNull
    private Boolean motion;

    @NotNull
    private Integer voltage;

    public Integer getLinkQuality() {
        return linkQuality;
    }

    public Boolean getMotion() {
        return motion;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public void setLinkQuality(Integer linkQuality) {
        this.linkQuality = linkQuality;
    }

    public void setMotion(Boolean motion) {
        this.motion = motion;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }
}