package com.agri.farm.service.impl;

import com.agri.farm.dto.PlotRequest;
import com.agri.farm.dto.PlotResponse;
import com.agri.farm.entity.Plot;
import com.agri.farm.exception.ResourceNotFoundException;
import com.agri.farm.repository.FarmRepository;
import com.agri.farm.repository.PlotRepository;
import com.agri.farm.service.PlotService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlotServiceImpl
        implements PlotService {

    private final PlotRepository plotRepository;

    private final FarmRepository farmRepository;

    public PlotServiceImpl(
            PlotRepository plotRepository,
            FarmRepository farmRepository) {

        this.plotRepository =
                plotRepository;

        this.farmRepository =
                farmRepository;
    }

    @Override
    public PlotResponse createPlot(
            PlotRequest request) {

        validateFarm(
                request.farmId()
        );

        Plot plot = new Plot();

        plot.setFarmId(
                request.farmId()
        );

        plot.setPlotName(
                request.plotName().trim()
        );

        plot.setAcres(
                request.acres()
        );

        Plot saved =
                plotRepository.save(plot);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PlotResponse getPlot(
            Long plotId) {

        return toResponse(
                findPlot(plotId)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlotResponse>
    getPlotsByFarm(
            Long farmId) {

        validateFarm(farmId);

        return plotRepository
                .findByFarmId(farmId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PlotResponse updatePlot(
            Long plotId,
            PlotRequest request) {

        Plot plot =
                findPlot(plotId);

        validateFarm(
                request.farmId()
        );

        plot.setFarmId(
                request.farmId()
        );

        plot.setPlotName(
                request.plotName().trim()
        );

        plot.setAcres(
                request.acres()
        );

        Plot updated =
                plotRepository.save(plot);

        return toResponse(updated);
    }

    @Override
    public void deletePlot(
            Long plotId) {

        Plot plot =
                findPlot(plotId);

        plotRepository.delete(plot);
    }

    private Plot findPlot(
            Long plotId) {

        return plotRepository
                .findById(plotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plot not found: "
                                        + plotId
                        )
                );
    }

    private void validateFarm(
            Long farmId) {

        farmRepository
                .findById(farmId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm not found: "
                                        + farmId
                        )
                );
    }

    private PlotResponse toResponse(
            Plot plot) {

        return new PlotResponse(
                plot.getId(),
                plot.getFarmId(),
                plot.getPlotName(),
                plot.getAcres(),
                plot.getCreatedAt(),
                plot.getUpdatedAt()
        );
    }
}