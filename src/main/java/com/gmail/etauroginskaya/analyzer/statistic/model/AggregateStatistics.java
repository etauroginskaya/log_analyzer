package com.gmail.etauroginskaya.analyzer.statistic.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AggregateStatistics {
    private static volatile AggregateStatistics mInstance;
    private Map<String, Integer> statistic = new HashMap<>();
    private Set<String> threadSet = new HashSet<>();
    private String resultFilePath;

    private AggregateStatistics() {
    }

    public static AggregateStatistics getInstance() {
        if (mInstance == null) {
            synchronized (AggregateStatistics.class) {
                if (mInstance == null) {
                    mInstance = new AggregateStatistics();
                }
            }
        }
        return mInstance;
    }

    public Map<String, Integer> getStatistic() {
        return statistic;
    }

    public void setStatistic(Map<String, Integer> statistic) {
        this.statistic = statistic;
    }

    public Set<String> getThreadSet() {
        return threadSet;
    }

    public String getResultFilePath() {
        return resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "---------------------" + System.lineSeparator() +
                "Aggregate Statistics:" + System.lineSeparator() + "---------------------" + System.lineSeparator() +
                getStatistic(statistic) +
                System.lineSeparator() + "Count of threads used to process files: " + threadSet.size() +
                System.lineSeparator() + "Path to output file: " + resultFilePath;
    }

    private String getStatistic(Map<String, Integer> statistic) {
        StringBuffer buffer = new StringBuffer();
        statistic.forEach((k, v) -> buffer.append(k + " - " + v + System.lineSeparator()));
        return buffer.toString();
    }
}