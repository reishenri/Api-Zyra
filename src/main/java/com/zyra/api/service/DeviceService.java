package com.zyra.api.service;

import com.zyra.api.dto.DeviceRequestDTO;
import com.zyra.api.dto.DeviceResponseDTO;
import com.zyra.api.model.Device;
import com.zyra.api.model.User;
import com.zyra.api.repository.DeviceRepository;
import com.zyra.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    public DeviceResponseDTO create(DeviceRequestDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Device device = new Device();
        device.setName(request.name());
        device.setSerialNumber(request.serialNumber());
        device.setLocation(request.location());
        device.setUser(user);

        Device saved = deviceRepository.save(device);
        return toDTO(saved);
    }

    public List<DeviceResponseDTO> findAll() {
        return deviceRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public DeviceResponseDTO findById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        return toDTO(device);
    }

    public DeviceResponseDTO update(Long id, DeviceRequestDTO request) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        device.setName(request.name());
        device.setSerialNumber(request.serialNumber());
        device.setLocation(request.location());
        device.setUser(user);

        Device updated = deviceRepository.save(device);
        return toDTO(updated);
    }

    public void delete(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        deviceRepository.delete(device);
    }

    public Device findEntityById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));
    }

    private DeviceResponseDTO toDTO(Device device) {
        return new DeviceResponseDTO(
                device.getId(),
                device.getName(),
                device.getSerialNumber(),
                device.getLocation(),
                device.getUser() != null ? device.getUser().getId() : null
        );
    }
}