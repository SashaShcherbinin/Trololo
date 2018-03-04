package com.sprinklebit.stepa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ResultWriter {

    private Set<Integer> completedRides = new HashSet<>();

    private Map<Integer, List<Integer>> completedRoutes = new TreeMap();

    public void addVehicle(Integer vechicleNumber) {
        completedRoutes.put(vechicleNumber, new ArrayList<>());
    }

    public void logRide(Integer vehicleNumber, Integer rideNumber) {

        completedRides.add(rideNumber);

        List<Integer> ridesForVehicle = completedRoutes.get(vehicleNumber);

        ridesForVehicle.add(rideNumber);
//        if (ridesForVehicle == null) {
//            ridesForVehicle = new ArrayList<>();
//            ridesForVehicle.add(rideNumber);
//
//            completedRoutes.put(vehicleNumber, ridesForVehicle);
//        } else {
//            ridesForVehicle.add(rideNumber);
//        }
    }

    public void printResult() {
        printToFile();
//        printToLog();
    }

    private void printToFile() {
        try {

            File outputFile = new File("outResult_e.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            StringBuilder sb = new StringBuilder();

            for(Map.Entry<Integer, List<Integer>> vehicle : completedRoutes.entrySet()) {

                sb.append(vehicle.getValue().size());
                sb.append(" ");

                for (Integer ride : vehicle.getValue()) {
                    sb.append(ride);
                    sb.append(" ");
                }

                sb.append("\n");
            }



            bw.write(sb.toString());
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToLog() {
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
