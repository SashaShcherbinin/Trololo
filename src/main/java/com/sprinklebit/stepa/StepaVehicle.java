package com.sprinklebit.stepa;

import com.sprinklebit.input.pojo.Vehicle;
import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StepaVehicle extends Vehicle {

    private int maxSteps;
    private List<Ride> rides = new ArrayList<>();
    private int currentStep = 0;

    public StepaVehicle(Point point, int number, int maxSteps) {
        super(point, number);
        this.maxSteps = maxSteps;
    }


    public Ride findBestRide(List<Ride> rides) {
        Ride bestRide = null;

        for (Ride ride : rides) {

            if (isBetter(ride, bestRide)) {
                bestRide = ride;
            }
        }

        return bestRide;
    }

    private boolean isBetter(Ride ride, Ride bestRide) {
        if (!isValid(ride)) {
            return false;
        }

        if (bestRide == null) {
            return true;
        } else {
            if (calculateStartStep(ride) < calculateStartStep(bestRide)) {
                return true;
            }
        }

        return false;
    }

    private int calculateStartStep(Ride ride) {
        int step = (int) (currentStep + Math.abs(ride.getStartPoint().getX() - getPoint().getX()) +
                Math.abs(ride.getStartPoint().getY() - getPoint().getY()));

        if (step < ride.getEarliestStart()) {
            step = ride.getEarliestStart();
        }

        return step;
    }

    private boolean isValid(Ride ride) {
        if (maxSteps > (int) (currentStep + Math.abs(ride.getStartPoint().getX() - getPoint().getX()) +
                Math.abs(ride.getStartPoint().getY() - getPoint().getY()) + Math.abs(ride.getEndPoint().getX() - getPoint().getX()) +
                Math.abs(ride.getEndPoint().getY() - getPoint().getY()))) {
            return true;
        }

        return false;
    }

    public void addRide(Ride bestRide) {
        rides.add(bestRide);
        calculateStep(bestRide);
        setPoint(bestRide.getEndPoint());
    }

    private void calculateStep(Ride ride) {
        currentStep = (int) (currentStep + Math.abs(ride.getStartPoint().getX() - getPoint().getX()) +
                Math.abs(ride.getStartPoint().getY() - getPoint().getY()));

        if (currentStep < ride.getEarliestStart()) {
            currentStep = ride.getEarliestStart();
        }

        currentStep = (int) (currentStep + Math.abs(ride.getEndPoint().getX() - ride.getStartPoint().getX()) +
                Math.abs(ride.getEndPoint().getY() - ride.getStartPoint().getY()));
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void toStartPoint(Ride ride) {
        currentStep = (int) (currentStep + Math.abs(ride.getStartPoint().getX() - getPoint().getX()) +
                Math.abs(ride.getStartPoint().getY() - getPoint().getY()));

        setPoint(ride.getStartPoint());
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int calculateDistance(Ride ride) {
        int distance;
        setPoint(ride.getEndPoint());

        distance = (int) (Math.abs(ride.getEndPoint().getX() - ride.getStartPoint().getX()) +
                        Math.abs(ride.getEndPoint().getY() - ride.getStartPoint().getY()));

        return distance;
    }
}
