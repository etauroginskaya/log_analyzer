package com.gmail.etauroginskaya.analyzer.filter;

import com.gmail.etauroginskaya.analyzer.statistic.model.StatisticTypesEnum;
import com.gmail.etauroginskaya.analyzer.statistic.StatisticFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.STATISTIC_OPTIONS;
import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.STATISTIC_TYPE;

public interface Filter {
    void filter(String inputFileName, Map<String, Object> inputOptions, String resultFileName);

    default void writeInResultFile(BufferedWriter bw, String line) {
        try {
            bw.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    default void includeInStatistic(Map<String, Object> inputOptions, String line) {
        StatisticFactory.getInstance()
                .getStatistic(StatisticTypesEnum.valueOf(inputOptions.get(STATISTIC_TYPE).toString()))
                .includeInAggregateStatistic((Map<String, Object>) inputOptions.get(STATISTIC_OPTIONS), line);
    }
}