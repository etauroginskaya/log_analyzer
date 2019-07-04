package com.gmail.etauroginskaya.analyzer.filter.impl;

import com.gmail.etauroginskaya.analyzer.filter.Filter;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.INPUT_END_TIME;
import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.INPUT_START_TIME;

public class FilterByTimePeriod implements Filter {
    private static final int NUMBER_INDEX_LOG_TIME = 0;

    @Override
    public void filter(String inputFileName, Map<String, Object> inputOptions, String resultFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName, true))) {
            Files.lines(Paths.get(inputFileName))
                    .filter(line -> includedInRange(inputOptions,
                            parseInData(line.split("\\|")[NUMBER_INDEX_LOG_TIME])))
                    .peek(line -> writeInResultFile(bw, line))
                    .forEach(line -> includeInStatistic(inputOptions, line));
            AggregateStatistics.getInstance().getThreadSet().add(Thread.currentThread().getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    LocalDate parseInData(Object object) {
        return LocalDate.parse(object.toString());
    }

    boolean includedInRange(Map<String, Object> inputOptions, LocalDate checkData) {
        LocalDate startPeriod = parseInData(inputOptions.get(INPUT_START_TIME));
        LocalDate endPeriod = parseInData(inputOptions.get(INPUT_END_TIME));
        return ((checkData.isAfter(startPeriod) && checkData.isBefore(endPeriod)) ||
                checkData.equals(startPeriod) ||
                checkData.equals(endPeriod));
    }
}