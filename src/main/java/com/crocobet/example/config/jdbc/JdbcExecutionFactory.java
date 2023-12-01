package com.crocobet.example.config.jdbc;

import org.apache.flink.connector.jdbc.JdbcExecutionOptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcExecutionFactory {

    private static final Integer defaultBatchSize = 1000;

    private static final Map<Integer, JdbcExecutionOptions> executions = new ConcurrentHashMap<>();

    /**
     * Create execution with custom batch size
     *
     * @param batchSize Batch size
     * @return JdbcExecutionOptions
     */
    public static JdbcExecutionOptions createExecution(Integer batchSize) {
        return executions.computeIfAbsent(batchSize, value -> jdbcExecutionOptions(batchSize));
    }

    /**
     * Create execution with default batch size
     *
     * @return JdbcExecutionOptions
     */
    public static JdbcExecutionOptions createExecution() {
        return createExecution(defaultBatchSize);
    }

    /**
     * Jdbc batch options
     *
     * @param batchSize Batch size
     * @return JdbcExecutionOptions
     */
    public static JdbcExecutionOptions jdbcExecutionOptions(Integer batchSize) {
        return JdbcExecutionOptions.builder()
                .withBatchSize(batchSize)
                .withBatchIntervalMs(200)
                .withMaxRetries(5)
                .build();
    }
}
