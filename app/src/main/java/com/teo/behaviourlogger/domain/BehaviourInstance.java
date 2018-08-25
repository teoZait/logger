package com.teo.behaviourlogger.domain;

import java.util.List;

/**
 * Created by teo on 19.04.2018.
 */

public class BehaviourInstance {
    private List<Integer> orderOfOpening;
    private List<Integer> startTimes;
    private List<Integer> endTimes;
    private List<Integer> totalDuration;

    public BehaviourInstance(List<Integer> orderOfOpening, List<Integer> startTimes, List<Integer> endTimes, List<Integer> totalDuration) {
        this.orderOfOpening = orderOfOpening;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.totalDuration = totalDuration;
    }

    public List<Integer> getOrderOfOpening() {
        return orderOfOpening;
    }

    public void setOrderOfOpening(List<Integer> orderOfOpening) {
        this.orderOfOpening = orderOfOpening;
    }

    public List<Integer> getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(List<Integer> startTimes) {
        this.startTimes = startTimes;
    }

    public List<Integer> getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(List<Integer> endTimes) {
        this.endTimes = endTimes;
    }

    public List<Integer> getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(List<Integer> totalDuration) {
        this.totalDuration = totalDuration;
    }
}
