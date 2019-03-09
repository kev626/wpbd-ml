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

        Analysis.BridgeResult result = Analysis.analyze(bridge);

        System.out.println(result.getCost());
        System.out.println(result.getPassed());

        Random random = new Random();

        int iterations = 0;

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

            System.out.println(result.getCost());
            System.out.println(result.getPassed());

            if (!result.getPassed()) {
                // Revert the bridge to what it was before
                System.out.println("Reverting to previous state.");
                for (int i = 0; i < changingMembersPerIteration; i++) {
                    bridge.getMembers().get(changedMembers[i]).setShape(bridge.getInventory().getShape(bridge.getMembers().get(changedMembers[i]).getShape(), 1));
                }
            } else {
                try {
                    bridge.write(new File("bridgeout/bridge" + iterations + ".bdc"));
                } catch (IOException e) {
                    System.out.println("Failed to write bridge!");
                }
            }

            Thread.sleep(10);
        }
    }

}
