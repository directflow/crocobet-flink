package com.crocobet.example.config.flnk;

import com.crocobet.example.config.Property;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.connector.pulsar.source.enumerator.cursor.StartCursor;
import org.apache.pulsar.client.impl.schema.JSONSchema;

import java.util.Objects;

@Data
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

    /**
     * Override hashCode for correct working in map
     *
     * @return int of hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(topicName + subscriptionName);
    }

    /**
     * Override equals for correct working in map
     *
     * @return Boolean result
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PulsarSourceBuilder)) {
            return false;
        }
        PulsarSourceBuilder pulsarSourceBuilder = (PulsarSourceBuilder) obj;
        return pulsarSourceBuilder.getTopicName().equals(this.topicName)
                && pulsarSourceBuilder.getSubscriptionName().equals(this.getSubscriptionName());
    }
}
