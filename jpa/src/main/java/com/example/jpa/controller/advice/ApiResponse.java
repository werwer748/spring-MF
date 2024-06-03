package com.example.jpa.controller.advice;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        int status, String message, Object data
) {
    public static ApiResponse success(Object data) {
        return new ApiResponse(
                HttpStatus.OK.value(), "ok", data
        );
    }

    public static ApiResponse error(HttpStatus status, String message) {
        return new ApiResponse(
                status.value(), message, null
        );
    }
}