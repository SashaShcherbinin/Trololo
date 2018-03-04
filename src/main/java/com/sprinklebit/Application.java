package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Vehicle;
import com.sprinklebit.input.pojo.Ride;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/1/18
 * Time: 9:25 PM
 */

class Application {

    private static final String EXAMPLE_A_PATH = "a_example.in";

    private static List<Pair<Integer, Ride>> startToPointASteps = new ArrayList<>();
    static Map<Integer, List<Ride>> resultMap = new HashMap<>();

    private static List<Vehicle> vehiclePool = new ArrayList<>();
    private static List<Vehicle> activeVehicles = new ArrayList<>();
    private static List<Ride> rides = new ArrayList<>();

    private static Parameters parameters;

    public static void main(String[] args) {
        InputData inputData = new InputData("a_example.in");
        rides = inputData.getRides();
        parameters = inputData.getParameters();
    }

//    private static void sortRides(List<Ride> rides) {
//
//        Collections.sort(rides);
//
//        for (Ride ride : rides) {
//            startToPointASteps.add(new Pair<>(ride.getDistanceToZeroPoint(), ride));
//
//            System.out.println("Ride added. Distance:" + ride.getDistanceToZeroPoint());
//        }
//    }
}
