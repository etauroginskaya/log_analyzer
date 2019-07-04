package com.gmail.etauroginskaya.analyzer.controller.validation.impl;

import com.gmail.etauroginskaya.analyzer.config.impl.ConfigurationManagerImpl;
import com.gmail.etauroginskaya.analyzer.controller.validation.ValidationService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ValidationServiceImpl implements ValidationService {
    private static final String DATE_REGEX = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    private static ValidationServiceImpl mInstance;

    private ValidationServiceImpl() {
    }

    public static ValidationServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new ValidationServiceImpl();
        }
        return mInstance;
    }

    @Override
    public boolean validateRequiredProperty() {
        if (!ConfigurationManagerImpl.getInstance().getProperty("input.path").equals("") &&
                !ConfigurationManagerImpl.getInstance().getProperty("filter.file.extension").equals("")) {
            if (Files.exists(Paths.get(ConfigurationManagerImpl.getInstance().getProperty("input.path")))) {
                return true;
            } else System.out.println("Sorry! \"input.path\" property doesn't exist.");
        } else System.out.println("Sorry! There is no required application parameter in the configuration file.");
        return false;
    }

    @Override
    public String validateInputData(String input) {
        Scanner scanner;
        while (!input.matches(DATE_REGEX)) {
            System.out.println("Invalid date format. Enter date in format: yyyy-MM-dd");
            scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                input = scanner.next();
            }
        }
        return input;
    }
}