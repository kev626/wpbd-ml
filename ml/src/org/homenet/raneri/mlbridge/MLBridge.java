package org.homenet.raneri.mlbridge;

import bridgedesigner.Analysis;
import bridgedesigner.BridgeModel;

import java.io.File;
import java.io.IOException;

public class MLBridge {

    public static void main(String[] args) {
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
    }

}
