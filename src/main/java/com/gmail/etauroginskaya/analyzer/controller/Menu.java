package com.gmail.etauroginskaya.analyzer.controller;

import com.gmail.etauroginskaya.analyzer.controller.model.MenuEntry;
import com.gmail.etauroginskaya.analyzer.controller.validation.impl.ValidationServiceImpl;
import com.gmail.etauroginskaya.analyzer.service.model.FilterTypesEnum;
import com.gmail.etauroginskaya.analyzer.service.impl.FileServiceImpl;
import com.gmail.etauroginskaya.analyzer.statistic.model.StatisticTypesEnum;
import com.gmail.etauroginskaya.analyzer.statistic.model.TimeUnitEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.gmail.etauroginskaya.analyzer.constants.InputOptionsConstant.*;

public class Menu {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<MenuEntry> entries = new ArrayList<>();
    private boolean isExit = false;

    public Menu() {
        entries.add(new MenuEntry("Filter by username") {
            @Override
            public void run() {
                System.out.println("\nPlease enter username to filter:");
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    Map<String, Object> options = new HashMap<>();
                    options.put(INPUT_USERNAME, scanner.nextLine());
                    FileServiceImpl.getInstance().filterFiles(FilterTypesEnum.USERNAME, checkStatisticType(options));
                }
            }
        });
        entries.add(new MenuEntry("Filter by time period") {
            @Override
            public void run() {
                System.out.println("\nPlease enter start period time to filter, for example " + LocalDate.now() + ":");
                Map<String, Object> options = new HashMap<>();
                Scanner scanner = new Scanner(System.in);
                String input;
                if (scanner.hasNext()) {
                    input = scanner.next();
                    input = ValidationServiceImpl.getInstance().validateInputData(input);
                    options.put(INPUT_START_TIME, LocalDate.parse(input));
                }
                System.out.println("\nPlease enter end period time to filter, for example " + LocalDate.now() + ":");
                scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    input = scanner.next();
                    input = ValidationServiceImpl.getInstance().validateInputData(input);
                    options.put(INPUT_END_TIME, LocalDate.parse(input));
                    FileServiceImpl.getInstance().filterFiles(FilterTypesEnum.TIME_PERIOD, checkStatisticType(options));
                }
            }
        });
        entries.add(new MenuEntry("Filter by pattern for custom message") {
            @Override
            public void run() {
                System.out.println("\nPlease enter pattern for custom message:");
                Map<String, Object> options = new HashMap<>();
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    options.put(INPUT_MESSAGE, scanner.nextLine());
                    FileServiceImpl.getInstance()
                            .filterFiles(FilterTypesEnum.PATTERN_FOR_CUSTOM_MESSAGE, checkStatisticType(options));
                }
            }
        });
        entries.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        while (!isExit) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choiceMenuNumber = scanner.nextInt();
                if (choiceMenuNumber <= entries.size() && choiceMenuNumber > 0) {
                    MenuEntry entry = entries.get(--choiceMenuNumber);
                    entry.run();
                } else {
                    System.out.println("\nAttention! Selected non-existent action.");
                }
            } else {
                System.out.println("\nAttention! Invalid format.");
            }
        }
    }

    private void printMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n----------\nMenu:\n----------\n");
        int i = 0;
        for (MenuEntry entry : entries) {
            buffer.append(String.format(MENU_PATTERN, (++i), entry.getTitle()));
        }
        buffer.append("\nPlease enter action number:\n");
        System.out.print(buffer.toString());
    }

    private Map<String, Object> checkStatisticType(Map<String, Object> options) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\nPlease enter type aggregate statistics number:\n");
        buffer.append(String.format(MENU_PATTERN, 1, "Username"));
        buffer.append(String.format(MENU_PATTERN, 2, "Time unit (1 day, 1 month, 1 year)\r"));
        System.out.print(buffer.toString());
        Map<String, Object> statisticOptions = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choiceMenuNumber = scanner.nextInt();
            switch (choiceMenuNumber) {
                case 1:
                    options.put(STATISTIC_TYPE, StatisticTypesEnum.USERNAME);
                    break;
                case 2: {
                    statisticOptions.put(STATISTIC_TIME_UNIT, checkTimeUnit());
                    options.put(STATISTIC_TYPE, StatisticTypesEnum.TIME_UNIT);
                    break;
                }
                default: {
                    System.out.println("\nAttention! Selected non-existent action.");
                    checkStatisticType(options);
                }
            }
        } else {
            System.out.println("\nAttention! Invalid format.");
            checkStatisticType(options);
        }
        options.put(STATISTIC_OPTIONS, statisticOptions);
        return options;
    }

    private TimeUnitEnum checkTimeUnit() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\nPlease enter time unit number for aggregate statistics:\n");
        buffer.append(String.format(MENU_PATTERN, 1, "One day"));
        buffer.append(String.format(MENU_PATTERN, 2, "One month"));
        buffer.append(String.format(MENU_PATTERN, 3, "One year\r"));
        System.out.print(buffer.toString());
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choiceMenuNumber = scanner.nextInt();
            switch (choiceMenuNumber) {
                case 1:
                    return TimeUnitEnum.ONE_DAY;
                case 2:
                    return TimeUnitEnum.ONE_MONTH;
                case 3:
                    return TimeUnitEnum.ONE_YEAR;
                default: {
                    System.out.println("\nAttention! Selected non-existent action.");
                    checkTimeUnit();
                }
            }
        }
        System.out.println("\nAttention! Invalid format.");
        checkTimeUnit();
        return null;
    }
}