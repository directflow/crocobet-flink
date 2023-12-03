package com.crocobet.example.config.pulsar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PulsarSourceFactory {

    private static final Map<String, PulsarSourceBuilder> sources = new ConcurrentHashMap<>();

    /**
     * Create source by topic and subscription name
     *
     * @param topicName        Topic name
     * @param subscriptionName Subscription name
     * @return PulsarSourceBuilder
     */
    public static PulsarSourceBuilder createSource(String topicName, String subscriptionName) {
        return sources.computeIfAbsent(topicName, value -> new PulsarSourceBuilder(topicName, subscriptionName));
    }
}
