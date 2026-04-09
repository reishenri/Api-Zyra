package com.zyra.api.repository;

import com.zyra.api.model.IrrigationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IrrigationLogRepository extends JpaRepository<IrrigationLog, Long> {

    List<IrrigationLog> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    List<IrrigationLog> findTop10ByDeviceIdOrderByCreatedAtDesc(Long deviceId);

    Optional<IrrigationLog> findTopByDeviceIdOrderByCreatedAtDesc(Long deviceId);
}