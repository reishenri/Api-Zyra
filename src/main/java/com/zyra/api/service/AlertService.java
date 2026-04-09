package com.zyra.api.service;

import com.zyra.api.dto.AlertResponseDTO;
import com.zyra.api.model.Alert;
import com.zyra.api.repository.AlertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<AlertResponseDTO> findByDevice(Long deviceId) {
        return alertRepository.findByDeviceIdOrderByCreatedAtDesc(deviceId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AlertResponseDTO findLatestByDevice(Long deviceId) {
        Alert alert = alertRepository.findTopByDeviceIdOrderByCreatedAtDesc(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum alerta encontrado"));

        return toResponse(alert);
    }

    public AlertResponseDTO resolveAlert(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));

        alert.setActive(false);
        Alert updated = alertRepository.save(alert);

        return toResponse(updated);
    }

    public Alert createEntity(Alert alert) {
        return alertRepository.save(alert);
    }

    private AlertResponseDTO toResponse(Alert alert) {
        return new AlertResponseDTO(
                alert.getId(),
                alert.getMessage(),
                alert.getType(),
                alert.getActive(),
                alert.getDevice().getId(),
                alert.getCreatedAt()
        );
    }
}