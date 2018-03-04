package com.sprinklebit.vlad;

import com.sprinklebit.input.pojo.Ride;

import java.awt.*;
import java.util.List;

public interface DistanceUtils {

    Ride getClosestRide(Point currentPoint, List<Ride> rides, int currentStep, int maxSteps);

    int getRideDistance(Point startPoint, Point endPoint);
}
