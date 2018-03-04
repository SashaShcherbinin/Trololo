package com.sprinklebit.input.pojo;

import java.awt.*;

public class Ride implements Comparable<Ride> {

    private Point startPoint;
    private Point finishPoint;
    private int earliestStart;
    private int latestFinish;
    private int number;

    public Ride(Point startPoint, Point finishPoint, int earliestStart, int latestFinish, int number) {
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }

    public void setLatestFinish(int latestFinish) {
        this.latestFinish = latestFinish;
    }

    @Override
    public int compareTo(Ride o) {
        return 0;
    }
}
