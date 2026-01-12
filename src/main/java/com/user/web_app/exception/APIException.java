package com.user.web_app.exception;

import lombok.*;

@Getter
@Setter
public class APIException extends RuntimeException{
    private String message;
    private int responseCode;

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, int responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

}
