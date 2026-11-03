package com.homeenergy.deviceservice.repository;

import com.homeenergy.deviceservice.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByUserId(Long userId);
}
