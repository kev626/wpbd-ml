package org.homenet.raneri.mlbridge;

import bridgedesigner.Analysis;
import bridgedesigner.BridgeModel;
import bridgedesigner.Inventory;
import bridgedesigner.Material;
import bridgedesigner.Shape;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MLBridge {

    public static void main(String[] args) throws InterruptedException {

        int changingMembersPerIteration = 1;

        BridgeModel bridge = new BridgeModel();

        try {
            bridge.read(new File("bridge.bdc"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Analysis.BridgeResult result;

        Random random = new Random();

        int iterations = 0;
        int lastSuccessful = 0;

        while (true) {

            iterations++;

            int[] changedMembers = new int[changingMembersPerIteration];

            for (int i = 0; i < changingMembersPerIteration; i++) {
                int changingMember = random.nextInt(bridge.getMembers().size());

                Shape oldShape = bridge.getMembers().get(changingMember).getShape();
                boolean canDecreaseShapeSize = (bridge.getInventory().getAllowedShapeChanges(oldShape) & Inventory.SHAPE_DECREASE_SIZE) == Inventory.SHAPE_DECREASE_SIZE;
                if (!canDecreaseShapeSize) {
                    i--;
                } else {
                    changedMembers[i] = changingMember;
                    bridge.getMembers().get(changingMember).setShape(bridge.getInventory().getShape(oldShape, -1));
                }
            }

            result = Analysis.analyze(bridge);



            if (!result.getPassed()) {
                // Revert the bridge to what it was before
                for (int i = 0; i < changingMembersPerIteration; i++) {
                    bridge.getMembers().get(changedMembers[i]).setShape(bridge.getInventory().getShape(bridge.getMembers().get(changedMembers[i]).getShape(), 1));
                }
            } else {
                System.out.println(result.getCost());
                lastSuccessful = iterations;
                try {
                    bridge.write(new File("bridgeout/bridge" + iterations + ".bdc"));
                } catch (IOException e) {
                    System.out.println("Failed to write bridge!");
                }
            }

            if (lastSuccessful + 1000 < iterations) {
                // Probably no more improvements to be made.
                System.out.println("No more improvements can be made.");
                break;
            }

            Thread.sleep(10);
        }
    }

}