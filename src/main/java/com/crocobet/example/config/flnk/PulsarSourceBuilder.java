package com.crocobet.example.config.flnk;

import com.crocobet.example.config.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.connector.pulsar.source.enumerator.cursor.StartCursor;
import org.apache.pulsar.client.impl.schema.JSONSchema;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class PulsarSourceBuilder {

    private final String topicName;

    private final String subscriptionName;

    /**
     * Build pulsar source by params
     *
     * @param cls              Deserialization class type
     * @param <T>              Generic type
     * @return PulsarSource with generic type
     */
    public <T> PulsarSource<T> build(Class<T> cls) {
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
