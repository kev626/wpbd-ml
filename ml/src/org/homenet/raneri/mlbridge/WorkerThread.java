package org.homenet.raneri.mlbridge;

import bridgedesigner.Affine;
import bridgedesigner.Analysis;
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
                System.out.println("Cost: " + MLBridge.optimize(bridge).getTotalCost());

                for (int i = 0; i < 100; i++) {

                    try {
                        BridgeModel mod = new BridgeModel();
                        mod.parseBytes(bridge.toBytes());

                        int mods = random.nextInt(3) + 1;

                        for (int j = 0; j < mods; j++) {
                            int jointID = random.nextInt(mod.getJoints().size());
                            int modSize = (int) Math.pow(2,random.nextInt(3));
                            double xIncrease = (double) (random.nextInt(3) - 1) / modSize;
                            double yIncrease = (double) (random.nextInt(3) - 1) / modSize;
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
                            } else {
                                j--;
                                continue;
                            }
                        }

                        BridgeModel newBridge = MLBridge.optimize(mod);
                        if (Analysis.analyze(newBridge).getPassed()) {
                            newBridge.setDesignedBy("Kevin Raneri");
                            newBridge.write(new File("bridgeout/" + Math.round(newBridge.getTotalCost()) + ".bdc"));

                            MLBridge.addBridge(new SortableBridge(mod, newBridge.getTotalCost()));
                        } else {
                            System.out.println("A non-passing bridge was optimized... weird.");
                        }

                        //MLBridge.sendNotif(newBridge.getTotalCost());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                BridgeModel newBridge = MLBridge.optimize(bridge);
                newBridge.setDesignedBy("Kevin Raneri");
                newBridge.write(new File("bridgeout/" + Math.round(newBridge.getTotalCost()) + ".bdc"));
                //System.out.println("Final cost: " + newBridge.getTotalCost());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
