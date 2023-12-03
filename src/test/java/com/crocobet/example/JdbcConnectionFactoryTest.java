package com.crocobet.example;

import com.crocobet.example.config.jdbc.JdbcConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JdbcConnectionFactoryTest {

    @Test
    public void createConnectionTest() {
        Assertions.assertDoesNotThrow(() -> Assertions.assertNotNull(JdbcConnectionFactory.createConnection()));
    }
}
