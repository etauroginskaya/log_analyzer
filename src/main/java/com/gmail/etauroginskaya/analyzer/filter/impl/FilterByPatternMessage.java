package com.gmail.etauroginskaya.analyzer.filter.impl;

import com.gmail.etauroginskaya.analyzer.filter.Filter;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.INPUT_MESSAGE;

public class FilterByPatternMessage implements Filter {
    private static final int NUMBER_INDEX_LOG_MESSAGE = 3;

    @Override
    public void filter(String inputFileName, Map<String, Object> inputOptions, String resultFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName, true))) {
            Files.lines(Paths.get(inputFileName))
                    .filter(line -> line.split("\\|")[NUMBER_INDEX_LOG_MESSAGE]
                            .contains(inputOptions.get(INPUT_MESSAGE).toString()))
                    .peek(line -> writeInResultFile(bw, line))
                    .forEach(line -> includeInStatistic(inputOptions, line));
            AggregateStatistics.getInstance().getThreadSet().add(Thread.currentThread().getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}