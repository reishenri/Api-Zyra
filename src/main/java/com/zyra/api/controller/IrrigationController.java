package com.zyra.api.controller;

import com.zyra.api.dto.IrrigationCommandRequestDTO;
import com.zyra.api.dto.IrrigationDecisionResponseDTO;
import com.zyra.api.service.IrrigationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/irrigation")
public class IrrigationController {

    private final IrrigationService irrigationService;

    public IrrigationController(IrrigationService irrigationService) {
        this.irrigationService = irrigationService;
    }

    @PostMapping("/manual")
    public ResponseEntity<IrrigationDecisionResponseDTO> executeManual(
            @RequestBody @Valid IrrigationCommandRequestDTO request
    ) {
        return ResponseEntity.ok(irrigationService.executeManual(request));
    }

    @GetMapping("/decision/{deviceId}")
    public ResponseEntity<IrrigationDecisionResponseDTO> getDecision(
            @PathVariable Long deviceId
    ) {
        return ResponseEntity.ok(irrigationService.getCurrentDecision(deviceId));
    }
}