package com.zyra.api.controller;

import com.zyra.api.dto.DeviceRequestDTO;
import com.zyra.api.dto.DeviceResponseDTO;
import com.zyra.api.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> create(
            @RequestBody @Valid DeviceRequestDTO request
    ) {
        DeviceResponseDTO response = deviceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid DeviceRequestDTO request
    ) {
        DeviceResponseDTO response = deviceService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}