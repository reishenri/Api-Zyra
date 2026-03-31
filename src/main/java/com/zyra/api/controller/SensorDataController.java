package com.zyra.api.controller;

import com.zyra.api.model.SensorData;
import com.zyra.api.service.SensorDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
@CrossOrigin(origins = "*")
public class SensorDataController {

    private final SensorDataService service;

    public SensorDataController(SensorDataService service) {
        this.service = service;
    }

    @PostMapping
    public SensorData save(@RequestBody SensorData data) {
        return service.save(data);
    }

    @GetMapping
    public List<SensorData> getAll() {
        return service.getAll();
    }

    @GetMapping("/latest")
    public SensorData getLatest() {
        return service.getLatest().orElse(null);
    }
}