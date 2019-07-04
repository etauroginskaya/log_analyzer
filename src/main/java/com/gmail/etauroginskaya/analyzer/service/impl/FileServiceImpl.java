package com.gmail.etauroginskaya.analyzer.service.impl;

import com.gmail.etauroginskaya.analyzer.service.model.FilterTypesEnum;
import com.gmail.etauroginskaya.analyzer.config.impl.ConfigurationManagerImpl;
import com.gmail.etauroginskaya.analyzer.filter.FilterFactory;
import com.gmail.etauroginskaya.analyzer.service.FileService;
import com.gmail.etauroginskaya.analyzer.statistic.model.AggregateStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileServiceImpl implements FileService {
    private static FileServiceImpl mInstance;

    private FileServiceImpl() {
    }

    public static FileServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new FileServiceImpl();
        }
        return mInstance;
    }

    @Override
    public void filterFiles(FilterTypesEnum typeFilter, Map<String, Object> inputOptions) {
        List<String> filesNames = getLogsNames(ConfigurationManagerImpl.getInstance().getProperty("input.path"));
        try {
            String finalFileName = createFile();
            AggregateStatistics.getInstance().setResultFilePath(finalFileName);
            filesNames.parallelStream()
                    .forEach(fileName ->
                            FilterFactory.getInstance()
                                    .getFilter(typeFilter).filter(fileName, inputOptions, finalFileName));
            StatisticServiceImpl.getInstance().printStatistic();
            StatisticServiceImpl.getInstance().writeStatisticToFile();
            StatisticServiceImpl.getInstance().clearStatistic();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private List<String> getLogsNames(String dir) {
        List fileNamesList = new ArrayList();
        try {
            Files.newDirectoryStream(Paths.get(dir), path -> path.toString().toLowerCase()
                    .endsWith(ConfigurationManagerImpl.getInstance().getProperty("filter.file.extension")))
                    .forEach(filePath -> fileNamesList.add(filePath.toString()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return fileNamesList;
    }

    private String createFile() throws IOException {
        String dir = ConfigurationManagerImpl.getInstance().getProperty("output.path");
        Path path = Paths.get(dir);
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        path = Paths.get(dir.concat("\\".concat(ConfigurationManagerImpl.getInstance().getProperty("output.filename"))));
        int i = 0;
        while (Files.exists(path)) {
            path = Paths.get(path.toString().replace(".", ++i + "."));
        }
        Files.createFile(path);
        return path.toString();
    }
}