package com.zyra.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "air_humidity", nullable = false)
    private Double airHumidity;

    @Column(name = "soil_moisture", nullable = false)
    private Double soilMoisture;

    @Column(name = "water_level")
    private Double waterLevel;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    public SensorData() {
    }

    public SensorData(Long id, Double temperature, Double airHumidity, Double soilMoisture,
                      Double waterLevel, LocalDateTime createdAt, Device device) {
        this.id = id;
        this.temperature = temperature;
        this.airHumidity = airHumidity;
        this.soilMoisture = soilMoisture;
        this.waterLevel = waterLevel;
        this.createdAt = createdAt;
        this.device = device;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getAirHumidity() {
        return airHumidity;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Device getDevice() {
        return device;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setAirHumidity(Double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}