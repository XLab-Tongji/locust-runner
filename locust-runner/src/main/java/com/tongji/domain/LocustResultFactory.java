package com.tongji.domain;

import java.util.Date;

/**
 * @author : rain
 * @date : 2018/11/20 5:03 PM
 */


public class LocustResultFactory {

    private LocustResultFactory() {
    }

    public static LocustResult getLocustResult(String scenarioId, String lineText) {
        LocustResult locustResult = new LocustResult();

        String lineStr[] = lineText.split(",");
        locustResult.setScenarioId(scenarioId);
        locustResult.setRequests(lineStr[2]);
        locustResult.setFailures(lineStr[3]);
        locustResult.setMedianResponseTime(lineStr[4]);
        locustResult.setAverageResponseTime(lineStr[5]);
        locustResult.setMinResponseTime(lineStr[6]);
        locustResult.setMaxResponseTime(lineStr[7]);
        locustResult.setAverageContentSize(lineStr[8]);
        locustResult.setRequestPerSecond(lineStr[9]);
        locustResult.setDateTime(new Date());

        return locustResult;
    }
}
