package com.sprinklebit.vlad;

import com.sprinklebit.input.pojo.Ride;
import jdk.internal.jline.internal.Nullable;

import java.awt.*;
import java.util.List;

public interface DistanceUtils {

    @Nullable
    Ride getClosestRide(Point currentPoint, List<Ride> rides, int maxSteps);

    int getRideDistance(Point startPoint, Point endPoint);
}
