package com.gmail.etauroginskaya.analyzer.statistic.impl;

import com.gmail.etauroginskaya.analyzer.statistic.Statistic;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.util.Map;

public class StatisticByUsername implements Statistic {
    private static final int NUMBER_INDEX_LOG_USERNAME = 2;

    @Override
    public void includeInAggregateStatistic(Map<String, Object> statisticOptions, String line) {
        String currentName = line.split("\\|")[NUMBER_INDEX_LOG_USERNAME];
        AggregateStatistics.getInstance()
                .getStatistic()
                .put(currentName, AggregateStatistics.getInstance().getStatistic().getOrDefault(currentName, 0) + 1);
    }
}
