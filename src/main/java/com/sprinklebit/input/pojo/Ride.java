package com.sprinklebit.input.pojo;

import java.awt.*;

public class Ride implements Comparable<Ride> {

    private Point startPoint;
    private Point endPoint;
    private int earliestStart;
    private int latestEnd;
    private int number;

    public Ride(Point startPoint, Point endPoint, int earliestStart, int latestEnd, int number) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.earliestStart = earliestStart;
        this.latestEnd = latestEnd;
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

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLatestEnd() {
        return latestEnd;
    }

    public void setLatestEnd(int latestEnd) {
        this.latestEnd = latestEnd;
    }

    @Override
    public int compareTo(Ride o) {
        return 0;
    }
}
