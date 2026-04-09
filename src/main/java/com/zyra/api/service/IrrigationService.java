package com.zyra.api.service;

import com.zyra.api.dto.IrrigationCommandRequestDTO;
import com.zyra.api.dto.IrrigationDecisionResponseDTO;
import com.zyra.api.model.Device;
import com.zyra.api.model.IrrigationLog;
import org.springframework.stereotype.Service;

@Service
public class IrrigationService {

    private final DeviceService deviceService;
    private final IrrigationLogService irrigationLogService;
    private final IrrigationRuleService irrigationRuleService;

    public IrrigationService(
            DeviceService deviceService,
            IrrigationLogService irrigationLogService,
            IrrigationRuleService irrigationRuleService
    ) {
        this.deviceService = deviceService;
        this.irrigationLogService = irrigationLogService;
        this.irrigationRuleService = irrigationRuleService;
    }

    public IrrigationDecisionResponseDTO executeManual(IrrigationCommandRequestDTO request) {

        Device device = deviceService.findEntityById(request.deviceId());

        IrrigationLog log = new IrrigationLog();
        log.setDevice(device);
        log.setDurationSeconds(request.durationSeconds());
        log.setMode("MANUAL");

        irrigationLogService.createEntity(log);

        return new IrrigationDecisionResponseDTO(
                true,
                request.durationSeconds(),
                "Irrigação manual executada"
        );
    }

    public IrrigationDecisionResponseDTO getCurrentDecision(Long deviceId) {

        var rule = irrigationRuleService.findEntityByDevice(deviceId);

        if (Boolean.TRUE.equals(rule.getAutomaticMode())) {
            return new IrrigationDecisionResponseDTO(
                    true,
                    rule.getIrrigationDurationSeconds(),
                    "Modo automático ativo"
            );
        }

        return new IrrigationDecisionResponseDTO(
                false,
                0,
                "Modo automático desligado"
        );
    }
}