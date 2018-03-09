package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.stepa.*;
import com.sprinklebit.input.RideDistribution;
import com.sprinklebit.vlad.Utils;
import javafx.util.Pair;

import java.awt.*;
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

public class Application {

    private static final String EXAMPLE_A_PATH = "a_example.in";
    private static final String EXAMPLE_B_PATH = "b_should_be_easy.in";
    private static final String EXAMPLE_C_PATH = "c_no_hurry.in";
    private static final String EXAMPLE_D_PATH = "d_metropolis.in";
    private static final String EXAMPLE_E_PATH = "e_high_bonus.in";

    private static List<Pair<Integer, Ride>> startToPointASteps = new ArrayList<>();
    static Map<Integer, List<Ride>> resultMap = new HashMap<>();

    private static Parameters parameters;

    public static void main(String[] args) throws CloneNotSupportedException {
        InputData input = new InputData(EXAMPLE_E_PATH, new Utils());

        startStepa(input);
//        startSasha(input);

    }

    private static void startSasha(InputData inputData) {
        RideDistribution rideDistribution = new RideDistribution(inputData.getRides(), inputData.getParameters());
        rideDistribution.start();
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
        private static void startStepa(InputData input) throws CloneNotSupportedException {

            System.out.println("Hello Stepan!!!");
            Core core = new Core(input, new ResultWriter());
            core.generateRoutes();
            core.printData();

            Score score = new Score(new InputData(EXAMPLE_E_PATH, new Utils()), new ResultReader("outResult_e.txt"));
            ResultReader r = new ResultReader("outResult_e.txt");
            int qty = 0;
            for (StepaRoute route : r.getStepaRoutes()) {
                qty = qty + route.getQty();
            }

            System.out.println("The score is " + qty);
            System.out.println("The score is " + score.calculateScore());
        }
}
