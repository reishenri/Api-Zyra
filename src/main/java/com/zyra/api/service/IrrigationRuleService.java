package com.zyra.api.service;

import com.zyra.api.dto.IrrigationRuleRequestDTO;
import com.zyra.api.dto.IrrigationRuleResponseDTO;
import com.zyra.api.model.Device;
import com.zyra.api.model.IrrigationRule;
import com.zyra.api.repository.DeviceRepository;
import com.zyra.api.repository.IrrigationRuleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IrrigationRuleService {

    private final IrrigationRuleRepository irrigationRuleRepository;
    private final DeviceRepository deviceRepository;

    public IrrigationRuleService(IrrigationRuleRepository irrigationRuleRepository,
                                 DeviceRepository deviceRepository) {
        this.irrigationRuleRepository = irrigationRuleRepository;
        this.deviceRepository = deviceRepository;
    }

    public IrrigationRuleResponseDTO create(IrrigationRuleRequestDTO request) {
        Device device = deviceRepository.findById(request.deviceId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        IrrigationRule rule = new IrrigationRule();
        rule.setDevice(device);
        rule.setMinSoilMoisture(request.minSoilMoisture());
        rule.setMaxTemperature(request.maxTemperature());
        rule.setIrrigationDurationSeconds(request.irrigationDurationSeconds());
        rule.setAutomaticMode(request.automaticMode());
        rule.setCooldownMinutes(request.cooldownMinutes());

        IrrigationRule saved = irrigationRuleRepository.save(rule);
        return toResponse(saved);
    }

    public IrrigationRuleResponseDTO findByDevice(Long deviceId) {
        IrrigationRule rule = irrigationRuleRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada para o dispositivo"));

        return toResponse(rule);
    }

    public IrrigationRuleResponseDTO update(Long id, IrrigationRuleRequestDTO request) {
        IrrigationRule rule = irrigationRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada"));

        Device device = deviceRepository.findById(request.deviceId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        rule.setDevice(device);
        rule.setMinSoilMoisture(request.minSoilMoisture());
        rule.setMaxTemperature(request.maxTemperature());
        rule.setIrrigationDurationSeconds(request.irrigationDurationSeconds());
        rule.setAutomaticMode(request.automaticMode());
        rule.setCooldownMinutes(request.cooldownMinutes());

        IrrigationRule updated = irrigationRuleRepository.save(rule);
        return toResponse(updated);
    }

    public void delete(Long id) {
        IrrigationRule rule = irrigationRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada"));

        irrigationRuleRepository.delete(rule);
    }

    public IrrigationRule findEntityByDevice(Long deviceId) {
        return irrigationRuleRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Regra não encontrada para o dispositivo"));
    }

    private IrrigationRuleResponseDTO toResponse(IrrigationRule rule) {
        return new IrrigationRuleResponseDTO(
                rule.getId(),
                rule.getDevice().getId(),
                rule.getMinSoilMoisture(),
                rule.getMaxTemperature(),
                rule.getIrrigationDurationSeconds(),
                rule.getAutomaticMode(),
                rule.getCooldownMinutes()
        );
    }
}