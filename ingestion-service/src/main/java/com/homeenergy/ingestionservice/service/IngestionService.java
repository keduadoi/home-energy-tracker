package com.homeenergy.ingestionservice.service;

import com.homeenergy.ingestionservice.dto.EnergyUsageDto;
import com.homeenergy.ingestionservice.kafka.event.EnergyUsageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestionService {

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public void ingestEnergyUsage(EnergyUsageDto input) {
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

        kafkaTemplate.send("energy-usage", event);
        log.info("Energy usage event sent: {}", event);
    }
}
