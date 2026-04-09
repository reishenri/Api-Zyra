package com.zyra.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_rules")
public class IrrigationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_soil_moisture", nullable = false)
    private Double minSoilMoisture;

    @Column(name = "max_temperature", nullable = false)
    private Double maxTemperature;

    @Column(name = "irrigation_duration_seconds", nullable = false)
    private Integer irrigationDurationSeconds;

    @Column(name = "automatic_mode", nullable = false)
    private Boolean automaticMode;

    @Column(name = "cooldown_minutes", nullable = false)
    private Integer cooldownMinutes;

    @OneToOne
    @JoinColumn(name = "device_id", nullable = false, unique = true)
    private Device device;

    public IrrigationRule() {
    }

    public IrrigationRule(Long id, Double minSoilMoisture, Double maxTemperature,
                          Integer irrigationDurationSeconds, Boolean automaticMode,
                          Integer cooldownMinutes, Device device) {
        this.id = id;
        this.minSoilMoisture = minSoilMoisture;
        this.maxTemperature = maxTemperature;
        this.irrigationDurationSeconds = irrigationDurationSeconds;
        this.automaticMode = automaticMode;
        this.cooldownMinutes = cooldownMinutes;
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public Double getMinSoilMoisture() {
        return minSoilMoisture;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Integer getIrrigationDurationSeconds() {
        return irrigationDurationSeconds;
    }

    public Boolean getAutomaticMode() {
        return automaticMode;
    }

    public Integer getCooldownMinutes() {
        return cooldownMinutes;
    }

    public Device getDevice() {
        return device;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMinSoilMoisture(Double minSoilMoisture) {
        this.minSoilMoisture = minSoilMoisture;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setIrrigationDurationSeconds(Integer irrigationDurationSeconds) {
        this.irrigationDurationSeconds = irrigationDurationSeconds;
    }

    public void setAutomaticMode(Boolean automaticMode) {
        this.automaticMode = automaticMode;
    }

    public void setCooldownMinutes(Integer cooldownMinutes) {
        this.cooldownMinutes = cooldownMinutes;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}