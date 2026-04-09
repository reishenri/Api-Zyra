package com.zyra.api.controller;

import com.zyra.api.dto.SensorDataRequestDTO;
import com.zyra.api.dto.SensorDataResponseDTO;
import com.zyra.api.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping
    public ResponseEntity<SensorDataResponseDTO> create(
            @RequestBody @Valid SensorDataRequestDTO request
    ) {
        SensorDataResponseDTO response = sensorDataService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/latest/{deviceId}")
    public ResponseEntity<SensorDataResponseDTO> getLatestByDevice(@PathVariable Long deviceId) {
        SensorDataResponseDTO response = sensorDataService.findLatestByDevice(deviceId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{deviceId}")
    public ResponseEntity<List<SensorDataResponseDTO>> getHistoryByDevice(@PathVariable Long deviceId) {
        List<SensorDataResponseDTO> response = sensorDataService.findHistoryByDevice(deviceId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDataResponseDTO> findById(@PathVariable Long id) {
        SensorDataResponseDTO response = sensorDataService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sensorDataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}