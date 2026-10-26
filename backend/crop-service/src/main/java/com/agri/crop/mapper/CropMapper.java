package com.agri.crop.mapper;

import com.agri.crop.dto.response.CropResponse;
import com.agri.crop.entity.Crop;
import com.agri.crop.entity.CropClimateRequirement;

import org.springframework.stereotype.Component;

@Component
public class CropMapper {

    public CropResponse toResponse(
            Crop crop,
            CropClimateRequirement climate) {

        return new CropResponse(

                crop.getId(),

                crop.getCropCode(),

                crop.getName(),

                crop.getScientificName(),

                crop.getCropType(),

                crop.getSeason(),

                crop.getDurationDays(),

                crop.getStatus(),

                climate != null
                        ? climate.getMinTemperature()
                        : null,

                climate != null
                        ? climate.getMaxTemperature()
                        : null,

                climate != null
                        ? climate.getMinHumidity()
                        : null,

                climate != null
                        ? climate.getMaxHumidity()
                        : null,

                climate != null
                        ? climate.getMinRainfall()
                        : null,

                climate != null
                        ? climate.getMaxRainfall()
                        : null,

                climate != null
                        ? climate.getMinWindSpeed()
                        : null,

                climate != null
                        ? climate.getMaxWindSpeed()
                        : null,

                climate != null
                        ? climate.getMinPh()
                        : null,

                climate != null
                        ? climate.getMaxPh()
                        : null,

                climate != null
                        ? climate.getWaterRequirement()
                        : null,

                crop.getDescription(),

                crop.getCreatedAt(),

                crop.getUpdatedAt()
        );
    }
}