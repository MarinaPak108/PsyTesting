package com.psytest.handler;

import java.util.List;

public class ErrorResponse {
    public ErrorResponse(String message, List<Object> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;
    private List<Object> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getDetails() {
        return details;
    }

    public void setDetails(List<Object> details) {
        this.details = details;
    }
}
