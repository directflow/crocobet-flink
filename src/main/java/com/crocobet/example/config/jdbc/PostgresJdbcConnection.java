package com.crocobet.example.config.jdbc;

import com.crocobet.example.config.Property;
import lombok.Getter;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

import java.util.Objects;

@Getter
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
        return Objects.hashCode((url + username + password));
    }

    /**
     * Override equals for correct working in map
     *
     * @return Boolean result
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostgresJdbcConnection)) {
            return false;
        }
        PostgresJdbcConnection postgresJdbcConnection = (PostgresJdbcConnection) obj;
        return postgresJdbcConnection.getUrl().equals(this.url)
                && postgresJdbcConnection.getUsername().equals(this.username)
                && postgresJdbcConnection.getPassword().equals(this.password);
    }
}
