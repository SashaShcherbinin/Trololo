package com.sprinklebit.stepa;
import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class Core {
    private final List<StepaVehicle> vehicles;
    private final List<Ride> rides;
    private final ResultWriter mResultWriter;

    public Core(InputData input, ResultWriter resultWriter) {

        mResultWriter = resultWriter;
        List<Ride> rides = input.getRides();

        List<StepaVehicle> vehicles = new ArrayList<>();

        for (int i=0; i<input.getParameters().getVehicles(); i++) {
            StepaVehicle vehicle = new StepaVehicle(new Point(0,0), i+1, input.getParameters().getSteps(), input.getParameters().getBonuses());
            vehicles.add(vehicle);
            mResultWriter.addVehicle(vehicle.getNumber());
        }

        this.vehicles = vehicles;
        this.rides = rides;

    }

    public void calculateRides() throws CloneNotSupportedException {
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

    public void generateRoutes() throws CloneNotSupportedException {
        int qty = rides.size();
        for (int i = 0; i<qty; i++) {
            List<Object[]> bestPairs = new ArrayList<>();
            for (StepaVehicle vehicle : vehicles) {
                Ride bestRide = vehicle.findBestRide(rides);
                Object[] pair = {vehicle, bestRide};
                bestPairs.add(pair);
            }

//            Comparator<Double> comp = (Double a, Double b) -> {
//                return b.compareTo(a);
//            };

//            Comparator<Object[]> comp = (Object[] a, Object[] b) -> {
//                StepaVehicle v1 = (StepaVehicle) a[0];
//                StepaVehicle v2 = (StepaVehicle) b[0];
//                return Integer.compare(v2.getCurrentStep(),v1.getCurrentStep());
//            };

            Collections.sort(bestPairs, (a, b) -> {
                StepaVehicle v1 = (StepaVehicle) a[0];
                StepaVehicle v2 = (StepaVehicle) b[0];
                return Integer.compare(v1.getCurrentStep(),v2.getCurrentStep());
            });


            int a =0;


            int pairIndex = getBestPair(bestPairs);
            if (pairIndex != -1) {
                StepaVehicle selectedVehicle = (StepaVehicle) bestPairs.get(pairIndex)[0];
                Ride selectedRide = (Ride) bestPairs.get(pairIndex)[1];
                if (selectedRide != null && selectedVehicle != null) {
                    selectedVehicle.addRide(selectedRide);
                    mResultWriter.logRide(selectedVehicle.getNumber(), selectedRide.getNumber());
                    rides.remove(selectedRide);
                }
            }
        }
    }

    private int getBestPair(List<Object[]> bestPairs) throws CloneNotSupportedException {
        int bestIndex = -1;
        Ride ridePrime = findRidePrime(bestPairs);

        if (ridePrime != null) {
            bestIndex = findFirstPairByRidePrime(ridePrime, bestPairs);

            for (Object[] pair : bestPairs) {

                StepaVehicle vehBest = (StepaVehicle) bestPairs.get(bestIndex)[0];
                Ride rideBest = (Ride) bestPairs.get(bestIndex)[1];
                StepaVehicle veh = (StepaVehicle) pair[0];
                Ride ride = (Ride) pair[1];


                if (ride != null && ride == rideBest) {
//                    if (calculatePoints(bestPairs.get(bestIndex)) < calculatePoints(pair)) {
//                        bestIndex = bestPairs.indexOf(pair);
//                    } else if (calculatePoints(bestPairs.get(bestIndex)) == calculatePoints(pair)) {
                        int distanceRide = veh.calculateStartStep(ride) - veh.getCurrentStep();
                        float deltaRide;
                        float deltaBestRide;
                        if (ride != null) {
                            deltaRide = (float) veh.calculatePoits(ride) / (float) (distanceRide + veh.calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
                        } else {
                            deltaRide = 0;
                        }
                        int distanceBestRide = vehBest.calculateStartStep(rideBest) - vehBest.getCurrentStep();
                        if (rideBest != null) {
                            deltaBestRide = (float) vehBest.calculatePoits(rideBest) / (float) (distanceBestRide + vehBest.calculateDistance(rideBest.getStartPoint(), rideBest.getEndPoint()));
                        } else {
                            deltaBestRide = 0;
                        }

                        if (deltaRide > deltaBestRide) {
                            bestIndex = bestPairs.indexOf(pair);
                        }
//                    }
                } else {
                    continue;
                }
            }

        }
//        for (Object[] pair : bestPairs) {
//
//            StepaVehicle vehBest = (StepaVehicle) bestPairs.get(bestIndex)[0];
//            Ride rideBest = (Ride) bestPairs.get(bestIndex)[1];
//            StepaVehicle veh = (StepaVehicle) pair[0];
//            Ride ride = (Ride) pair[1];
//
//            if (rideBest != null) {
//                if (ride != null && ride == rideBest) {
//                    if (calculatePoints(bestPairs.get(bestIndex)) < calculatePoints(pair)) {
//                        bestIndex = bestPairs.indexOf(pair);
//                    }
//                }
//            }


//            if (vehBest.getCurrentStep() <= veh.getCurrentStep() && rideBest != null) {
//                if (ride != null && ride.getNumber() == rideBest.getNumber()) {
//                    if (calculatePoints(bestPairs.get(bestIndex)) < calculatePoints(pair)) {
//                        bestIndex = bestPairs.indexOf(pair);
//                    } else if (calculatePoints(bestPairs.get(bestIndex)) == calculatePoints(pair)) {
//                        int distanceRide = veh.calculateStartStep(ride) - veh.getCurrentStep();
//                        float deltaRide;
//                        float deltaBestRide;
//                        if (ride != null) {
//                            deltaRide = (float) veh.calculatePoits(ride) / (float) (distanceRide + veh.calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
//                        } else {
//                            deltaRide = 0;
//                        }
//                        int distanceBestRide = vehBest.calculateStartStep(rideBest) - vehBest.getCurrentStep();
//                        if (rideBest != null) {
//                            deltaBestRide = (float) vehBest.calculatePoits(rideBest) / (float) (distanceBestRide + vehBest.calculateDistance(rideBest.getStartPoint(), rideBest.getEndPoint()));
//                        } else {
//                            deltaBestRide = 0;
//                        }
//
//                        if (deltaRide > deltaBestRide) {
//                            bestIndex = bestPairs.indexOf(pair);
//                        }
//                    }
//                } else {
//                    continue;
//                }
//            } else  if (ride != null && rideBest != null && ride.getNumber() == rideBest.getNumber()) {
//                if (calculatePoints(bestPairs.get(bestIndex)) < calculatePoints(pair)) {
//                    bestIndex = bestPairs.indexOf(pair);
//                } else if (calculatePoints(bestPairs.get(bestIndex)) == calculatePoints(pair)) {
//                    int distanceRide = veh.calculateStartStep(ride) - veh.getCurrentStep();
//                    float deltaRide;
//                    float deltaBestRide;
//                    if (ride != null) {
//                        deltaRide = (float) veh.calculatePoits(ride) / (float) (distanceRide + veh.calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
//                    } else {
//                        deltaRide = 0;
//                    }
//                    int distanceBestRide = vehBest.calculateStartStep(rideBest) - vehBest.getCurrentStep();
//                    if (rideBest != null) {
//                        deltaBestRide = (float) vehBest.calculatePoits(rideBest) / (float) (distanceBestRide + vehBest.calculateDistance(rideBest.getStartPoint(), rideBest.getEndPoint()));
//                    } else {
//                        deltaBestRide = 0;
//                    }
//
//                    if (deltaRide > deltaBestRide) {
//                        bestIndex = bestPairs.indexOf(pair);
//                    }
//                }
//            } else {
//                int distanceRide = veh.calculateStartStep(ride) - veh.getCurrentStep();
//                float deltaRide;
//                float deltaBestRide;
//                if (ride != null) {
//                    deltaRide = (float) veh.calculatePoits(ride) / (float) (distanceRide + veh.calculateDistance(ride.getStartPoint(), ride.getEndPoint()));
//                } else {
//                    deltaRide = 0;
//                }
//                int distanceBestRide = vehBest.calculateStartStep(rideBest) - vehBest.getCurrentStep();
//                if (rideBest != null) {
//                    deltaBestRide = (float) vehBest.calculatePoits(rideBest) / (float) (distanceBestRide + vehBest.calculateDistance(rideBest.getStartPoint(), rideBest.getEndPoint()));
//                } else {
//                    deltaBestRide = 0;
//                }
//
//                if (deltaRide > deltaBestRide) {
//                    bestIndex = bestPairs.indexOf(pair);
//                }
//            }

//            if (calculatePoints(bestPairs.get(bestIndex)) == calculatePoints(pair) && calculatePoints(pair) != 0) {
//                if (moveToStart(bestPairs.get(bestIndex)) > moveToStart(pair)) {
//                    bestIndex = bestPairs.indexOf(pair);
//                }
//            } else if (calculatePoints(bestPairs.get(bestIndex)) < calculatePoints(pair)) {
//                bestIndex = bestPairs.indexOf(pair);
//            }
//        }

        return bestIndex;
    }

    private int findFirstPairByRidePrime(Ride ridePrime, List<Object[]> bestPairs) {
        if (ridePrime != null) {
            for (Object[] pair : bestPairs) {
                Ride ride = (Ride) pair[1];
                if (ride != null && ride.getNumber() == ridePrime.getNumber()) {
                    return bestPairs.indexOf(pair);
                }
            }
        }

        return 0;
    }

    private Ride findRidePrime(List<Object[]> bestPairs) {
        for (Object[] pair : bestPairs) {
            if (pair[1] != null) {
                Ride ride = (Ride) pair[1];
                return ride;
            }
        }

        return null;
    }

    private int moveToStart(Object[] pair) {
        int result = 0;
        StepaVehicle veh = (StepaVehicle) pair[0];
        Ride ride = (Ride) pair[1];
        int distance = veh.calculateDistance(veh.getPoint(), ride.getStartPoint());
        if (veh.getCurrentStep() + distance < ride.getEarliestStart()) {
            result = ride.getEarliestStart() - veh.getCurrentStep();
        } else {
            result = distance;
        }


        return result;
    }

    private int calculatePoints(Object[] pair) throws CloneNotSupportedException {
        StepaVehicle veh = (StepaVehicle) pair[0];

        return veh.calculatePoits((Ride) pair[1]);
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
                StepaVehicle veh = new StepaVehicle(new Point(0,0), 0, maxSteps, extraBonus);
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
