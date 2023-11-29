package com.crocobet.example.listener;

import com.crocobet.example.config.ExecutionEnvironment;
import com.crocobet.example.config.PaymentSinkFunction;
import com.crocobet.example.config.Property;
import com.crocobet.example.config.PulsarSourceBuilder;
import com.crocobet.example.domain.Payment;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PulsarPaymentListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PulsarPaymentListener.class);

    private static PulsarSource<Payment> getSource() {
        return PulsarSourceBuilder.build(
                Property.getInstance().get("pulsar.payment-topic-name"),
                Property.getInstance().get("pulsar.payment-consumer-name"),
                Payment.class);
    }

    private static DataStream<Payment> getDataStream() {
        return ExecutionEnvironment.getInstance().getEnvironment()
                .fromSource(getSource(), WatermarkStrategy.noWatermarks(), "Pulsar Payment Source")
                .name("pulsarPaymentSource")
                .uid("pulsarPaymentSource");
    }

    public static void listen() {

        DataStream<Payment> paymentDataStream = getDataStream();

        paymentDataStream
                .map(payment -> {
                    payment.setFlinkStream("Flink random:" + UUID.randomUUID());
                    LOGGER.info(payment.toString());
                    return payment;
                })
                .addSink(PaymentSinkFunction.insert())
                .uid("pulsarPaymentDataStream")
                .name("pulsarPaymentDataStream");
    }
}
