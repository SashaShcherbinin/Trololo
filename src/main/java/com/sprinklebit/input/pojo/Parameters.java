package com.sprinklebit.input.pojo;



public class Parameters {

    private int rows;
    private int columns;
    private int vehicles;
    private int bonuses;
    private int rides;
    private int steps;

    public Parameters(int[] parameters) {
        rows = parameters[0];
        columns = parameters[1];
        vehicles = parameters[2];
        rides = parameters[3];
        bonuses = parameters[4];
        steps = parameters[5];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getVehicles() {
        return vehicles;
    }

    public int getBonuses() {
        return bonuses;
    }

    public int getRides() {
        return rides;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        String outString = "==========================\n";
        outString += "Rows Count = " + rows + "\n";
        outString += "Columns Count = " + columns + "\n";
        outString += "Vehicles Count = " + vehicles + "\n";
        outString += "Rides Count = " + rides + "\n";
        outString += "Bonuses Count = " + bonuses + "\n";
        outString += "Steps Count = " + steps + "\n";
        outString += "==========================";
        return outString;
    }
}
