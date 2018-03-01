package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import javafx.util.Pair;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/1/18
 * Time: 9:25 PM
 */

class Application {

    private static final String EXAMPLE_A_PATH = "a_example.in";

    List<Pair<Integer, Ride>> startToPointASteps = null;

    public static void main(String[] args) {

        InputData inputData = new InputData(EXAMPLE_A_PATH);
        Parameters parameters = inputData.getParameters();
        List<Ride> rides = inputData.getRides();

        for (int i = 0; i < parameters.getVehicles(); i++) {
            
        }

        System.out.println(parameters);
//        System.out.println(rides);
    }
}
