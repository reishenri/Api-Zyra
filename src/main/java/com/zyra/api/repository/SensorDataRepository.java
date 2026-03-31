package com.zyra.api.repository;

import com.zyra.api.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    Optional<SensorData> findTopByOrderByIdDesc();
}