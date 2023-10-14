package com.json.parser.db.antipn.exception;

public class JsonException extends RuntimeException {
    private String message;

    public JsonException(String error) {
        super(error);
        this.message = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

