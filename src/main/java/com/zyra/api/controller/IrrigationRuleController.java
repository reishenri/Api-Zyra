package com.zyra.api.controller;

import com.zyra.api.dto.IrrigationRuleRequestDTO;
import com.zyra.api.dto.IrrigationRuleResponseDTO;
import com.zyra.api.service.IrrigationRuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/irrigation-rules")
public class IrrigationRuleController {

    private final IrrigationRuleService irrigationRuleService;

    public IrrigationRuleController(IrrigationRuleService irrigationRuleService) {
        this.irrigationRuleService = irrigationRuleService;
    }

    @PostMapping
    public ResponseEntity<IrrigationRuleResponseDTO> create(
            @RequestBody @Valid IrrigationRuleRequestDTO request
    ) {
        IrrigationRuleResponseDTO response = irrigationRuleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<IrrigationRuleResponseDTO> findByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(irrigationRuleService.findByDevice(deviceId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IrrigationRuleResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid IrrigationRuleRequestDTO request
    ) {
        return ResponseEntity.ok(irrigationRuleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        irrigationRuleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}