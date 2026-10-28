package com.agri.crop.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.crop.enums.CropSeason;
import com.agri.crop.enums.CropStatus;
import com.agri.crop.enums.CropType;

public class CropResponse {

    private UUID id;

    private String cropCode;

    private String name;

    private String scientificName;

    private CropType cropType;

    private CropSeason season;

    private Integer durationDays;

    private CropStatus status;

    private BigDecimal minTemperature;

    private BigDecimal maxTemperature;

    private BigDecimal minHumidity;

    private BigDecimal maxHumidity;

    private BigDecimal minRainfall;

    private BigDecimal maxRainfall;

    private BigDecimal minWindSpeed;

    private BigDecimal maxWindSpeed;

    private BigDecimal minPh;

    private BigDecimal maxPh;

    private BigDecimal waterRequirement;

    private String description;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;


    public CropResponse() {
    }


    public CropResponse(
            UUID id,
            String cropCode,
            String name,
            String scientificName,
            CropType cropType,
            CropSeason season,
            Integer durationDays,
            CropStatus status,
            BigDecimal minTemperature,
            BigDecimal maxTemperature,
            BigDecimal minHumidity,
            BigDecimal maxHumidity,
            BigDecimal minRainfall,
            BigDecimal maxRainfall,
            BigDecimal minWindSpeed,
            BigDecimal maxWindSpeed,
            BigDecimal minPh,
            BigDecimal maxPh,
            BigDecimal waterRequirement,
            String description,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {

        this.id = id;
        this.cropCode = cropCode;
        this.name = name;
        this.scientificName = scientificName;
        this.cropType = cropType;
        this.season = season;
        this.durationDays = durationDays;
        this.status = status;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minRainfall = minRainfall;
        this.maxRainfall = maxRainfall;
        this.minWindSpeed = minWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.minPh = minPh;
        this.maxPh = maxPh;
        this.waterRequirement = waterRequirement;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public UUID getId() {
        return id;
    }

    public String getCropCode() {
        return cropCode;
    }

    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public CropType getCropType() {
        return cropType;
    }

    public CropSeason getSeason() {
        return season;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public CropStatus getStatus() {
        return status;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public BigDecimal getMinHumidity() {
        return minHumidity;
    }

    public BigDecimal getMaxHumidity() {
        return maxHumidity;
    }

    public BigDecimal getMinRainfall() {
        return minRainfall;
    }

    public BigDecimal getMaxRainfall() {
        return maxRainfall;
    }

    public BigDecimal getMinWindSpeed() {
        return minWindSpeed;
    }

    public BigDecimal getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public BigDecimal getMinPh() {
        return minPh;
    }

    public BigDecimal getMaxPh() {
        return maxPh;
    }

    public BigDecimal getWaterRequirement() {
        return waterRequirement;
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}