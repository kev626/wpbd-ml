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
import java.util.concurrent.*;

public class MLBridge {

    private static Object threadLock = new Object();
    private static double lowestCost = Double.MAX_VALUE;

    private static PriorityBlockingQueue<SortableBridge> bridgeQueue;
    public static BridgeModel getNextBridge() {
        try {
            return bridgeQueue.poll(120, TimeUnit.MINUTES).getBridge();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized void addBridge(SortableBridge bridge) {
        bridgeQueue.add(bridge);
    }

    public static void main(String[] args) throws InterruptedException {

        bridgeQueue = new PriorityBlockingQueue();

        BridgeModel bridge = new BridgeModel();
        try {
            bridge.read(new File("bridge.bdc"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Calculating cost of initial bridge...");
        bridgeQueue.add(new SortableBridge(bridge, optimize(bridge).getTotalCost()));

        // Start workers
        ExecutorService executor = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
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
            double xIncrease = (double) (random.nextInt(3) - 1) / 4;
            double yIncrease = (double) (random.nextInt(3) - 1) / 4;
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
                    System.out.println("Can't increase size! Member " + changingMember + " Shape " + oldShape.toString());
                    return bridge;
                } else {
                    changedMembers[i] = changingMember;
                    bridge.getMembers().get(changingMember).setShape(bridge.getInventory().getShape(oldShape, 1));
                }
            }

            result = Analysis.analyze(bridge);

            if (!result.getPassed() || result.getCost() >= previousCost) {
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
        }

        return bridge;
    }
}
