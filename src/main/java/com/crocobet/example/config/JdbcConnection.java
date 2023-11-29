package com.crocobet.example.config;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;

public class JdbcConnection {

    public static JdbcExecutionOptions jdbcExecutionOptions() {
        return JdbcExecutionOptions.builder()
                .withBatchSize(1000)
                .withBatchIntervalMs(200)
                .withMaxRetries(5)
                .build();
    }

    public static JdbcConnectionOptions jdbcConnectionOptions() {
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withDriverName("org.postgresql.Driver")
                .withUrl(Property.getInstance().get("datasource.url"))
                .withUsername(Property.getInstance().get("datasource.username"))
                .withPassword(Property.getInstance().get("datasource.password"))
                .build();
    }
}
