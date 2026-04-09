package com.zyra.api.repository;

import com.zyra.api.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    Optional<SensorData> findTopByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    List<SensorData> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    List<SensorData> findTop10ByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    List<SensorData> findByDeviceId(Long deviceId);
}