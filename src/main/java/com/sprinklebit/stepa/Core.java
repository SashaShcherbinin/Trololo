package com.sprinklebit.stepa;
import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Core {
    private final List<StepaVehicle> vehicles;
    private final List<Ride> rides;
    private final ResultWriter mResultWriter;

    public Core(InputData input, ResultWriter resultWriter) {

        mResultWriter = resultWriter;
        List<Ride> rides = input.getRides();

        List<StepaVehicle> vehicles = new ArrayList<>();

        for (int i=0; i<input.getParameters().getVehicles(); i++) {
            StepaVehicle vehicle = new StepaVehicle(new Point(0,0), i+1, input.getParameters().getSteps());
            vehicles.add(vehicle);
            mResultWriter.addVehicle(vehicle.getNumber());
        }

        this.vehicles = vehicles;
        this.rides = rides;

    }

    public void calculateRides() {
        for(StepaVehicle vehicle : vehicles) {
            Ride bestRide = vehicle.findBestRide(rides);
            while (bestRide != null) {
                vehicle.addRide(bestRide);
                mResultWriter.logRide(vehicle.getNumber(), bestRide.getNumber());
                rides.remove(bestRide);

                bestRide = vehicle.findBestRide(rides);
            }
        }
    }

    public void printData() {
        mResultWriter.printResult();
//        for (StepaVehicle vehicle : vehicles) {
//            System.out.print("\n" + vehicle.getNumber() + " ");
//            for (Ride ride : vehicle.getRides()) {
//                System.out.print(ride.getNumber() + " ");
//            }
//        }
    }

    public int calculateScore(int maxSteps, int extraBonus) {
        int score = 0;
        for (StepaVehicle vehicle : vehicles) {
            for (Ride ride : vehicle.getRides()) {
                int bonus = 0;
                StepaVehicle veh = new StepaVehicle(new Point(0,0), 0, maxSteps);
                veh.toStartPoint(ride);
                if (veh.getCurrentStep() <= ride.getEarliestStart()) {
                    veh.setCurrentStep(ride.getEarliestStart());
                    bonus = extraBonus;
                }
                score = score + veh.calculateDistance(ride) + bonus;
            }
        }

        return score;
    }
}
