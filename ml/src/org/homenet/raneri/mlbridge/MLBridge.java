package org.homenet.raneri.mlbridge;

import bridgedesigner.Affine;
import bridgedesigner.Analysis;
import bridgedesigner.BridgeModel;
import bridgedesigner.Inventory;
import bridgedesigner.Joint;
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

        double initialCost = bridge.getTotalCost();
        double previousCost = initialCost;

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

            // Also change a joint position
            int jointID = random.nextInt(bridge.getJoints().size());
            boolean movedJoint = false;
            int xIncrease = random.nextInt(3) - 1;
            int yIncrease = random.nextInt(3) - 1;
            Affine.Point newPoint = bridge.getJoints().get(jointID).getPointWorld().plus(xIncrease, yIncrease);
            boolean cancelJointMove = false;
            if (random.nextInt(100) < 100) { // Decrease chance a joint is moved
                cancelJointMove = true;
            }
            for (Joint joint : bridge.getJoints()) {
                if (joint.getPointWorld().equals(newPoint)) {
                    cancelJointMove = true;
                }
            }
            if (!bridge.getJoints().get(jointID).isFixed() && !cancelJointMove) {
                bridge.getJoints().get(jointID).setPointWorld(
                        newPoint
                );
                movedJoint = true;
            }


            result = Analysis.analyze(bridge);



            if (!result.getPassed() || (movedJoint && bridge.getTotalCost() > previousCost)) {
                // Revert the bridge to what it was before
                for (int i = 0; i < changingMembersPerIteration; i++) {
                    bridge.getMembers().get(changedMembers[i]).setShape(bridge.getInventory().getShape(bridge.getMembers().get(changedMembers[i]).getShape(), 1));
                }

                if (movedJoint) {
                    bridge.getJoints().get(jointID).setPointWorld(
                            bridge.getJoints().get(jointID).getPointWorld().plus(-xIncrease, -yIncrease)
                    );
                }
            } else {
                System.out.println(result.getCost());
                lastSuccessful = iterations;
                previousCost = result.getCost();
                try {
                    bridge.write(new File("bridgeout/bridge" + iterations + ".bdc"));
                } catch (IOException e) {
                    System.out.println("Failed to write bridge!");
                }
            }

            if (lastSuccessful + 1000 < iterations) {
                // Probably no more improvements to be made.
                System.out.println("Finished optimization. Reduced cost by " + (double)Math.round((1 - bridge.getTotalCost()/initialCost)*1000)/10 + "%");
                break;
            }

            Thread.sleep(10);
        }

        System.out.println("Beginning like-sized material optimization.");
        lastSuccessful = iterations;

        while (true) {
            iterations++;

            int[] changedMembers = new int[changingMembersPerIteration];

            for (int i = 0; i < changingMembersPerIteration; i++) {
                int changingMember = random.nextInt(bridge.getMembers().size());

                Shape oldShape = bridge.getMembers().get(changingMember).getShape();
                boolean canIncreaseShapeSize = (bridge.getInventory().getAllowedShapeChanges(oldShape) & Inventory.SHAPE_INCREASE_SIZE) == Inventory.SHAPE_INCREASE_SIZE;
                if (!canIncreaseShapeSize) {
                    i--;
                } else {
                    changedMembers[i] = changingMember;
                    bridge.getMembers().get(changingMember).setShape(bridge.getInventory().getShape(oldShape, 1));
                }
            }

            result = Analysis.analyze(bridge);

            if (!result.getPassed() || result.getCost() > previousCost) {
                // Revert
                for (int i = 0; i < changingMembersPerIteration; i++) {
                    bridge.getMembers().get(changedMembers[i]).setShape(bridge.getInventory().getShape(bridge.getMembers().get(changedMembers[i]).getShape(), -1));
                }
            } else {
                System.out.println(result.getCost());
                lastSuccessful = iterations;
                previousCost = result.getCost();
                try {
                    bridge.write(new File("bridgeout/bridge" + iterations + ".bdc"));
                } catch (IOException e) {
                    System.out.println("Failed to write bridge!");
                }
            }

            if (lastSuccessful + 1000 < iterations) {
                // Probably no more improvements to be made.
                System.out.println("Finished optimization. Reduced cost by " + (double) Math.round((1 - bridge.getTotalCost() / initialCost) * 1000) / 10 + "%");
                break;
            }

            Thread.sleep(10);
        }
    }

}
