package com.crocobet.example.config;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcConnectionFactory {

    private static final String defaultUnitName = "default";

    private static final Map<String, JdbcConnectionOptions> connections = new ConcurrentHashMap<>();

    /**
     * Create connection with custom unit name
     *
     * @param unitName Unit name
     * @return JdbcConnectionOptions
     */
    public static JdbcConnectionOptions createConnection(String unitName) {
        return connections.computeIfAbsent(unitName, value -> jdbcConnectionOptions());
    }

    /**
     * Create connection with default unit name
     *
     * @return JdbcConnectionOptions
     */
    public static JdbcConnectionOptions createConnection() {
        return createConnection(defaultUnitName);
    }

    /**
     * Jdbc connection builder
     *
     * @return JdbcConnectionOptions
     */
    private static JdbcConnectionOptions jdbcConnectionOptions() {
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withDriverName("org.postgresql.Driver")
                .withUrl(Property.getInstance().get("datasource.url"))
                .withUsername(Property.getInstance().get("datasource.username"))
                .withPassword(Property.getInstance().get("datasource.password"))
                .build();
    }
}
