package com.sprinklebit.vlad.route;

import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.input.pojo.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Route {

    private static final int START_X = 0;
    private static final int START_Y = 0;

    private Vehicle mVehicle;

    private List<Ride> mRides = new ArrayList<>();

    public void addRide(Ride ride) {
        mRides.add(ride);
    }

    public Point getLastPoint() {
        Point lastPoint;

        Ride lastRide = getLastRide();

        if (lastRide != null) {
            lastPoint = lastRide.getEndPoint();
        } else {
            lastPoint = new Point(START_X, START_Y);
        }

        return lastPoint;
    }

<<<<<<< HEAD


=======
>>>>>>> aacd2376ec19a392928ce065459520848dfa7090
    private Ride getLastRide() {
        Ride lastRide = null;

        if (!mRides.isEmpty()) {
            lastRide = mRides.get(mRides.size() -1);
        }

        return lastRide;
    }
}
