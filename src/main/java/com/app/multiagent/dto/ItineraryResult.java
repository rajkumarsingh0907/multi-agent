package com.app.multiagent.dto;

import java.math.BigDecimal;
import java.util.List;

public record ItineraryResult(List<DayPlan> days) {

    public record DayPlan(
            int day,
            String morning,
            String afternoon,
            String evening,
            BigDecimal estimatedCost
    ) {}

}
