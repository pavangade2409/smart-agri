package com.agri.irrigation.client;

import org.springframework.stereotype.Component;

@Component
public class DeviceClientImpl implements DeviceClient {

    @Override
    public void startPump(
            String pumpId,
            String valveId) {

        System.out.println(
                "Starting pump: "
                        + pumpId
                        + ", valve: "
                        + valveId
        );
    }

    @Override
    public void stopPump(
            String pumpId,
            String valveId) {

        System.out.println(
                "Stopping pump: "
                        + pumpId
                        + ", valve: "
                        + valveId
        );
    }
}