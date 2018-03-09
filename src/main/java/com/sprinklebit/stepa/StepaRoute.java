package com.sprinklebit.stepa;

public class StepaRoute {
    private final int qty;
    private int[] rideIndexes;

    public StepaRoute(int qty, int[] rideIndexes) {
        this.qty = qty;
        this.rideIndexes = rideIndexes;
    }

    public int getQty() {
        return qty;
    }

    public int[] getRideIndexes() {
        return rideIndexes;
    }
}
