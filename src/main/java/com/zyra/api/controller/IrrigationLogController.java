package com.zyra.api.controller;

import com.zyra.api.dto.IrrigationLogResponseDTO;
import com.zyra.api.service.IrrigationLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irrigation-logs")
public class IrrigationLogController {

    private final IrrigationLogService irrigationLogService;

    public IrrigationLogController(IrrigationLogService irrigationLogService) {
        this.irrigationLogService = irrigationLogService;
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<IrrigationLogResponseDTO>> findByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(irrigationLogService.findByDevice(deviceId));
    }

    @GetMapping("/latest/{deviceId}")
    public ResponseEntity<List<IrrigationLogResponseDTO>> findLatestByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(irrigationLogService.findLatestByDevice(deviceId));
    }
}