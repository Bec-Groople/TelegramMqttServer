package com.bec.mqttserver.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.errorTypeName = errorTypeName;
        this.message = message;
    }
}