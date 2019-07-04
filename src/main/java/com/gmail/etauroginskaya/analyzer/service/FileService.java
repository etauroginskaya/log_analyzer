package com.gmail.etauroginskaya.analyzer.service;

import com.gmail.etauroginskaya.analyzer.service.model.FilterTypesEnum;

import java.util.Map;

public interface FileService {
    void filterFiles(FilterTypesEnum typeFilter, Map<String, Object> inputOptions);
}
