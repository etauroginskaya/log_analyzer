package com.gmail.etauroginskaya.analyzer.statistic.impl;

import com.gmail.etauroginskaya.analyzer.statistic.Statistic;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.STATISTIC_TIME_UNIT;

public class StatisticByTimeUnit implements Statistic {
    private static final int NUMBER_INDEX_LOG_TIME = 0;

    @Override
    public void includeInAggregateStatistic(Map<String, Object> statisticOptions, String line) {
        String currentTime = line.split("\\|")[NUMBER_INDEX_LOG_TIME];
        switch (statisticOptions.get(STATISTIC_TIME_UNIT).toString()) {
            case "ONE_DAY": {
                AggregateStatistics.getInstance()
                        .getStatistic()
                        .put(currentTime, AggregateStatistics.getInstance().getStatistic().getOrDefault(currentTime, 0) + 1);
            }
            break;
            case "ONE_MONTH": {
                currentTime = LocalDate.parse(currentTime).format(DateTimeFormatter.ofPattern("yyyy-MM"));
                AggregateStatistics.getInstance()
                        .getStatistic()
                        .put(currentTime, AggregateStatistics.getInstance()
                                .getStatistic().getOrDefault(currentTime, 0) + 1);
            }
            break;
            case "ONE_YEAR": {
                currentTime = LocalDate.parse(currentTime).format(DateTimeFormatter.ofPattern("yyyy"));
                AggregateStatistics.getInstance()
                        .getStatistic()
                        .put(currentTime, AggregateStatistics.getInstance()
                                .getStatistic().getOrDefault(currentTime, 0) + 1);
            }
            break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
