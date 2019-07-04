package com.gmail.etauroginskaya.analyzer.controller.validation;

public interface ValidationService {
    boolean validateRequiredProperty();

    String validateInputData(String input);
}
