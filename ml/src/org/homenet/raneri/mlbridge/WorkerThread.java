package org.homenet.raneri.mlbridge;

import bridgedesigner.Affine;
import bridgedesigner.BridgeModel;
import bridgedesigner.Joint;

import java.io.File;
import java.util.Random;

public class WorkerThread implements Runnable {

    public void run() {
        try {
            Random random = new Random();
            while (true) {

                BridgeModel bridge = MLBridge.getNextBridge();
                System.out.println("Picking up new bridge");

                for (int i = 0; i < 100; i++) {
                    try {
                        BridgeModel mod = new BridgeModel();
                        mod.parseBytes(bridge.toBytes());

                        for (int j = 0; j < 2; j++) {
                            int jointID = random.nextInt(mod.getJoints().size());
                            double xIncrease = (double) (random.nextInt(3) - 1) / 4;
                            double yIncrease = (double) (random.nextInt(3) - 1) / 4;
                            if (xIncrease == 0 && yIncrease == 0) {
                                j--;
                                continue;
                            }
                            Affine.Point newPoint = mod.getJoints().get(jointID).getPointWorld().plus(xIncrease, yIncrease);

                            for (Joint joint : mod.getJoints()) {
                                if (joint.getPointWorld().equals(newPoint)) {
                                    j--;
                                    continue;
                                }
                            }

                            if (!mod.getJoints().get(jointID).isFixed()) {
                                mod.getJoints().get(jointID).setPointWorld(
                                        newPoint
                                );

                                BridgeModel newBridge = MLBridge.optimize(mod);
                                newBridge.setDesignedBy("Kevin Raneri");
                                newBridge.write(new File("bridgeout/" + Math.round(newBridge.getTotalCost()) + ".bdc"));
                                System.out.println("Final cost: " + newBridge.getTotalCost());

                                MLBridge.addBridge(new SortableBridge(bridge, mod.getTotalCost()));
                            } else {
                                j--;
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

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
