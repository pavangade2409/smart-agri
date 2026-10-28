package com.agri.crop.dto.request;

import com.agri.crop.enums.CropSeason;
import com.agri.crop.enums.CropStatus;
import com.agri.crop.enums.CropType;

public class CropSearchRequest {

    private String name;

    private CropType cropType;

    private CropSeason season;

    private CropStatus status;

    public CropSearchRequest() {
    }

    public CropSearchRequest(
            String name,
            CropType cropType,
            CropSeason season,
            CropStatus status) {

        this.name = name;
        this.cropType = cropType;
        this.season = season;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CropStatus getStatus() {
        return status;
    }

    public void setStatus(CropStatus status) {
        this.status = status;
    }
}