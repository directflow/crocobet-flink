package com.crocobet.example.function;

import com.crocobet.example.config.JdbcConnectionFactory;
import com.crocobet.example.config.JdbcExecutionFactory;
import com.crocobet.example.domain.Payment;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class PaymentSinkFunction {

    /**
     * Jdbc payment sink
     * Insert payment entity received from main example app via pulsar
     *
     * @return SinkFunction<Payment>
     */
    public static SinkFunction<Payment> insert() {
        return JdbcSink.sink(
                "insert into payments (transaction_id, created_at, flink_stream, amount) values (?, ?, ?, ?)",
                (statement, payment) -> {
                    statement.setLong(1, payment.getTransactionId());
                    statement.setLong(2, payment.getCreatedAt());
                    statement.setString(3, payment.getFlinkStream());
                    statement.setDouble(4, payment.getAmount());
                },
                JdbcExecutionFactory.createExecution(),
                JdbcConnectionFactory.createConnection()
        );
    }
}
