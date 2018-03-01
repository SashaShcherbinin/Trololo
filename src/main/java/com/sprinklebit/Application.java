package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import javafx.util.Pair;

import java.util.*;

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

    public static void main(String[] args) {

        InputData inputData = new InputData(EXAMPLE_A_PATH);
        Parameters parameters = inputData.getParameters();
        List<Ride> rides = inputData.getRides();
        sortRides(rides);

        addingCar(parameters);

        System.out.println(parameters);
//        System.out.println(rides);
    }

    private static void addingCar(Parameters parameters) {
        for (int i = 0; i < parameters.getVehicles(); i++) {
            int car = i + 1;
            List<Ride> rideList = resultMap.get(car);
            if (rideList == null) {
                rideList = new ArrayList<>();
            }
            rideList.add(startToPointASteps.get(i).getValue());
            resultMap.put(car, rideList);
        }
    }

    private static void sortRides(List<Ride> rides) {

        Collections.sort(rides);

        for (Ride ride : rides) {
            startToPointASteps.add(new Pair<>(ride.getDistanceToZeroPoint(), ride));

            System.out.println("Ride added. Distance:" + ride.getDistanceToZeroPoint());
        }
    }
}
