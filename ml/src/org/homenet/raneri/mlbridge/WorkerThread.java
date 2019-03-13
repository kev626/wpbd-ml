package org.homenet.raneri.mlbridge;

import bridgedesigner.BridgeModel;

import java.io.File;

public class WorkerThread implements Runnable {

    public void run() {
        try {
            BridgeModel bridge;
            while ((bridge = MLBridge.getNextBridge()) != null) {
                BridgeModel newBridge = MLBridge.optimize(bridge);
                newBridge.setDesignedBy("Kevin Raneri");
                newBridge.write(new File("bridgeout/" + Math.round(newBridge.getTotalCost()) + ".bdc"));
                System.out.println("Final cost: " + newBridge.getTotalCost());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
