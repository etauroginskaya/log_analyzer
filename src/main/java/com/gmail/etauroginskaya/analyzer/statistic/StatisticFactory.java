package com.gmail.etauroginskaya.analyzer.statistic;

import com.gmail.etauroginskaya.analyzer.statistic.model.StatisticTypesEnum;
import com.gmail.etauroginskaya.analyzer.statistic.impl.StatisticByTimeUnit;
import com.gmail.etauroginskaya.analyzer.statistic.impl.StatisticByUsername;

public class StatisticFactory {
    private static StatisticFactory mInstance;

    private StatisticFactory() {
    }

    public static StatisticFactory getInstance() {
        if (mInstance == null) {
            mInstance = new StatisticFactory();
        }
        return mInstance;
    }

    public Statistic getStatistic(StatisticTypesEnum typeStatistic) {
        Statistic toReturn;
        switch (typeStatistic) {
            case USERNAME:
                toReturn = new StatisticByUsername();
                break;
            case TIME_UNIT:
                toReturn = new StatisticByTimeUnit();
                break;
            default:
                throw new UnsupportedOperationException("Wrong statistic type: " + typeStatistic);
        }
        return toReturn;
    }
}
