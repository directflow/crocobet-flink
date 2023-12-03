package com.crocobet.example.stream;

import com.crocobet.example.config.flnk.PaymentPulsarSource;
import com.crocobet.example.domain.Payment;
import com.crocobet.example.function.PaymentSinkFunction;
import lombok.RequiredArgsConstructor;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.pulsar.source.PulsarSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.UUID;

@RequiredArgsConstructor
public class PulsarPaymentStream {

    private final StreamExecutionEnvironment streamExecutionEnvironment;

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
                    System.out.println(payment);
                    return payment;
                })
                .addSink(PaymentSinkFunction.insert())
                .uid("PulsarPaymentDataStream")
                .name("PulsarPaymentDataStream");
    }
}
