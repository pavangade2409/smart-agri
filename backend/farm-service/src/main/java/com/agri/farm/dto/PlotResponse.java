package com.agri.farm.dto;

import java.time.LocalDateTime;

public record PlotResponse(

        Long plotId,

        Long farmId,

        String plotName,

        Double acres,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}