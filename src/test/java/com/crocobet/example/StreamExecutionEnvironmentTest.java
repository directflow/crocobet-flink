package com.crocobet.example;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StreamExecutionEnvironmentTest {

    private static StreamExecutionEnvironment streamExecutionEnvironment;

    @BeforeAll
    public static void beforeAll() {
        Configuration config = new Configuration();
        streamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config);
    }

    @AfterAll
    public static void afterAll() {
        Assertions.assertDoesNotThrow(() -> streamExecutionEnvironment.execute());
        Assertions.assertDoesNotThrow(() -> streamExecutionEnvironment.close());
    }

    @Test
    public void printTest() {

        Assertions.assertDoesNotThrow(() ->
                streamExecutionEnvironment
                        .fromElements(UUID.randomUUID().toString())
                        .print()
        );
    }
}
