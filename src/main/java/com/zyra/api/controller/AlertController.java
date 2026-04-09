package com.zyra.api.controller;

import com.zyra.api.dto.AlertResponseDTO;
import com.zyra.api.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<AlertResponseDTO>> findByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(alertService.findByDevice(deviceId));
    }

    @GetMapping("/latest/{deviceId}")
    public ResponseEntity<AlertResponseDTO> findLatestByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(alertService.findLatestByDevice(deviceId));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<AlertResponseDTO> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}