package com.tongji.domain;

import java.util.Date;

/**
 * @author : rain
 * @date : 2018/11/11 4:46 PM
 */



public class LocustResult {

    private String scenarioId;
    private String requests;
    private String failures;
    private String medianResponseTime;
    private String averageResponseTime;
    private String minResponseTime;
    private String maxResponseTime;
    private String averageContentSize;
    private String requestPerSecond;
    private Date dateTime;

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }

    public String getFailures() {
        return failures;
    }

    public void setFailures(String failures) {
        this.failures = failures;
    }

    public String getMedianResponseTime() {
        return medianResponseTime;
    }

    public void setMedianResponseTime(String medianResponseTime) {
        this.medianResponseTime = medianResponseTime;
    }

    public String getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(String averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public String getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(String minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public String getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(String maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public String getAverageContentSize() {
        return averageContentSize;
    }

    public void setAverageContentSize(String averageContentSize) {
        this.averageContentSize = averageContentSize;
    }

    public String getRequestPerSecond() {
        return requestPerSecond;
    }

    public void setRequestPerSecond(String requestPerSecond) {
        this.requestPerSecond = requestPerSecond;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LocustResult{" +
                "scenarioId='" + scenarioId + '\'' +
                ", requests='" + requests + '\'' +
                ", failures='" + failures + '\'' +
                ", medianResponseTime='" + medianResponseTime + '\'' +
                ", averageResponseTime='" + averageResponseTime + '\'' +
                ", minResponseTime='" + minResponseTime + '\'' +
                ", maxResponseTime='" + maxResponseTime + '\'' +
                ", averageContentSize='" + averageContentSize + '\'' +
                ", requestPerSecond='" + requestPerSecond + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
