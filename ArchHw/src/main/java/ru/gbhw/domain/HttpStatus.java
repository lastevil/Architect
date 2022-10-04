package ru.gbhw.domain;

public enum HttpStatus {
    NOT_FOUND (404),
    BAD_REQUEST(400),
    UNAUTHORISED(401),
    OK (200);

    private int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
