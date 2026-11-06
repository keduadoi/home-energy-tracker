package com.homeenergy.insightservice.service;

import com.homeenergy.insightservice.client.UsageClient;
import com.homeenergy.insightservice.dto.DeviceDto;
import com.homeenergy.insightservice.dto.InsightDto;
import com.homeenergy.insightservice.dto.UsageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsightService {

    private final UsageClient usageClient;
    private final OllamaChatModel ollamaChatModel;

    public InsightDto getSavingTips(Long userId) {
        // Fetch data from Usage Service
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info ("Calling Ollama for userId {} with total usage {}",
                userId, totalUsage);

        String prompt = "This is my total consumption over the past 3 days." +
                "How can I reduce my energy consumption? How does it compare to average households?" +
                "Total energu used: \n" +
                totalUsage;

        ChatResponse response = ollamaChatModel.call(
                Prompt.builder()
                        .content(prompt)
                        .build());

        return InsightDto.builder()
                .userId(userId)
                .tips(response.getResult().getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }

    public InsightDto getOverview(Long userId) {
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info("Calling Ollama for userId {} with total usage {}", userId, totalUsage);

        String prompt = "Analyse the following energy usage data and provide a " +
                "concise overview with actionable insights." +
                "This data is the aggregate data for the past 3 days." +
                "Usage Data: \n" +
                usageData.devices();

        ChatResponse response = ollamaChatModel.call(
                Prompt.builder()
                        .content(prompt)
                        .build()
        );

        return InsightDto.builder()
                .userId(userId)
                .tips(response.getResult().getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }
}
