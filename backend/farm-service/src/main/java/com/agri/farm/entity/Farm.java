package com.agri.farm.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "farms",
        indexes = {
                @Index(
                        name = "idx_farms_farmer_id",
                        columnList = "farmer_id"
                )
        }
)
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "farmer_id",
            nullable = false
    )
    private Long farmerId;

    @Column(
            name = "farm_name",
            nullable = false,
            length = 150
    )
    private String farmName;

    @Column(
            nullable = false
    )
    private Double latitude;

    @Column(
            nullable = false
    )
    private Double longitude;

    @Column(
            name = "total_acres",
            nullable = false
    )
    private Double totalAcres;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now =
                LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt =
                LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getTotalAcres() {
        return totalAcres;
    }

    public void setTotalAcres(Double totalAcres) {
        this.totalAcres = totalAcres;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}