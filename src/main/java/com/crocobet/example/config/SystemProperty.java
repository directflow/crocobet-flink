package com.crocobet.example.config;

public class SystemProperty {
    public static void set(String[] args) {
        System.setProperty("active.profile", args[0]);
    }

    public static String get() {
        return System.getProperty("active.profile");
    }
}
