package com.gmail.etauroginskaya.analyzer.config.impl;

import com.gmail.etauroginskaya.analyzer.config.ConfigurationManager;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfigurationManagerImpl implements ConfigurationManager {
    private static ConfigurationManagerImpl mInstance;

    private static final String BUNDLE_NAME = "app";
    private static final String DEFAULT_NUMBER_THREADS = "0";
    public static final String THREADS_COUNT = "threads.count";
    public static final String OUTPUT_FILENAME = "output.filename";
    public static final String DEFAULT_OUTPUT_FILENAME = "final.txt";
    public static final String OUTPUT_PATH = "output.path";

    private ResourceBundle resourceBundle;

    private ConfigurationManagerImpl() {
    }

    public static ConfigurationManagerImpl getInstance() {
        if (mInstance == null) {
            mInstance = new ConfigurationManagerImpl();
            mInstance.resourceBundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
        }
        return mInstance;
    }

    @Override
    public String getProperty(String key) {
        String value = (String) resourceBundle.getObject(key);
        if (System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism") == null) {
            String threadsCount = String.valueOf(resourceBundle.getObject(THREADS_COUNT));
            if (threadsCount.equals("") || !threadsCount.matches("[1-9]+")) {
                System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", DEFAULT_NUMBER_THREADS);
            } else System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                    String.valueOf(Integer.parseInt(threadsCount) - 1));
        }
        if (key.equals(OUTPUT_FILENAME) && value.equals("")) {
            return DEFAULT_OUTPUT_FILENAME;
        }
        if (key.equals(OUTPUT_PATH) && value.equals("")) {
            return System.getProperty("user.home");
        }
        return value;
    }
}