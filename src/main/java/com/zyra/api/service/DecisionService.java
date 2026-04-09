package com.zyra.api.service;

import com.zyra.api.model.Alert;
import com.zyra.api.model.IrrigationLog;
import com.zyra.api.model.IrrigationRule;
import com.zyra.api.model.SensorData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DecisionService {

    private final IrrigationRuleService irrigationRuleService;
    private final AlertService alertService;
    private final IrrigationLogService irrigationLogService;

    public DecisionService(
            IrrigationRuleService irrigationRuleService,
            AlertService alertService,
            IrrigationLogService irrigationLogService
    ) {
        this.irrigationRuleService = irrigationRuleService;
        this.alertService = alertService;
        this.irrigationLogService = irrigationLogService;
    }

    public void processReading(SensorData sensorData) {
        IrrigationRule rule = irrigationRuleService.findEntityByDevice(sensorData.getDevice().getId());

        if (sensorData.getSoilMoisture() != null &&
                sensorData.getSoilMoisture() < rule.getMinSoilMoisture()) {

            Alert alert = new Alert();
            alert.setDevice(sensorData.getDevice());
            alert.setMessage("Umidade do solo abaixo do limite");
            alert.setType("SOIL_MOISTURE_LOW");
            alert.setActive(true);
            alert.setCreatedAt(LocalDateTime.now());

            alertService.createEntity(alert);

            if (Boolean.TRUE.equals(rule.getAutomaticMode())) {
                IrrigationLog log = new IrrigationLog();
                log.setDevice(sensorData.getDevice());
                log.setDurationSeconds(rule.getIrrigationDurationSeconds());
                log.setMode("AUTO");
                log.setCreatedAt(LocalDateTime.now());

                irrigationLogService.createEntity(log);
            }
        }

        if (sensorData.getTemperature() != null &&
                sensorData.getTemperature() > rule.getMaxTemperature()) {

            Alert alert = new Alert();
            alert.setDevice(sensorData.getDevice());
            alert.setMessage("Temperatura acima do limite configurado");
            alert.setType("TEMPERATURE_HIGH");
            alert.setActive(true);
            alert.setCreatedAt(LocalDateTime.now());

            alertService.createEntity(alert);
        }

        if (sensorData.getWaterLevel() != null &&
                sensorData.getWaterLevel() < 20) {

            Alert alert = new Alert();
            alert.setDevice(sensorData.getDevice());
            alert.setMessage("Nível de água crítico");
            alert.setType("WATER_LEVEL_LOW");
            alert.setActive(true);
            alert.setCreatedAt(LocalDateTime.now());

            alertService.createEntity(alert);
        }
    }
}