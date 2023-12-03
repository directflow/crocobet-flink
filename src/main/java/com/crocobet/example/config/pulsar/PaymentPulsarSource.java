package com.crocobet.example.config.pulsar;

import com.crocobet.example.config.Property;
import com.crocobet.example.config.pulsar.PulsarSourceFactory;
import com.crocobet.example.domain.Payment;
import org.apache.flink.connector.pulsar.source.PulsarSource;

public class PaymentPulsarSource {

    /**
     * Build pulsar source from factory
     *
     * @return PulsarSource of Payment
     */
    public static PulsarSource<Payment> build() {
        return PulsarSourceFactory
                .createSource(
                        Property.getInstance().get("pulsar.payment-topic-name"),
                        Property.getInstance().get("pulsar.payment-consumer-name")
                )
                .build(Payment.class);
    }
}
