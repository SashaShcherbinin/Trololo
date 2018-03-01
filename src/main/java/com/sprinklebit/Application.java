package com.sprinklebit;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/1/18
 * Time: 9:25 PM
 */

class Application {

    private static final String EXAMPLE_A_PATH = "a_example.in";

    public static void main(String[] args) {

        InputData inputData = new InputData(EXAMPLE_A_PATH);
        Parameters parameters = inputData.getParameters();
        List<Ride> rides = inputData.getRides();


        System.out.println(parameters);
//        System.out.println(rides);
    }
}
