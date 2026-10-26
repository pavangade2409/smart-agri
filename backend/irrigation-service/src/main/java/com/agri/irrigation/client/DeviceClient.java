package com.agri.irrigation.client;

public interface DeviceClient {

    void startPump(
            String pumpId,
            String valveId
    );

    void stopPump(
            String pumpId,
            String valveId
    );
}