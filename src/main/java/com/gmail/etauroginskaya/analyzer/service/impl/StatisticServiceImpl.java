package com.gmail.etauroginskaya.analyzer.service.impl;

import com.gmail.etauroginskaya.analyzer.service.StatisticService;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StatisticServiceImpl implements StatisticService {
    private static StatisticServiceImpl mInstance;

    private StatisticServiceImpl() {
    }

    public static StatisticServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new StatisticServiceImpl();
        }
        return mInstance;
    }

    @Override
    public void printStatistic() {
        System.out.println(AggregateStatistics.getInstance().toString());
    }

    @Override
    public void writeStatisticToFile() {
        try {
            try (FileWriter fw = new FileWriter(AggregateStatistics.getInstance().getResultFilePath(), true)) {
                fw.write(AggregateStatistics.getInstance().toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void clearStatistic() {
        AggregateStatistics.getInstance().getStatistic().clear();
        AggregateStatistics.getInstance().getThreadSet().clear();
    }
}
