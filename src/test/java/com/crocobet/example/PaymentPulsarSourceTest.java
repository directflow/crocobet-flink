package com.crocobet.example;

import com.crocobet.example.config.pulsar.PaymentPulsarSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentPulsarSourceTest {

    @Test
    public void buildSourceTest() {
        Assertions.assertDoesNotThrow(() -> Assertions.assertNotNull(PaymentPulsarSource.build()));
    }
}
