package com.sprinklebit.input;

import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.input.pojo.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 3/4/18
 * Time: 1:55 PM
 */

class RideDistribution {

    private List<Vehicle> vehiclePool = new ArrayList<>();
    private List<Vehicle> activeVehicles = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();
    private Parameters parameters;

    public RideDistribution(List<Ride> rides, Parameters parameters) {
        this.rides = rides;
        this.parameters = parameters;
        for (int i = 0; i < parameters.getVehicles(); i++) {
            vehiclePool.add(new Vehicle(new Point(0, 0), i + 1));
        }
    }

    public void start() {
        for (int i = 0; i < parameters.getSteps(); i++) {

        }
    }

}
