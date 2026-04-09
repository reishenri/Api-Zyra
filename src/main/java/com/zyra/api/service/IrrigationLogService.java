package com.zyra.api.service;

import com.zyra.api.dto.IrrigationLogResponseDTO;
import com.zyra.api.model.IrrigationLog;
import com.zyra.api.repository.IrrigationLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationLogService {

    private final IrrigationLogRepository irrigationLogRepository;

    public IrrigationLogService(IrrigationLogRepository irrigationLogRepository) {
        this.irrigationLogRepository = irrigationLogRepository;
    }

    public List<IrrigationLogResponseDTO> findByDevice(Long deviceId) {
        return irrigationLogRepository.findByDeviceIdOrderByCreatedAtDesc(deviceId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<IrrigationLogResponseDTO> findLatestByDevice(Long deviceId) {
        return irrigationLogRepository.findTop10ByDeviceIdOrderByCreatedAtDesc(deviceId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public IrrigationLog createEntity(IrrigationLog irrigationLog) {
        return irrigationLogRepository.save(irrigationLog);
    }

    private IrrigationLogResponseDTO toDTO(IrrigationLog log) {
        return new IrrigationLogResponseDTO(
                log.getId(),
                log.getDevice() != null ? log.getDevice().getId() : null,
                log.getDurationSeconds(),
                log.getMode(),
                log.getCreatedAt()
        );
    }
}