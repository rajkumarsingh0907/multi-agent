package com.app.multiagent.dto;

public record TripState(
        TripRequest request,
        ResearchResult research,
        BudgetResult budget,
        ItineraryResult itinerary
) {

    public static TripState start(TripRequest request) {

        return new TripState(request, null, null, null);
    }

    public TripState withResearch(ResearchResult value) {

        return new TripState(request, value, budget, itinerary);
    }

    public TripState withBudget(BudgetResult value) {

        return new TripState(request, research, value, itinerary);
    }

    public TripState withItinerary(ItineraryResult value) {

        return new TripState(request, research, budget, value);
    }
}
