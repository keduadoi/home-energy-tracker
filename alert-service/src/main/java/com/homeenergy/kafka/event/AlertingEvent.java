package com.homeenergy.kafka.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertingEvent {
    private Long userId;
    private String message;
    private double threshold;
    private double energyConsumed;
    private String email;
}
