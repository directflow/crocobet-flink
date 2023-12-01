package com.crocobet.example.config.jdbc;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

public interface JdbcConnection {
    JdbcConnectionOptions jdbcConnectionOptions();
}
