package com.app.multiagent.dto;

import java.math.BigDecimal;

public record TripRequest(
        String destination,
        int numberOfDays,
        BigDecimal budget,
        String preferences
) {
}
