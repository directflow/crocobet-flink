package com.crocobet.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Property {

    private static final Logger LOGGER = LoggerFactory.getLogger(Property.class);

    private static final String FILE_NAME = "application-%s.properties";

    private final Properties properties = new Properties();

    private static Property instance = new Property();

    private Property() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getActiveProfile())) {
            properties.load(inputStream);

            if (isProd()) {
                replace();
            }

        } catch (IOException io) {
            LOGGER.error(io.getMessage(), io);
        }
    }

    public static Property getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Property();
        }
        return instance;
    }

    private boolean isProd() {
        return SystemProperty.get().equals("prod");
    }

    private void replace() {
        properties.forEach(this::replaceEach);
    }

    private void replaceEach(Object key, Object value) {
        properties.replace(key, System.getProperty(value.toString(), value.toString()));
    }

    private String getActiveProfile() {
        return String.format(FILE_NAME, SystemProperty.get());
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
