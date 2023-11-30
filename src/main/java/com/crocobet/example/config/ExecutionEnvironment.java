package com.crocobet.example.config;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Singleton wrapper of StreamExecutionEnvironment
 */
public class ExecutionEnvironment {

    private static final ExecutionEnvironment executionEnvironment = new ExecutionEnvironment();

    private final StreamExecutionEnvironment streamExecutionEnvironment;

    /**
     * Create StreamExecutionEnvironment instance on constructor call
     * Web UI is running on default 8081 port
     */
    private ExecutionEnvironment() {
        Configuration configuration = new Configuration();
        streamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
    }

    /**
     * Get ExecutionEnvironment instance
     *
     * @return ExecutionEnvironment
     */
    public static ExecutionEnvironment getInstance() {
        return executionEnvironment;
    }

    /**
     * Get StreamExecutionEnvironment
     *
     * @return StreamExecutionEnvironment
     */
    public StreamExecutionEnvironment getEnvironment() {
        return streamExecutionEnvironment;
    }

    /**
     * Execute StreamExecutionEnvironment
     *
     * @throws Exception On error
     */
    public void execute() throws Exception {
        streamExecutionEnvironment.execute("Payment Enrichment executor");
    }
}
