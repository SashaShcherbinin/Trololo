package com.sprinklebit.stepa;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Ride;

import java.awt.*;

public class Score {
    private InputData input;
    private ResultReader output;

    public Score(InputData input, ResultReader output) {
        this.input = input;
        this.output = output;
    }

    public int calculateScore() {
        int score = 0;
        for (StepaRoute route : output.getStepaRoutes()) {
            StepaVehicle veh = new StepaVehicle(new Point(0,0), 0, input.getParameters().getSteps(), input.getParameters().getBonuses());
            for (int rideIndex : route.getRideIndexes()) {
                int bonus = 0;

                Ride ride = input.getRides().get(rideIndex);

                int distanceToStart = veh.calculateDistance(veh.getPoint(), ride.getStartPoint());
                if (veh.getCurrentStep() + distanceToStart < input.getParameters().getSteps()) {
                    if (veh.getCurrentStep() + distanceToStart <= ride.getEarliestStart()) {
                        veh.setCurrentStep(ride.getEarliestStart());
                        veh.setPoint(ride.getStartPoint());
                        bonus = input.getParameters().getBonuses();
                    } else {
                        veh.setCurrentStep(veh.getCurrentStep() + distanceToStart);
                        veh.setPoint(ride.getStartPoint());
                    }
                } else {
                    veh.setCurrentStep(input.getParameters().getSteps() - 1);
                }

                int distanceToEnd = veh.calculateDistance(veh.getPoint(), ride.getEndPoint());
                if (veh.getCurrentStep() + distanceToEnd <= input.getParameters().getSteps()) {
                    if (veh.getCurrentStep() + distanceToEnd <= ride.getLatestEnd()) {
                        veh.setCurrentStep(veh.getCurrentStep() + distanceToEnd);
                        veh.setPoint(ride.getEndPoint());
                        score = score + distanceToEnd + bonus;
                    } else {
                        veh.setCurrentStep(veh.getCurrentStep() + distanceToEnd);
                        veh.setPoint(ride.getEndPoint());
                    }
                } else {
                    veh.setCurrentStep(input.getParameters().getSteps() - 1);
                }

            }
        }

        return score;
    }
}
