package com.agri.irrigation.decision;

import java.util.UUID;

public interface IrrigationDecisionEngine {

    IrrigationDecision evaluate(
            UUID zoneId
    );
}