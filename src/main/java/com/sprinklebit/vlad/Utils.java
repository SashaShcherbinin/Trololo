package com.sprinklebit.vlad;

import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.List;

public class Utils implements DistanceUtils {

    /**
     * @return closest Ride. Null if we can't get new Ride in time
     */
    @Override
    public Ride getClosestRide(Point currentPoint, List<Ride> rides, int currentStep, int maxSteps) {
        return null;
    }

    @Override
    public int getRideDistance(Point startPoint, Point endPoint) {
        return 0;
    }
}
