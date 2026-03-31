package com.zyra.api.service;

import com.zyra.api.model.SensorData;
import com.zyra.api.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {

    private final SensorDataRepository repository;

    public SensorDataService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public SensorData save(SensorData data) {
        applyAlertLogic(data);
        return repository.save(data);
    }

    public List<SensorData> getAll() {
        return repository.findAll();
    }

    public Optional<SensorData> getLatest() {
        return repository.findTopByOrderByIdDesc();
    }

    private void applyAlertLogic(SensorData data) {
        if (data.getSoilMoisture() == null) {
            data.setAlert("Sem leitura");
            return;
        }

        double soil = data.getSoilMoisture();

        if (soil < 20) {
            data.setAlert("Crítico: solo muito seco");
        } else if (soil < 30) {
            data.setAlert("Solo seco");
        } else if (soil > 80) {
            data.setAlert("Solo muito úmido");
        } else {
            data.setAlert("Normal");
        }
    }
}