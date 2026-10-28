package com.agri.crop.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crop_climate_requirements")
public class CropClimateRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "crop_id",
            nullable = false,
            unique = true
    )
    private Crop crop;

    @Column(name = "min_temperature")
    private BigDecimal minTemperature;

    @Column(name = "max_temperature")
    private BigDecimal maxTemperature;

    @Column(name = "min_humidity")
    private BigDecimal minHumidity;

    @Column(name = "max_humidity")
    private BigDecimal maxHumidity;

    @Column(name = "min_rainfall")
    private BigDecimal minRainfall;

    @Column(name = "max_rainfall")
    private BigDecimal maxRainfall;

    @Column(name = "min_wind_speed")
    private BigDecimal minWindSpeed;

    @Column(name = "max_wind_speed")
    private BigDecimal maxWindSpeed;

    @Column(name = "min_ph")
    private BigDecimal minPh;

    @Column(name = "max_ph")
    private BigDecimal maxPh;

    @Column(name = "water_requirement")
    private BigDecimal waterRequirement;

    public CropClimateRequirement() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature) {
        this.minTemperature = minTemperature;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public BigDecimal getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(BigDecimal minHumidity) {
        this.minHumidity = minHumidity;
    }

    public BigDecimal getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(BigDecimal maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public BigDecimal getMinRainfall() {
        return minRainfall;
    }

    public void setMinRainfall(BigDecimal minRainfall) {
        this.minRainfall = minRainfall;
    }

    public BigDecimal getMaxRainfall() {
        return maxRainfall;
    }

    public void setMaxRainfall(BigDecimal maxRainfall) {
        this.maxRainfall = maxRainfall;
    }

    public BigDecimal getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(BigDecimal minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public BigDecimal getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(BigDecimal maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public BigDecimal getMinPh() {
        return minPh;
    }

    public void setMinPh(BigDecimal minPh) {
        this.minPh = minPh;
    }

    public BigDecimal getMaxPh() {
        return maxPh;
    }

    public void setMaxPh(BigDecimal maxPh) {
        this.maxPh = maxPh;
    }

    public BigDecimal getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(BigDecimal waterRequirement) {
        this.waterRequirement = waterRequirement;
    }
}