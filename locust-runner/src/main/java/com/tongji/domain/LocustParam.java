package com.tongji.domain;

/**
 * @author : rain
 * @date : 2018/11/11 2:32 PM
 */

public class LocustParam {

    private Integer scenarioId;
    private Integer clients;
    private Integer hatchRate;
    private Integer runTime;
    private String host;

    public Integer getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Integer scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public Integer getHatchRate() {
        return hatchRate;
    }

    public void setHatchRate(Integer hatchRate) {
        this.hatchRate = hatchRate;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "LocustParam{" +
                "scenarioId=" + scenarioId +
                ", clients=" + clients +
                ", hatchRate=" + hatchRate +
                ", runTime=" + runTime +
                ", host='" + host + '\'' +
                '}';
    }
}
