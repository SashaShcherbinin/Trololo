package com.sprinklebit;

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
