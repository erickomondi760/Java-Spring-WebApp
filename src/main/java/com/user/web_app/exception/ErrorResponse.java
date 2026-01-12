package com.user.web_app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data

@AllArgsConstructor
public class ErrorResponse {
    private int responseCode;
    private String message;
    private Instant timeStamp;
}
