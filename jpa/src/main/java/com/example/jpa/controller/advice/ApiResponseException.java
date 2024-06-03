package com.example.jpa.controller.advice;

import org.springframework.http.HttpStatus;

public class ApiResponseException extends RuntimeException {
    private final ApiResponse apiResponse;

    public ApiResponseException(HttpStatus status, String message) {
        super(message);
        this.apiResponse = ApiResponse.error(status, message);
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
