package com.crocobet.example.config;

public class SystemProperty {

    /**
     * Read active profile from args
     * Set active profile name to system properties
     *
     * @param args Run args
     */
    public static void set(String[] args) {
        System.setProperty("active.profile", args[0]);
    }

    /**
     * Get active profile name
     *
     * @return Active profile name
     */
    public static String get() {
        return System.getProperty("active.profile");
    }
}
