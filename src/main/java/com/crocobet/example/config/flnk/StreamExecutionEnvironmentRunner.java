package com.crocobet.example.config.flnk;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.function.Consumer;

public class StreamExecutionEnvironmentRunner {

    /**
     * Create StreamExecutionEnvironment instance on constructor call
     * Web UI is running on default 8081 port
     * Execute StreamExecutionEnvironment instance
     *
     * @param consumer Consumer of StreamExecutionEnvironment
     * @throws Exception On eny failure
     */
    public StreamExecutionEnvironmentRunner(Consumer<StreamExecutionEnvironment> consumer) throws Exception {
        Configuration config = new Configuration();
        try (StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config)) {
            consumer.accept(env);
            env.execute();
        }
    }
}
