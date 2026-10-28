package com.agri.crop.dto.request;

import com.agri.crop.enums.CropSeason;
import com.agri.crop.enums.CropType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateCropRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 150)
    private String scientificName;

    @NotNull
    private CropType cropType;

    @NotNull
    private CropSeason season;

    @NotNull
    @Positive
    private Integer durationDays;

    @Size(max = 1000)
    private String description;

    public UpdateCropRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public CropSeason getSeason() {
        return season;
    }

    public void setSeason(CropSeason season) {
        this.season = season;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}