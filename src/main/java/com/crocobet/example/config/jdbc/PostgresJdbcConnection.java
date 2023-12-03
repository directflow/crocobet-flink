package com.crocobet.example.config.jdbc;

import com.crocobet.example.config.Property;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;

@Getter
@EqualsAndHashCode
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
}
