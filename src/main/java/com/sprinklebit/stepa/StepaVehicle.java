package com.sprinklebit.stepa;

import com.sprinklebit.input.pojo.Vehicle;
import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StepaVehicle extends Vehicle implements Cloneable {

    private final int bonus;
    private int maxSteps;
    private List<Ride> rides = new ArrayList<>();
    private int currentStep = 0;

    public StepaVehicle(Point point, int number, int maxSteps, int bonus) {
        super(point, number);
        this.maxSteps = maxSteps;
        this.bonus = bonus;
    }


    public Ride findBestRide(List<Ride> rides) throws CloneNotSupportedException {
        Ride bestRide = null;

        for (Ride ride : rides) {

            if (isBetter(ride, bestRide)) {
                bestRide = ride;
            }
        }

        return bestRide;
    }

    private boolean isBetter(Ride ride, Ride bestRide) throws CloneNotSupportedException {
        if (!isValid(ride)) {
            return false;
        }



        if (bestRide == null) {
            return true;
        } else {
            //ToDo : add logic here
//            int distanceRide = calculateStartStep(ride) - this.getCurrentStep();
//            float deltaRide;
//            float deltaBestRide;
//            deltaRide = (float) calculatePoits(ride) / (float) (distanceRide + calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
//            int distanceBestRide = calculateStartStep(bestRide) - this.getCurrentStep();
//            deltaBestRide = (float) calculatePoits(bestRide) / (float) (distanceBestRide + calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
//
//
//            if (deltaRide > deltaBestRide) {
//                return true;
//            }

            if (calculatePoits(ride) > calculatePoits(bestRide)) {
                return true;
            }
        }

        return false;
    }

    public int calculatePoits(Ride ride) throws CloneNotSupportedException {
        StepaVehicle veh = (StepaVehicle) clone();

        int points = 0;
        int bonus = 0;
        if (ride != null) {
            int distanceToStart = veh.calculateDistance(veh.getPoint(), ride.getStartPoint());
            if (veh.getCurrentStep() + distanceToStart < maxSteps) {
                if (veh.getCurrentStep() + distanceToStart <= ride.getEarliestStart()) {
                    veh.setCurrentStep(ride.getEarliestStart());
                    veh.setPoint(ride.getStartPoint());
                    bonus = this.bonus;
                } else {
                    veh.setCurrentStep(veh.getCurrentStep() + distanceToStart);
                    veh.setPoint(ride.getStartPoint());
                }
            } else {
                veh.setCurrentStep(maxSteps - 1);
            }

            int distanceToEnd = veh.calculateDistance(veh.getPoint(), ride.getEndPoint());
            if (veh.getCurrentStep() + distanceToEnd < maxSteps) {
                if (veh.getCurrentStep() + distanceToEnd <= ride.getLatestEnd()) {
                    veh.setCurrentStep(veh.getCurrentStep() + distanceToEnd);
                    veh.setPoint(ride.getEndPoint());
                    points = distanceToEnd + bonus;
                } else {
                    veh.setCurrentStep(veh.getCurrentStep() + distanceToEnd);
                    veh.setPoint(ride.getEndPoint());
                }
            } else {
                veh.setCurrentStep(maxSteps - 1);
            }
        }
        return points;
    }

    public int calculateStartStep(Ride ride) {
        int step = 0;

        if (ride == null) {
            step = currentStep;
        } else {
            step = (int) (currentStep + Math.abs(ride.getStartPoint().getX() - getPoint().getX()) +
                    Math.abs(ride.getStartPoint().getY() - getPoint().getY()));

            if (step < ride.getEarliestStart()) {
                step = ride.getEarliestStart();
            }
        }

        return step;
    }

    private boolean isValid(Ride ride) throws CloneNotSupportedException {
        StepaVehicle veh = (StepaVehicle) clone();

        int distanceToStart = veh.calculateDistance(veh.getPoint(), ride.getStartPoint());
        if (veh.getCurrentStep() + distanceToStart < maxSteps) {
            if (veh.getCurrentStep() + distanceToStart <= ride.getEarliestStart()) {
                veh.setCurrentStep(ride.getEarliestStart());
                veh.setPoint(ride.getStartPoint());
            } else {
                veh.setCurrentStep(veh.getCurrentStep() + distanceToStart);
                veh.setPoint(ride.getStartPoint());
            }
        } else {
            return false;
        }

        int distanceToEnd = veh.calculateDistance(veh.getPoint(), ride.getEndPoint());
        if (veh.getCurrentStep() + distanceToEnd < maxSteps) {
            if (veh.getCurrentStep() + distanceToEnd <= ride.getLatestEnd()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addRide(Ride bestRide) {
        if (bestRide != null) {
            rides.add(bestRide);
            calculateStep(bestRide);
            setPoint(bestRide.getEndPoint());
        }
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

        currentStep += distance;

        return distance;
    }

    public int calculateDistance(Point start, Point end) {
        return (int) (Math.abs(start.getX() - end.getX()) +
                Math.abs(start.getY() - end.getY()));
    }
}
