package com.crocobet.example.config.jdbc;

import com.crocobet.example.config.Property;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

import java.util.Objects;

public class PostgresJdbcConnection implements JdbcConnection {

    private final String url = Property.getInstance().get("datasource.url");

    private final String username = Property.getInstance().get("datasource.username");

    private final String password = Property.getInstance().get("datasource.password");

    /**
     * Jdbc connection builder
     *
     * @return JdbcConnectionOptions
     */
    @Override
    public JdbcConnectionOptions jdbcConnectionOptions() {
        return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withDriverName("org.postgresql.Driver")
                .withUrl(url)
                .withUsername(username)
                .withPassword(password)
                .build();
    }

    /**
     * Override hashCode for correct working in map
     *
     * @return int of hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(hashCodeBuilder());
    }

    /**
     * Override toString for correct working in map
     *
     * @return Concatenated string
     */
    @Override
    public String toString() {
        return hashCodeBuilder();
    }

    /**
     * Concatenate fields
     *
     * @return Concatenated string
     */
    private String hashCodeBuilder() {
        return (url + username + password);
    }
}
