package com.zyra.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    private String location;

    // 🔥 CORREÇÃO DO LOOP
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // 🔥 EVITA PROBLEMAS DE SERIALIZAÇÃO (opcional, mas recomendado)
    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorDataList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> alerts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IrrigationLog> irrigationLogs = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private IrrigationRule irrigationRule;

    public Device() {}

    public Device(Long id, String name, String serialNumber, String location, User user) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.location = location;
        this.user = user;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public List<Alert> getAlerts() {
        return alerts; // 🔥 CORRIGIDO
    }

    public List<IrrigationLog> getIrrigationLogs() {
        return irrigationLogs;
    }

    public IrrigationRule getIrrigationRule() {
        return irrigationRule;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public void setIrrigationLogs(List<IrrigationLog> irrigationLogs) {
        this.irrigationLogs = irrigationLogs;
    }

    public void setIrrigationRule(IrrigationRule irrigationRule) {
        this.irrigationRule = irrigationRule;
    }
}