package com.zyra.api.repository;

import com.zyra.api.model.IrrigationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IrrigationRuleRepository extends JpaRepository<IrrigationRule, Long> {

    Optional<IrrigationRule> findByDeviceId(Long deviceId);

    boolean existsByDeviceId(Long deviceId);
}