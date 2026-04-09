package com.zyra.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "irrigation_logs")
public class IrrigationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @Column(nullable = false, length = 20)
    private String mode; // AUTO ou MANUAL

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    public IrrigationLog() {
    }

    public IrrigationLog(Long id, Integer durationSeconds, String mode,
                         LocalDateTime createdAt, Device device) {
        this.id = id;
        this.durationSeconds = durationSeconds;
        this.mode = mode;
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

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public String getMode() {
        return mode;
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

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}