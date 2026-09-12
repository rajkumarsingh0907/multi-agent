package com.app.multiagent.dto;

import java.math.BigDecimal;
import java.util.List;

public record BudgetResult(
        BigDecimal accommodationCost,
        BigDecimal transportCost,
        BigDecimal foodCost,
        BigDecimal activityCost,
        BigDecimal totalCost,
        boolean withinBudget,
        List<String> recommendations) {
}
