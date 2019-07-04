package com.gmail.etauroginskaya.analyzer.filter;

import com.gmail.etauroginskaya.analyzer.service.model.FilterTypesEnum;
import com.gmail.etauroginskaya.analyzer.filter.impl.FilterByPatternMessage;
import com.gmail.etauroginskaya.analyzer.filter.impl.FilterByTimePeriod;
import com.gmail.etauroginskaya.analyzer.filter.impl.FilterByUsername;

public class FilterFactory {

    private static FilterFactory mInstance;

    private FilterFactory() {
    }

    public static FilterFactory getInstance() {
        if (mInstance == null) {
            mInstance = new FilterFactory();
        }
        return mInstance;
    }

    public Filter getFilter(FilterTypesEnum typeFilter) {
        Filter toReturn;
        switch (typeFilter) {
            case USERNAME:
                toReturn = new FilterByUsername();
                break;
            case TIME_PERIOD:
                toReturn = new FilterByTimePeriod();
                break;
            case PATTERN_FOR_CUSTOM_MESSAGE:
                toReturn = new FilterByPatternMessage();
                break;
            default:
                throw new UnsupportedOperationException("Wrong filter type: " + typeFilter);
        }
        return toReturn;
    }
}
