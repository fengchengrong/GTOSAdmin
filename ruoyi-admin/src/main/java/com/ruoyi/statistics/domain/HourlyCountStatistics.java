package com.ruoyi.statistics.domain;

public class HourlyCountStatistics {
    private Integer hour;
    private Integer count;

    public HourlyCountStatistics() {
    }

    public HourlyCountStatistics(Integer hour, Integer count) {
        this.hour = hour;
        this.count = count;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
