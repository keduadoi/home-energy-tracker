package com.homeenergy.alertservice.repository;

import com.homeenergy.alertservice.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
