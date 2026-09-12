package com.app.multiagent.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfiguration {

    @Bean
    ChatClient researchChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                         You are a travel research agent.
                                                
                         Your only responsibility is to identify useful places,
                         food options and practical travel information.
                                                
                         Do not create the final itinerary.
                         Do not perform budget calculations.
                         Return factual and concise information.
                        """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    ChatClient budgetChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                    You are a travel budget agent.

                    Estimate realistic costs using the supplied research.
                    Never silently exceed the user's budget.
                    Clearly indicate whether the proposed trip is affordable.
                    """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    ChatClient itineraryChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                    You are an itinerary planning agent.

                    Create a realistic day-by-day plan using only the supplied
                    research and budget constraints.

                    Avoid impossible travel times and budget violations.
                    """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    ChatClient reviewerChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                    You are the final review agent.

                    Review the research, budget and itinerary.
                    Correct inconsistencies and produce a clear final answer.

                    Never invent missing prices or attractions.
                    """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
