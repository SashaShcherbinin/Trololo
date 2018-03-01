package com.sprinklebit.input.pojo;

import java.awt.*;

public class Ride {

    private Point startPoint;

    private Point finishPoint;

    private int earliestStart;

    private int latestFinish;

    public Ride(Point startPoint, Point finishPoint, int earliestStart, int latestFinish) {
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
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
}
