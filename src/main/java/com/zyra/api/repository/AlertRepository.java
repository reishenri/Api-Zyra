package com.zyra.api.repository;

import com.zyra.api.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    List<Alert> findByDeviceIdAndActiveTrueOrderByCreatedAtDesc(Long deviceId);

    Optional<Alert> findTopByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    Optional<Alert> findTopByDeviceIdAndTypeAndActiveTrueOrderByCreatedAtDesc(Long deviceId, String type);
}