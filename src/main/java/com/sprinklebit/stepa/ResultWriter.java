package com.sprinklebit.stepa;

import java.util.*;

public class ResultWriter {

    private Set<Integer> completedRides = new HashSet<>();

    private Map<Integer, List<Integer>> completedRoutes = new TreeMap();

    public void logRide(Integer vehicleNumber, Integer rideNumber) {

        completedRides.add(rideNumber);

        List<Integer> ridesForVehicle = completedRoutes.get(vehicleNumber);

        if (ridesForVehicle == null) {
            ridesForVehicle = new ArrayList<>();
            ridesForVehicle.add(rideNumber);

            completedRoutes.put(vehicleNumber, ridesForVehicle);
        } else {
            ridesForVehicle.add(rideNumber);
        }
    }

    public void printResult() {

        System.out.println("Writing data with logger");

        for(Map.Entry<Integer, List<Integer>> vehicle : completedRoutes.entrySet()) {
            System.out.print("\n" + vehicle.getKey() + " ");

            for (Integer ride : vehicle.getValue()) {
                System.out.print(ride + " ");
            }
        }

        System.out.println("End data");
    }

}
