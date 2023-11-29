package com.crocobet.example.config;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Objects;

public class ExecutionEnvironment {

    private static ExecutionEnvironment executionEnvironment;


    private final StreamExecutionEnvironment streamExecutionEnvironment;

    private ExecutionEnvironment() {
        Configuration configuration = new Configuration();
        streamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
    }

    public static ExecutionEnvironment getInstance() {

        if(Objects.isNull(executionEnvironment)) {
            executionEnvironment = new ExecutionEnvironment();
        }

        return executionEnvironment;
    }

    public StreamExecutionEnvironment getEnvironment() {
        return streamExecutionEnvironment;
    }

    public void execute() throws Exception {
        streamExecutionEnvironment.execute("Payment Enrichment executor");
    }
}
