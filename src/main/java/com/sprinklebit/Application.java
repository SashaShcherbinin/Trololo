package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.stepa.Core;
import com.sprinklebit.stepa.ResultWriter;
import com.sprinklebit.stepa.StepaVehicle;
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
    private static final String EXAMPLE_E_PATH = "e_high_bonus.in";

    private static List<Pair<Integer, Ride>> startToPointASteps = new ArrayList<>();
    static Map<Integer, List<Ride>> resultMap = new HashMap<>();

    private static Parameters parameters;

    public static void main(String[] args) {
        InputData input = new InputData(EXAMPLE_E_PATH, new Utils());
        startStepa(input);
        startSasha(input);
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
        private static void startStepa(InputData input){

            System.out.println("Hello Stepan!!!");

            List<Ride> rides = input.getRides();

            List<StepaVehicle> vehicles = new ArrayList<>();

            for (int i=0; i<input.getParameters().getVehicles(); i++)
            {
            vehicles.add(new StepaVehicle(new Point(0,0), i, input.getParameters().getSteps()));
            }

            Core core = new Core(vehicles, rides, new ResultWriter());
            core.calculateRides();
            core.printData();

            int score = core.calculateScore(input.getParameters().getSteps(), input.getParameters().getBonuses());

            System.out.println("The score is " + score);
        }
}
