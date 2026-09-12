package com.app.multiagent.dto;

import java.util.List;

public record ReviewResult(
        boolean valid,
        String summary,
        List<String> issues,
        List<String> recommendations
) {
}
