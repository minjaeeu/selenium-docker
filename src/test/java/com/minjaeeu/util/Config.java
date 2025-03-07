package com.minjaeeu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final static Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() {
        properties = loadProperties();
        // checks for cli overrides
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }
        //prints all the loaded properties (overridden or not)
        log.info("Test properties");
        log.info("***************************");
        for (String key : properties.stringPropertyNames()) {
            log.info("key: {}, value {}", key, properties.getProperty(key));
        }
        log.info("***************************");
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        properties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Unable to read file {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}
