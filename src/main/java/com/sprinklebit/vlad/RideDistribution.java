package com.sprinklebit.vlad;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.input.pojo.Vehicle;
import com.sprinklebit.vlad.route.Route;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RideDistribution {

    private InputData mData;
    private DistanceUtils mUtils;
    private HashSet<Vehicle> mVehicles;
    private List<Ride> mRides;
    private Parameters mParameters;
    private HashSet<Route> mRoutes = new HashSet<>();

    public RideDistribution(InputData inputData, DistanceUtils utils) {
        mData = inputData;
        mRides = inputData.getRides();
        mParameters = inputData.getParameters();
        mUtils = utils;
    }

    public void start() {
        initVehicles();
        initRoutes();
    }

    private void initRoutes() {
        Route route = new Route();

//        while ()

        mUtils.getClosestRide(route.getLastPoint(), mRides, mParameters.getSteps());

    }

    private void initVehicles() {
        for (int i = 1; i < mParameters.getVehicles(); i++) {
            mVehicles.add(new Vehicle(new Point(0,0), i));
        }
    }
}
