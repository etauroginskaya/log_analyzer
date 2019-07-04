package com.gmail.etauroginskaya.analyzer.statistic;

import java.util.Map;

public interface Statistic {
    void includeInAggregateStatistic(Map<String, Object> statisticOptions, String line);
}
