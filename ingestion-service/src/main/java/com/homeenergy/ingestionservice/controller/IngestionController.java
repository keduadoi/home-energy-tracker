package com.homeenergy.ingestionservice.controller;

import com.homeenergy.ingestionservice.dto.EnergyUsageDto;
import com.homeenergy.ingestionservice.service.IngestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestion")
@RequiredArgsConstructor
@Slf4j
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto energyUsageDto) {
        ingestionService.ingestEnergyUsage(energyUsageDto);
    }
}
