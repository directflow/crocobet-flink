package com.crocobet.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Singleton wrapper of Properties with active profile
 */
public class Property {

    private static final Logger LOGGER = LoggerFactory.getLogger(Property.class);

    private static final String FILE_NAME = "application-%s.properties";

    private final Properties properties = new Properties();

    private static final Property property = new Property();

    /**
     * Reading property file on constructor call
     * Replace docker-compose environment properties if active profile is prod after load
     */
    private Property() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getPropertyFile())) {
            properties.load(inputStream);
            if (isProd()) {
                replace();
            }
        } catch (IOException io) {
            LOGGER.error(io.getMessage(), io);
        }
    }

    /**
     * Get Property instance
     *
     * @return Property
     */
    public static Property getInstance() {
        return property;
    }

    /**
     * Check is active.profile argument is prod
     *
     * @return Check result
     */
    private boolean isProd() {
        return getActiveProfile().equals("prod");
    }

    /**
     * Replace all properties from docker-compose if active profile is prod
     */
    private void replace() {
        properties.forEach(this::replaceEach);
    }

    /**
     * Check is property exists
     * Replace existing property from docker-compose
     *
     * @param key   Property key
     * @param value property value
     */
    private void replaceEach(Object key, Object value) {
        if (Objects.nonNull(System.getenv(value.toString()))) {
            properties.replace(key.toString(), System.getenv(value.toString()));
        }
    }

    /**
     * Get file by active profile name
     *
     * @return File name
     */
    private String getPropertyFile() {
        return String.format(FILE_NAME, getActiveProfile());
    }

    /**
     * Get active profile from docker-compose environment variable or set default as dev
     *
     * @return Env property
     */
    private String getActiveProfile() {
        return Optional.ofNullable(System.getenv("ACTIVE_PROFILE")).orElse("dev");
    }

    /**
     * Get property value by key
     *
     * @param key Property key
     * @return Property value
     */
    public String get(String key) {
        return properties.getProperty(key);
    }
}
