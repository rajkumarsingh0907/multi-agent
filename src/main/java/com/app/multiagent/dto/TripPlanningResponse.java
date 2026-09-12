package com.app.multiagent.dto;

public record TripPlanningResponse(
        TripRequest request,
        ResearchResult research,
        BudgetResult budget,
        ItineraryResult itinerary,
        ReviewResult review,
        String status
) {
}
