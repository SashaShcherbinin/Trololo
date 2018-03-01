package com.sprinklebit.input.pojo;

import java.awt.*;

public class Ride {

    private Point startPoint;

    private Point finishPoint;

    private int earliestStart;

    private int latestFinish;

    public Ride(int[] parameters) {
        startPoint = new Point(parameters[0], parameters[1]);
        finishPoint = new Point(parameters[2], parameters[3]);
        earliestStart = parameters[4];
        latestFinish = parameters[5];
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
    public String toString() {
        return super.toString();
    }
}
