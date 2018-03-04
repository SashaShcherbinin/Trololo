package com.sprinklebit.input;

import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.input.pojo.Vehicle;
import com.sprinklebit.stepa.ResultWriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/4/18
 * Time: 1:55 PM
 */

public class RideDistribution {

    private final ResultWriter resultWriter;
    private Queue<Vehicle> vehiclePool = new LinkedTransferQueue<>();
    private List<Vehicle> activeVehicles = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();
    private Parameters parameters;
    private int score;

    public RideDistribution(List<Ride> rides, Parameters parameters) {
        this.rides = rides;
        this.parameters = parameters;
        resultWriter = new ResultWriter();
        for (int i = 0; i < parameters.getVehicles(); i++) {
            Vehicle e = new Vehicle(new Point(0, 0), i + 1);
            vehiclePool.add(e);
            resultWriter.addVehicle(e.getNumber());
        }
    }

    public void start() {
        for (int step = 0; step < parameters.getSteps(); step++) {

            if (rides.size() != 0) {
                while (!vehiclePool.isEmpty()) {
                    Vehicle vehicle = vehiclePool.poll();

                    Ride closestRide = getClosestRide(step, vehicle, rides);
                    rides.remove(closestRide);
                    vehicle.setRide(closestRide);
                    resultWriter.logRide(vehicle.getNumber(), closestRide.getNumber());
                    int distance = getDistance(closestRide.getStartPoint(), closestRide.getEndPoint());
                    int distanceToPoint = getDistance(vehicle.getPoint(), closestRide.getStartPoint());
                    int earliestStart = closestRide.getEarliestStart() - step;
                    if (earliestStart < 0) {
                        earliestStart = 0;
                    }
                    vehicle.setLeftSteps(distance + distanceToPoint + earliestStart);
                    activeVehicles.add(vehicle);
                }
            }

            for (int i = activeVehicles.size() - 1; i >= 0; i--) {
                Vehicle activeVehicle = activeVehicles.get(i);
                if (activeVehicle.getLeftSteps() == 0) {
                    activeVehicles.remove(i);
                    vehiclePool.add(activeVehicle);
                    Ride ride = activeVehicle.getRide();
                    activeVehicle.setPoint(ride.getEndPoint());
                    int steps = getSteps(ride.getStartPoint(), activeVehicle.getRide().getEndPoint(), 0);
                    if (activeVehicle.getRide().getEarliestStart() == step - steps) {
                        score += parameters.getBonuses();
                        System.out.println("plus bonuses= " + parameters.getBonuses());
                    }
                    if (step <= ride.getLatestEnd()) {
                        score += steps;
                    }
                } else {
                    activeVehicle.setLeftSteps(activeVehicle.getLeftSteps() - 1);
                }
            }

            if (activeVehicles.size() == 0) {
                System.out.println("step = " + step);
                break;
            }
        }
        System.out.println("rides left= " + rides.size());
        System.out.println("score = " + score);
        resultWriter.printResult();
    }

    public Ride getClosestRide(int step, Vehicle vehicle, List<Ride> rides) {
        int minSteps = Integer.MAX_VALUE;
        Ride closestRide = null;
        for (Ride ride : rides) {
            int earliestStart = ride.getEarliestStart() - step;
            if (earliestStart < 0) {
                earliestStart = 0;
            }
            int steps = getSteps(vehicle.getPoint(), ride.getStartPoint(), earliestStart);
            if (step <= ride.getEarliestStart()) {
                steps = steps - parameters.getBonuses();
            } else {
//                System.out.println("more = " + step);
            }
            if (minSteps > steps) {
                minSteps = steps;
                closestRide = ride;
            }
        }
        return closestRide;
    }

    private int getSteps(Point vehicle, Point ride, int earliestStart) {
        int distance = getDistance(vehicle, ride);
        distance = distance + earliestStart;
        return distance;
    }

    private int getDistance(Point vehicle, Point ride) {
        return Math.abs(vehicle.x - ride.x) + Math.abs(vehicle.y - ride.y);
    }

}
