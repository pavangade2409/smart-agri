package com.agri.farm.service;

import com.agri.farm.dto.PlotRequest;
import com.agri.farm.dto.PlotResponse;

import java.util.List;

public interface PlotService {

    PlotResponse createPlot(
            PlotRequest request
    );

    PlotResponse getPlot(
            Long plotId
    );

    List<PlotResponse> getPlotsByFarm(
            Long farmId
    );

    PlotResponse updatePlot(
            Long plotId,
            PlotRequest request
    );

    void deletePlot(
            Long plotId
    );
}