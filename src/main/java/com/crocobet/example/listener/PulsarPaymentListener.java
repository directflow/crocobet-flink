package com.crocobet.example.listener;

import com.crocobet.example.config.flnk.PaymentPulsarSource;
import com.crocobet.example.domain.Payment;
import com.crocobet.example.function.PaymentSinkFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PulsarPaymentListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PulsarPaymentListener.class);

    private final StreamExecutionEnvironment streamExecutionEnvironment;

    /**
     * Dependency Injection of StreamExecutionEnvironment in constructor
     *
     * @param streamExecutionEnvironment StreamExecutionEnvironment instance
     */
    public PulsarPaymentListener(StreamExecutionEnvironment streamExecutionEnvironment) {
        this.streamExecutionEnvironment = streamExecutionEnvironment;
    }

    /**
     * Create pulsar source
     *
     * @return PulsarSource of Payment
     */
    private PulsarSource<Payment> getSource() {
        return PaymentPulsarSource.build();
    }

    /**
     * Create DataStream of payment from pulsar source without watermarks
     *
     * @return DataStream of payment
     */
    private DataStream<Payment> getDataStream() {
        return streamExecutionEnvironment
                .fromSource(getSource(), WatermarkStrategy.noWatermarks(), "Pulsar Payment Source")
                .name("PulsarPaymentSource")
                .uid("PulsarPaymentSource");
    }

    /**
     * Listen new Payment from pulsar source
     * Transform Payment object with simple UUID string
     * Send to sink transformed payment object
     * Save transformed payment object to db
     */
    public void run() {

        getDataStream()
                .map(payment -> {
                    payment.setFlinkStream("Flink random:" + UUID.randomUUID());
                    LOGGER.info(payment.toString());
                    return payment;
                })
                .addSink(PaymentSinkFunction.insert())
                .uid("PulsarPaymentDataStream")
                .name("PulsarPaymentDataStream");
    }
}
