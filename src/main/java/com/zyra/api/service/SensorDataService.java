package com.zyra.api.service;

import com.zyra.api.dto.SensorDataRequestDTO;
import com.zyra.api.dto.SensorDataResponseDTO;
import com.zyra.api.model.Device;
import com.zyra.api.model.SensorData;
import com.zyra.api.repository.DeviceRepository;
import com.zyra.api.repository.SensorDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final DeviceRepository deviceRepository;
    private final DecisionService decisionService;

    public SensorDataService(
            SensorDataRepository sensorDataRepository,
            DeviceRepository deviceRepository,
            DecisionService decisionService
    ) {
        this.sensorDataRepository = sensorDataRepository;
        this.deviceRepository = deviceRepository;
        this.decisionService = decisionService;
    }

    public SensorDataResponseDTO create(SensorDataRequestDTO request) {
        Device device = deviceRepository.findById(request.deviceId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        SensorData sensorData = new SensorData();
        sensorData.setDevice(device);
        sensorData.setTemperature(request.temperature());
        sensorData.setAirHumidity(request.airHumidity());
        sensorData.setSoilMoisture(request.soilMoisture());
        sensorData.setWaterLevel(request.waterLevel());
        sensorData.setCreatedAt(LocalDateTime.now());

        SensorData saved = sensorDataRepository.save(sensorData);

        decisionService.processReading(saved);

        return toResponse(saved);
    }

    public SensorDataResponseDTO findLatestByDevice(Long deviceId) {
        SensorData sensorData = sensorDataRepository
                .findTopByDeviceIdOrderByCreatedAtDesc(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma leitura encontrada para esse dispositivo"));

        return toResponse(sensorData);
    }

    public List<SensorDataResponseDTO> findHistoryByDevice(Long deviceId) {
        return sensorDataRepository.findByDeviceId(deviceId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SensorDataResponseDTO findById(Long id) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        return toResponse(sensorData);
    }

    public void delete(Long id) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        sensorDataRepository.delete(sensorData);
    }

    private SensorDataResponseDTO toResponse(SensorData sensorData) {
        return new SensorDataResponseDTO(
                sensorData.getId(),
                sensorData.getDevice().getId(),
                sensorData.getTemperature(),
                sensorData.getAirHumidity(),
                sensorData.getSoilMoisture(),
                sensorData.getWaterLevel(),
                sensorData.getCreatedAt()
        );
    }
}