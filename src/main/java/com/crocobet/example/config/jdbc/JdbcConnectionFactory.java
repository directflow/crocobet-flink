package com.crocobet.example.config.jdbc;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcConnectionFactory {

    private static final Map<JdbcConnection, JdbcConnectionOptions> connections = new ConcurrentHashMap<>();

    /**
     * Create connection with custom JdbcConnection implementation
     *
     * @param jdbcConnection JdbcConnection implementation
     * @return JdbcConnectionOptions
     */
    public static JdbcConnectionOptions createConnection(JdbcConnection jdbcConnection) {
        return connections.computeIfAbsent(jdbcConnection, value -> jdbcConnection.jdbcConnectionOptions());
    }

    /**
     * Create connection with default Postgres JdbcConnection implementation
     *
     * @return JdbcConnectionOptions
     */
    public static JdbcConnectionOptions createConnection() {
        return createConnection(new PostgresJdbcConnection());
    }
}
