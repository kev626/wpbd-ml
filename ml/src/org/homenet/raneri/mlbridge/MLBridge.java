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
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.SortedMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class MLBridge {

    private static Queue<BridgeModel> bridgeQueue;
    public static synchronized BridgeModel getNextBridge() {
        return bridgeQueue.remove();
    }

    public static void main(String[] args) throws InterruptedException {

        bridgeQueue = new ArrayBlockingQueue(1024);

        BridgeModel bridge = new BridgeModel();
        try {
            bridge.read(new File("bridge.bdc"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Random random = new Random();

        bridgeQueue.offer(bridge);
        for (int i = 0; i < 1000; i++) {
            try {
                BridgeModel mod = new BridgeModel();
                mod.parseBytes(bridge.toBytes());

                for (int j = 0; j < 2; j++) {
                    int jointID = random.nextInt(mod.getJoints().size());
                    int xIncrease = random.nextInt(3) - 1;
                    int yIncrease = random.nextInt(3) - 1;
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
                        bridgeQueue.offer(mod);
                    } else {
                        j--;
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 16; i++) {
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
    }

    public static BridgeModel optimize(BridgeModel _bridge) throws InterruptedException {

        BridgeModel bridge = new BridgeModel();
        try {
            bridge.parseBytes(_bridge.toBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int changingMembersPerIteration = 1;

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
            boolean cancelJointMove = true;
                /*
                if (random.nextInt(100) < 100) { // Decrease chance a joint is moved
                    cancelJointMove = true;
                }

                for (Joint joint : bridge.getJoints()) {
                    if (joint.getPointWorld().equals(newPoint)) {
                        cancelJointMove = true;
                    }
                }
                */
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
                lastSuccessful = iterations;
                previousCost = result.getCost();
            }

            if (lastSuccessful + 1000 < iterations) {
                // Probably no more improvements to be made.
                break;
            }

            Thread.sleep(10);
        }

        lastSuccessful = iterations;

        while (true) {
            iterations++;

            int[] changedMembers = new int[changingMembersPerIteration];

            if (iterations == 999)
                changingMembersPerIteration = 1;

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
                lastSuccessful = iterations;
                previousCost = result.getCost();
            }

            if (lastSuccessful + 1000 < iterations) {
                // Probably no more improvements to be made.
                break;
            }

            Thread.sleep(10);
        }

        return bridge;
    }
}
