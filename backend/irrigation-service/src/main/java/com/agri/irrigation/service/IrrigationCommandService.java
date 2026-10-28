package com.agri.irrigation.service;

import com.agri.irrigation.dto.request.IrrigationCommandRequest;
import com.agri.irrigation.dto.response.IrrigationCommandResponse;

public interface IrrigationCommandService {

    IrrigationCommandResponse execute(
            IrrigationCommandRequest request
    );
}