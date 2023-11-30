package com.crocobet.example.config;

import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.connector.pulsar.source.enumerator.cursor.StartCursor;
import org.apache.pulsar.client.impl.schema.JSONSchema;

public class PulsarSourceBuilder {

    /**
     * Build pulsar source by params
     *
     * @param topicName        Pulsar topic name
     * @param subscriptionName Pulsar subscription name
     * @param cls              Deserialization class type
     * @param <T>              Generic type
     * @return PulsarSource with generic type
     */
    public static <T> PulsarSource<T> build(String topicName, String subscriptionName, Class<T> cls) {
        return PulsarSource.builder()
                .setServiceUrl(Property.getInstance().get("pulsar.client.service-url"))
                .setAdminUrl(Property.getInstance().get("pulsar.administration.service-url"))
                .setStartCursor(StartCursor.latest())
                .setTopics(topicName)
                .setDeserializationSchema(JSONSchema.of(cls), cls)
                .setSubscriptionName(subscriptionName)
                .setConsumerName(subscriptionName)
                .build();
    }
}
