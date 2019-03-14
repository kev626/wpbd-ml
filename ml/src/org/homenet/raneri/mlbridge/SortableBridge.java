package org.homenet.raneri.mlbridge;

import bridgedesigner.BridgeModel;

public class SortableBridge implements Comparable {

    private BridgeModel bridge;
    private double optimalCost;

    public SortableBridge(BridgeModel bridge, double optimalCost) {
        this.bridge = bridge;
        this.optimalCost = optimalCost;
    }

    public BridgeModel getBridge() {
        return bridge;
    }

    public double getOptimalCost() {
        return optimalCost;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof SortableBridge)) return 0;
        SortableBridge other = (SortableBridge) o;
        if (this.getBridge().getTotalCost() > other.getBridge().getTotalCost())
            return 1;

        if (this.getOptimalCost() < other.getOptimalCost())
            return -1;

        return 0;
    }
}
