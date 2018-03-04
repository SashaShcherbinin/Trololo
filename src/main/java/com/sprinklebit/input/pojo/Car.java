package com.sprinklebit.input.pojo;

import java.awt.*;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/1/18
 * Time: 11:07 PM
 */


public class Car {

    private Point point;
    private int number;
    private int leftSteps;
    private Ride ride;

    public Car(Point point, int number) {
        this.point = point;
        this.number = number;
    }

    public int getLeftSteps() {
        return leftSteps;
    }

    public void setLeftSteps(int leftSteps) {
        this.leftSteps = leftSteps;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
