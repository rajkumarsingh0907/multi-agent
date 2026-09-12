package com.app.multiagent.service.impl;

import com.app.multiagent.dto.ReviewResult;
import com.app.multiagent.dto.TripState;
import com.app.multiagent.service.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ReviewAgent implements Agent<TripState, ReviewResult> {

    private static final Logger log =
            LoggerFactory.getLogger(ReviewAgent.class);

    private final ChatClient chatClient;

    public ReviewAgent(
            @Qualifier("reviewerChatClient") ChatClient chatClient
    ) {
        this.chatClient = chatClient;
    }

    @Override
    public String name() {
        return "review-agent";
    }

    @Override
    public ReviewResult execute(TripState state) {
        log.info("Agent started: {}", name());

        long startTime = System.currentTimeMillis();

        ReviewResult result = chatClient
                .prompt()
                .user(user -> user
                        .text("""
                            Review the following trip plan.

                            Original request:
                            {request}

                            Research:
                            {research}

                            Budget:
                            {budget}

                            Itinerary:
                            {itinerary}

                            Verify:
                            - The itinerary has the correct number of days.
                            - The total cost is within budget.
                            - The activities are practical.
                            - The itinerary is consistent with the research.

                            Return:
                            - Whether the plan is valid
                            - A concise summary
                            - Any issues
                            - Recommendations
                            """)
                        .param("request", state.request())
                        .param("research", state.research())
                        .param("budget", state.budget())
                        .param("itinerary", state.itinerary()))
                .call()
                .entity(ReviewResult.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        log.info(
                "Agent completed: {}, executionTimeMs={}, result={}",
                name(),
                executionTime,
                result
        );

        return result;
    }
}
