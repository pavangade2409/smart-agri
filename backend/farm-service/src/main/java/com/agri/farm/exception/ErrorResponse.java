package com.agri.farm.exception;

import java.time.LocalDateTime;

public record ErrorResponse(

        boolean success,

        String message,

        LocalDateTime timestamp

) {
}